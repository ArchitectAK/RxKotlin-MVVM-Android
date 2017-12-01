package com.freeankit.ankitmvvmsample.model

/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
data class People(var gender: String, var name: Name, var location: Location, var email: String, var login: Login, var phone: String, var cell: String, var picture: Picture) {
    lateinit var fullName: String
    fun hasEmail(): Boolean {
        return !email.isEmpty()
    }
}