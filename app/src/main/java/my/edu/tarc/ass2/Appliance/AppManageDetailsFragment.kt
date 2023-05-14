package my.edu.tarc.ass2.Appliance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentAppManageDetailsBinding

class AppManageDetailsFragment : Fragment() {

    //View model
    private val appliancesViewModel: AppliancesViewModel by viewModels()

    private var _binding: FragmentAppManageDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppManageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCancel.setOnClickListener{
            findNavController().navigate(R.id.action_appManageDetailsFragment_to_appManageAddedFragment)
        }

        //Selected appliance
        val userEmail = "123412341111"
        val appName = "Testing2"
        //Get data
        lifecycleScope.launch {
            val getApp = appliancesViewModel.getAppliances(userEmail, appName)
            getApp.observe(viewLifecycleOwner, Observer { appliances ->
                if (appliances != null) {
                    // Set text
                    binding.editTextDetailsName.setText(appName)
                    binding.editTextDetailsEstimate.setText(appliances.EstimatedUsage.toString())
                    binding.editTextDetailsPower.setText(appliances.AppliancesPower.toString())
                    val appType = appliances.AppliancesType
                    // Set the spinner selection based on the value of the AppliancesType
                    if (appliances.AppliancesType.toString()=="Air conditioner"){
                        binding.spinnerDetailsType.setSelection(0)
                    }
                    else if (appliances.AppliancesType.toString()=="Clothes dryer"){
                        binding.spinnerDetailsType.setSelection(1)
                    }
                    else if (appliances.AppliancesType.toString()=="Electric kettle"){
                        binding.spinnerDetailsType.setSelection(2)
                    }
                    else if (appliances.AppliancesType.toString()=="Hair dryer"){
                        binding.spinnerDetailsType.setSelection(3)
                    }
                    else if (appliances.AppliancesType.toString()=="Microwave oven"){
                        binding.spinnerDetailsType.setSelection(4)
                    }
                    else if (appliances.AppliancesType.toString()=="Oven"){
                        binding.spinnerDetailsType.setSelection(5)
                    }
                    else if (appliances.AppliancesType.toString()=="Refrigerator"){
                        binding.spinnerDetailsType.setSelection(6)
                    }
                    else if (appliances.AppliancesType.toString()=="Washing machine"){
                        binding.spinnerDetailsType.setSelection(7)
                    }
                    else if (appliances.AppliancesType.toString()=="Water heater"){
                        binding.spinnerDetailsType.setSelection(8)
                    }
                    else if (appliances.AppliancesType.toString()=="Others"){
                        binding.spinnerDetailsType.setSelection(9)
                    }
                } else {
                    // Handle the case where the appliances object is null
                }
            })
        }
    }

}