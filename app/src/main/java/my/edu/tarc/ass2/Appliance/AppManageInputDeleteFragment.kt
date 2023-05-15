package my.edu.tarc.ass2.Appliance

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
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
import my.edu.tarc.ass2.databinding.FragmentAppManageInputDeleteBinding


class AppManageInputDeleteFragment : Fragment() {

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

    private var _binding: FragmentAppManageInputDeleteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppManageInputDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Get user email
        sharedPre=requireActivity().getPreferences(Context.MODE_PRIVATE)

        binding.buttonDelete.setOnClickListener{
            lifecycleScope.launch {
                if (binding.editTextDelete.text.toString().isEmpty()){
                    Toast.makeText(context,"Invalid delete", Toast.LENGTH_SHORT).show()
                } else {
                    appliancesViewModel.deleteAppliances(binding.editTextDelete.text.toString())
                    Toast.makeText(context,"Appliance deleted", Toast.LENGTH_SHORT).show()
                    //findNavController().navigate(R.id.action_appManageInputDeleteFragment_to_appManageAddedFragment)

                    //Clear
                    appliancesArrayList.clear()
                    recyclerView?.adapter?.notifyDataSetChanged()

                    //Update view
                    val layoutManager = LinearLayoutManager(context)
                    recyclerView = binding.recyclerViewApp
                    recyclerView.layoutManager = layoutManager
                    recyclerView.setHasFixedSize(true)
                    adapter = MyAdapter(appliancesArrayList, findNavController())
                    recyclerView.adapter = adapter
                }
            }


        }

        binding.buttonCancel.setOnClickListener{
            findNavController().navigate(R.id.action_appManageInputDeleteFragment_to_appManageAddedFragment)
        }

        //To display added appliances
        dataInitializer()

        val layoutManager = LinearLayoutManager(context)
        recyclerView = binding.recyclerViewApp
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MyAdapter(appliancesArrayList, findNavController())
        recyclerView.adapter = adapter

    }

    //To display appliances
    private fun dataInitializer(){
        appliancesArrayList = arrayListOf<AddedAppliance>()

        //Get data list
        lifecycleScope.launch {
            val userEmail = sharedPre.getString(getString(R.string.LoginEmail),"").toString()
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

    }

}