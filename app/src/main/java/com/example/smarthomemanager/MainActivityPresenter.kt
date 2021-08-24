package com.example.smarthomemanager

import android.util.Log
import com.example.smarthomemanager.fragments.*
import com.example.smarthomemanager.network.API
import com.example.smarthomemanager.network.ResponseMain
import com.example.smarthomemanager.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter (val mainView: MainActivityContract.MainView): MainActivityContract.Presenter{

    override fun getDbData() {
        TODO("Not yet implemented")
    }


    override fun getServerData() {
        var retrofit =
            RetrofitClient.getClient("https://api.coindesk.com/").create(API::class.java)
                         retrofit.getCurrentPrice().enqueue(object : Callback<ResponseMain> {
            override fun onResponse(call: Call<ResponseMain>, response: Response<ResponseMain>) {
                Log.d("MyLog", "220 V") }

            override fun onFailure(call: Call<ResponseMain>, t: Throwable) {
                Log.d("MyLog", "failure") }
        })

    }

    override fun showLightFragment() {
        val fragment = LightFragment.newInstance("", "")
        mainView.setFragment(fragment)
        fragment.setPresenter(this)
    }

    override fun showKitchenFragment() {
        val fragment = KitchenFragment.newInstance("", "")
        mainView.setFragment(fragment)
        fragment.setPresenter(this)
    }

    override fun showTodoFragment() {
        val fragment = TodoFragment.newInstance("", "")
        mainView.setFragment(fragment)
        fragment.setPresenter(this)
    }

    override fun showConditioningFragment() {
        val fragment = ConditioningFragment.newInstance("", "")
        mainView.setFragment(fragment)
        fragment.setPresenter(this)
    }

    override fun showStatisticsFragment() {
        val fragment = StatisticsFragment.newInstance("", "")
        mainView.setFragment(fragment)
        fragment.setPresenter(this)
    }

}