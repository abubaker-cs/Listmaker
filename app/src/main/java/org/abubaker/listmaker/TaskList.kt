package org.abubaker.listmaker

import android.content.Context
import android.preference.Preference
import android.preference.PreferenceManager

// This is the model for the list, which we will be using throughout the app
class TaskList(val name: String, val tasks: ArrayList<Any> = java.util.ArrayList()) {

}

@Suppress("DEPRECATION")
class ListDataManager(private val context: Context) {

    // saveLists
    fun saveList(list: TaskList) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putString(list.name, list.tasks.toHashSet().toString())
        sharedPreferences.apply()
    }

    // readLists()
    fun readLists(): ArrayList<TaskList> {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferenceContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()

        for (taskList in sharedPreferenceContents) {
            val itemsHashSet = ArrayList(taskList.value as HashSet<*>)
            val list = TaskList(taskList.key, itemsHashSet)

            taskLists.add(list)
        }
        return taskLists
    }

}