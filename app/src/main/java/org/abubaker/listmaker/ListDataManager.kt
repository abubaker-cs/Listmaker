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

        // Here we are asking to get access to WRITE to the app's default SharedPreference store
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

        // Here we are asking to get READ-ONLY access to the app's default SharedPreference store
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        // Get store's content as a MAP Object (i.e Dictionary, as it will contain UNIQUE keys)
        val sharedPreferenceContents = sharedPreferences.all

        // We are creating taskLists (an empty ArrayList) that will be used to store retrieved data
        val taskLists = ArrayList<TaskList>()

        for (taskList in sharedPreferenceContents) {

            // CONVERSION: Value will be casted as a HashSet<String>
            // as TaskList cannot be directly stored as a String
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)

            // We will recreate the TaskList by passing the KEY and the Value
            val list = TaskList(taskList.key, itemsHashSet)

            // We will add content from list to the taskLists (array)
            taskLists.add(list)
        }

        // Return the contents of taskLists to the caller of the method.
        return taskLists
    }

}