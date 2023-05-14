package my.edu.tarc.ass2.Appliance

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import my.edu.tarc.ass2.AppDatabase
import my.edu.tarc.ass2.Appliances
import my.edu.tarc.ass2.databaseDao

class AppliancesViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var databaseDao: databaseDao

    suspend fun deleteAppliances(appliancesName: String){
        return withContext(Dispatchers.IO) {
            val d= databaseDao.deleteAppliances(appliancesName)
            d
        }
    }

    suspend fun getAppliances(userEmail: String, appliancesName: String): LiveData<Appliances> {
        return withContext(Dispatchers.IO) {
            val d= databaseDao.getAppliances(userEmail, appliancesName)
            d
        }
    }

    suspend fun getAllAppliances(userEmail: String):LiveData<List<Appliances>>{
        return withContext(Dispatchers.IO) {
            val d= databaseDao.getAllAppliances(userEmail)
            d
        }
    }


    suspend fun getAppliancesName(userEmail: String): String{
        return withContext(Dispatchers.IO) {
            val d= databaseDao.getAppliancesName(userEmail)
            d
        }
    }

    suspend fun getAppliancesType(userEmail: String, appliancesName: String): String{
        return withContext(Dispatchers.IO) {
            val d= databaseDao.getAppliancesType(userEmail, appliancesName)
            d
        }
    }

    suspend fun getEstimatedUsage(userEmail: String, appliancesName: String): Double{
        return withContext(Dispatchers.IO) {
            val d= databaseDao.getEstimatedUsage(userEmail, appliancesName)
            d
        }
    }

    suspend fun getAppliancesPower(userEmail: String, appliancesName: String): Double{
        return withContext(Dispatchers.IO) {
            val d= databaseDao.getAppliancesPower(userEmail, appliancesName)
            d
        }
    }

    suspend fun setAppliances(Appliances: Appliances){
        return withContext(Dispatchers.IO) {
            val d= databaseDao.setAppliances(Appliances)
            d
        }
    }

    suspend fun setAppName(appliancesName: String){
        return withContext(Dispatchers.IO) {
            val d= databaseDao.setAppName(appliancesName)
            d
        }
    }

    suspend fun setAppType(appliancesType: String){
        return withContext(Dispatchers.IO) {
            val d= databaseDao.setAppType(appliancesType)
            d
        }
    }

    suspend fun setEstimatedUsage(estimatedUsage: Double){
        return withContext(Dispatchers.IO) {
            val d= databaseDao.setEstimatedUsage(estimatedUsage)
            d
        }
    }

    suspend fun setAppPower(appPower: Double){
        return withContext(Dispatchers.IO) {
            val d= databaseDao.setAppPower(appPower)
            d
        }
    }

    init {
        val database = AppDatabase.getDatabase(application)
        databaseDao = database.databaseDao()
    }
}

class AppViewFragment : Fragment() {
    private val appliancesViewModel: AppliancesViewModel by viewModels()
}