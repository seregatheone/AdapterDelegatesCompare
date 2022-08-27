package com.patproject.test.recviewadapterdelagate.ui

import android.content.Context
import android.view.View
import com.patproject.test.recviewadapterdelagate.R
import com.patproject.test.recviewadapterdelagate.ui.viewholders.PhotoClassViewHolder
import com.patproject.test.recviewadapterdelagate.ui.viewholders.PhotoRefClassViewHolder
import com.patproject.test.recviewadapterdelagate.ui.viewholders.PostClassViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.HolderFactory

class RecyclerHolderFactory (private val context : Context) : HolderFactory {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.picture_item_layout -> PhotoClassViewHolder(view,context)
            R.layout.reference_item_layout -> PhotoRefClassViewHolder(view,context)
            R.layout.text_item_layout -> PostClassViewHolder(view)
            else -> null
        }
    }
}