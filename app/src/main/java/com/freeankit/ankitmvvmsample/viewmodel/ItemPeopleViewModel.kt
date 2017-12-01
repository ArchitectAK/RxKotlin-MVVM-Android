package com.freeankit.ankitmvvmsample.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.freeankit.ankitmvvmsample.model.People
import com.freeankit.ankitmvvmsample.view.PeopleDetailActivity

/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
class ItemPeopleViewModel(private val people: People, private val context: Context) : BaseObservable() {


    fun getFullName(): String {
        people.name = people.name.title + "." + people.name.first + " " + people.name.last
        return people.name
    }

    fun getCell(): String {
        return people.cell
    }

    fun getMail(): String {
        return people.email
    }

    fun getPictureProfile(): String {
        return people.picture.medium
    }

    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

    fun onItemClick(view: View) {
        context.startActivity(PeopleDetailActivity.launchDetail(view.context, people))
    }

    fun setPeople(people: People) {
        this.people = people
        notifyChange()
    }
}