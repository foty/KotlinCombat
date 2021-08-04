package com.example.upacket

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Create by lxx
 * Date : 2020/12/22 17:15
 * Use by
 */
class NetModel {

    suspend fun getData(): List<Fiction> {
        return withContext(Dispatchers.IO) {
            RetrofitFactory.instance
                .getService(ApiService::class.java)
                .getFictions()
                .dataConvert()
        }
    }
}