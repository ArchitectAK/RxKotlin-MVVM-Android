package com.freeankit.ankitmvvmsample.viewmodel

import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.util.Log
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
class PeopleViewModelKotlin(private val context: Context) : Observable() {

    var peopleProgress: ObservableInt = ObservableInt(View.GONE)
    var peopleRecycler: ObservableInt = ObservableInt(View.GONE)
    var peopleLabel: ObservableInt = ObservableInt(View.VISIBLE)
    var messageLabel: ObservableField<String> = ObservableField(context.getString(R.string.default_loading_people))

    private val peopleList: MutableList<People> = ArrayList()
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()


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

//    private fun fetchPeopleList() {
//
//        val peopleApplication = PeopleApplication().create(context)
//        val peopleService = peopleApplication.getPeopleService()
//
//        val disposable = peopleService.fetchPeople(PeopleFactory().RANDOM_USER_URL)
//                .subscribeOn(peopleApplication.subscribeScheduler())
////                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ response ->
//                    kotlin.run {
//                        changePeopleDataSet(response.results)
//                        peopleProgress.set(View.GONE)
//                        peopleLabel.set(View.GONE)
//                        peopleRecycler.set(View.VISIBLE)
//                    }
//                }, { error ->
//                    kotlin.run {
//                        messageLabel.set(context.getString(R.string.error_loading_people))
//                        peopleProgress.set(View.GONE)
//                        peopleLabel.set(View.VISIBLE)
//                        peopleRecycler.set(View.GONE)
//                        Log.d("EJP", error.message)
//                    }
//                })
//
//        compositeDisposable?.add(disposable)
//    }


    private fun fetchPeopleList() {

        val peopleApplication = PeopleApplication().create(context)
        val peopleService = peopleApplication.getPeopleService()

        val disposable = peopleService.fetchPeople(PeopleFactory().RANDOM_USER_URL)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ peopleResponse ->
                    Log.d("SJP", peopleResponse.results.toString())
                    peopleProgress.set(View.GONE)
                    peopleLabel.set(View.GONE)
                    peopleRecycler.set(View.VISIBLE)
                    changePeopleDataSet(peopleResponse.results.toMutableList())
                }, { throwable ->
                    messageLabel.set(context.getString(R.string.error_loading_people))
                    peopleProgress.set(View.GONE)
                    peopleLabel.set(View.VISIBLE)
                    peopleRecycler.set(View.GONE)
                    Log.d("EJP", throwable.message)
                })

        compositeDisposable?.add(disposable)
    }

    private fun changePeopleDataSet(peoples: MutableList<People>) {
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
        compositeDisposable = null
    }
}