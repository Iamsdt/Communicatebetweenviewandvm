package com.iamsdt.communicatebetweenviewandvm.ui.main

import android.arch.lifecycle.ViewModel
import android.os.Handler
import android.os.HandlerThread
import com.iamsdt.communicatebetweenviewandvm.SingleLiveEvent
import com.iamsdt.communicatebetweenviewandvm.ui.Status
import java.lang.Exception

class MainViewModel : ViewModel() {

    val observableEvent = SingleLiveEvent<Status>()

    fun mockLogin(){
        observableEvent.value = Status.LOGIN
        background()
    }

    fun mockSignUp(){
        Thread({
            try {
                Thread.sleep(2000) //2 sec wait
            } catch (e:Exception){
                //nothing
            } finally {
                observableEvent.postValue(Status.SIGNUP)
                //do some task from background
                background()
            }
        }).start()
    }

    private fun background(){
        val thread = HandlerThread("Background")
        thread.start()

        Handler(thread.looper).post({

            //wait for 2 sec
            Thread({
                try {
                    Thread.sleep(2000) //2 sec wait
                } catch (e:Exception){
                    //nothing
                } finally {
                    observableEvent.postValue(Status.FAILED)
                }
            }).start()
        })
    }

}
