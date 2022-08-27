package com.patproject.test.recviewadapterdelagate.ui.viewholders

import android.view.View
import com.patproject.test.api.models.PostClass
import com.patproject.test.recviewadapterdelagate.databinding.TextItemLayoutBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder

class PostClassViewHolder(
    view: View
) : BaseViewHolder<PostClass>(view) {

    private val binding = TextItemLayoutBinding.bind(view)

    override fun bind(item: PostClass) {
        binding.idText.text = item.id.toString()
        binding.bodyText.text = item.body
        binding.titleText.text = item.title
        binding.userIdText.text = item.userId.toString()
    }
}