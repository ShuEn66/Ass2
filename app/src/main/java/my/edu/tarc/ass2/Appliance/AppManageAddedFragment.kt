package my.edu.tarc.ass2.Appliance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.ass2.AddedAppliance
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.Registration.MyAdapter
import my.edu.tarc.ass2.databinding.FragmentAppManageAddedBinding

class AppManageAddedFragment : Fragment() {

    //RecyclerView for added appliances
    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var appliancesArrayList: ArrayList<AddedAppliance>
    lateinit var applianceName : Array<String>
    lateinit var appliances : Array<String>

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
        dataInitializer()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerViewApp)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MyAdapter(appliancesArrayList)
        recyclerView.adapter = adapter
    }

    //To display appliances
    private fun dataInitializer(){
        appliancesArrayList = arrayListOf<AddedAppliance>()
        //Get string name
        applianceName = arrayOf(
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
        }
    }

}