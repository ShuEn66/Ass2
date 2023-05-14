package my.edu.tarc.ass2.Appliance

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import my.edu.tarc.ass2.AddedAppliance
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.Registration.MyAdapter
import my.edu.tarc.ass2.databinding.FragmentAppManageAddedBinding
import androidx.lifecycle.observe

class AppManageAddedFragment : Fragment() {

    //View model
    private val appliancesViewModel: AppliancesViewModel by viewModels()

    //To get user email
    private val profileViewModel: AppliancesViewModel by viewModels()
    private lateinit var sharedPre: SharedPreferences

    //RecyclerView for added appliances
    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var appliancesArrayList: ArrayList<AddedAppliance>
    lateinit var applianceName : Array<String>
    lateinit var appliances : Array<String>

    //Button
    private val onButtonClickListener = object : MyAdapter.onButtonClickListener {
        override fun onButtonClick(position: Int) {
            // Handle button click here
        }
    }

    private var _binding: FragmentAppManageAddedBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppManageAddedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Get user email
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)
        val loginEmail = sharedPre.getString(getString(R.string.LoginEmail),"")

        binding.buttonDelete.setOnClickListener{
            findNavController().navigate(R.id.action_appManageAddedFragment_to_appliancesFragment)
        }

        binding.buttonAddNew.setOnClickListener{
            findNavController().navigate(R.id.action_appManageAddedFragment_to_appManageAddNewFragment)
        }

        //To display added appliances
        dataInitializer()

        val layoutManager = LinearLayoutManager(context)
        recyclerView = binding.recyclerViewApp
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MyAdapter(appliancesArrayList, findNavController())
        recyclerView.adapter = adapter

        //Button action
        val onButtonClickListener = object : MyAdapter.onButtonClickListener {
            override fun onButtonClick(position: Int) {
                // Handle button click here
                //Toast.makeText(context,"Clicked", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_appManageAddedFragment_to_appManageDetailsFragment)
            }
        }

        //Button
        // Set the onButtonClickListener to the adapter
        adapter = MyAdapter(appliancesArrayList, findNavController())
        adapter.setOnButtonClickListener(onButtonClickListener)
        recyclerView.adapter = adapter


    }

    //To display appliances
    private fun dataInitializer(){
        appliancesArrayList = arrayListOf<AddedAppliance>()

        //Current user
        val userEmail = "123412341111"

        //Get data list
        lifecycleScope.launch {
            appliancesViewModel.getAllAppliances(userEmail).observe(viewLifecycleOwner, Observer { appliancesList ->

                //Display 1 by 1
                for (i in appliancesList.indices) {
                    val appliances = AddedAppliance(appliancesList[i].AppliancesName)
                    appliancesArrayList.add(appliances)
                }

                // Set the adapter after the data is fetched
                adapter = MyAdapter(appliancesArrayList, findNavController())
                recyclerView.adapter = adapter

            })
        }

        //Old code
        //Get string name
        /*applianceName = arrayOf(
            getString(R.string.appliances_cal_ok),
            getString(R.string.appliances_cal_ok),
            getString(R.string.appliances_cal_ok),
            getString(R.string.appliances_cal_ok),
            getString(R.string.appliances_cal_ok),
            getString(R.string.appliances_cal_ok)
        )

        for (i in applianceName.indices){
            val appliances = AddedAppliance(applianceName[i])
            appliancesArrayList.add(appliances)
        }*/
    }

}