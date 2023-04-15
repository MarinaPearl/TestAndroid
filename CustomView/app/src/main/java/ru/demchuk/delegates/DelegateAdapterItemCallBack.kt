package ru.demchuk.delegates

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DelegateAdapterItemCallBack: DiffUtil.ItemCallback<DelegateItem>() {

    override fun areItemsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean {
       return oldItem::class == newItem::class
    }

    override fun areContentsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean {
        return oldItem.compareToOther(newItem)
    }
}