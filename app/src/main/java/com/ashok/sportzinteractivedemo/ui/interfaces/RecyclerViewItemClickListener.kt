package com.ashok.sportzinteractivedemo.ui.interfaces

import android.app.Person
import com.ashok.sportzinteractivedemo.model.Player

interface RecyclerViewItemClickListener {
    fun onItemClicked(item: Player)
}