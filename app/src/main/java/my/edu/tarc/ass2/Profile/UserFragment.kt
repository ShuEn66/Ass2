package my.edu.tarc.ass2.Profile

import android.R
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import my.edu.tarc.ass2.databinding.FragmentUserBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by viewModels()
    private var _binding: FragmentUserBinding? = null
    private lateinit var sharedPre: SharedPreferences

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
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)

        val loginEmail = sharedPre.getString(getString(my.edu.tarc.ass2.R.string.LoginEmail),"")

        binding.textViewUpdateUserName.setOnClickListener {

            findNavController().navigate(my.edu.tarc.ass2.R.id.action_userFragment_to_editNameFragment)

        }
        binding.textViewUpdateUserMobile.setOnClickListener {
            findNavController().navigate(my.edu.tarc.ass2.R.id.action_userFragment_to_editMobileFragment)
        }

        //binding with database

        lifecycleScope.launch {

            binding.textViewUserEmailValue.text = loginEmail

            val getUserName = loginEmail?.let { profileViewModel.getUserName(it) }
            binding.textViewUserNameValue.text = getUserName

            val getUserIC = loginEmail?.let { profileViewModel.getUserIC(it) }
            binding.textViewUserICValue.text = getUserIC

            val getUserMobile = loginEmail?.let { profileViewModel.getUserMobile(it) }
            binding.textViewUserMobileValue.text = getUserMobile

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}