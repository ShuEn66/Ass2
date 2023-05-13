package my.edu.tarc.ass2.Appliance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.edu.tarc.ass2.R
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.databinding.FragmentAppManageAddedBinding
import my.edu.tarc.ass2.databinding.FragmentAppliancesBinding

class AppliancesFragment : Fragment() {

    private var _binding: FragmentAppliancesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppliancesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonViewAppliances.setOnClickListener{
            findNavController().navigate(R.id.action_appliancesFragment3_to_appManageAddedFragment)
        }

        binding.buttonCostCalculator.setOnClickListener{
            findNavController().navigate(R.id.action_appliancesFragment3_to_costCalculatorFragment)
        }
    }

}