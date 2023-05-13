package my.edu.tarc.ass2.Billing

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
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentBillInfoBinding
import my.edu.tarc.ass2.databinding.FragmentBillingBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BillInfoFragment : Fragment() {
    private val billViewModel: BillViewModel by viewModels()
    private var _binding: FragmentBillInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBillInfoBinding.inflate(inflater, container, false)

        val current = LocalDateTime.now()
        val monthDisplay =  current.monthValue
        var yearDisplay =  current.year
        if (monthDisplay == 1){
            yearDisplay -= 1
        }

        lifecycleScope.launch {

            val getInvoiceDate = billViewModel.getInvoiceDate(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayInvDate.text = getInvoiceDate

            val getPaymentDue = billViewModel.getPaymentDue(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayDueDate.text = getPaymentDue

            val getTotalAmount = billViewModel.getTotalAmount(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayTot.text = getTotalAmount.toString()

            val getCurrentCharges = billViewModel.getCurrentCharges(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayCurrentBill.text = getCurrentCharges.toString()

            val getOverdueCharges = billViewModel.getOverdueCharges(123412341111,(monthDisplay-1),yearDisplay)
            binding.displayPrevDue.text = getOverdueCharges.toString()

        }

        return binding.root
    }


}