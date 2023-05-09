package my.edu.tarc.ass2.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import my.edu.tarc.ass2.AppDatabase
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databaseDao
import my.edu.tarc.ass2.databinding.ActivityDashboardBinding

private lateinit var binding: ActivityDashboardBinding
private lateinit var dashboardViewModel: DashboardViewModel
class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize ViewModel
        dashboardViewModel= ViewModelProvider(this).get(DashboardViewModel::class.java)

        //Connect database
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "my-database").build()
        val databaseDao = db.databaseDao()
        viewModel = ViewModelProvider(this, ViewModelFactory(databaseDao)).get(DashboardViewModel::class.java)


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

    }
}