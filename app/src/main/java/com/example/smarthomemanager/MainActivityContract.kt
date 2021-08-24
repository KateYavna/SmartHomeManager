package com.example.smarthomemanager

import androidx.fragment.app.Fragment
import com.example.smarthomemanager.network.ResponseMain
import retrofit2.Call
import retrofit2.http.GET

class MainActivityContract {
    interface MainView{
        fun setFragment(fragment: Fragment)
        fun updateTopMenu(enumItem: Int)
    }
    interface LightFragmentView{
        fun setPresenter(presenter: Presenter)
    }
    interface KitchenFragmentView{
        fun setPresenter(presenter: Presenter)
    }
    interface TodoFragmentView{
        fun setPresenter(presenter: Presenter)
    }
    interface ConditioningFragmentView{
        fun setPresenter(presenter: Presenter)
    }
    interface StatisticsFragmentView{
        fun setPresenter(presenter: Presenter)
    }


    interface Presenter{
        fun getDbData()
        fun getServerData()
        fun showLightFragment()
        fun showKitchenFragment()
        fun showTodoFragment()
        fun showConditioningFragment()
        fun showStatisticsFragment()
    }
}