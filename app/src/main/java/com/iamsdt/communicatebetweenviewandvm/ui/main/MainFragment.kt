package com.iamsdt.communicatebetweenviewandvm.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.iamsdt.communicatebetweenviewandvm.R
import com.iamsdt.communicatebetweenviewandvm.ui.Status
import com.iamsdt.communicatebetweenviewandvm.ui.gone
import com.iamsdt.communicatebetweenviewandvm.ui.vis
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val progressBar:ProgressBar = progressBar
        progressBar.gone()

        button.setOnClickListener {
            progressBar.vis()
            viewModel.mockLogin()
        }

        button2.setOnClickListener {
            progressBar.vis()
            viewModel.mockSignUp()
        }

        viewModel.observableEvent.observe(this, Observer {
            if (it == Status.LOGIN){
                progressBar.gone()
                "Login Successful".showToast()
            } else if (it == Status.SIGNUP) {
                progressBar.gone()
                "Sign up Successful".showToast()
            }

            if (it == Status.FAILED){
                "Job Failed from background thread".showToast()
            }
        })

    }

    private fun String.showToast(){
        Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
    }

}
