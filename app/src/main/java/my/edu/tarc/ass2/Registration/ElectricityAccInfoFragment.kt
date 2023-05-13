package my.edu.tarc.ass2.Registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentElectricityAccInfoBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonConfirmAccInfo.setOnClickListener{
            findNavController().navigate(R.id.action_electricityAccInfoFragment_to_loginFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}