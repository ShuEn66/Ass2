package my.edu.tarc.ass2.Billing

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.Profile.ProfileViewModel
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentBillHistoryBinding
import my.edu.tarc.ass2.databinding.FragmentBillInfoBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BillHistoryFragment : Fragment() {
    private val billViewModel: BillViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var sharedPre: SharedPreferences
    private var _binding: FragmentBillHistoryBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)
        val current = LocalDateTime.now()
        val monthDisplay =  current.monthValue
        var yearDisplay =  current.year
        if (monthDisplay == 1){
            yearDisplay -= 1
        }

        lifecycleScope.launch {

            val loginEmail = sharedPre.getString(getString(R.string.LoginEmail),"")
            val accNo = loginEmail.let { profileViewModel.getAccNumber(it.toString()) }
            val getInvoiceDate = billViewModel.getInvoiceDate(accNo, (monthDisplay - 1), yearDisplay)
            binding.displayInvDate.text = getInvoiceDate
            binding.displayPaymentStat.text = "Paid"
            binding.displayPaymentMethod.text = "Online Banking"


        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBillHistoryBinding.inflate(inflater, container, false)


        return binding.root
    }

}