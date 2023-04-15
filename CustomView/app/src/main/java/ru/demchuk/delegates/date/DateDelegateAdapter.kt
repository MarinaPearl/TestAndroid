package ru.demchuk.delegates.date

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.demchuk.customview.databinding.DateBinding
import ru.demchuk.delegates.AdapterDelegate
import ru.demchuk.delegates.DelegateItem

class DateDelegateAdapter : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(DateBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int
    ) {
        (holder as ViewHolder).bind(item.content() as DateModel)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is DateDelegate

    class ViewHolder(private val binding: DateBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: DateModel) {
            binding.date.text = model.date
        }
    }
}