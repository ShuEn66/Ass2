package my.edu.tarc.ass2.Dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HotlineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HotlineFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}