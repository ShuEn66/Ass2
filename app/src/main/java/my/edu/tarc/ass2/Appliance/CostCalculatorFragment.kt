package my.edu.tarc.ass2.Appliance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentConfirmDeleteDialogBinding
import my.edu.tarc.ass2.databinding.FragmentCostCalculatorBinding

class CostCalculatorFragment : Fragment() {
    private var _binding: FragmentCostCalculatorBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCostCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }
}