package my.edu.tarc.ass2.Appliance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentCalAdviceDialogBinding
import my.edu.tarc.ass2.databinding.FragmentCalHelpDialogBinding


class CalAdviceDialogFragment : Fragment() {
    private var _binding: FragmentCalAdviceDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalAdviceDialogBinding.inflate(inflater, container, false)
        val stringArray = resources.getStringArray(R.array.advices_list_general)
        val stringArray2 = resources.getStringArray(R.array.advices_list_appliances)
        val random = (0..8).random()
        val random2 = (0..5).random()
        binding.textViewAdvice1.text = stringArray2[random]
        binding.textViewAdvice2.text = stringArray[random]
        //binding.textViewAdvice3.text = stringArray[3]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAdviceOk.setOnClickListener{
            findNavController().navigate(R.id.action_calAdviceDialogFragment_to_costCalculatorFragment)
        }
    }
}