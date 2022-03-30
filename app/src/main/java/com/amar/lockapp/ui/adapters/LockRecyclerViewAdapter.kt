package com.amar.lockapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amar.lockapp.R
import com.amar.lockapp.helper.Utils.Companion.setHighLightedText
import com.amar.lockapp.model.Lock


class LockRecyclerViewAdapter(private var dataSet: List<Lock>, var query: String) : RecyclerView.Adapter<LockRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.lock_listview_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val lock = dataSet.get(position)

        viewHolder.nameTV.text = String.format("Name: %s", lock.name)
        viewHolder.typeTV.text = String.format("Type: %s", lock.type)
        viewHolder.roomNumberTV.text = String.format("Room number: %s", lock.roomNumber)
        viewHolder.serialNumberTV.text = String.format("Serial number: %s", lock.serialNumber)

        setHighLightedText(viewHolder.nameTV, query)
    }


    override fun getItemCount() = dataSet.size

    fun setItems(list: List<Lock>, q: String) {
        query = q
        dataSet = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTV: TextView
        val typeTV: TextView
        val roomNumberTV: TextView
        val serialNumberTV: TextView

        init {
            nameTV = view.findViewById(R.id.nameTV)
            typeTV = view.findViewById(R.id.typeTV)
            roomNumberTV = view.findViewById(R.id.roomNumberTV)
            serialNumberTV = view.findViewById(R.id.serialNumberTV)
        }
    }

}