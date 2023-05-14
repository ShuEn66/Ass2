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
import my.edu.tarc.ass2.databinding.FragmentDashboardBinding

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
                ||destination.id==R.id.editPasswordFragment
                ||destination.id == R.id.billingFragment
                ||destination.id==R.id.billInfoFragment
                || destination.id==R.id.billHistoryFragment
                ||destination.id == R.id.paymentFragment)
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
                ||destination.id==R.id.appManageDetailsFragment
                 ){
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
            else if(destination.id==R.id.billInfoFragment){
                supportActionBar!!.title =getString(R.string.billInfo)
            }
            else if(destination.id==R.id.billHistoryFragment ){
                supportActionBar!!.title =getString(R.string.billHistoryTitle)
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


}