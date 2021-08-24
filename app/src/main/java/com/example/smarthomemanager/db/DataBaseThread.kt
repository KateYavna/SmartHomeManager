package com.example.smarthomemanager.db



class DataBaseThread :Runnable{

    init {
        Thread(this).start()
    }
    override fun run() {
        val db = App.instance.database
        val currentReadingsDao = db.currentReadingsDao()

       /* val currentReadings = CurrentReadings ("30/07/2021  ","07672")
        currentReadingsDao.insert(currentReadings)*/

    }
}