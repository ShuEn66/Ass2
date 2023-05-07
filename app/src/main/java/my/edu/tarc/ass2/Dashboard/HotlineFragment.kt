package my.edu.tarc.ass2.Dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentHotlineBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HotlineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotlineFragment : Fragment() {

    private var _binding: FragmentHotlineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotlineBinding.inflate(inflater, container, false)

        return binding.root

        binding.buttonDone.setOnClickListener() {

            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(this)
            transaction.commit()

            findNavController().navigateUp()
        }

    }

}