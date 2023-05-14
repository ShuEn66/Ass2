package my.edu.tarc.ass2.Appliance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentConfirmDeleteDialogBinding
import my.edu.tarc.ass2.databinding.FragmentCostCalculatorBinding

class CostCalculatorFragment : Fragment() {
    //View model
    private val appliancesViewModel: AppliancesViewModel by viewModels()

    private var _binding: FragmentCostCalculatorBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCostCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewHelpButton.setOnClickListener{
            findNavController().navigate(R.id.action_costCalculatorFragment_to_calHelpDialogFragment)
        }

        binding.buttonAdvice.setOnClickListener{
            findNavController().navigate(R.id.action_costCalculatorFragment_to_calAdviceDialogFragment)
        }
    }
}