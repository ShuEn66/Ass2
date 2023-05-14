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
import my.edu.tarc.ass2.databinding.FragmentProfileBinding
import my.edu.tarc.ass2.databinding.FragmentRegisterAddElectricityAccBinding


/**
 * A simple [Fragment] subclass.
 * Use the [registerAddElectricityAccFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class registerAddElectricityAccFragment : Fragment() {
    private var _binding: FragmentRegisterAddElectricityAccBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var sharedPre: SharedPreferences
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterAddElectricityAccBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)

        binding.buttonAddAcc.setOnClickListener {
            val name = binding.editTextTextAccNickname.text.toString()
            val accNo = binding.editTextTextAccNum.text.toString()
            val storedEmail = sharedPre.getString(getString(R.string.UserEmail), "")

            lifecycleScope.launch {

                val existingAcc = profileViewModel.getAccount(accNo.toLong())
                if(existingAcc != null) {
                    if (name != "" && accNo != "") {
                        with(sharedPre.edit()){
                            putLong(getString(R.string.ElectricityAccNum),accNo.toLong())
                            putString(getString(R.string.ElectricityAccNickname),name)
                            apply()
                        }
                        Toast.makeText(
                            context, getString(R.string.UpdateSuccessful), Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_registerAddElectricityAccFragment_to_accConfirmationFragment)
                    } else {
                        Toast.makeText(
                            context, getString(R.string.registerUnSuccessful), Toast.LENGTH_SHORT
                        ).show()
                    }
                }else{
                    Toast.makeText(requireContext(), "Account does not exists, please try again", Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}