package my.edu.tarc.ass2.Profile

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import my.edu.tarc.ass2.databinding.FragmentUserBinding
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.User

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by viewModels()
    private var _binding: FragmentUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserBinding.inflate(inflater, container, false)



        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewUpdateUserName.setOnClickListener {
            findNavController().navigate(my.edu.tarc.ass2.R.id.action_userFragment_to_dialogEditNameFragment)
        }
        binding.textViewUpdateUserEmail.setOnClickListener {

        }
        binding.textViewUpdateUserMobile.setOnClickListener {

        }

        //binding with database

        lifecycleScope.launch {
            //date formatter

            //set bill details for 1 time for data retrieval afterwards
            val user1 = User("lily@gmail.com", "12345", "Lily", "010101010101","0123456789", "House", 1, 3000.00,3412341111)
            val user2 = User("ali@gmail.com", "12345", "Lily", "010101010101","0123456789", "House", 1, 3000.00,3412341111)
            profileViewModel.setUserDetails(user1)

            val getUserName = profileViewModel.getUserName("lily@gmail.com")
            binding.textViewUserNameValue.text = getUserName
        }



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}