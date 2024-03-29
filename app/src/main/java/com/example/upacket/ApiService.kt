package com.example.upacket

import retrofit2.http.GET

interface ApiService {
    @GET("https://www.apiopen.top/novelApi")
    suspend fun getFictions(): BaseResp<List<Fiction>>
}
