package com.example.visionapp.registerActivity

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.visionapp.R
import com.example.visionapp.baseResponseClasses.BaseResponse
import com.example.visionapp.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(R.layout.activity_register) {
    //const
    val TAG : String = "RegisterActivity"

    //vars
    private lateinit var viewModel: RegisterViewModel

    override fun initViews() {
        viewModel = createViewModel()
    }

    private fun createViewModel(): RegisterViewModel {
        return ViewModelProvider(this@RegisterActivity).get(RegisterViewModel(application)::class.java)
    }

    fun continueRegistration(view: View) {

        val name : String = edt_driver_name.text.toString()
        val lastName : String = edt_driver_name.text.toString()
        val email : String = edt_driver_email.text.toString()
        val cellphoneNumber : String = edt_driver_cell_phone.text.toString()

        pgb_base.visibility = View.VISIBLE
        viewModel.registrationStep1(name, lastName, cellphoneNumber, email).observe(this@RegisterActivity, Observer<BaseResponse>{baseResponse ->
            if(baseResponse.result!!){
                Toast.makeText(application, baseResponse.message, Toast.LENGTH_LONG).show()
                pgb_base.visibility = View.GONE
            }else{
                Toast.makeText(application, baseResponse.message, Toast.LENGTH_LONG).show()
                pgb_base.visibility = View.GONE
            }
        })
    }


}
