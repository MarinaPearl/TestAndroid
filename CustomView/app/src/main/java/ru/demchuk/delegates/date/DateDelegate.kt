package ru.demchuk.delegates.date

import ru.demchuk.delegates.DelegateItem

class DateDelegate(
    private val value: DateModel,
    private val id: Int
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as DateDelegate).value == content()
    }
}