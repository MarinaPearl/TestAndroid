package ru.demchuk.lab1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class ServiceContacts : Service() {

    private var listContacts: Int = 0


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val taskInService = Thread {
            getContacts()
            sendMessage()
        }.start()
        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun getContacts() {
        listContacts = 5
    }

    private fun sendMessage() {
        val intent = Intent("get-contacts")
        intent.putExtra("message", "This is my message!")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

}