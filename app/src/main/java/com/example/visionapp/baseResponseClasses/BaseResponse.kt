package com.example.visionapp.baseResponseClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class BaseResponse {

    constructor(result: Boolean, message: String){
        this.result = result
        this.message = message
    }

    @SerializedName("result")
    @Expose
    var result: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null

}