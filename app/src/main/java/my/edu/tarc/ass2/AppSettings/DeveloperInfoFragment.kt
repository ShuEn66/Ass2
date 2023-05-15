package my.edu.tarc.ass2.AppSettings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.Dashboard.HotlineFragment
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentAppSettingsBinding
import my.edu.tarc.ass2.databinding.FragmentDeveloperInfoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeveloperInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeveloperInfoFragment : Fragment() {
    private var _binding: FragmentDeveloperInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeveloperInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRateUs.setOnClickListener(){
            findNavController().navigate(R.id.action_developerInfoFragment_to_ratingFragment)
        }
    }



}