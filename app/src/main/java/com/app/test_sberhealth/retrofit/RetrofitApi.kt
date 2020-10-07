package com.app.test_sberhealth.retrofit

import com.app.test_sberhealth.entities.DrugItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/v3/b168786f-720f-4f30-8fba-a0345d49823a")
    fun getDrugs(): Single<MutableList<DrugItem>>

}