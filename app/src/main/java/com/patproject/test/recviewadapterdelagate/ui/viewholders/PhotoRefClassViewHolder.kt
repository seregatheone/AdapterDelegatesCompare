package com.patproject.test.recviewadapterdelagate.ui.viewholders

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import com.patproject.test.api.models.PhotoRefClass
import com.patproject.test.api.models.PostClass
import com.patproject.test.recviewadapterdelagate.databinding.ReferenceItemLayoutBinding
import com.patproject.test.recviewadapterdelagate.databinding.TextItemLayoutBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder

class PhotoRefClassViewHolder(
    private val view: View,private val context : Context
) : BaseViewHolder<PhotoRefClass>(view) {

    private val binding = ReferenceItemLayoutBinding.bind(view)

    override fun bind(item: PhotoRefClass) {
        binding.imageIdRef.text = item.id.toString()
        binding.urlRef.text = item.url
        binding.refLayout.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
            context.startActivity(intent)
        }
    }
}