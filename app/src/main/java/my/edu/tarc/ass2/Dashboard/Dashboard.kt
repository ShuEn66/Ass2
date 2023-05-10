package my.edu.tarc.ass2.Dashboard

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import java.text.SimpleDateFormat
import java.util.Date

import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.ActivityDashboardBinding
import my.tarc.mycontact.Bill
import java.time.format.DateTimeFormatter

private lateinit var binding: ActivityDashboardBinding
private lateinit var dashboardViewModel: DashboardViewModel
class Dashboard : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize ViewModel
        dashboardViewModel= ViewModelProvider(this).get(DashboardViewModel::class.java)

        //onclicks
        binding.hotlinePic.setOnClickListener(){
            val fragment = HotlineFragment()
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, fragment)//add(R.id.overlapped_container, overlappedFragment)
                .addToBackStack(null)
                .commit()
        }

        //date formatter
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")


        binding.profilePic.setOnClickListener(){
            val navController = Navigation.findNavController(this, R.id.dashboard)
            navController.navigate(R.id.nav_ProfileFragment)

        }

        binding.buttonAppliances.setOnClickListener(){
            val navController = Navigation.findNavController(this, R.id.dashboard)
            //navController.navigate(R.id.)

        }

        binding.buttonViewBill.setOnClickListener(){
            val navController = Navigation.findNavController(this, R.id.dashboard)
            //navController.navigate(R.id.)

        }

        //back press
        val backPressedCallback = object: OnBackPressedCallback(true)
        {
            override fun handleOnBackPressed()
            {
                val builder = AlertDialog.Builder(this@Dashboard)
                builder.setMessage(getString(R.string.exit_message))
                    .setPositiveButton(getString(R.string.exit),{_,_ -> finish() })
                    .setNegativeButton(getString(R.string.cancel),{_,_ ->  })

                builder.create().show()
            }
        }
        onBackPressedDispatcher.addCallback(backPressedCallback)

        //set bill details for 1 time for data retrieval afterwards
        val newContact11 = Bill("001", 12, 2022, 90.00, dateFormat.parse("2022-12-01"), dateFormat.parse("2022-12-31"), "Paid", 400.00, 90.00,0.00,0.00,123412341111,"A001")
        val newContact12 = Bill("002", 1, 2023,100.00, dateFormat.parse("2023-01-01"), dateFormat.parse("2023-01-31"), "Paid", 400.00, 100.00,0.00,0.00,123412341111,"A002")
        val newContact13 = Bill("003", 2, 2023,110.00, dateFormat.parse("2023-02-01"), dateFormat.parse("2023-02-31"), "Paid", 400.00, 110.00,0.00,0.00,123412341111,"A003")
        val newContact14 = Bill("004", 3, 2023,120.00, dateFormat.parse("2023-03-01"), dateFormat.parse("2023-03-31"), "Paid", 400.00, 120.00,0.00,0.00,123412341111,"A004")
        val newContact15 = Bill("005", 4, 2023,120.00, dateFormat.parse("2023-04-01"), dateFormat.parse("2023-04-31"), "Unpaid", 400.00, 120.00,0.00,0.00,123412341111,"A005")
        val newContact2 = Bill("001", 3, 2023,100.00, dateFormat.parse("2023-03-01"), dateFormat.parse("2023-03-31"), "Unpaid", 400.00, 100.00,0.00,0.00,123412341112,"A001")
        val newContact2a = Bill("002", 4, 2023,220.00, dateFormat.parse("2023-04-01"), dateFormat.parse("2023-04-31"), "Unpaid", 400.00, 100.00,200.00,20.00,123412341112,"A002")

        dashboardViewModel.setBillingDetails(newContact11)
        dashboardViewModel.setBillingDetails(newContact12)
        dashboardViewModel.setBillingDetails(newContact13)
        dashboardViewModel.setBillingDetails(newContact14)
        dashboardViewModel.setBillingDetails(newContact15)
        dashboardViewModel.setBillingDetails(newContact2)
        dashboardViewModel.setBillingDetails(newContact2a)

        val getOverallUsage= dashboardViewModel.getOverallUsage(123412341111, 12, 2022)
        binding.displayOverallUsage.text = getOverallUsage.toString()






    }
}