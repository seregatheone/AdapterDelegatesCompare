package com.patproject.test.recviewadapterdelagate.ui

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.patproject.test.api.models.DisplayableItem

class AdapterDelegate(context : Context) : ListDelegationAdapter<List<DisplayableItem>>(
    photoAdapterDelegate(context),
    photoRefAdapterDelegate(context),
    textAdapterDelegate()
)