package org.abubaker.listmaker

// This is the model for the list, which we will be using throughout the app
class TaskList(
    val name: String,
    val tasks: ArrayList<String> = ArrayList()
) {

}