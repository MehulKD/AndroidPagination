package com.shankasur.android.pagination

import com.shankasur.android.pagination.base.BasePresenter
import com.shankasur.android.pagination.base.BaseView
import com.shankasur.android.pagination.data.SampleData

interface CardListingContract
{
    interface View : BaseView<Presenter> {

        fun displayCards(cards: List<SampleData>, totalItemCount: Int)

        fun noCardsFound()

        fun showError(message: String)

    }

    interface Presenter : BasePresenter {

        fun getCards(skip: Int, limit: Int)

    }
}