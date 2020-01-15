package com.example.newsapps

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_about_us.*

class AboutUsForUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us_for_user)
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences(getString(R.string.pref_file_name), Context.MODE_PRIVATE)
        val aboutus = sharedPreferences.getString(getString(R.string.title1), getString(R.string.aboutuscontent))
        val goal = sharedPreferences.getString(getString(R.string.goal), getString(R.string.goalcontent))
        val focusOn = sharedPreferences.getString(getString(R.string.focus), getString(R.string.focusoncontent))
        val contactUs = sharedPreferences.getString(getString(R.string.contactus), getString(R.string.contactuscontent))

        lblabout.text = String.format("%s\n\n%s", getString(R.string.title1),aboutus)
        lblgoalcontent.text = String.format("%s\n\n%s",getString(R.string.goal), goal)
        lblfocuscontent.text = String.format("%s\n\n%s", getString(R.string.focus),focusOn)
        lblcontactcontent.text = String.format("%s\n\n%s", getString(R.string.contactus), contactUs)

    }
}
