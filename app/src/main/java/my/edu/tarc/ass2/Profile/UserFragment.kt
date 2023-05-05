package my.edu.tarc.ass2.Profile

import android.R
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import my.edu.tarc.ass2.databinding.FragmentUserBinding
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewUpdateUserName.setOnClickListener {

        }
        binding.textViewUpdateUserEmail.setOnClickListener {

        }
        binding.textViewUpdateUserMobile.setOnClickListener {

        }
    }


//    class EditNameDialogFragment :  DialogFragment(){
//        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//            val builder = AlertDialog.Builder(requireActivity())
//                // Get the layout inflater
//                val inflater = requireActivity().layoutInflater;
//
//                builder.setView(inflater.inflate((R.layout.dialog_update_username), null))
//                    .setPositiveButton(getString(R.string.updateClickableText),
//                        { _,_ ->
//                            requireActivity().finish()    //terminate this app
//                        })
//                    . setNegativeButton(getString(R.string.cancel),
//                        { _,_ ->
//                            //DO NOTHING
//                        })
//                return builder.create()
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}