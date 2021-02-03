package org.abubaker.listmaker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // It will hold the reference to the ID textview_task from the task_view_holder.xml file
    val taskTextView = itemView.findViewById(R.id.textview_task) as TextView

}