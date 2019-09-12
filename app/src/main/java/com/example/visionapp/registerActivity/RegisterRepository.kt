package com.example.visionapp.registerActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.visionapp.baseResponseClasses.BaseResponse
import com.example.visionapp.services_network.Retrofi2Class
import com.example.visionapp.services_network.Services
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

//singleton behavior
object RegisterRepository {
    //const
    val TAG: String = "RegisterViewModel"


     suspend fun registarionProccessStep1(cellPhoneId: String, name: String, lastName: String, cellphoneNumber: String, email: String): MutableLiveData<BaseResponse>  {

        val call = Retrofi2Class.apiService.registrationProcess(cellPhoneId, name, lastName, cellphoneNumber, email)
        val mutableLiveData = MutableLiveData<BaseResponse>()

        withContext(Dispatchers.IO) {
            try {
                withTimeout(10000) {
                    call.enqueue(object : Callback<BaseResponse> {
                        override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                            Log.e(TAG, "An error has ocurred: ${t.message.toString()}")
                            val baseResponse = BaseResponse(false, t.message.toString())
                            mutableLiveData.value = baseResponse

                        }

                        override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                            Log.i(TAG, "Registration step 1 successful")
                            mutableLiveData.value = response.body()
                        }

                    })
                }
            }catch(e: Exception){
                Log.e(TAG,"an error has happen ${e.message}")
            }
        }


        return mutableLiveData

    }






}