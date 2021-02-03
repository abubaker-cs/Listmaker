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

        // Setup for AlertDialog
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->

                // We are fetching user's provided data throught the textField
                val task = taskEditText.text.toString()

                // Retrieved data is being used to create a new task
                list.tasks.add(task)

                // We are informing the Adapter that a new item has been added
                // This will enforce RecyclerView to update its rows
                val recyclerAdapter =
                    listItemsRecyclerView.adapter as ListSelectionRecyclerViewAdapter

                recyclerAdapter.notifyItemInserted(list.tasks.size - 1)

                // After adding new tasks, we are closing the AlertDialog by dismissing it
                dialog.dismiss()

            }

    }


}