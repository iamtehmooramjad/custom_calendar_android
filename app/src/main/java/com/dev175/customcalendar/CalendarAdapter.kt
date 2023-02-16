package com.dev175.customcalendar


import com.dev175.customcalendar.databinding.ItemCellBinding
import javax.inject.Inject

class CalendarAdapter @Inject constructor() :
    BaseRecyclerAdapter<CalendarItem, ItemCellBinding>() {
    override val layout: Int = R.layout.item_cell

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemCellBinding>,
        position: Int
    ) {
        holder.binding.data = items[position]
        holder.itemView.setOnClickListener {
            listener?.invoke(holder.itemView, items[position], position)
        }
    }

}