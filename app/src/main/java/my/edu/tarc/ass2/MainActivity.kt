package my.edu.tarc.ass2

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation

import kotlinx.coroutines.launch

import androidx.navigation.fragment.findNavController

import my.edu.tarc.ass2.Appliance.AppManageAddedFragment
import my.edu.tarc.ass2.Dashboard.DashboardViewModel
import my.edu.tarc.ass2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

       //go dashboard from main activity
        //navController.navigate(R.id.dashboard)

        lifecycleScope.launch {
            //set bill details for 1 time for data retrieval afterwards
            val newContact11 = Bill(
                "001",
                1,
                2023,
                110.00,
                "2023-01-01",
                "2023-01-31",
                "Paid",
                100.00,
                110.00,
                0.00,
                0.00,
                123412341111,
                "A001"
            )
            val newContact12 = Bill(
                "002",
                2,
                2023,
                110.00,
                "2023-02-01",
                "2023-02-28",
                "Paid",
                200.00,
                110.00,
                0.00,
                0.00,
                123412341111,
                "A002"
            )
            val newContact13 = Bill(
                "003",
                3,
                2023,
                120.00,
                "2023-03-01",
                "2023-03-31",
                "Paid",
                300.00,
                120.00,
                0.00,
                0.00,
                123412341111,
                "A003"
            )
            val newContact14 = Bill(
                "004",
                4,
                2023,
                120.00,
                "2023-04-01",
                "2023-04-30",
                "Unpaid",
                400.00,
                120.00,
                0.00,
                0.00,
                123412341111,
                "A004"
            )
            val newContact15 = Bill(
                "005",
                5,
                2023,
                111.00,
                "2023-05-01",
                "2023-05-31",
                "Unpaid",
                500.00,
                230.00,
                0.00,
                0.00,
                123412341111,
                "A005"
            )
            val newContact2 = Bill(
                "001",
                3,
                2023,
                100.00,
                "2023-03-01",
                "2023-03-31",
                "Unpaid",
                400.00,
                100.00,
                0.00,
                0.00,
                123412341112,
                "A001"
            )
            val newContact2a = Bill(
                "002",
                4,
                2023,
                220.00,
                "2023-04-01",
                "2023-04-30",
                "Unpaid",
                400.00,
                100.00,
                200.00,
                20.00,
                123412341112,
                "A002"
            )

            dashboardViewModel.setBillingDetails(newContact11)
            dashboardViewModel.setBillingDetails(newContact12)
            dashboardViewModel.setBillingDetails(newContact13)
            dashboardViewModel.setBillingDetails(newContact14)
            dashboardViewModel.setBillingDetails(newContact15)
            dashboardViewModel.setBillingDetails(newContact2)
            dashboardViewModel.setBillingDetails(newContact2a)
        }


        navController.addOnDestinationChangedListener{
                _,destination,_->
            if(destination.id==R.id.userRegistrationFragment
                ||destination.id==R.id.userInfoRegisterFragment
                ||destination.id==R.id.registerAddElectricityAccFragment
                ||destination.id==R.id.accConfirmationFragment
                ||destination.id==R.id.electricityAccInfoFragment
                ||destination.id==R.id.loginFragment
                ||destination.id==R.id.editNameFragment
                ||destination.id==R.id.editAccNameFragment
                ||destination.id==R.id.editMobileFragment
                ||destination.id==R.id.editMonthlyIncomeFragment
                ||destination.id==R.id.editNoResidentFragment
                ||destination.id==R.id.deleteAccFragment
                ||destination.id==R.id.editPasswordFragment)
            {
                supportActionBar!!.hide()
            }else if(destination.id==R.id.profileFragment ){
                supportActionBar!!.show()
                supportActionBar!!.title =getString(R.string.ProfileTitle)
            }
            else if(destination.id==R.id.userFragment ){
                supportActionBar!!.show()
                supportActionBar!!.title =getString(R.string.userProfileTitle)
            }
            else if(destination.id==R.id.appliancesFragment
                ||destination.id==R.id.appManageAddedFragment
                ||destination.id==R.id.appManageAddNewFragment
                ||destination.id==R.id.appManageDeleteFragment
                ||destination.id==R.id.appManageDetailsFragment){
                supportActionBar!!.show()
                supportActionBar!!.title =getString(R.string.appliances_main_fragment_title)
            }
            else if (destination.id==R.id.costCalculatorFragment
                ||destination.id==R.id.calAdviceDialogFragment
                ||destination.id==R.id.calHelpDialogFragment){
                supportActionBar!!.show()
                supportActionBar!!.title =getString(R.string.appliances_cal_title)
            }
            else if(destination.id==R.id.electricityAccFragment ){
                supportActionBar!!.title =getString(R.string.ElectricityAccountProfileTitle)
            }
            else if(destination.id==R.id.appSettingsFragment ){
                supportActionBar!!.title =getString(R.string.appSettings)
            }
            else if(destination.id==R.id.changLanguageFragment ){
                supportActionBar!!.title =getString(R.string.changeLang)
            }
            else if(destination.id==R.id.developerInfoFragment ){
                supportActionBar!!.title =getString(R.string.devInfo)
            }
            else if(destination.id==R.id.ratingFragment ){
                supportActionBar!!.title =getString(R.string.rateUs)
            }
            else if(destination.id==R.id.billingFragment ){
                supportActionBar!!.title =getString(R.string.billing)
            }
            else{
                supportActionBar!!.title =getString(R.string.app_name)
            }
        }



    }

    //To display added appliances
    private fun replaceFragment(homeFragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.recyclerViewApp, homeFragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val currentDestination = navController.currentDestination

        // Check if the current destination is the login fragment
        if (currentDestination?.id == R.id.loginFragment) {
            navController.navigate(R.id.action_loginFragment_to_userRegistrationFragment)
        }
        else if(currentDestination?.id == R.id.loginFragment){

        }
        else {
            // If the current destination is not the login fragment,
            // perform the default back button behavior

        }
    }
}