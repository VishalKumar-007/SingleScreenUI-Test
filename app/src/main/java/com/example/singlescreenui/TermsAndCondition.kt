package com.example.singlescreenui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TermsAndCondition : AppCompatActivity() {

    lateinit var backTermsAndConditions: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_condition)

        backTermsAndConditions = findViewById(R.id.backTermsAndConditions)

        backTermsAndConditions.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        finish()
    }
}