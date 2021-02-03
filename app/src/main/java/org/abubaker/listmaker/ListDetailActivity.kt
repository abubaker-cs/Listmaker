package org.abubaker.listmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListDetailActivity : AppCompatActivity() {

    // It will store the data received through Bundle() from the MainActivity
    lateinit var list: TaskList

    // It will be a reference to our RecyclerView in our XML file
    lateinit var listItemsRecyclerView: RecyclerView

    //
    lateinit var addTaskButton: FloatingActionButton

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

        // FAB
        addTaskButton = findViewById(R.id.add_task_button)
        addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }
    }

    // Custom Dialog
    private fun showCreateTaskDialog() {

        // Inserting a inputField to collect data from the user
        val taskEditText = EditText(this)

        // Defining the TYPE of inputField so proper KEYBOARD can be shown
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->

                val task = taskEditText.text.toString()
                list.tasks.add(task)

                val recyclerAdapter =
                    listItemsRecyclerView.adapter as ListSelectionRecyclerViewAdapter

                recyclerAdapter.notifyItemInserted(list.tasks.size - 1)

                // We will later on add code here to preserve user's submitted data.
                dialog.dismiss()

            }


    }


}