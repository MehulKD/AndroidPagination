package com.shankasur.android.pagination.base

interface BaseView<T> where T : BasePresenter {

    fun showProgressIndicator()

    fun dismissProgressIndicator()

    fun showGenericError(errorMsg: String)
}