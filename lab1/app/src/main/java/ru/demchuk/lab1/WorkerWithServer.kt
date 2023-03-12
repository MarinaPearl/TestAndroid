package ru.demchuk.lab1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class WorkerWithServer : AppCompatActivity() {

    private var message: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_with_server)
        val intentFilter = IntentFilter("get-contacts")
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, intentFilter)
    }

    fun onClickStartService(view: View) {
        startService(Intent(this, ServiceContacts::class.java))
    }

    private val messageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            message = intent.getStringArrayListExtra("message")
            stopService()
            sendMessageInFirstActivity()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver)
    }

    private fun stopService() {
        stopService(Intent(this, ServiceContacts::class.java))
    }

    private fun sendMessageInFirstActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putStringArrayListExtra("message", message)
        startActivity(intent)
    }
}