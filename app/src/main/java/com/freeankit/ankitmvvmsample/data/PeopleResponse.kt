package com.freeankit.ankitmvvmsample.data

import com.freeankit.ankitmvvmsample.model.People
import com.google.gson.annotations.SerializedName


/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
class PeopleResponse {
    @SerializedName("results") private var peopleList: List<People>? = null

    fun getPeopleList(): List<People>? {
        return peopleList
    }

    fun setPeopleList(mPeopleList: List<People>) {
        this.peopleList = mPeopleList
    }
}