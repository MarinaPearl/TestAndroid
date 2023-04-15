package ru.demchuk.delegates

import ru.demchuk.delegates.date.DateDelegate
import ru.demchuk.delegates.date.DateModel
import ru.demchuk.delegates.message.MessageDelegate
import ru.demchuk.delegates.message.MessageModel

fun List<MessageModel>.concatenateWithDate(dates: List<DateModel>): List<DelegateItem> {
    val delegateItemList: MutableList<DelegateItem> = mutableListOf()

    dates.forEach { dateModel ->

        // Сразу добавляем в список дату
        delegateItemList.add(
            DateDelegate(id = dateModel.id, value = dateModel)
        )

        val date = dateModel.date
        // Выбираем покупки по дате
        val allDayExpenses = this.filter { expense ->
            expense.date == date
        }
        // Добавляем эти покупки
        allDayExpenses.forEach { model ->
            delegateItemList.add(
                MessageDelegate(
                    id = model.id_message,
                    value = model,
                )
            )
        }
    }
    return delegateItemList
}