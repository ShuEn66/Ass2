package my.edu.tarc.ass2.Appliance

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.ass2.AddedAppliance
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.Registration.MyAdapter

class CalAdapter(private val appliancesList : ArrayList<AddedAppliance>, private val navController: NavController):RecyclerView.Adapter<CalAdapter.DeleteViewHolder>() {

    interface onButtonClickListener {
        fun onButtonClick(position: Int)
    }
    private var buttonClickListener: MyAdapter.onButtonClickListener? = null
    fun setOnButtonClickListener(listener: MyAdapter.onButtonClickListener) {
        buttonClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_app_cal, parent, false)
        return DeleteViewHolder(itemView, buttonClickListener)
    }

    override fun getItemCount(): Int {
        return appliancesList.size
    }
    override fun onBindViewHolder(holder: DeleteViewHolder, position: Int) {
        val currentItem = appliancesList[position]
        holder.textView1.setText(currentItem.appliance)
        holder.textView2.setText((currentItem.appUsage).toString())
        holder.textView3.setText((currentItem.appCost).toString())

    }

    class DeleteViewHolder(itemView: View, private val buttonClickListener: MyAdapter.onButtonClickListener?):
        RecyclerView.ViewHolder(itemView){
        //val applianceName : Button = itemView.findViewById(R.id.addedApp)
        val textView1: TextView = itemView.findViewById(R.id.textTableApp1)
        val textView2: TextView = itemView.findViewById(R.id.textTableAppUsage1)
        val textView3: TextView = itemView.findViewById(R.id.textTableAppCost1)

    }
}