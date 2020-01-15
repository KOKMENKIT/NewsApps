package com.example.newsapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_admin_main_page.*

class AdminMainPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main_page)

        imgUser.setOnClickListener {

        }

        imgNews.setOnClickListener {
            val intent = Intent(this,NewsList::class.java)
            startActivity(intent)
        }

        imgEvent.setOnClickListener {

        }

        imgUpdate.setOnClickListener {
            val intent = Intent(this,AboutUs::class.java)
            startActivity(intent)
        }

        imgUpload.setOnClickListener {

        }
    }
}
