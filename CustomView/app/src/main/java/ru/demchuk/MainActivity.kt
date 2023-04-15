package ru.demchuk

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.demchuk.customview.databinding.ActivityMainBinding
import ru.demchuk.delegates.MainAdapterDelegate
import ru.demchuk.delegates.concatenateWithDate
import ru.demchuk.delegates.date.DateDelegateAdapter
import ru.demchuk.delegates.date.DateModel
import ru.demchuk.delegates.message.MessageDelegateAdapter
import ru.demchuk.delegates.message.MessageModel
import ru.demchuk.model.Message
import ru.demchuk.notifyApi.NotifyMessageAdapter
import ru.demchuk.notifyApi.NotifyViewModel


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    // private val listMessage: NotifyViewModel by viewModels()
    // private lateinit var notifyMessageAdapter: NotifyMessageAdapter
    private val adapterDelegate: MainAdapterDelegate by lazy { MainAdapterDelegate() }

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            adapterDelegate.apply {
                addDelegate(MessageDelegateAdapter())
                addDelegate(DateDelegateAdapter())
            }
            binding.recycler.adapter = adapterDelegate
            adapterDelegate.submitList(stubMessageList.concatenateWithDate(stubDatesList))
        } catch (error: Exception) {
            error.printStackTrace()
        }
//        val flexbox = binding.message.children.last() as FlexBoxLayout
//        flexbox.children.forEach { child ->
//            child.setOnClickListener {
//                it.isSelected = !it.isSelected
//                val reaction = it as ReactionView
//                ++reaction.count
//            }
//        }


    }

    companion object {

        const val TAG = "DelegatesFragment TAG"

        private const val JUL_5 = "5 июля"
        private const val SEP_1 = "1 сенятбря"
        private const val SEP_12 = "12 сенятбря"
        private const val DEC_7 = "7 декабря"

        private val stubDatesList = listOf(
            DateModel(
                id = 1,
                date = SEP_1,
            ),
            DateModel(
                id = 2,
                date = SEP_12,
            ),
            DateModel(
                id = 3,
                date = JUL_5,
            ),
            DateModel(
                id = 4,
                date = DEC_7,
            ),
        )

        private val stubMessageList = listOf(
            MessageModel(1, 1, "1", "Ваня", "Привет", JUL_5),
            MessageModel(2, 2, "2", "Катя", "Привет", SEP_12),
            MessageModel(3, 3, "3", "Даша", "Привет", JUL_5),
            MessageModel(4, 4, "4", "Саша", "Привет", SEP_1),
            MessageModel(5, 5, "5", "Лиза", "Привет", DEC_7),
            MessageModel(6, 6, "6", "Вася", "Привет", JUL_5),
            MessageModel(7, 7, "7", "Надя", "Привет", DEC_7),
            MessageModel(8, 8, "8", "Вероника", "Привет", SEP_12),
            MessageModel(9, 9, "9", "Ярик", "Привет", SEP_1)
        )
    }
}