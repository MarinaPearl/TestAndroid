package ru.demchuk.delegates.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.demchuk.customview.R
import ru.demchuk.customview.databinding.CustomViewGroupReactionBinding
import ru.demchuk.delegates.AdapterDelegate
import ru.demchuk.delegates.DelegateItem

class MessageDelegateAdapter : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
         return  ViewHolder(CustomViewGroupReactionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int
    ) {
        (holder as ViewHolder).bind(item.content() as MessageModel)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is MessageDelegate

    inner class ViewHolder(private val binding: CustomViewGroupReactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: MessageModel) {
            with(binding.viewGroup) {
                avatar.setImageResource(R.drawable.white_person_48)
                message.text = model.message
                name.text = model.name
            }
        }
    }
}