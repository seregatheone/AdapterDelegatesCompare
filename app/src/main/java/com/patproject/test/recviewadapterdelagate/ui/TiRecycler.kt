package com.patproject.test.recviewadapterdelagate.ui

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.tinkoff.mobile.tech.ti_recycler.TiRecyclerBuilder
import ru.tinkoff.mobile.tech.ti_recycler.base.HolderFactory
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

internal interface TiRecycler<T : ViewTyped> {
    fun setItems(items: List<T>)

    val adapter: BaseAdapter<T>

    companion object {

        @JvmOverloads
        operator fun <T : ViewTyped?> invoke(
            recyclerView: RecyclerView,
            holderFactory: HolderFactory,
            diffCallback: DiffUtil.ItemCallback<T>? = null,
            init: TiRecyclerBuilder<T>.() -> Unit = {}
        ): TiRecycler<T> {
            return TiRecyclerBuilderImpl(
                holderFactory = holderFactory,
                diffCallback = diffCallback
            )
                .apply(init)
                .build(recyclerView)
        }
    }