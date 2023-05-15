package my.edu.tarc.ass2.Billing

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
import my.edu.tarc.ass2.Billing.BillHistory

class BillHAdapter(private val billList : ArrayList<BillHistory>, private val navController: NavController):RecyclerView.Adapter<BillHAdapter.BillViewHolder>() {

    interface onButtonClickListener {
        fun onButtonClick(position: Int)
    }
    private var buttonClickListener: BillHAdapter.onButtonClickListener? = null
    fun setOnButtonClickListener(listener: BillHAdapter.onButtonClickListener) {
        buttonClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bill_history_holder, parent, false)
        return BillViewHolder(itemView, buttonClickListener)
    }

    override fun getItemCount(): Int {
        return billList.size
    }
    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val currentItem = billList[position]
        holder.textViewDate.setText(currentItem.payment)
        holder.textViewRM.setText((currentItem.amount).toString())

    }

    class BillViewHolder(itemView: View, private val buttonClickListener: BillHAdapter.onButtonClickListener?):
        RecyclerView.ViewHolder(itemView){
        //val applianceName : Button = itemView.findViewById(R.id.addedApp)
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        val textViewRM: TextView = itemView.findViewById(R.id.textViewRM)

    }
}