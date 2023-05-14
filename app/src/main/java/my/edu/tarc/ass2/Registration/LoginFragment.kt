package my.edu.tarc.ass2.Registration

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.Profile.ProfileViewModel
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.User
import my.edu.tarc.ass2.databinding.FragmentLoginBinding
import my.edu.tarc.ass2.databinding.FragmentProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var sharedPre: SharedPreferences
    private val profileViewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPre = requireActivity().getPreferences(Context.MODE_PRIVATE)

        binding.buttonLogin.setOnClickListener{

            val email = binding.editTextLoginEmail.text.toString()
            val password = binding.editTextLoginPassword.text.toString()

            lifecycleScope.launch {
                val existingUser = profileViewModel.getUserbyEmail(email)
                val correctPassword = profileViewModel.getUserPassword(email)
                if (existingUser == null) {
                    Toast.makeText(requireContext(), "Account does not exist, please register an account", Toast.LENGTH_SHORT).show()
                }else{
                    if(isEmailValid(email)){
                        if(email!=""&&password!=""){
                            if(password==correctPassword){
                                with(sharedPre.edit()){
                                    putString(getString(R.string.LoginEmail),email)
                                    apply()
                                }
                                Toast.makeText(context,getString(R.string.loginSuccessful)
                                    , Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_loginFragment_to_profileFragment)}
                            else{
                                Toast.makeText(context,getString(R.string.WrongPassword)
                                    , Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(context,getString(R.string.loginFailed)
                                , Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        binding.textViewErrorEmail.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.textViewSignInToRegister.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_userRegistrationFragment)
        }
    }

    fun isEmailValid(email: String): Boolean {
        val pattern = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
        return pattern.matches(email)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}