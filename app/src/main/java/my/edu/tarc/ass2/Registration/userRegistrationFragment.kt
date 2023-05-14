package my.edu.tarc.ass2.Registration

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.FocusFinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.ElectricityAcc
import my.edu.tarc.ass2.Profile.ProfileViewModel
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.User
import my.edu.tarc.ass2.databinding.FragmentUserRegistrationBinding


class userRegistrationFragment : Fragment() {
    private var _binding: FragmentUserRegistrationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var sharedPre: SharedPreferences
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        _binding = FragmentUserRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)
        //Read Shared Prefrence Data
        //dont know need ma
//        val email = sharedPre.getString(getString(R.string.UserEmail),"")
//        val password = sharedPre.getString(getString(R.string.UserPassword),"")
//
//        binding.editTextTextUserEmail.setText(email)
//        binding.editTextUserPassword.setText(password)

        binding.buttonRegister.setOnClickListener {
            val email = binding.editTextTextUserEmail.text.toString()
            val password = binding.editTextUserPassword.text.toString()


            lifecycleScope.launch {
                val user1 = User("lily@gmail.com", "12345", "Lily", "010101010101","0123456789", "House", 1, 3000.00,3412341111)
                val user2 = User("ali@gmail.com", "54321", "Ali", "101010101010","0987654321", "Home", 3, 8000.00,1234500000)
                profileViewModel.setUserDetails(user1)
                profileViewModel.setUserDetails(user2)

                val acc1 = ElectricityAcc(3412341111,"No1,Jalan Besar,Kampung Kecil, 12345, WPKL","Apartment","Lily")
                val acc2 = ElectricityAcc(1234500000,"No2,Jalan Besar,Kampung Kecil, 12345, WPKL","hostel","Ali")
                val acc3 = ElectricityAcc(1020304050,"No3,Jalan Besar,Kampung Kecil, 12345, WPKL","terrace","Abu")
                val acc4 = ElectricityAcc(6789000000,"No4,Jalan Besar,Kampung Kecil, 12345, WPKL","Apartment","John")
                val acc5 = ElectricityAcc(6070809000,"No5,Jalan Besar,Kampung Kecil, 12345, WPKL","hostel","Jane")
                val acc6 = ElectricityAcc(1357900000,"No6,Jalan Besar,Kampung Kecil, 12345, WPKL","terrace","Peter")
                val acc7 = ElectricityAcc(2468000000,"No7,Jalan Besar,Kampung Kecil, 12345, WPKL","terrace","Muthu")
                val acc8 = ElectricityAcc(1030507090,"No8,Jalan Besar,Kampung Kecil, 12345, WPKL","Apartment","Sandy")
                val acc9 = ElectricityAcc(2040608000,"No9,Jalan Besar,Kampung Kecil, 12345, WPKL","Apartment","Candy")

                profileViewModel.setAccDetails(acc1)
                profileViewModel.setAccDetails(acc2)
                profileViewModel.setAccDetails(acc3)
                profileViewModel.setAccDetails(acc4)
                profileViewModel.setAccDetails(acc5)
                profileViewModel.setAccDetails(acc6)
                profileViewModel.setAccDetails(acc7)
                profileViewModel.setAccDetails(acc8)
                profileViewModel.setAccDetails(acc9)

                val existingUser = profileViewModel.getUserbyEmail(email)
                if (existingUser != null) {
                    Toast.makeText(requireContext(), "Email already exists", Toast.LENGTH_SHORT).show()
                }else{
                    if(isEmailValid(email)){
                        if(email!=""&&password!=""){
                            with(sharedPre.edit()){
                                putString(getString(R.string.UserEmail),email)
                                putString(getString(R.string.UserPassword),password)
                                apply()
                            }

                            findNavController().navigate(R.id.action_userRegistrationFragment_to_userInfoRegisterFragment)
                        }else{
                            Toast.makeText(context,getString(R.string.registerUnSuccessful)
                                , Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        binding.textViewErrorEmail.visibility = View.VISIBLE
                    }
                }
            }


            
        }

        binding.textViewRegisterToSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_userRegistrationFragment_to_loginFragment)
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