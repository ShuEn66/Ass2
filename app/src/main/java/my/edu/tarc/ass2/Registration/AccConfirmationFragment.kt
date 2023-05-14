package my.edu.tarc.ass2.Registration

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.Profile.ProfileViewModel
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentAccConfirmationBinding
import my.edu.tarc.ass2.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AccConfirmationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccConfirmationFragment : Fragment() {
    private var _binding: FragmentAccConfirmationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var sharedPre: SharedPreferences
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccConfirmationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)

        lifecycleScope.launch {
            val storedAccNum: Long = sharedPre.getLong(getString(R.string.ElectricityAccNum),0)
                    binding.textViewAddressAccConfirmValue.text=profileViewModel.getAccountAddress(storedAccNum)
                    binding.textViewOwnerAccConfirmValue.text=profileViewModel.getAccountOwner(storedAccNum)
                    binding.textViewPropertyTypeAccConfirmValue.text=profileViewModel.getAccountProperty(storedAccNum)
                
        }

        binding.buttonNoAccConfirm.setOnClickListener{
            findNavController().navigate(R.id.action_accConfirmationFragment_to_registerAddElectricityAccFragment)
        }

        binding.buttonYesAccConfirm.setOnClickListener{
            lifecycleScope.launch {
                val storedEmail = sharedPre.getString(getString(R.string.UserEmail), "")
                val storedAccNum: Long = sharedPre.getLong(getString(R.string.ElectricityAccNum),0)
                val storedAccName = sharedPre.getString(getString(R.string.ElectricityAccNickname), "")
                if (storedEmail != null&&storedAccName!=null) {
                    profileViewModel.updateAccNum(storedEmail,storedAccNum)
                    profileViewModel.updateAccName(storedEmail,storedAccName)
                }
            }
            Toast.makeText(context, getString(R.string.UpdateSuccessful), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_accConfirmationFragment_to_electricityAccInfoFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}