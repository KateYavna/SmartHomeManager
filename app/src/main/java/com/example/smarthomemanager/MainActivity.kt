package com.example.smarthomemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.smarthomemanager.db.DataBaseThread
import com.example.smarthomemanager.settings.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.MainView {

    val presenter = MainActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataBaseThread()
        btLight.setOnClickListener { presenter.showLightFragment() }
        btKitchen.setOnClickListener { presenter.showKitchenFragment() }
        btTodo.setOnClickListener { presenter.showTodoFragment() }
        btConditioning.setOnClickListener { presenter.showConditioningFragment() }

    }


    override fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flFragment, fragment)
            .commit()
    }

    override fun updateTopMenu(enumItem: Int) {
        TODO("Not yet implemented")
    }
}