package my.edu.tarc.ass2.Billing

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.Bill
import my.edu.tarc.ass2.Dashboard.DashboardViewModel
import my.edu.tarc.ass2.Profile.ProfileViewModel
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.User
import my.edu.tarc.ass2.databinding.FragmentBillingBinding
import my.edu.tarc.ass2.databinding.FragmentChangLanguageBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BillingFragment : Fragment() {
    private val billViewModel: BillViewModel by viewModels()
    private var _binding: FragmentBillingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentBillingBinding.inflate(inflater, container, false)

        val current = LocalDateTime.now()
        val ldt = LocalDateTime.parse(current.toString() )
        val formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu", Locale.ENGLISH)
        val output = ldt.format(formatter)
        binding.displayDate.text = output.toString()
        val monthDisplay = current.monthValue
        var yearDisplay =  current.year
        if (monthDisplay == 1){
            yearDisplay -= 1
        }

        lifecycleScope.launch {

            val getPaymentDue = billViewModel.getPaymentDue(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayPaymentDue2.text = getPaymentDue

            val getTotalAmount = billViewModel.getTotalAmount(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayTot.text = getTotalAmount.toString()

            val getCurrentCharges = billViewModel.getCurrentCharges(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayCurrentCharges.text = getCurrentCharges.toString()

            val getOutstandingCharges = billViewModel.getOutstandingCharges(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayOutstanding.text = getOutstandingCharges.toString()

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCheckBill.setOnClickListener(){
            findNavController().navigate(R.id.action_billingFragment_to_billInfoFragment)

        }

    }

}