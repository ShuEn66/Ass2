package my.edu.tarc.ass2.Registration

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.ass2.AddedAppliance
import my.edu.tarc.ass2.R


class MyAdapter(private val appliancesList : ArrayList<AddedAppliance>, private val navController: NavController):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    //Button
    interface onButtonClickListener {
        fun onButtonClick(position: Int)
    }
    private var buttonClickListener: onButtonClickListener? = null
    fun setOnButtonClickListener(listener: onButtonClickListener) {
        buttonClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_app_added, parent, false)
        return MyViewHolder(itemView, buttonClickListener)
    }

    override fun getItemCount(): Int {
        return appliancesList.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = appliancesList[position]
        holder.applianceName.setText(currentItem.appliance)
        holder.applianceName.isAllCaps = false
    }

    class MyViewHolder(itemView: View, private val buttonClickListener: onButtonClickListener?):RecyclerView.ViewHolder(itemView){
        val applianceName : Button = itemView.findViewById(R.id.addedApp)
        init {
            applianceName.setOnClickListener {
                Log.d("MyAdapter", "Button clicked at position $adapterPosition")
                buttonClickListener?.onButtonClick(adapterPosition)
            }
        }
    }



}