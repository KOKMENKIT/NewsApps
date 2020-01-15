package com.example.newsapps

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapps.Database.News
import kotlinx.android.synthetic.main.activity_post_news.*
import java.text.SimpleDateFormat
import java.util.*

class PostNews : AppCompatActivity() {

    private lateinit var TXTtitle: EditText
    private lateinit var TXTcontent: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_news)

        TXTtitle = findViewById(R.id.txttitle)
        TXTcontent = findViewById(R.id.txtcontent)

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
        val formatedDate = formatter.format(date)

        lbldate.text = formatedDate.toString()

        btnupdatenews.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(TXTtitle.text) || TextUtils.isEmpty(TXTcontent.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = TXTtitle.text.toString()
                val content = TXTcontent.text.toString()
                val datepublish = formatedDate.toString()

                replyIntent.putExtra(EXTRA_REPLY1,title)
                replyIntent.putExtra(EXTRA_REPLY2,content)
                replyIntent.putExtra(EXTRA_REPLY3,datepublish)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        btncancelpost.setOnClickListener {
            val mAlertDialog = AlertDialog.Builder(this)
            mAlertDialog.setIcon(R.mipmap.ic_launcher_round)
            mAlertDialog.setTitle("INFO")
            mAlertDialog.setMessage("Are you sure want to quit? \nOnce you quit, all the content are entered will not be saved")
            mAlertDialog.setPositiveButton("Yes") { dialog, id ->
                onBackPressed()
                clear()
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

    fun clear(){
        txttitle.setText("")
        txtcontent.setText("")
    }

    companion object {
        const val EXTRA_REPLY1 = "com.example.android.newstitlesql.REPLY"
        const val EXTRA_REPLY2 = "com.example.android.newsdescsql.REPLY"
        const val EXTRA_REPLY3 = "com.example.android.newsdatepublishsql.REPLY"
    }

}
