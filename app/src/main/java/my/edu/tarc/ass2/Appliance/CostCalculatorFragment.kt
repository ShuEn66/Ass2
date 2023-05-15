package my.edu.tarc.ass2.Appliance

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Delete
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.AddedAppliance
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.Appliance.CalAdapter
import my.edu.tarc.ass2.Registration.MyAdapter
import my.edu.tarc.ass2.databinding.FragmentConfirmDeleteDialogBinding
import my.edu.tarc.ass2.databinding.FragmentCostCalculatorBinding

class CostCalculatorFragment : Fragment() {
    //View model
    private val appliancesViewModel: AppliancesViewModel by viewModels()

    private var _binding: FragmentCostCalculatorBinding? = null

    //RecyclerView for added appliances
    private lateinit var adapter: CalAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var appliancesArrayList: ArrayList<AddedAppliance>
    lateinit var applianceName : Array<String>
    lateinit var appliances : Array<String>
    private val profileViewModel: AppliancesViewModel by viewModels()
    private lateinit var sharedPre: SharedPreferences

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCostCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)
        binding.imageViewHelpButton.setOnClickListener{
            findNavController().navigate(R.id.action_costCalculatorFragment_to_calHelpDialogFragment)
        }

        binding.buttonAdvice.setOnClickListener{
            findNavController().navigate(R.id.action_costCalculatorFragment_to_calAdviceDialogFragment)
        }

        //val stringArray = resources.getStringArray(R.array.calculatorPeriodical)
        //binding.textCostCalculator2.text = stringArray[1]

        //To display added appliances
        dataInitializer()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = binding.calRecyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = CalAdapter(appliancesArrayList, findNavController())
        recyclerView.adapter = adapter
    }

    private fun dataInitializer(){
        appliancesArrayList = arrayListOf<AddedAppliance>()

        //Current user


        //Get data list
        lifecycleScope.launch {
            val userEmail = sharedPre.getString(getString(R.string.LoginEmail),"").toString()
            appliancesViewModel.getAllAppliances(userEmail).observe(viewLifecycleOwner, Observer { appliancesList ->

                var totalkwh = 0.0
                var rate = 0.0
                var totalCost = 0.0
                var dRate = 0.0

                fun calculateTotalAmount(dRate: Double) {

                    appliancesArrayList.clear()
                    totalCost = 0.0
                    totalkwh = 0.0
                    //Display 1 by 1
                    for (i in appliancesList.indices) {
                        //Calculate
                        val usage:Double = String.format("%.2f", appliancesList[i].EstimatedUsage).toDouble()
                        val power = String.format("%.2f", appliancesList[i].AppliancesPower).toDouble()
                        val kwh = (power * usage)/1000
                        var cost = 0.0

                        totalkwh += kwh
                        if(totalkwh<=200.00){
                            rate = 0.2180
                        } else if (200.00<totalkwh && totalkwh<=300.00){
                            rate = 0.3340
                        } else if (300.00<totalkwh && totalkwh<=600){
                            rate = 0.5160
                        } else{
                            rate = 0.5460
                        }
                        cost = (totalkwh * rate)*dRate
                        totalCost += cost

                        //Display
                        val appliances = AddedAppliance(appliancesList[i].AppliancesName, usage, String.format("%.2f", cost).toDouble())
                        appliancesArrayList.add(appliances)
                    }

                    binding.textTableTotalNumber.text = (String.format("%.2f", totalCost).toDouble()).toString()

                    // Set the adapter after the data is fetched
                    adapter = CalAdapter(appliancesArrayList, findNavController())
                    recyclerView.adapter = adapter

                }

                dRate = 0.033
                calculateTotalAmount(dRate)

                //If daily / monthly / yearly
                binding.spinnerPeriodical.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (binding.spinnerPeriodical.selectedItemPosition == 0){
                            dRate = 0.033
                        } else if (binding.spinnerPeriodical.selectedItemPosition == 1){
                            dRate = 1.0
                        } else {
                            dRate = 12.17
                        }
                        calculateTotalAmount(dRate)
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        dRate = 0.033
                        calculateTotalAmount(dRate)
                    }
                }
            })
        }
    }
}