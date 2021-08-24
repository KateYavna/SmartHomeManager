package com.example.smarthomemanager.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.smarthomemanager.MainActivityContract
import com.example.smarthomemanager.MainActivityPresenter
import com.example.smarthomemanager.R
import com.example.smarthomemanager.network.API
import com.example.smarthomemanager.network.ResponseMain
import com.example.smarthomemanager.network.RetrofitClient
import com.example.smarthomemanager.settings.SharedPrefManager
import kotlinx.android.synthetic.main.fragment_light.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LightFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LightFragment : Fragment(), MainActivityContract.LightFragmentView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var presenterLight : MainActivityContract.Presenter? = null

    override fun setPresenter(presenter: MainActivityContract.Presenter) {
       this.presenterLight = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_light, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swPower.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                     var retrofit =
                         RetrofitClient.getClient("https://api.coindesk.com/").create(API::class.java)
                         retrofit.getCurrentPrice().enqueue(object : Callback<ResponseMain> {
                    override fun onResponse(call: Call<ResponseMain>, response: Response<ResponseMain>) {
                        Log.d("MyLog", "220 V")
                        if (response.body() != null) tvPower.visibility = View.VISIBLE}

                    override fun onFailure(call: Call<ResponseMain>, t: Throwable) {
                        Log.d("MyLog", "failure") } })
                    SharedPrefManager(requireContext()).setSwitch(true)
                }
            else {SharedPrefManager(requireContext()).setSwitch(false)
                     tvPower.visibility = View.INVISIBLE}
        }
        if (SharedPrefManager(requireContext()).getSwitch().equals(true)) swPower.isChecked = true
        btStatistics.setOnClickListener { presenterLight?.showStatisticsFragment() }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LightFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LightFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}