package org.abubaker.listmaker

import android.content.Context
import androidx.preference.PreferenceManager

/**
 * This class will manage the LIST which ListMaker will create
 */
@Suppress("UNCHECKED_CAST") // avoids providing HashSet<*>
class ListDataManager(private val context: Context) {

    // saveLists
    fun saveList(list: TaskList) {

        // Here we are asking to get write-able access to the app's default SharedPreference store
        // This will allow us to write key-value pairs
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()

        // 1. By using list.name we are asking SharedPreferences to use name as a KEY
        // 2. SharePreference cannot store <Arrays> as a String, but it can store a SET as a STRING, thus
        // we will CONVERT our TaskList using .toHashSet() so it can be stored using SharedPreference
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())

        // We are asking SharedPreferences Editor to store changes.
        sharedPreferences.apply()
    }

    // readLists()
    fun readLists(): ArrayList<TaskList> {

        //
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        //
        val sharedPreferenceContents = sharedPreferences.all

        //
        val taskLists = ArrayList<TaskList>()

        //
        for (taskList in sharedPreferenceContents) {
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemsHashSet)

            //
            taskLists.add(list)
        }
        return taskLists
    }

}