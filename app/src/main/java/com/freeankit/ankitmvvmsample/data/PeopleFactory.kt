package com.freeankit.ankitmvvmsample.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
class PeopleFactory {

    companion object {

        val RANDOM_USER_URL = "http://api.randomuser.me/?results=10&nat=en"

        val BASE_URL = "http://api.randomuser.me/"
        val PROJECT_URL = "https://github.com/AnkitDroidGit/Ankit_MVVM_Sample"
        fun create(): PeopleService {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(PeopleService::class.java)
        }
    }


}
