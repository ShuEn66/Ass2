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
import my.edu.tarc.ass2.User
import my.edu.tarc.ass2.databinding.FragmentUserInfoRegisterBinding
import my.edu.tarc.ass2.databinding.FragmentUserRegistrationBinding

/**
 * A simple [Fragment] subclass.
 * Use the [userInfoRegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class userInfoRegisterFragment : Fragment() {
    private var _binding: FragmentUserInfoRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var sharedPre: SharedPreferences
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserInfoRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)

       binding.buttonResetUserInfo.setOnClickListener {
           binding.editTextTextUserName.text.clear()
           binding.editTextUserIC.text.clear()
           binding.editTextUserMobile.text.clear()

       }
        binding.buttonConfirmUserInfo.setOnClickListener {
            val name = binding.editTextTextUserName.text.toString()
            val ic = binding.editTextUserIC.text.toString()
            val mobile = binding.editTextUserMobile.text.toString()
            val storedEmail = sharedPre.getString(getString(R.string.UserEmail), "")
            val storedPassword = sharedPre.getString(getString(R.string.UserPassword), "")

            lifecycleScope.launch {
                if(isICValid(ic)){
                    if(name!=""&&ic!=""&&mobile!=""){
                        if (storedEmail != null&&storedPassword!=null) {
                            val newUser= User(storedEmail,storedPassword,name,ic,mobile,"",0,0.0,0)
                            profileViewModel.setUserDetails(newUser)
                            Toast.makeText(context,getString(R.string.registerSuccessful)
                                , Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_userInfoRegisterFragment_to_registerAddElectricityAccFragment)
                        }
                    }else{
                        Toast.makeText(context,getString(R.string.registerUnSuccessful)
                            , Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    binding.textViewErrorIC.visibility = View.VISIBLE
                }
            }
        }

    }

    fun isICValid(ic: String): Boolean {
        val icRegex = Regex("""^\d{12}$""")
        return icRegex.matches(ic)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}