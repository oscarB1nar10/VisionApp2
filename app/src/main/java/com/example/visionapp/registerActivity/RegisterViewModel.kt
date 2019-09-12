package com.example.visionapp.registerActivity

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.telephony.TelephonyManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.visionapp.baseResponseClasses.BaseResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.channels.Channel
import okhttp3.Dispatcher


class RegisterViewModel(application: Application) : AndroidViewModel(application){
    //const
    val TAG: String = "RegisterViewModel"
    val context: Context = application

    //vars
    var registerMulatbeLIveData: MutableLiveData<BaseResponse>? = null

    init {
        registerMulatbeLIveData = MutableLiveData()
    }


    /**
     * function to return a result about registration step one
     */
    @SuppressLint("MissingPermission")
    fun registrationStep1(name: String, lastName: String, cellphoneNumber: String, email: String): LiveData<BaseResponse> {

        val telephonyManager: TelephonyManager
        telephonyManager = (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?)!!
        /*
        * getDeviceId() returns the unique device ID.
        * For example,the IMEI for GSM and the MEID or ESN for CDMA phones.
        */
        val deviceId = telephonyManager.deviceId
        /*
        * getSubscriberId() returns the unique subscriber ID,
        * For example, the IMSI for a GSM phone.
        */
        val cellPhoneId = telephonyManager.subscriberId

        val channel = Channel<LiveData<Int>>()

        viewModelScope.launch(Dispatchers.Unconfined) {
            val mutableLiveData = GlobalScope.async(Dispatchers.Unconfined) {
                RegisterRepository?.registarionProccessStep1(
                    cellPhoneId,
                    name,
                    lastName,
                    cellphoneNumber,
                    email
                )!!
            }
            registerMulatbeLIveData = mutableLiveData.await()
        }


        return registerMulatbeLIveData!!

    }





}