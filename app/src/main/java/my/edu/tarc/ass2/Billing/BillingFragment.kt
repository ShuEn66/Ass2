package my.edu.tarc.ass2.Billing

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.AddedAppliance
import my.edu.tarc.ass2.Appliance.CalAdapter
import my.edu.tarc.ass2.Bill
import my.edu.tarc.ass2.Dashboard.DashboardViewModel
import my.edu.tarc.ass2.Profile.ProfileViewModel
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.Registration.MyAdapter
import my.edu.tarc.ass2.User
import my.edu.tarc.ass2.databinding.FragmentBillingBinding
import my.edu.tarc.ass2.databinding.FragmentChangLanguageBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BillingFragment : Fragment() {
    private val billViewModel: BillViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private var _binding: FragmentBillingBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPre: SharedPreferences

    //RecyclerView
    private lateinit var adapter: BillHAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var billArrayList: ArrayList<BillHistory>
    lateinit var paymentDate: Array<String>
    lateinit var amount: Array<Double>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre = requireActivity().getPreferences(Context.MODE_PRIVATE)
        binding.buttonCheckBill.setOnClickListener() {
            findNavController().navigate(R.id.action_billingFragment_to_billInfoFragment)
        }

        /*binding.buttonBillH.setOnClickListener(){
            findNavController().navigate(R.id.action_billingFragment_to_billHistoryFragment)
        }*/

        val current = LocalDateTime.now()
        val ldt = LocalDateTime.parse(current.toString())
        val formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu", Locale.ENGLISH)
        val output = ldt.format(formatter)
        binding.displayDate.text = output.toString()
        val monthDisplay = current.monthValue
        var yearDisplay = current.year
        if (monthDisplay == 1) {
            yearDisplay -= 1
        }

        billArrayList = arrayListOf<BillHistory>()
        lifecycleScope.launch {
            val loginEmail = sharedPre.getString(getString(R.string.LoginEmail), "")
            val accNo = loginEmail.let { profileViewModel.getAccNumber(it.toString()) }
            billViewModel.getAllBill(accNo).observe(viewLifecycleOwner, Observer { billList ->
                for (i in billList.indices) {

                    if (billList[i].BillStatus == "Paid") {
                        val invoice: String = billList[i].InvoiceDate
                        val amount: Double =
                            String.format("%.2f", billList[i].TotalAmount).toDouble()
                        val bill = BillHistory(invoice, amount)
                        billArrayList.add(bill)
                    }

                }
                // Set the adapter after the data is fetched
                adapter = BillHAdapter(billArrayList, findNavController())
                recyclerView.adapter = adapter
            })


            if (billViewModel.getBillStatus(accNo, (monthDisplay - 1), yearDisplay) == "Paid") {
                binding.buttonCheckBill.isEnabled = false
                val getPaymentDue =
                    billViewModel.getPaymentDue(accNo, (monthDisplay - 1), yearDisplay)
                binding.displayPaymentDue2.text = "-"
                val getCurrentCharges =
                    billViewModel.getCurrentCharges(accNo, (monthDisplay - 1), yearDisplay)
                binding.displayCurrentCharges.text = getCurrentCharges.toString()
                val getOutstandingCharges =
                    billViewModel.getOutstandingCharges(accNo, (monthDisplay - 1), yearDisplay)
                binding.displayOutstanding.text = getOutstandingCharges.toString()
                val getTotalAmount =
                    billViewModel.getTotalAmount(accNo, (monthDisplay - 1), yearDisplay)
                binding.displayTot1.text = getTotalAmount.toString()
            } else {
                binding.buttonCheckBill.isEnabled = true

                val getPaymentDue =
                    billViewModel.getPaymentDue(accNo, (monthDisplay - 1), yearDisplay)
                binding.displayPaymentDue2.text = getPaymentDue

                val getTotalAmount =
                    billViewModel.getTotalAmount(accNo, (monthDisplay - 1), yearDisplay)
                binding.displayTot1.text = getTotalAmount.toString()

                val getCurrentCharges =
                    billViewModel.getCurrentCharges(accNo, (monthDisplay - 1), yearDisplay)
                binding.displayCurrentCharges.text = getCurrentCharges.toString()

                val getOutstandingCharges =
                    billViewModel.getOutstandingCharges(accNo, (monthDisplay - 1), yearDisplay)
                binding.displayOutstanding.text = getOutstandingCharges.toString()
            }


        }

        //recycler
        val layoutManager = LinearLayoutManager(context)
        recyclerView = binding.recyclerViewBillH
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = BillHAdapter(billArrayList, findNavController())
        recyclerView.adapter = adapter

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBillingBinding.inflate(inflater, container, false)

        return binding.root
    }
}
