package my.edu.tarc.ass2.Registration

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.Profile.ProfileViewModel
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentElectricityAccBinding
import my.edu.tarc.ass2.databinding.FragmentElectricityAccInfoBinding
import my.edu.tarc.ass2.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ElectricityAccInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ElectricityAccInfoFragment : Fragment() {
    private var _binding: FragmentElectricityAccInfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var sharedPre: SharedPreferences
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentElectricityAccInfoBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)

        lifecycleScope.launch {
            val storedAccNum: Long = sharedPre.getLong(getString(R.string.ElectricityAccNum),0)



                binding.textViewAddressAccInfoValue.text=profileViewModel.getAccountAddress(storedAccNum)
                binding.textViewOwnerAccInfoValue.text=profileViewModel.getAccountOwner(storedAccNum)
                binding.textViewPropertyTypeAccInfoValue.text=profileViewModel.getAccountProperty(storedAccNum)

        }

        binding.buttonConfirmAccInfo.setOnClickListener{
            val noResident = binding.editTextNoResidentAccInfo.text.toString().toInt()
            val monthlyIncome = binding.editTextMonthlyIncomeAccInfo.text.toString().toDouble()
            val storedEmail = sharedPre.getString(getString(R.string.UserEmail), "")
            lifecycleScope.launch {
                if(storedEmail!=null&&noResident!=0&&monthlyIncome!=0.0){
                    profileViewModel.updateNoResident(storedEmail,noResident)
                    profileViewModel.updateMonthlyIncome(storedEmail,monthlyIncome)
                    Toast.makeText(context, getString(R.string.UpdateSuccessful), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_electricityAccInfoFragment_to_loginFragment)
                }
                else{
                    Toast.makeText(context,getString(R.string.registerUnSuccessful)
                        , Toast.LENGTH_SHORT).show()
                }
            }


        }
        binding.buttonResetAccInfo.setOnClickListener {
            binding.editTextNoResidentAccInfo.text.clear()
            binding.editTextMonthlyIncomeAccInfo.text.clear()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}