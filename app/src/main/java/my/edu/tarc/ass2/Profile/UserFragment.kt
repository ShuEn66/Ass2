package my.edu.tarc.ass2.Profile

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import my.edu.tarc.ass2.databinding.FragmentUserBinding
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.User

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by viewModels()
    private var _binding: FragmentUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            //date formatter


            //set bill details for 1 time for data retrieval afterwards
            val newContact11 = User("lily@gmail.com", "12345", "Lily", "010101010101","0123456789", "House", 1, 3000.00,3412341111)


            profileViewModel.setUserDetails(newContact11)
        }


        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewUpdateUserName.setOnClickListener {

//            view ->
//            val EditNameAlertDialog = AlertDialog.Builder(requireActivity())
//            with(EditNameAlertDialog){
//                setTitle(getString(R.string.UpdateUserNameTitle)
//                        setMessage(getString(R.string.UpdateUserNameTitle)
//                        setPositiveButton(getString(R.string.updateClickableText)) { _, _ ->
////                    val contact = myContactViewModel.contactList.value!![myContactViewModel.selectedIndex]
////                    myContactViewModel.deleteContact(contact)
//                    //have edit action here
//                    findNavController().navigateUp()
//                }
//                        setNegativeButton(getString(R.string.cancel)) { _, _ ->
//                    //DO NOTHING HERE
//                }
//            }
//            EditNameAlertDialog.setView(R.layout.dialog_update_username)
//            EditNameAlertDialog.create().show()
        }
        binding.textViewUpdateUserEmail.setOnClickListener {

        }
        binding.textViewUpdateUserMobile.setOnClickListener {

        }

        //binding with database

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}