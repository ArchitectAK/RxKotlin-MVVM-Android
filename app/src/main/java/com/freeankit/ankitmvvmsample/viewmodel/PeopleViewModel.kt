package com.freeankit.ankitmvvmsample.viewmodel

import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.freeankit.ankitmvvmsample.PeopleApplication
import com.freeankit.ankitmvvmsample.R
import com.freeankit.ankitmvvmsample.data.PeopleFactory
import com.freeankit.ankitmvvmsample.model.People
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*

/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
class PeopleViewModel(private val context: Context) : Observable() {

    var peopleProgress: ObservableInt
    var peopleRecycler: ObservableInt
    var peopleLabel: ObservableInt
    var messageLabel: ObservableField<String>

    private val peopleList: MutableList<People>
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    init {

        this.peopleList = ArrayList()
        peopleProgress = ObservableInt(View.GONE)
        peopleRecycler = ObservableInt(View.GONE)
        peopleLabel = ObservableInt(View.VISIBLE)
        messageLabel = ObservableField(context.getString(R.string.default_loading_people))
    }


    fun onClickFabLoad(arg0: View) {
        initializeViews()
        fetchPeopleList()
    }

    //It is "public" to show an example of test
    private fun initializeViews() {
        peopleLabel.set(View.GONE)
        peopleRecycler.set(View.GONE)
        peopleProgress.set(View.VISIBLE)
    }

    private fun fetchPeopleList() {

        val peopleApplication = PeopleApplication().create(context)
        val peopleService = peopleApplication.getPeopleService()

        val disposable = peopleService.fetchPeople(PeopleFactory().RANDOM_USER_URL)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ PeopleResponse ->
                    changePeopleDataSet(PeopleResponse.getPeopleList()!!)
                    peopleProgress.set(View.GONE)
                    peopleLabel.set(View.GONE)
                    peopleRecycler.set(View.VISIBLE)
                }, {
                    messageLabel.set(context.getString(R.string.error_loading_people))
                    peopleProgress.set(View.GONE)
                    peopleLabel.set(View.VISIBLE)
                    peopleRecycler.set(View.GONE)
                })

        compositeDisposable!!.add(disposable)
    }

    private fun changePeopleDataSet(peoples: List<People>) {
        peopleList.addAll(peoples)
        setChanged()
        notifyObservers()
    }

    fun getPeopleList(): List<People> {
        return peopleList
    }

    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    fun reset() {
        unSubscribeFromObservable()
    }
}