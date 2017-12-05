package com.freeankit.ankitmvvmsample.model

import com.google.gson.annotations.SerializedName

/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
data class Location(var street: String,
                    var city: String,
                    var state: String,
                    @SerializedName("postcode") var zip: Int)