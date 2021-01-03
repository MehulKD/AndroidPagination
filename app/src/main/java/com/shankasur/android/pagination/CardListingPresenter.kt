package com.shankasur.android.pagination

import com.shankasur.android.pagination.base.ErrorWrapper
import com.shankasur.android.pagination.data.DataRepository
import com.shankasur.android.pagination.data.DataSource
import com.shankasur.android.pagination.data.SampleData
import com.shankasur.android.pagination.helper.PaginationList

class CardListingPresenter constructor(private var view: CardListingContract.View?, private val dataRepo: DataRepository) :
    CardListingContract.Presenter {
    override fun getCards(skip: Int, limit: Int) {
        view?.showProgressIndicator()
        //Hit API call
        dataRepo.getCardList(
            limit,
            skip,
            object : DataSource.OnResponseCallback<PaginationList<SampleData>> {
                override fun onSuccess(obj: PaginationList<SampleData>) {
                    view?.dismissProgressIndicator()
                    if (obj.metaData!!.totalItemsCount == 0)
                        view?.noCardsFound()
                    else {
                        view?.displayCards(obj,obj.metaData!!.totalItemsCount)
                    }
                }

                override fun onError(error: ErrorWrapper) {
                    view?.dismissProgressIndicator()
                    view?.showError(error.errorMessage)
                }

            })
    }
    override fun start() {
    }

    override fun detachView() {
        view = null
    }

}