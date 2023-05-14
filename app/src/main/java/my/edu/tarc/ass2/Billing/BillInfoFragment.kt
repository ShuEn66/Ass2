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
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.Profile.ProfileViewModel
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentBillInfoBinding
import my.edu.tarc.ass2.databinding.FragmentBillingBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BillInfoFragment : Fragment() {
    private val billViewModel: BillViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var sharedPre: SharedPreferences
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
            val loginEmail = sharedPre.getString(getString(R.string.LoginEmail),"")
            val accNo = loginEmail.let { profileViewModel.getAccNumber(it.toString()) }
            val getInvoiceDate = billViewModel.getInvoiceDate(accNo,(monthDisplay-1),yearDisplay)
            binding.displayInvDate.text = getInvoiceDate

            val getPaymentDue = billViewModel.getPaymentDue(accNo,(monthDisplay-1),yearDisplay)
            binding.displayDueDate.text = getPaymentDue

            val getTotalAmount = billViewModel.getTotalAmount(accNo,(monthDisplay-1),yearDisplay)
            binding.displayTot.text = getTotalAmount.toString()

            val getCurrentCharges = billViewModel.getCurrentCharges(accNo,(monthDisplay-1),yearDisplay)
            binding.displayCurrentBill.text = getCurrentCharges.toString()

            val getOverdueCharges = billViewModel.getOverdueCharges(accNo,(monthDisplay-1),yearDisplay)
            binding.displayPrevDue.text = getOverdueCharges.toString()

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)
        binding.buttonPayBill.setOnClickListener(){
            findNavController().navigate(R.id.action_billInfoFragment_to_paymentFragment)

        }

    }
}