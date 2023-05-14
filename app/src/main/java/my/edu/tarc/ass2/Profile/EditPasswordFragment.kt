package my.edu.tarc.ass2.Profile

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
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentEditAccNameBinding
import my.edu.tarc.ass2.databinding.FragmentEditMobileBinding
import my.edu.tarc.ass2.databinding.FragmentEditPasswordBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EditNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditPasswordFragment : Fragment() {

    private var _binding: FragmentEditPasswordBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var sharedPre: SharedPreferences

    /** The system calls this to get the DialogFragment's layout, regardless
    of whether it's being displayed as a dialog or an embedded fragment. */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditPasswordBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre = requireActivity().getPreferences(Context.MODE_PRIVATE)

        binding.buttonCancelEditPassword.setOnClickListener{
            findNavController().navigateUp()
        }

        binding.buttonUpdateEditPassword.setOnClickListener{
            val newPassword = binding.editTextEditPasswordValue.text.toString()
            val loginEmail = sharedPre.getString(getString(my.edu.tarc.ass2.R.string.LoginEmail),"")
            lifecycleScope.launch {
                val oriPassword= loginEmail?.let { it1 -> profileViewModel.getUserPassword(it1) }
                if(newPassword !=null){
                    if(oriPassword==newPassword){
                        Toast.makeText(context,getString(R.string.SameInformation)
                            , Toast.LENGTH_SHORT).show()
                    }else{
                        if (loginEmail != null) {
                            profileViewModel.updateUserPassword(loginEmail,newPassword)
                        }
                        Toast.makeText(context,getString(R.string.UpdateSuccessful)
                            , Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context,getString(R.string.registerUnSuccessful)
                        , Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}