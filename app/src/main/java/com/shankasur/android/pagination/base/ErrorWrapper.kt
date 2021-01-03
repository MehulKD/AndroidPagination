package com.shankasur.android.pagination.base

import com.shankasur.android.pagination.helper.MetaData

interface ErrorWrapper {
    val errorStatusCode: Int
    val errorMessage: String
    val errorMessages: Map<String?, List<String?>?>?
    val errorStatus: String?
    val errorCode: String?
    val header: MetaData?
}
