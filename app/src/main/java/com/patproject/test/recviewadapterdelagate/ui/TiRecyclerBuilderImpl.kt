package com.patproject.test.recviewadapterdelagate.ui

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.tinkoff.mobile.tech.ti_recycler.BaseTiRecycler
import ru.tinkoff.mobile.tech.ti_recycler.TiRecyclerBuilder
import ru.tinkoff.mobile.tech.ti_recycler.adapters.BaseTiAdapter
import ru.tinkoff.mobile.tech.ti_recycler.adapters.SimpleTiAdapter
import ru.tinkoff.mobile.tech.ti_recycler.base.HolderFactory
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

internal class TiRecyclerBuilderImpl<T : ViewTyped>(
    override val adapter: BaseTiAdapter<T, HolderFactory>
) : TiRecyclerBuilder<T, HolderFactory, BaseTiRecycler<T,HolderFactory>> {

    constructor(
        holderFactory: HolderFactory,
        diffCallback: DiffUtil.ItemCallback<T>? = null
    ) : this(diffCallback?.run {
        BaseTiAdapter(holderFactory, this)
    } ?: SimpleTiAdapter(
        holderFactory
    ))

    override val itemDecoration: MutableList<RecyclerView.ItemDecoration> = mutableListOf()
    override var layoutManager: RecyclerView.LayoutManager? = null
    override var hasFixedSize: Boolean = true

    override fun build(recyclerView: RecyclerView): TiRecycler<T> {
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            layoutManager ?: recyclerView.layoutManager ?: LinearLayoutManager(recyclerView.context)

        itemDecoration.forEach(recyclerView::addItemDecoration)
        recyclerView.setHasFixedSize(hasFixedSize)
        return TiRecyclerImpl(recyclerView, adapter)
    }
}