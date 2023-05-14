package my.edu.tarc.ass2.Registration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.ass2.AddedAppliance
import my.edu.tarc.ass2.R

//Old code using a AddedAppliance array list
class MyAdapter(private val appliancesList : ArrayList<AddedAppliance>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

//New code
//class MyAdapter(var appliancesArrayList: List<AddedAppliance>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_app_added, parent, false)
        return MyViewHolder(itemView)
    }

    //New code
    /*fun updateData(newData: List<AddedAppliance>) {
        appliancesArrayList = newData
        notifyDataSetChanged()
    }
     */

    //old code
    override fun getItemCount(): Int {
        return appliancesList.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = appliancesList[position]
        holder.applianceName.setText(currentItem.appliance)
    }


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val applianceName : Button = itemView.findViewById(R.id.addedApp)
    }

}