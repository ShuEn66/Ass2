package my.edu.tarc.ass2.Dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.edu.tarc.ass2.R

class UsageAnalysisFragment : Fragment() {

    companion object {
        fun newInstance() = UsageAnalysisFragment()
    }

    private lateinit var viewModel: UsageAnalysisViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_usage_analysis, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsageAnalysisViewModel::class.java)
        // TODO: Use the ViewModel
    }

}