package my.edu.tarc.ass2.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardViewUserProfileTab.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_userFragment)
        }

        binding.cardViewElectricityAccProfileTab.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_electricityAccFragment)
        }

        binding.cardViewAppSettingTab.setOnClickListener{
            //navigate to app setting
        }

        binding.buttonLogOut.setOnClickListener {
            //write log out function
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}