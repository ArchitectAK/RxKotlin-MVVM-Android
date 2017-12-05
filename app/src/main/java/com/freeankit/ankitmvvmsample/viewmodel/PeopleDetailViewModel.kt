package com.freeankit.ankitmvvmsample.viewmodel

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.freeankit.ankitmvvmsample.model.People

/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
class PeopleDetailViewModel(private val people: People) {


    fun getFullUserName(): String {
        people.fullName = people.name.title + "." + people.name.first + " " + people.name.last
        return people.fullName!!
    }

    fun getUserName(): String {
        return people.login.username
    }

    fun getEmail(): String {
        return people.email
    }

    fun getEmailVisibility(): Int {
        return if (people.hasEmail()) View.VISIBLE else View.GONE
    }

    fun getCell(): String {
        return people.cell
    }

    fun getPictureProfile(): String {
        return people.picture.large
    }

    fun getAddress(): String {
        return (people.location.street
                + " "
                + people.location.city
                + " "
                + people.location.state)
    }

    fun getGender(): String {
        return people.gender
    }

    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        Glide.with(view.context).load(imageUrl).into(view)
    }
}