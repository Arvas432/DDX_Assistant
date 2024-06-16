package com.example.ddxassistant.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ddxassistant.databinding.CalendarListItemBinding
import com.example.ddxassistant.domain.model.CalendarItemPojo
import com.example.ddxassistant.ui.viewHolders.CalendarItemViewHolder

class CalendarAdapter(private val days: List<CalendarItemPojo>, private val currentDate: String, private val action:() -> Unit): RecyclerView.Adapter<CalendarItemViewHolder>() {
    private var selectedPos = RecyclerView.NO_POSITION
    init {
        selectedPos = days.indexOf(days.filter { it.date == currentDate}[0])
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return CalendarItemViewHolder(CalendarListItemBinding.inflate(layoutInspector, parent, false))
    }

    override fun getItemCount(): Int {
        return days.size
    }

    override fun onBindViewHolder(holder: CalendarItemViewHolder, position: Int) {
        holder.bind(days[position])
        holder.itemView.isSelected = (selectedPos == position)
        holder.itemView.setOnClickListener {
            notifyItemChanged(selectedPos)
            selectedPos = holder.layoutPosition
            notifyItemChanged(selectedPos)
            action
        }
    }
    fun getSelectedDay(): Int{
        return selectedPos+1
    }

}