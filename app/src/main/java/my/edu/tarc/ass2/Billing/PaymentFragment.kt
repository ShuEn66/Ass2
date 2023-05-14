package my.edu.tarc.ass2.Billing

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Query
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.Bill
import my.edu.tarc.ass2.Payment
import my.edu.tarc.ass2.Profile.ProfileViewModel
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.FragmentBillHistoryBinding
import my.edu.tarc.ass2.databinding.FragmentPaymentBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Calendar
import java.util.Date

class PaymentFragment : Fragment() {
    private val billViewModel: BillViewModel by viewModels()
    private var _binding: FragmentPaymentBinding? = null
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var sharedPre: SharedPreferences
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)
            binding.buttonDone.setOnClickListener(){
                Toast.makeText(context,R.string.donePayment, Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_paymentFragment_to_billingFragment)

            }

            lifecycleScope.launch {
                val loginEmail = sharedPre.getString(getString(R.string.LoginEmail),"")
                val accNo = loginEmail.let { profileViewModel.getAccNumber(it.toString()) }
                val current = LocalDateTime.now()
                val ldt = LocalDateTime.parse(current.toString())
                val formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu", Locale.ENGLISH)
                val monthDisplay = current.monthValue
                var yearDisplay = current.year
                val timeDisplay = current.toLocalTime()
                if (monthDisplay == 1) {
                    yearDisplay -= 1
                }
                val output = ldt.format(formatter)
                val getTotalAmount = billViewModel.getTotalAmount(accNo , (monthDisplay - 1), yearDisplay)
                binding.displayAmountPaid.text = getTotalAmount.toString()

                val newPayment1 = Payment("111111111111111", output, "Successful", "Online Banking", getTotalAmount)
                billViewModel.setPaymentDetails(newPayment1)
                val getPaymentDate = billViewModel.getPaymentDate(111111111111111)
                binding.displayPaymentDate2.text = getPaymentDate

                binding.displayTimePayment.text = timeDisplay.toString()
                billViewModel.updateBillStatus()

                val getPaymentStatus = billViewModel.getPaymentStatus(111111111111111)
                binding.displayStatus.text = getPaymentStatus

                val getPaymentMethod = billViewModel.getPaymentMethod(111111111111111)
                binding.displayPM.text = getPaymentMethod

                billViewModel.updateCurrentChanges()
                billViewModel.updateOutstandingChanges()
                billViewModel.updateTotalAmount()

            }


        }

}