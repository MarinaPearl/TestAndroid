package ru.demchuk.notifyApi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.demchuk.customview.R
import ru.demchuk.customview.databinding.CustomViewGroupReactionBinding
import ru.demchuk.model.Message

class NotifyMessageAdapter(private val messages: MutableList<Message>) :
    RecyclerView.Adapter<NotifyMessageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            CustomViewGroupReactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(messages[position]) {
            messages.remove(messages[position])
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, messages.size)
        }
    }

    inner class ViewHolder(_binding: CustomViewGroupReactionBinding) :
        RecyclerView.ViewHolder(_binding.root) {

        private var binding = CustomViewGroupReactionBinding.bind(itemView)

        fun bind(model: Message, action: () -> Unit) {
            with(binding.viewGroup) {
                message.text = model.message
                name.text = model.name
                avatar.setImageResource(R.drawable.white_person_48)
            }
            binding.viewGroup.setOnClickListener {
                action()
            }
        }
    }
}