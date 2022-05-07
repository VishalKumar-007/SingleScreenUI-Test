package com.example.singlescreenui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var etFirstName: EditText
    lateinit var etLastName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etMobileNumber: EditText
    lateinit var radioMale: RadioButton
    lateinit var radioFemale: RadioButton
    lateinit var cbMetric: CheckBox
    lateinit var cbIntermediate: CheckBox
    lateinit var cbGraduation: CheckBox
    lateinit var cbPostGraduation: CheckBox
    lateinit var etBio : TextInputEditText
    lateinit var txtTermsOfUse: TextView
    lateinit var cbAgreement: CheckBox
    lateinit var tvAgreementError: TextView
    lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etMobileNumber = findViewById(R.id.etMobileNumber)
        radioMale = findViewById(R.id.radioMale)
        radioFemale = findViewById(R.id.radioFemale)
        cbMetric = findViewById(R.id.cbMetric)
        cbIntermediate = findViewById(R.id.cbIntermediate)
        cbGraduation = findViewById(R.id.cbGraduation)
        cbPostGraduation = findViewById(R.id.cbPostGraduation)
        etBio = findViewById(R.id.etBio)
        etBio = findViewById(R.id.etBio)
        txtTermsOfUse = findViewById(R.id.txtTermsOfUse)
        tvAgreementError = findViewById(R.id.tvAgreementError)
        btnSubmit = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {

            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val mobileNumber = etMobileNumber.text.toString()

            if(firstName.isEmpty()){
                etFirstName.error = "Please enter your first name"
            }
            else if (firstName.length < 2){
                etFirstName.error = "First name should contain at least two characters"
            }
            else if (lastName.isEmpty()){
                etLastName.error = "Please enter your last name"
            }
            else if (lastName.length < 2){
                etLastName.error = "Last name should contain at least two characters"
            }
            else if (firstName == lastName){
                etLastName.error = "First name and last name can't be same"
            }
            else if (email.isEmpty()){
                etEmail.error = "Please enter your email address"
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                etEmail.error = "Please enter valid email address"
            }
            else if (password.isEmpty()){
                etPassword.error = "Please enter your password"
            }
            else if (password.length < 8){
                etPassword.error = "Password should contain at least 8 characters."
            }
            else if (mobileNumber.isEmpty()){
                etMobileNumber.error = "Please enter your mobile number"
            }
            else if (mobileNumber.length < 10){
                etMobileNumber.error = "Mobile number should contain at least 10 digits"
            }
            else if (!cbAgreement.isChecked){
                tvAgreementError.setText("Please accept terms and conditions")
            }
            else {
                tvAgreementError.setText("")
                Toast.makeText(this, "Submitted...", Toast.LENGTH_SHORT)
            }

        }

        // Adding scrollable behaviour in enter message edittext
        etBio.setOnTouchListener(View.OnTouchListener { v, event ->
            if (etBio.hasFocus()) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_SCROLL -> {
                        v.parent.requestDisallowInterceptTouchEvent(false)
                        return@OnTouchListener true
                    }
                }
            }
            false
        })
        etBio.setSelection(0)

        cbIntermediate.setOnClickListener {
            cbMetric.isChecked = true
//            cbMetric.isEnabled = false
        }

        cbGraduation.setOnClickListener {
            cbMetric.isChecked = true
//            cbMetric.isEnabled = false
            cbIntermediate.isChecked = true
//            cbIntermediate.isEnabled = false
        }

        cbPostGraduation.setOnClickListener {
            cbMetric.isChecked = true
//            cbMetric.isEnabled = false
            cbIntermediate.isChecked = true
//            cbIntermediate.isEnabled = false
            cbGraduation.isChecked = true
//            cbGraduation.isEnabled = false
        }

        txtTermsOfUse.setOnClickListener {
            Intent(this, TermsAndCondition::class.java).also {
                startActivity(it)
            }
        }

    }
}