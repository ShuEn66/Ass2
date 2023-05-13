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
import my.edu.tarc.ass2.User
import my.edu.tarc.ass2.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val profileViewModel: ProfileViewModel by viewModels()

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
            findNavController().navigate(R.id.action_profileFragment_to_appSettingsFragment)
        }

        binding.buttonLogOut.setOnClickListener {
            //write log out function
        }

        lifecycleScope.launch {
            //date formatter

            //set bill details for 1 time for data retrieval afterwards
            val user1 = User("lily@gmail.com", "12345", "Lily", "010101010101","0123456789", "House", 1, 3000.00,3412341111)
            val user2 = User("ali@gmail.com", "54321", "Ali", "101010101010","0987654321", "Home", 3, 8000.00,1234500000)
            profileViewModel.setUserDetails(user1)
            profileViewModel.setUserDetails(user2)

            val getUserEmail = profileViewModel.getUserEmail(3412341111)
            binding.textViewUserEmailValue.text = getUserEmail

            val getUserName = profileViewModel.getUserName(getUserEmail)
            binding.textViewUserNameValue.text = getUserName



        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}