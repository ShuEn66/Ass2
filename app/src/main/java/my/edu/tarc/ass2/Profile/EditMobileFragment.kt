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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentEditMobileBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EditNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditMobileFragment : Fragment() {

    private var _binding: FragmentEditMobileBinding? = null

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

        _binding = FragmentEditMobileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre = requireActivity().getPreferences(Context.MODE_PRIVATE)

        binding.buttonCancelEditUserMobile.setOnClickListener{
            findNavController().navigateUp()
        }

        binding.buttonUpdateEditUserMobile.setOnClickListener{
            val newMobile = binding.editTextEditMobileValue.text.toString()
            val loginEmail = sharedPre.getString(getString(my.edu.tarc.ass2.R.string.LoginEmail),"")
            lifecycleScope.launch {
                val oriMobile= loginEmail?.let { it1 -> profileViewModel.getUserMobile(it1) }
                if(newMobile !=null){
                    if(oriMobile==newMobile){
                        Toast.makeText(context,getString(R.string.SameInformation)
                            , Toast.LENGTH_SHORT).show()
                    }else{
                        if (loginEmail != null) {
                            profileViewModel.updateUserMobile(loginEmail,newMobile)
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