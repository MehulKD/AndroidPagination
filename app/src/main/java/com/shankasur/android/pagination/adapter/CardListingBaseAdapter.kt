package com.shankasur.android.pagination.adapter

import androidx.recyclerview.widget.RecyclerView


abstract class CardListingBaseAdapter<VH : CardListingBaseViewHolder> : RecyclerView.Adapter<VH>() {

    abstract fun removeItemAndRefresh(id: String)
}