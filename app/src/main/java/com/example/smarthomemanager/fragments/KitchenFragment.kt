package com.example.smarthomemanager.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import com.example.smarthomemanager.MainActivityContract
import com.example.smarthomemanager.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_kitchen.*
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KitchenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KitchenFragment : Fragment(), MainActivityContract.KitchenFragmentView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var presenterKitchen: MainActivityContract.Presenter? = null

    override fun setPresenter(presenter: MainActivityContract.Presenter) {
        this.presenterKitchen = presenter
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
        return inflater.inflate(R.layout.fragment_kitchen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fun timer (time:Long,count:Long){
            Observable.intervalRange(
                time,
                count,
                0L,
                1L,
                TimeUnit.SECONDS
            )
                .subscribe { it ->
                    if ((it*(-1)/60L) >= 10) etMin.setText((it*(-1)/60L).toString())
                    else etMin.setText("0" + (it*(-1)/60L).toString())
                    if ((it*(-1)%60L) >= 10) etSec.setText((it*(-1)%60L).toString())
                    else etSec.setText("0" + (it*(-1)%60L).toString())
                    if (it == 0L){
                        var mediaPlayer = MediaPlayer.create(requireContext(), R.raw.beep)
                        mediaPlayer.start()
                    }
                }
        }

        etSec.afterTextChanged { btStart.setOnClickListener {
            etSec.isFocusable = false

            var min = etMin.text.toString()
            var sec = etSec.text.toString()
            if (min.equals("")){
                var time = sec.toLong() * (-1)
                var count = sec.toLong() + 1
                timer(time,count) }
            else {
                var time = (min.toLong() * 60 + sec.toLong()) * (-1)
                var count = min.toLong() * 60 + sec.toLong() + 1
                timer(time,count)}


        } }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KitchenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KitchenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}