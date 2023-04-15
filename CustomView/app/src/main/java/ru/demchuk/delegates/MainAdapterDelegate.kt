package ru.demchuk.delegates

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MainAdapterDelegate : ListAdapter<DelegateItem, RecyclerView.ViewHolder>(DelegateAdapterItemCallBack()) {

    private val delegates: MutableList<AdapterDelegate> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return delegates[viewType].onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegates[getItemViewType(position)].onBindViewHolder(holder, getItem(position), position)
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("aaaaaaaaaaaaaaaaaaaaaaaaaa", currentList.toString())
        return delegates.indexOfFirst { it.isOfViewType(currentList[position]) }
    }
    fun addDelegate(delegate: AdapterDelegate) {
        delegates.add(delegate)
    }
}