package com.freeankit.ankitmvvmsample.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.freeankit.ankitmvvmsample.R
import com.freeankit.ankitmvvmsample.databinding.ItemPeopleBinding
import com.freeankit.ankitmvvmsample.model.People
import com.freeankit.ankitmvvmsample.viewmodel.ItemPeopleViewModel

/**
 *@author by Ankit Kumar (ankitdroiddeveloper@gmail.com) on 12/1/17 (MM/DD/YYYY )
 **/
class PeopleAdapter(private var peopleList: List<People> = emptyList()) : RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapterViewHolder {
        val itemPeopleBinding: ItemPeopleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_people,
                parent, false)
        return PeopleAdapterViewHolder(itemPeopleBinding)
    }

    override fun onBindViewHolder(holder: PeopleAdapterViewHolder, position: Int) {
        holder.bindPeople(peopleList[position])
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    fun setPeopleList(peopleList: List<People>) {
        this.peopleList = peopleList
        notifyDataSetChanged()
    }

    class PeopleAdapterViewHolder(private var mItemPeopleBinding: ItemPeopleBinding) : RecyclerView.ViewHolder(mItemPeopleBinding.itemPeople) {

        internal fun bindPeople(people: People) {
            if (mItemPeopleBinding.peopleViewModel == null) {
                mItemPeopleBinding.peopleViewModel = ItemPeopleViewModel(people, itemView.context)
            } else {
                mItemPeopleBinding.peopleViewModel?.setPeople(people)
            }
        }
    }
}