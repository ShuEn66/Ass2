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


/**
 * A simple [Fragment] subclass.
 * Use the [EditNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditAccNameFragment : Fragment() {

    private var _binding: FragmentEditAccNameBinding? = null
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var sharedPre: SharedPreferences

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /** The system calls this to get the DialogFragment's layout, regardless
    of whether it's being displayed as a dialog or an embedded fragment. */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditAccNameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre = requireActivity().getPreferences(Context.MODE_PRIVATE)

        binding.buttonCancelEditAccName.setOnClickListener{
            findNavController().navigateUp()
        }

        binding.buttonUpdateEditAccName.setOnClickListener{
            val newAccName = binding.editTextEditAccNameValue.text.toString()
            val loginEmail = sharedPre.getString(getString(my.edu.tarc.ass2.R.string.LoginEmail),"")
            lifecycleScope.launch {
                val oriAccName = loginEmail?.let { it1 -> profileViewModel.getAccNickName(it1) }
                if(newAccName !=null){
                    if(oriAccName==newAccName){
                        Toast.makeText(context,getString(R.string.SameInformation)
                            , Toast.LENGTH_SHORT).show()
                    }else{
                        if (loginEmail != null) {
                            profileViewModel.updateAccName(loginEmail,newAccName)
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