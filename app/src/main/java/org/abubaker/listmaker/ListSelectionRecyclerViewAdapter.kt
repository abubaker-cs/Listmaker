package org.abubaker.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter(
    val lists: ArrayList<TaskList>,
    val clickListener: ListSelectionRecyclerViewClickListener
) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    // Reason: We will create a new interface that our Activity can implement,
    // Then viewHolder can easily inform RecyclerView of any taps
    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: TaskList)
    }

    // onCreate
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent, false)

        return ListSelectionViewHolder(view)
    }

    // onBind - UI components through ViewHolder
    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position + 1).toString()
        holder.listTitle.text = lists[position].name
        // holder.listTitle.text = lists.get(position).name | replaced with indexing operation

        // on tap listener
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }

    }

    // Total items ?
    override fun getItemCount(): Int {
        return lists.size
    }

    // addList()
    fun addList(list: TaskList) {

        // Update the ArrayList with the new item provided by the user
        lists.add(list)

        // We are informing the Adapter that we have updated the data source (ArrayList),
        // so it can update the RecyclerView.
        notifyItemInserted(lists.size - 1)
    }

}
