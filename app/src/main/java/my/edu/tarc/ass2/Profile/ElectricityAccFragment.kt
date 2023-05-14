package my.edu.tarc.ass2.Profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentElectricityAccBinding


class ElectricityAccFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var sharedPre: SharedPreferences
    private var _binding: FragmentElectricityAccBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentElectricityAccBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre = requireActivity().getPreferences(Context.MODE_PRIVATE)

        val loginEmail = sharedPre.getString(getString(my.edu.tarc.ass2.R.string.LoginEmail),"")

        binding.textViewUpdateElectricityAccNickname.setOnClickListener {
            findNavController().navigate(R.id.action_electricityAccFragment_to_editAccNameFragment)
        }
        binding.textViewUpdateNoOfResident.setOnClickListener {
            findNavController().navigate(R.id.action_electricityAccFragment_to_editNoResidentFragment)
        }
        binding.textViewUpdateMonthlyIncome.setOnClickListener {
            findNavController().navigate(R.id.action_electricityAccFragment_to_editMonthlyIncomeFragment)
        }
        binding.buttonDeleteAccount.setOnClickListener {
            findNavController().navigate(R.id.action_electricityAccFragment_to_deleteAccFragment)

        }

        lifecycleScope.launch {


            val getAccountProperty = loginEmail?.let {
                profileViewModel.getAccNumber(
                    it
                )
            }?.let { profileViewModel.getAccountProperty(it) }
            binding.textViewPropertyTypeValue.text = getAccountProperty

            val getAccountAddress = loginEmail?.let {
                profileViewModel.getAccNumber(
                    it
                )
            }?.let { profileViewModel.getAccountAddress(it) }
            binding.textViewElectricityAccAddressValue.text = getAccountAddress

            val getAccNickName = loginEmail?.let { profileViewModel.getAccNickName(it) }
            binding.textViewElectricityAccNicknameValue.text = getAccNickName

            val getNoResident = loginEmail?.let { profileViewModel.getNoResident(it) }
            binding.textViewNoOfResidentValue.text = getNoResident.toString()

            val getMonthlyIncome = loginEmail?.let { profileViewModel.getMonthlyIncome(it) }
            binding.textViewMonthlyIncomeValue.text = "RM "+getMonthlyIncome.toString()

            val getAccNumber = loginEmail?.let { profileViewModel.getAccNumber(it) }
            binding.textViewElectricityAccNumValue.text = getAccNumber.toString()

            val getAccountOwner = loginEmail?.let {
                profileViewModel.getAccNumber(
                    it
                )
            }?.let { profileViewModel.getAccountOwner(it) }
            binding.textViewAccountOwnerValue.text = getAccountOwner.toString()



        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
