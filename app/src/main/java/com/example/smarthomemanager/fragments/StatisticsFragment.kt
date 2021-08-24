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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smarthomemanager.Adapter
import com.example.smarthomemanager.MainActivityContract
import com.example.smarthomemanager.R
import com.example.smarthomemanager.db.App
import com.example.smarthomemanager.db.CurrentReadings
import kotlinx.android.synthetic.main.fragment_statistics.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatisticsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatisticsFragment : Fragment(), MainActivityContract.StatisticsFragmentView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var presenterStatistics : MainActivityContract.Presenter? = null

    override fun setPresenter(presenter: MainActivityContract.Presenter) {
        this.presenterStatistics = presenter
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
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentReadings =  App.instance.database.currentReadingsDao().all
        rvCurrentReadings.adapter = Adapter(currentReadings,requireContext())
        rvCurrentReadings.layoutManager = LinearLayoutManager(requireContext())

        etDate.afterTextChanged { tvAddData.setOnClickListener{
            etValue.requestFocus()
        etValue.afterTextChanged { tvAddValue.setOnClickListener {
            val currentReadingsNew = CurrentReadings (etDate.text.toString(),etValue.text.toString())
       Thread{ App.instance.database.currentReadingsDao().insert(currentReadingsNew )}.start() } }}}}

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatisticsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatisticsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
}