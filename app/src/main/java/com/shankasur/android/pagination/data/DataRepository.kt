package com.shankasur.android.pagination.data

import com.shankasur.android.pagination.helper.PaginationList

class DataRepository  : DataSource
{
    override fun getCardList(
        limit: Int,
        skip: Int,
        callback: DataSource.OnResponseCallback<PaginationList<SampleData>>
    ) {
        //API implementation
    }

}