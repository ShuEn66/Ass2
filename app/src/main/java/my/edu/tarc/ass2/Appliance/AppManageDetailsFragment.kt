package my.edu.tarc.ass2.Appliance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentAppManageDeleteBinding
import my.edu.tarc.ass2.databinding.FragmentAppManageDetailsBinding

class AppManageDetailsFragment : Fragment() {

    private var _binding: FragmentAppManageDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppManageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}