package com.msla_mac.pig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }

    fun imageViewOnClick(vw: View){
        //switching the activity by clicking on image.
        val intent = Intent(this, MainActivity::class.java)
        //takes you from splash page and onto main activity
        startActivity(intent)
    }

    fun btnSupportOnClick(vw: View){

    }

    fun btnAboutOnClick(vw: View){

    }

}