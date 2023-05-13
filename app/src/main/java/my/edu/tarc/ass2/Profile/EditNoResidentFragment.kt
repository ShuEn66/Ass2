package my.edu.tarc.ass2.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentEditNoResidentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EditNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditNoResidentFragment : Fragment() {

    private var _binding: FragmentEditNoResidentBinding? = null

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

        binding.buttonCancelEditNoResident.setOnClickListener{
            findNavController().navigate(R.id.action_editNoResidentFragment_to_electricityAccFragment)

        }

        binding.buttonUpdateEditNoResident.setOnClickListener{
            //update
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}