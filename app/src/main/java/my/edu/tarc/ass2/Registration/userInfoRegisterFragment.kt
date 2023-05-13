package my.edu.tarc.ass2.Registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserInfoRegisterBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.buttonResetUserInfo.setOnClickListener {

       }
        binding.buttonConfirmUserInfo.setOnClickListener {
            findNavController().navigate(R.id.action_userInfoRegisterFragment_to_registerAddElectricityAccFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}