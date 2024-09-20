package com.example.divisionsearch_usingretrofil

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.divisionsearch_usingretrofil.databinding.AdapterDivisionBinding


class DivisionAdapter(
    private val divisionList: ArrayList<DivisionResponseItem>,


    ) : RecyclerView.Adapter<DivisionAdapter.ViewHolder>() {
    class ViewHolder(var binding: AdapterDivisionBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        var listener: ItemClickListener? = null
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterDivisionBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val division = divisionList[position]
        viewHolder.binding.tvEmployeeDivisionName.text = division.name
        viewHolder.itemView.setOnClickListener {
            listener?.onItemClick(division)
        }
    }

    override fun getItemCount(): Int {
        return divisionList.size
    }

    interface ItemClickListener {
        fun onItemClick(division: DivisionResponseItem)

    }


}