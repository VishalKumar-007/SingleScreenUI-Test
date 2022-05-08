package com.example.singlescreenui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class MainActivity : AppCompatActivity() {

//    lateinit var imgCircleProfile: CircleImageView
//    lateinit var imgAddProfile: ImageView
//    lateinit var imgRemoveProfile: ImageView
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
    lateinit var tvQualificationError: TextView
    lateinit var etBio : TextInputEditText
    lateinit var datePicker : TextView
    lateinit var timePicker: TextView
    lateinit var txtTermsOfUse: TextView
    lateinit var cbAgreement: CheckBox
    lateinit var tvAgreementError: TextView
    lateinit var btnSubmit: Button

    // Added timePicker functionality
    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                // logic to properly handle the timings picked by the user
                val formattedTime: String = when {
                    hourOfDay == 0 -> {
                        if (minute < 10) {
                            "${hourOfDay + 12}:0${minute} am"
                        } else {
                            "${hourOfDay + 12}:${minute} am"
                        }
                    }
                    hourOfDay > 12 -> {
                        if (minute < 10) {
                            "${hourOfDay - 12}:0${minute} pm"
                        } else {
                            "${hourOfDay - 12}:${minute} pm"
                        }
                    }
                    hourOfDay == 12 -> {
                        if (minute < 10) {
                            "${hourOfDay}:0${minute} pm"
                        } else {
                            "${hourOfDay}:${minute} pm"
                        }
                    }
                    else -> {
                        if (minute < 10) {
                            "${hourOfDay}:${minute} am"
                        } else {
                            "${hourOfDay}:${minute} am"
                        }
                    }
                }

                timePicker.text = formattedTime
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        imgCircleProfile = findViewById(R.id.imgCircleProfile)
//        imgAddProfile = findViewById(R.id.imgAddProfile)
//        imgRemoveProfile = findViewById(R.id.imgRemoveProfile)
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
        tvQualificationError = findViewById(R.id.tvQualificationError)
        etBio = findViewById(R.id.etBio)
        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker)
        txtTermsOfUse = findViewById(R.id.txtTermsOfUse)
        cbAgreement = findViewById(R.id.cbAgreement)
        tvAgreementError = findViewById(R.id.tvAgreementError)
        btnSubmit = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {

            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val mobileNumber = etMobileNumber.text.toString()

            if(firstName.isEmpty()){
                etFirstName.error = "Please enter your first name."
            }
            else if (firstName.length < 2){
                etFirstName.error = "First name should contain at least two characters."
            }
            else if (lastName.isEmpty()){
                etLastName.error = "Please enter your last name."
            }
            else if (lastName.length < 2){
                etLastName.error = "Last name should contain at least two characters."
            }
            else if (firstName == lastName){
                etLastName.error = "First name and last name can't be same."
            }
            else if (email.isEmpty()){
                etEmail.error = "Please enter your email address."
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                etEmail.error = "Please enter valid email address."
            }
            else if (password.isEmpty()){
                etPassword.error = "Please enter your password."
            }
            else if (password.length < 8){
                etPassword.error = "Password should contain at least 8 characters."
            }
            else if (mobileNumber.isEmpty()){
                etMobileNumber.error = "Please enter your mobile number."
            }
            else if (mobileNumber.length < 10){
                etMobileNumber.error = "Mobile number should contain at least 10 digits."
            }
            else if ( !( (cbMetric.isChecked) || (cbIntermediate.isChecked) || (cbGraduation.isChecked) || (cbPostGraduation.isChecked) ) ){
                tvQualificationError.visibility = View.VISIBLE
                tvQualificationError.text = "*Please select at least one checkbox."
            }
            else if (!cbAgreement.isChecked){
                tvAgreementError.visibility = View.VISIBLE
                tvAgreementError.text = "*Please accept terms and conditions."
            }
            else {
                tvQualificationError.visibility = View.GONE
                tvAgreementError.visibility = View.GONE

                // It will clear all fields when submit button is clicked.
                etFirstName.text.clear()
                etLastName.text.clear()
                etEmail.text.clear()
                etPassword.text.clear()
                etMobileNumber.text.clear()
                cbMetric.isChecked = false
                cbIntermediate.isChecked = false
                cbGraduation.isChecked = false
                cbPostGraduation.isChecked = false
                etBio.text?.clear()
                cbAgreement.isChecked = false

                Toast.makeText(this, "Submitted...", Toast.LENGTH_SHORT).show()
            }

        }

        // Adding scrollable behaviour in Bio Edittext.
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

        // Added checkbox validations in Qualifications part
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

        // Added datePicker functionality
        val myCalendar = Calendar.getInstance()
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)

        datePicker.setOnClickListener {

            datePicker.setTextColor(Color.parseColor("#000000"))

            var datePicker = DatePickerDialog(
                this, R.style.DatePickerTheme,
                { view, year, month, dayOfMonth -> // adding the selected date in the edittext
                    datePicker.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year)
                }, year, month, day

            )
            datePicker!!.getDatePicker().setMinDate(myCalendar.getTimeInMillis())

            // shows the dialog
            datePicker!!.show()

        }

        // Added timePicker functionality
        timePicker.setOnClickListener {
            val timePicker: TimePickerDialog = TimePickerDialog(
                // pass the Context
                this,
                // listener to perform task when time is picked
                timePickerDialogListener,
                // default hour when the time picker dialog is opened
                12,
                // default minute when the time picker dialog is opened
                10,
                // 24 hours time picker is
                // false (varies according to the region)
                false
            )

            // then after building the timepicker dialog, show the dialog to user
            timePicker.show()
        }

        txtTermsOfUse.setOnClickListener {
            Intent(this, TermsAndCondition::class.java).also {
                startActivity(it)
            }
        }

    }
}