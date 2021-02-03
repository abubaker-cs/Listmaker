package org.abubaker.listmaker

import android.os.Parcel
import android.os.Parcelable

// This is the model for the list, which we will be using throughout the app
class TaskList constructor(val name: String, val tasks: ArrayList<String> = ArrayList()) : Parcelable {

    // Reading from the Parcel
    // Read received data, and create a local ArrayList<> of Strings
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.createStringArrayList()!!
    )

    //
    override fun describeContents() = 0

    // Write to the Parcel
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeStringList(tasks)
    }

    // ISSUE: Kotlin does not have "static" method, so we will create a "companion object"
    // ==================================================================================
    // We cannot create a static method in Kotlin, as it is done in Java using:
    // public static Parcelable.Creator<T> CREATOR so, to meet the meet the protocol's requirements,
    // we will instead create a "companion object" in kotlin and override appropriate functions.
    companion object CREATOR : Parcelable.Creator<TaskList> {

        // Receiver: our source
        override fun createFromParcel(source: Parcel): TaskList = TaskList(source)

        // Sender: new Array
        override fun newArray(size: Int): Array<TaskList?> = arrayOfNulls(size)

    }


}