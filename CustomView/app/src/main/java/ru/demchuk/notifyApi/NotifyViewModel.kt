package ru.demchuk.notifyApi

import androidx.lifecycle.ViewModel
import ru.demchuk.listMessage

class NotifyViewModel: ViewModel() {
    val listMutableMessage = listMessage
}