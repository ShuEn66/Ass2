package my.edu.tarc.ass2.Profile

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.User
import my.edu.tarc.ass2.databinding.FragmentDialogEditNameBinding
import my.edu.tarc.ass2.databinding.FragmentUserBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DialogEditNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogEditNameFragment : DialogFragment() {

    private var _binding: FragmentDialogEditNameBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

        /** The system calls this to get the DialogFragment's layout, regardless
        of whether it's being displayed as a dialog or an embedded fragment. */
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            _binding = FragmentDialogEditNameBinding.inflate(inflater, container, false)
            return binding.root

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCancelEditUserName.setOnClickListener{
            findNavController().navigate(R.id.action_dialogEditNameFragment_to_userFragment)
        }

        binding.buttonUpdateEditUserName.setOnClickListener{
            //update
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}