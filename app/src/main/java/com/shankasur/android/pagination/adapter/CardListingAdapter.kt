package com.shankasur.android.pagination.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shankasur.android.pagination.R

class CardListingAdapter(private val context: Context, private val dataList: MutableList<Any>) : CardListingBaseAdapter<CardListingAdapter.CardViewHolder>()
{


    inner class CardViewHolder(view: View) : CardListingBaseViewHolder(view)
    {
        //add your stuff here
        private val context = view.context
        val title: TextView =view.findViewById(R.id.title)
    }

    override fun removeItemAndRefresh(id: String) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_card_listing, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        //set your data
    }

    fun updateList(updateList: List<Any>) {
        val oldSize = itemCount
        dataList.addAll(updateList)
        notifyItemRangeInserted(oldSize, itemCount)
    }

    override fun getItemCount(): Int {
       return dataList.size
    }
}