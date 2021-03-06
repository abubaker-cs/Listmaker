package org.abubaker.listmaker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(),
    ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    // This will hold the ListDataManager
    val listDataManager: ListDataManager = ListDataManager(this)

    lateinit var listsRecyclerView: RecyclerView

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

        // We are now passing in the activity as a listener
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
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

            // Forward the "new list" to the new Activity using Intent (Bundle)
            showListDetail(list)

        }

        // Initialize the Dialog
        builder.create().show()
    }

    // Prepare Intent : Markup for sending DATA through Bundle
    private fun showListDetail(list: TaskList) {

        // We are providing CURRENT context, and defining the Activity where we will like to navigate
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)

        // Receiving Activity will be able to reference our provided data using INTENT_LIST_KEY
        // list will be the ArrayList<> that we will forward
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)

        // We are asking to now move to the targeted Activity, i.e. ListDetailActivity
        // startActivity(listDetailIntent)

        startActivityForResult(listDetailIntent, LIST_DETAIL_REQUEST_CODE)
    }

    // Confirming to the newly assigned interface
    // ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener in the MainActivity
    override fun listItemClicked(list: TaskList) {
        showListDetail(list)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LIST_DETAIL_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                listDataManager.saveList(data.getParcelableExtra(INTENT_LIST_KEY) as TaskList)
                updateLists()
            }
        }
    }

    private fun updateLists() {
        val lists = listDataManager.readLists()
        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)
    }

    // We are using "companion object" so our data can be placed inside the Bundle
    companion object {
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 123
    }
}