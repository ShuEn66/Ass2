package my.edu.tarc.ass2.AppSettings

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentChangLanguageBinding
import my.edu.tarc.ass2.databinding.FragmentRatingBinding
import java.util.*

class ChangLanguageFragment : Fragment() {
    private var _binding: FragmentChangLanguageBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPre: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangLanguageBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locale = Locale("en")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        binding.radioGroupLang.setOnCheckedChangeListener{ _, checkedId ->
            val locale = when (checkedId){
                binding.radioButtonEng.id -> Locale("en")
                binding.radioButtonChi.id -> Locale("zh")
                binding.radioButtonMalay.id -> Locale("ms")
                else -> Locale.getDefault()
            }
            Locale.setDefault(locale)
            val config = Configuration()
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)

        }
    }

}