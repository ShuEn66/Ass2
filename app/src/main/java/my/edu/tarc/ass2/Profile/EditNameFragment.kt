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
import my.edu.tarc.ass2.databinding.FragmentEditNameBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EditNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditNameFragment : Fragment() {

    private var _binding: FragmentEditNameBinding? = null

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

            _binding = FragmentEditNameBinding.inflate(inflater, container, false)
            return binding.root

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre = requireActivity().getPreferences(Context.MODE_PRIVATE)

        binding.buttonCancelEditUserName.setOnClickListener{
            findNavController().navigateUp()
        }

        binding.buttonUpdateEditUserName.setOnClickListener{
            val newName = binding.editTextEditNameValue.text.toString()
            val loginEmail = sharedPre.getString(getString(my.edu.tarc.ass2.R.string.LoginEmail),"")
            lifecycleScope.launch {
                val oriName = loginEmail?.let { it1 -> profileViewModel.getUserName(it1) }
                if(newName !=null){
                    if(oriName==newName){
                        Toast.makeText(context,getString(R.string.SameInformation)
                            , Toast.LENGTH_SHORT).show()
                    }else{
                        if (loginEmail != null) {
                            profileViewModel.updateUserName(loginEmail,newName)
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