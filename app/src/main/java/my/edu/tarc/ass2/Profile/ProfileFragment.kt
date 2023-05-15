package my.edu.tarc.ass2.Profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.ElectricityAcc
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.User
import my.edu.tarc.ass2.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var sharedPre: SharedPreferences

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
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)

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
            Toast.makeText(context, getString(R.string.LogoutSuccessful), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        lifecycleScope.launch {
            with(sharedPre.edit()){
                putString(getString(R.string.LoginEmail),profileViewModel.getCurrentUser().currentEmail)
                apply()
            }

            val loginEmail = sharedPre.getString(getString(R.string.LoginEmail),"")

            binding.textViewUserEmailValue.text = loginEmail

            val getUserName = loginEmail?.let { profileViewModel.getUserName(it) }
            binding.textViewUserNameValue.text = getUserName

            val getAccountNumber = loginEmail?.let { profileViewModel.getAccNumber(it) }
            binding.textViewElectricityAccNumValue.text = getAccountNumber.toString()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}