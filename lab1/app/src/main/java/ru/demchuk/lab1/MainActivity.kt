package ru.demchuk.lab1


import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_READ_CONTACTS = 1
    private var READ_CONTACTS_GRANTED = false


    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val hasReadContactPermission =
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            READ_CONTACTS_GRANTED = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_READ_CONTACTS
            )
        }
        if (READ_CONTACTS_GRANTED) {
            setListContacts()
        }
    }

    fun onClickStart(view: View) {
        val intent = Intent(this, WorkerWithServer::class.java)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_READ_CONTACTS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    READ_CONTACTS_GRANTED = true
                }
            }
        }
        if (READ_CONTACTS_GRANTED) {
            setListContacts()
        } else {
            Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show()
        }
    }

    private fun setListContacts() {
        val listContacts = findViewById<ListView>(R.id.listContacts)
        val intent = intent
        val list = intent.getStringArrayListExtra("message")
        val adapter =
            list?.let { ArrayAdapter(this, android.R.layout.simple_list_item_1, it.toList()) }
        listContacts.adapter = adapter
    }
}