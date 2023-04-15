package ru.demchuk.delegates.message

import ru.demchuk.delegates.DelegateItem

class MessageDelegate(
    private val id: Int,
    private val value: MessageModel
) : DelegateItem {
    override fun content(): Any = value

    override fun id() = id

    override fun compareToOther(other: DelegateItem): Boolean {
       return (other as MessageDelegate) == content()
    }
}