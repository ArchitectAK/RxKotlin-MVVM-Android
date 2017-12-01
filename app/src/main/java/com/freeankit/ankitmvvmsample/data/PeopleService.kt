package com.freeankit.ankitmvvmsample.data

import retrofit2.http.GET
import retrofit2.http.Url
import io.reactivex.Observable

/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
interface PeopleService {
    @GET
    fun fetchPeople(@Url url: String): Observable<PeopleResponse>
}