package org.abubaker.listmaker

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var listsRecyclerView: RecyclerView

    // This will hold the ListDataManager
    val listDataManager: ListDataManager = ListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            showCreateListDialog()
        }

        val lists = listDataManager.readLists()
        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)

        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // showCreateListDialog()
    private fun showCreateListDialog() {

        // Retrieving values from @res/values/strings.xml
        val dialogTitle = getString(R.string.name_of_list)
        val createButtonTitle = getString(R.string.create_list)

        // Dialog Title
        val builder = AlertDialog.Builder(this)

        // Inserting a inputField to collect data from the user
        val listTitleEditText = EditText(this)

        // Defining the TYPE of inputField so proper KEYBOARD can be shown
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        // Implement Dialog Title
        builder.setTitle(dialogTitle)

        // Implement InputField
        builder.setView(listTitleEditText)

        // onClick Listener for createButtonTitle
        builder.setPositiveButton(createButtonTitle) { dialog, _ ->

            val list = TaskList(listTitleEditText.text.toString())
            listDataManager.saveList(list)

            val recyclerAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
            recyclerAdapter.addList(list)

            // We will later on add code here to preserve user's submitted data.
            dialog.dismiss()

        }

        // Initialize the Dialog
        builder.create().show()
    }

    // Prepare Intent
    private fun showListDetail(list: TaskList) {

        // We are providing CURRENT context, and defining the Activity where we will like to navigate
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)

        // Receiving Activity will be able to reference our provided data using INTENT_LIST_KEY
        // list will be the ArrayList<> that we will forward
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)

        // We are asking to now move to the targeted Activity, i.e. ListDetailActivity
        startActivity(listDetailIntent)
    }

}