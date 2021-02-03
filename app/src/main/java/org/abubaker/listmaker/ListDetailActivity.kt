package org.abubaker.listmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ListDetailActivity : AppCompatActivity() {

    // It will store the data received through Bundle() from the MainActivity
    lateinit var list: TaskList

    // onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load @res/layout/activity_list_detail.xml file
        setContentView(R.layout.activity_list_detail)

        // By using the KEY we are asking the Bundle to provide us the variable and store it in the local object: "list"
        list = intent.getParcelableExtra<TaskList>(MainActivity.INTENT_LIST_KEY) as TaskList

        // We will be using the "name" variable form the Bundle as the TITLE of our new Activity in the appbar
        title = list.name
    }


}