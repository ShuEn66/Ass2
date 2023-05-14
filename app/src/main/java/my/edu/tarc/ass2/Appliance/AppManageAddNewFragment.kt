package my.edu.tarc.ass2.Appliance

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.room.Dao
import my.edu.tarc.ass2.Appliances
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentAppManageAddNewBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.Profile.ProfileViewModel


class AppManageAddNewFragment : Fragment() {

    //View model
    private val appliancesViewModel: AppliancesViewModel by viewModels()

    private var _binding: FragmentAppManageAddNewBinding? = null

    //To get user email
    private val profileViewModel: AppliancesViewModel by viewModels()
    private lateinit var sharedPre: SharedPreferences

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppManageAddNewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //To get user email
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)

        binding.buttonCancel.setOnClickListener{
            findNavController().navigate(R.id.action_appManageAddNewFragment_to_appManageAddedFragment)
        }

        //Get user email


        //Add new appliance to database
        binding.buttonAddNew.setOnClickListener {
            if (binding.editTextDetailsName.text.toString().isEmpty()
                ||binding.editTextDetailsEstimate.text.toString().isEmpty()
                ||binding.editTextDetailsPower.text.toString().isEmpty()){
                Toast.makeText(context,"Fields cannot be empty",Toast.LENGTH_SHORT).show()
            }
            else{
                lifecycleScope.launch {
                    val userEmail = sharedPre.getString(getString(R.string.LoginEmail),"").toString()
                    val estimateUsage : Double = binding.editTextDetailsEstimate.text.toString().toDouble()
                    val power : Double = binding.editTextDetailsPower.text.toString().toDouble()
                    val newAppliance = Appliances(binding.editTextDetailsName.text.toString(),
                        binding.spinnerDetailsType.selectedItem.toString(),
                        estimateUsage,
                        power,
                        //Logged in user email
                        userEmail)

                //Binding with database

                    //Insert into database

                    appliancesViewModel.setAppliances(newAppliance)
                }

                //Successful added message
                Toast.makeText(context,"Appliance added",Toast.LENGTH_SHORT).show()
            }
        }
    }

}