package com.example.divisionsearch_usingretrofil

import retrofit2.Response
import retrofit2.http.GET

interface ResponseApi {
    @GET("division_and_district.json")
    suspend fun getDivision(): Response<DivisionResponse>
}