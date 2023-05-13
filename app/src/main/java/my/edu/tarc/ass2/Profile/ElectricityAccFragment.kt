package my.edu.tarc.ass2.Profile

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
import my.edu.tarc.ass2.databinding.FragmentUserBinding


class ElectricityAccFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()
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

        binding.textViewUpdateElectricityAccNickname.setOnClickListener {
            findNavController().navigate(R.id.action_electricityAccFragment_to_editAccNameFragment)
        }
        binding.textViewUpdateNoOfResident.setOnClickListener {
            findNavController().navigate(R.id.action_electricityAccFragment_to_editNoResidentFragment)
        }
        binding.textViewUpdateMonthlyIncome.setOnClickListener {
            findNavController().navigate(R.id.action_electricityAccFragment_to_editMonthlyIncomeFragment)
        }

        lifecycleScope.launch {
            //date formatter

            //set bill details for 1 time for data retrieval afterwards

            val getAccountNumber = profileViewModel.getAccountNumber("lily@gmail.com")
            binding.textViewElectricityAccNumValue.text = getAccountNumber.toString()

            val getAccountProperty = profileViewModel.getAccountProperty("lily@gmail.com")
            binding.textViewPropertyTypeValue.text = getAccountProperty

            val getAccountAddress = profileViewModel.getAccountAddress("lily@gmail.com")
            binding.textViewElectricityAccAddressValue.text = getAccountAddress

            val getAccNickName = profileViewModel.getAccNickName("lily@gmail.com")
            binding.textViewElectricityAccAddressValue.text = getAccNickName





        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
