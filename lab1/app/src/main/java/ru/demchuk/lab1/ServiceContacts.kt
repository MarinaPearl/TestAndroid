package ru.demchuk.lab1

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.database.Cursor
import android.os.IBinder
import android.provider.ContactsContract
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class ServiceContacts : Service() {

    private val listContacts = ArrayList<String>()


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

    @SuppressLint("Range")
    private fun getContacts() {
        val contentResolver = contentResolver
        val cursor: Cursor? =
            contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {

                val contact: String =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                listContacts.add(contact)
            }
            cursor.close()
        }
    }

    private fun sendMessage() {
        val intent = Intent("get-contacts")
        intent.putStringArrayListExtra("message", listContacts)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

}