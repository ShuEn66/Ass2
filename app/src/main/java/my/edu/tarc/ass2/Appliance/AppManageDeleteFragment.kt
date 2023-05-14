package my.edu.tarc.ass2.Appliance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentAppManageAddNewBinding
import my.edu.tarc.ass2.databinding.FragmentAppManageDeleteBinding

class AppManageDeleteFragment : Fragment() {

    //View model
    private val appliancesViewModel: AppliancesViewModel by viewModels()

    private var _binding: FragmentAppManageDeleteBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppManageDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCancel.setOnClickListener{
            findNavController().navigate(R.id.action_appManageDeleteFragment_to_appManageAddedFragment)
        }
    }

}