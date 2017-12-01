package com.freeankit.ankitmvvmsample.data

import com.freeankit.ankitmvvmsample.model.People
import com.google.gson.annotations.SerializedName


/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
data class PeopleResponse(@SerializedName("results") var peopleList: List<People>)