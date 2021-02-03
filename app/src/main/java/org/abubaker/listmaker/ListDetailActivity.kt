package org.abubaker.listmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListDetailActivity : AppCompatActivity() {

    // It will store the data received through Bundle() from the MainActivity
    lateinit var list: TaskList

    // It will be a reference to our RecyclerView in our XML file
    lateinit var listItemsRecyclerView: RecyclerView

    // onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load @res/layout/activity_list_detail.xml file
        setContentView(R.layout.activity_list_detail)

        // By using the KEY we are asking the Bundle to provide us the variable and store it in the local object: "list"
        list = intent.getParcelableExtra<TaskList>(MainActivity.INTENT_LIST_KEY) as TaskList

        // We will be using the "name" variable form the Bundle as the TITLE of our new Activity in the appbar
        title = list.name

        // Reference to ID list_items_recyclerview in the activity_list_detail.xml file
        listItemsRecyclerView = findViewById(R.id.list_items_recyclerview)

        // Pass the list to our custom Adapter, as it needs to know the list so it can tell the RecyclerView what tasks to show
        listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)

        // We are asking the LinearLayoutManager to handle the presentational responsibilities
        listItemsRecyclerView.layoutManager = LinearLayoutManager(this)
    }


}