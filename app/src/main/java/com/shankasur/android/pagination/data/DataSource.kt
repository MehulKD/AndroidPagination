package com.shankasur.android.pagination.data

import com.shankasur.android.pagination.base.ErrorWrapper
import com.shankasur.android.pagination.helper.PaginationList

interface DataSource
{
    interface OnResponseCallback<T> {
        fun onSuccess(obj: T)
        fun onError(error: ErrorWrapper)
    }

    fun getCardList(limit: Int, skip: Int, callback: OnResponseCallback<PaginationList<SampleData>>)
}