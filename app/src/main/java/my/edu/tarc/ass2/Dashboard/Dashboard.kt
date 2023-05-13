package my.edu.tarc.ass2.Dashboard

import ImageSliderAdapter
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.Bill
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.ActivityDashboardBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import java.sql.Connection
import java.util.Timer
import java.util.TimerTask
//import com.mysql.cj.jdbc.Driver


class Dashboard : AppCompatActivity() {
    private val dashboardViewModel: DashboardViewModel by viewModels()
    //private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var timer: Timer
    lateinit var imageList: List<Int>
    private lateinit var connection: Connection
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize ViewModel
        //dashboardViewModel= ViewModelProvider(this).get(DashboardViewModel::class.java)

        //onclicks
        binding.hotlinePic.setOnClickListener(){
            val fragment = HotlineFragment()
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, fragment)//add(R.id.overlapped_container, overlappedFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.profilePic.setOnClickListener(){
            val navController = Navigation.findNavController(this, R.id.dashboard)
            navController.navigate(R.id.profileFragment)

        }

        binding.buttonAppliances.setOnClickListener(){
            val navController = Navigation.findNavController(this, R.id.dashboard)
            //navController.navigate(R.id.)

        }

        binding.buttonViewBill.setOnClickListener(){
            val navController = Navigation.findNavController(this, R.id.dashboard)
            //navController.navigate(R.id.)

        }


        viewPager = findViewById(R.id.viewPager)

        //announcement slider
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.about
        imageList = imageList + R.drawable.developer
        imageList = imageList + R.drawable.about
        val adapter = ImageSliderAdapter(imageList )
        viewPager.adapter = adapter
        timer = Timer()
        timer.scheduleAtFixedRate(AutoScrollTask(adapter.itemCount), 4000, 4000)

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

        //show system date time
        val current = LocalDateTime.now()
        val ldt = LocalDateTime.parse(current.toString() )
        val formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu", Locale.ENGLISH)
        val output = ldt.format(formatter)
        binding.displayDateTime.text = output.toString()
        val monthDisplay =  current.monthValue
        var yearDisplay =  current.year
        if (monthDisplay == 1){
            yearDisplay -= 1
        }


        binding.displayMonth.text = current.month.toString() + "  " + current.year


        //binding with database
        lifecycleScope.launch {
//            //jdbc
//            val url = "jdbc:mysql://files.000webhost.com:21/id20710696_gol_database?max_allowed_packet=16777216"
//            val username = "guardianoflighting"
//            val password = "Ipad5210*"
//            val databaseManager = JDBCDatabase(url, username, password)
//            databaseManager.connect()
//            if (::connection.isInitialized) {
//                val getOverallUsage= databaseManager.performSelectQueryBill("OverallUsage", 1234123411 ,4 , 2023)
//                binding.displayOverallUsage.text = getOverallUsage.toString()
//
//            } else {
//                println("WTF")
//            }
//            databaseManager.disconnect()
            //databaseManager.performInsertQuery()
            //databaseManager.performUpdateQuery()
            //databaseManager.performDeleteQuery()


            //set bill details for 1 time for data retrieval afterwards
            val newContact11 = Bill("001", 12, 2022, 90.00, "2022-12-01","2022-12-31", "Paid", 400.00, 90.00,0.00,0.00,123412341111,"A001")
            val newContact12 = Bill("002", 1, 2023,100.00, "2023-01-01", "2023-01-31", "Paid", 400.00, 100.00,0.00,0.00,123412341111,"A002")
            val newContact13 = Bill("003", 2, 2023,110.00, "2023-02-01", "2023-02-31", "Paid", 400.00, 110.00,0.00,0.00,123412341111,"A003")
            val newContact14 = Bill("004", 3, 2023,120.00, "2023-03-01", "2023-03-31", "Paid", 400.00, 120.00,0.00,0.00,123412341111,"A004")
            val newContact15 = Bill("005", 4, 2023,120.00, "2023-04-01", "2023-04-31", "Unpaid", 400.00, 120.00,0.00,0.00,123412341111,"A005")
            val newContact16 = Bill("005", 5, 2023,111.00, "2023-04-01", "2023-04-31", "Unpaid", 400.00, 230.00,0.00,0.00,123412341111,"A006")
            val newContact2 = Bill("006", 3, 2023,100.00, "2023-03-01","2023-03-31", "Unpaid", 400.00, 100.00,0.00,0.00,123412341112,"A001")
            val newContact2a = Bill("002", 4, 2023,220.00, "2023-04-01", "2023-04-31", "Unpaid", 400.00, 100.00,200.00,20.00,123412341112,"A002")

            dashboardViewModel.setBillingDetails(newContact11)
            dashboardViewModel.setBillingDetails(newContact12)
            dashboardViewModel.setBillingDetails(newContact13)
            dashboardViewModel.setBillingDetails(newContact14)
            dashboardViewModel.setBillingDetails(newContact15)
            dashboardViewModel.setBillingDetails(newContact16)
            dashboardViewModel.setBillingDetails(newContact2)
            dashboardViewModel.setBillingDetails(newContact2a)

            //get user acc
            //val getOverallUsage = dashboardViewModel.getOverallUsage(123412341111 ,(monthDisplay-1),yearDisplay)
            //binding.displayOverallUsage.text = getOverallUsage.toString()


            val getBillStatus = dashboardViewModel.getBillStatus(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayBillStatus.text = getBillStatus

            val getPaymentDue = dashboardViewModel.getPaymentDue(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayPaymentDue.text = getPaymentDue


            val getTotalAmount = dashboardViewModel.getTotalAmount(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayTotalAmount.text = "RM" + getTotalAmount.toString()


            // val getAddress = dashboardViewModel.getAddress()
        }


    }
    inner class AutoScrollTask(private val numPages: Int) : TimerTask() {
        override fun run() {
            runOnUiThread {
                val currentItem = viewPager.currentItem
                val nextPage = if (currentItem == numPages - 1) 0 else currentItem + 1
                viewPager.setCurrentItem(nextPage, true)
            }
        }
    }
}