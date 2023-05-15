package my.edu.tarc.ass2.AppSettings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentDeveloperInfoBinding
import my.edu.tarc.ass2.databinding.FragmentRatingBinding

class RatingFragment : Fragment() {
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRatingBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSubmitRating.setOnClickListener(){
            Toast.makeText(context,R.string.thanks,Toast.LENGTH_LONG).show()
        }
    }



}