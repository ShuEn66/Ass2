package my.edu.tarc.ass2.Appliance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentAppliancesBinding
import my.edu.tarc.ass2.databinding.FragmentConfirmDeleteDialogBinding

class ConfirmDeleteDialogFragment : Fragment() {
    private var _binding: FragmentConfirmDeleteDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfirmDeleteDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonHelpOk.setOnClickListener{
            findNavController().navigate(R.id.action_confirmDeleteDialogFragment_to_appManageDeleteFragment)
        }
    }
}