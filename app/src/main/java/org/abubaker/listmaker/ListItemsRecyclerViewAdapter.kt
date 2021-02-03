package org.abubaker.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListItemsRecyclerViewAdapter(var list: TaskList) :
    RecyclerView.Adapter<ListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.task_view_holder, parent, false)
        return ListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {

        // Using the reference from ListITemViewHolder.kt file we are binding a specific task to
        // the list generated using #id: textview_task depending on the position of the viewHolder
        holder.taskTextView.text = list.tasks[position]

    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }


}