package com.freeankit.ankitmvvmsample

import android.app.Application
import android.content.Context
import com.freeankit.ankitmvvmsample.data.PeopleFactory
import com.freeankit.ankitmvvmsample.data.PeopleService
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
class PeopleApplication : Application() {

    private var peopleService: PeopleService? = null
    private var scheduler: Scheduler? = null

    private operator fun get(context: Context): PeopleApplication {
        return context.applicationContext as PeopleApplication
    }

    companion object {
        fun create(context: Context): PeopleApplication {
            return context as PeopleApplication
        }

    }

    fun getPeopleService(): PeopleService {
        if (peopleService == null) {
            peopleService = PeopleFactory.create()
        }

        return peopleService as PeopleService
    }

    fun subscribeScheduler(): Scheduler {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }

        return scheduler as Scheduler
    }

    fun setPeopleService(peopleService: PeopleService) {
        this.peopleService = peopleService
    }

    fun setScheduler(scheduler: Scheduler) {
        this.scheduler = scheduler
    }
}