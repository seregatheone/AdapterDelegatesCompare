package com.patproject.test.recviewadapterdelagate.ui.viewholders

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.patproject.test.api.models.PhotoClass
import com.bumptech.glide.request.target.Target
import com.patproject.test.recviewadapterdelagate.databinding.PictureItemLayoutBinding
import com.patproject.test.recviewadapterdelagate.databinding.ReferenceItemLayoutBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder

class PhotoClassViewHolder(
    private val view: View, private val context : Context
) : BaseViewHolder<PhotoClass>(view) {

    private val binding = PictureItemLayoutBinding.bind(view)

    override fun bind(item: PhotoClass) {
        binding.title.text = item.title
        Glide.with(context)
            .load(item.url)
            .addListener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.i("GlideExceptionGlide",e.toString())
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .dontAnimate()
            .dontTransform()
            .into(binding.imageIcon)
        binding.imageId.text = item.id.toString()
    }
}