package ru.demchuk.delegates.message

data class MessageModel(
    var id_message: Int,
    var id_user: Int,
    var avatar: String,
    var name: String,
    var message: String,
    var date : String
)