package com.example.newsapps

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_about_us.*

class EditAboutUs : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private var aboutus: String = ""
    private var goal: String = ""
    private var focusOn: String = ""
    private var contactUs: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_about_us)

        sharedPreferences = getSharedPreferences(getString(R.string.pref_file_name), Context.MODE_PRIVATE)
        aboutus = sharedPreferences.getString(getString(R.string.title1),getString(R.string.aboutuscontent))?: return
        goal = sharedPreferences.getString(getString(R.string.goal),getString(R.string.goalcontent))?: return
        focusOn = sharedPreferences.getString(getString(R.string.focus),getString(R.string.focusoncontent))?: return
        contactUs = sharedPreferences.getString(getString(R.string.contactus),getString(R.string.contactuscontent))?: return

        editaboutText.setText(String.format("%s", aboutus))
        editgoalText.setText(String.format("%s", goal))
        editfocusText.setText(String.format("%s", focusOn))
        editcontactText.setText(String.format("%s", contactUs))

        imgsave1.setOnClickListener {
            aboutus = editaboutText.text.toString()
            goal = editgoalText.text.toString()
            focusOn = editfocusText.text.toString()
            contactUs = editcontactText.text.toString()

            editaboutText.setText(String.format("%s", aboutus))

            with(sharedPreferences.edit()){
                putString(getString(R.string.title1),aboutus)
                putString(getString(R.string.goal),goal)
                putString(getString(R.string.focus),focusOn)
                putString(getString(R.string.contactus),contactUs)
                apply()
            }
            Toast.makeText(
                applicationContext,
                "Edited successfully",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }

        btncanceledit.setOnClickListener {
            val mAlertDialog = AlertDialog.Builder(this)
            mAlertDialog.setIcon(R.mipmap.ic_launcher_round)
            mAlertDialog.setTitle("INFO")
            mAlertDialog.setMessage("Are you sure want to quit? \nOnce you quit, all the content are edited will not be saved")
            mAlertDialog.setPositiveButton("Yes") { dialog, id ->
                onBackPressed()
            }
            mAlertDialog.setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
            mAlertDialog.setNeutralButton("Cancel") { dialog, id ->
                dialog.cancel()
            }
            mAlertDialog.show()
        }

    }
}
