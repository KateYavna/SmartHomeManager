package com.example.smarthomemanager.settings

import android.content.Context

class SharedPrefManager (val context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    private val keaPower = "220 V"
    private val kea = arrayListOf<String>("0","1","2","3","4","5","6","7","8","9")

    fun setSwitch  ( position: Boolean){
        prefs.edit().putBoolean(keaPower,position).apply()
    }
    fun getSwitch () :Boolean {
        val position:Boolean =
            if (prefs.getBoolean(keaPower,false) != null)
                prefs.getBoolean(keaPower,false)!!
            else false
        return position

    }
    fun setText  ( kea :String, text: String){
        prefs.edit().putString(kea,text).apply()
    }
    fun getText (kea: String) :String {
        val text:String =
            if (prefs.getString(kea,"input task") != null)
                prefs.getString(kea,"input task")!!
            else "input task"
        return text

    }


}