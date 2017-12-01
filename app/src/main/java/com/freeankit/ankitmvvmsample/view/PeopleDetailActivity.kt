package com.freeankit.ankitmvvmsample.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import com.freeankit.ankitmvvmsample.R
import com.freeankit.ankitmvvmsample.databinding.PeopleDetailActivityBinding
import com.freeankit.ankitmvvmsample.model.People
import com.freeankit.ankitmvvmsample.viewmodel.PeopleDetailViewModel

/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
class PeopleDetailActivity : AppCompatActivity() {


    private var peopleDetailActivityBinding: PeopleDetailActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        peopleDetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.people_detail_activity)
        setSupportActionBar(peopleDetailActivityBinding!!.toolbar)
        displayHomeAsUpEnabled()
        getExtrasFromIntent()
    }

    companion object {
        private val EXTRA_PEOPLE = "EXTRA_PEOPLE"

        fun launchDetail(context: Context, people: People): Intent {
            val intent = Intent(context, PeopleDetailActivity::class.java)
            intent.putExtra(EXTRA_PEOPLE, people as Parcelable)
            return intent
        }
    }

    private fun displayHomeAsUpEnabled() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getExtrasFromIntent() {
        val people = intent.getSerializableExtra(EXTRA_PEOPLE) as People
        val peopleDetailViewModel = PeopleDetailViewModel(people)
        peopleDetailActivityBinding?.peopleDetailViewModel = peopleDetailViewModel
        title = people.name.title + "." + people.name.first + " " + people.name.last
    }
}