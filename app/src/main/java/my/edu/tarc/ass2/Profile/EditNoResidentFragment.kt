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
import my.edu.tarc.ass2.databinding.FragmentEditNoResidentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EditNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditNoResidentFragment : Fragment() {

    private var _binding: FragmentEditNoResidentBinding? = null
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

        _binding = FragmentEditNoResidentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre = requireActivity().getPreferences(Context.MODE_PRIVATE)

        binding.buttonCancelEditNoResident.setOnClickListener{
            findNavController().navigateUp()
        }

        binding.buttonUpdateEditNoResident.setOnClickListener{
            val newNo = binding.editTextEditNoResidentValue.text.toString().toInt()
            val loginEmail = sharedPre.getString(getString(my.edu.tarc.ass2.R.string.LoginEmail),"")
            lifecycleScope.launch {
                val oriNo = loginEmail?.let { it1 -> profileViewModel.getNoResident(it1) }
                if(newNo !=0){
                    if(oriNo==newNo){
                        Toast.makeText(context,getString(R.string.SameInformation)
                            , Toast.LENGTH_SHORT).show()
                    }else{
                        if (loginEmail != null) {
                            profileViewModel.updateNoResident(loginEmail,newNo)
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