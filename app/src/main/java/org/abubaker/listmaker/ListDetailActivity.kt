package org.abubaker.listmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ListDetailActivity : AppCompatActivity() {

    // onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load @res/layout/activity_list_detail.xml file
        setContentView(R.layout.activity_list_detail)

    }


}