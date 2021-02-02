package org.abubaker.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter(private val lists: ArrayList<TaskList>) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    val listTitles = arrayOf(
        "Shopping List",
        "Chores",
        "Android Tutorials",
        "Ann Chovey",
        "Barry Cuda",
        "Barry Mundy",
        "John Dory",
        "Murray Cod",
        "Coral Trout",
        "Red Salmon",
        "Tiger Prawn",
        "Rock Cod",
        "Rock Lobster",
        "Ray Manta",
        "E.L. Moray",
        "Hazel Nutt",
        "Chris P. Bacon",
        "Chris P. Creem",
        "Marsha Mellow",
        "Barb Akew",
        "Pete Tsar",
        "Marge Areen",
        "Violet Crumb-Ball",
        "Rosemary Lamb",
        "Cesar Salad",
        "Frank Furter",
        "Polly N. Satcherayted",
        "Des E. Kaytedcoconut"
    )

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
        holder.listTitle.text = lists.get(position).name
    }

    // Total items ?
    override fun getItemCount(): Int {
        return lists.size
    }

    // addList()
    fun addList(list: TaskList) {

        lists.add(list)

        notifyItemInserted(lists.size - 1)
    }

}
