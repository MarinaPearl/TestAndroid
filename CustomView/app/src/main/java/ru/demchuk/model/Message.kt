package ru.demchuk.model

data class Message(
    var id_message: Int,
    var id_user: Int,
    var avatar: String,
    var name: String,
    var message: String
)
