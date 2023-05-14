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
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import my.edu.tarc.ass2.Appliance.AppManageAddedFragment
import my.edu.tarc.ass2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

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

        /*navController.addOnDestinationChangedListener{
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
                ||destination.id==R.id.editNoResidentFragment)
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
            else if(destination.id==R.id.electricityAccFragment ){
                supportActionBar!!.title =getString(R.string.ElectricityAccountProfileTitle)
            }
            else{
                supportActionBar!!.title =getString(R.string.app_name)
            }
        }*/



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