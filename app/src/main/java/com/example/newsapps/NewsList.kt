package com.example.newsapps

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolapps.Database.News
import com.example.schoolapps.ViewModel.NewsViewModel
import com.example.schoolapps.ViewModel.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_dialog_message.*
import kotlinx.android.synthetic.main.activity_dialog_message.view.*
import kotlinx.android.synthetic.main.activity_news_list.*
import kotlinx.android.synthetic.main.list_layout.*
import java.text.SimpleDateFormat
import java.util.*

class NewsList : AppCompatActivity() {

    private val NewsActivityRequestCode = 1
    private lateinit var newsViewModel: NewsViewModel

//    private lateinit var deleteIcon: Drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        //for recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = RecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        //get a existing model view model for live data
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        newsViewModel.allnews.observe(this, Observer { list ->
            list?.let {
                adapter.setNews(it)
            }
        })

//        deleteIcon = ContextCompat.getDrawable(this, R.drawable.ic_delete)!!
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

                val position = viewHolder.adapterPosition
                val news: News = adapter.getNewsAtPosition(position)

                if (swipeDir == ItemTouchHelper.LEFT) {
                    val mAlertDialog = AlertDialog.Builder(this@NewsList)
                    mAlertDialog.setIcon(R.mipmap.ic_launcher_round)
                    mAlertDialog.setTitle("INFO")
                    mAlertDialog.setMessage("Are you sure want to delete this news? \nOnce you delete, that deleted news is unable to undo")
                    mAlertDialog.setPositiveButton("Yes") { dialog, id ->

                        newsViewModel.delete(news)

                        Toast.makeText(
                            applicationContext,
                            "News deleted successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    mAlertDialog.setNegativeButton("No") { dialog, id ->
                        dialog.dismiss()
                    }
                    mAlertDialog.setNeutralButton("Cancel") { dialog, id ->
                        dialog.cancel()
                    }
                    mAlertDialog.show()
                } else {
                    val mDialogView = LayoutInflater.from(this@NewsList).inflate(R.layout.activity_dialog_message, null)
                    //AlertDialogBuilder
                    val mBuilder = AlertDialog.Builder(this@NewsList)
                        .setView(mDialogView)
                        .setTitle("Edit News")
                    //show dialog
                    val  mAlertDialog = mBuilder.show()

                    val date = Calendar.getInstance().time
                    val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
                    val formatedDate = formatter.format(date)

                    mDialogView.dialogdateEt.text = formatedDate.toString()

                    //edit button click of custom layout
                    mDialogView.dialogEditBtn.setOnClickListener {
                        val mAlertDialog = AlertDialog.Builder(this@NewsList)
                        mAlertDialog.setIcon(R.mipmap.ic_launcher_round)
                        mAlertDialog.setTitle("INFO")
                        mAlertDialog.setMessage("Are you sure want to edit this news?")
                        mAlertDialog.setPositiveButton("Yes") { dialog, id ->
                            val NewsTitle = mDialogView.dialogTitleEt.text.toString()
                            val NewsDesc = mDialogView.dialogDescEt.text.toString()
                            val NewsDatePublish = mDialogView.dialogdateEt.text.toString()

                            val news = News(position, NewsTitle,NewsDesc,NewsDatePublish)
                            newsViewModel.update(news)
                            newsViewModel.allnews
                            Toast.makeText(this@NewsList, "News edited successfully", Toast.LENGTH_SHORT).show()
                        }
                        mAlertDialog.setNeutralButton("Cancel") { dialog, id ->
                            dialog.cancel()
                        }
                        mAlertDialog.show()
                    }


                    mDialogView.dialogCancelBtn.setOnClickListener {
                        //dismiss dialog
                        mAlertDialog.cancel()
                    }

//                    val builder = android.app.AlertDialog.Builder(this@NewsList)
//
//                    val edittitle = EditText(this@NewsList)
//                    edittitle.hint = "Enter the title ..."
//                    edittitle.gravity = Gravity.CENTER_HORIZONTAL
//                    edittitle.ellipsize
//
//                    val editdesc = EditText(this@NewsList)
//                    editdesc.hint = "Enter the description for news ..."
//                    editdesc.gravity = Gravity.CENTER_HORIZONTAL
//                    editdesc.ellipsize
//
//                    val editdate = TextView(this@NewsList)
//                    editdate.gravity = Gravity.CENTER_HORIZONTAL
//                    editdate.ellipsize
//
//                    val date = Calendar.getInstance().time
//                    val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
//                    val formatedDate = formatter.format(date)
//                    editdate.text = formatter.format(date)
//
//                    builder.setTitle("Edit News")
//                    builder.setMessage("Once edited completed, please choose 'OK'. If abort edit infomation, please choose 'Cancel'")
//                    builder.setView(editdesc)
//                    builder.setPositiveButton("OK") { _, _ ->
//                            val NewsTitle = edittitle.text.toString()
//                            val NewsDesc = editdesc.text.toString()
//                            val NewsDatePublish = formatedDate.toString()
//
//                            if (NewsDesc.isEmpty()) {
//                                Toast.makeText(this@NewsList, "Edit unable to be saved because it is empty", Toast.LENGTH_SHORT).show()
//                            } else {
//                                val news = News(position, NewsTitle,NewsDesc,NewsDatePublish)
//                                newsViewModel.update(news)
//                                newsViewModel.allnews
//                                Toast.makeText(this@NewsList, "News edited successfully", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    builder.setNegativeButton("Cancel") { dialog, _ ->
//                        Toast.makeText(this@NewsList, "Editing canceled", Toast.LENGTH_SHORT).show()
//                        dialog.cancel()
//                    }
//                    builder.show()
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                val icon: Bitmap

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    val itemView = viewHolder.itemView

                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3

                    val p = Paint()
                    if (dX > 0) {

                        p.color = Color.parseColor("#1A7DCB")
                        val background = RectF(
                            itemView.left.toFloat(),
                            itemView.top.toFloat(),
                            dX,
                            itemView.bottom.toFloat())
                        c.drawRect(background, p)

                        val left = itemView.left.toFloat() + width
                        val top = itemView.top.toFloat() + width
                        val right = itemView.left.toFloat() + 2 * width
                        val bottom = itemView.bottom.toFloat() - width

                        icon = getBitmapFromVectorDrawable(applicationContext, R.drawable.ic_edit)
                        val iconDest = RectF(left, top, right, bottom)

                        c.drawBitmap(icon,null,iconDest,p)

                    } else {

                        p.color = Color.parseColor("#CB1A1A")

                        val background = RectF(
                            itemView.right.toFloat() + dX,
                            itemView.top.toFloat(),
                            itemView.right.toFloat(),
                            itemView.bottom.toFloat()
                        )
                        c.drawRect(background,p)


                        icon = getBitmapFromVectorDrawable(applicationContext, R.drawable.ic_delete_one)

                        val left = itemView.right.toFloat() - 2 * width
                        val top = itemView.top.toFloat() + width
                        val right = itemView.right.toFloat() - width
                        val bottom = itemView.bottom.toFloat() - width
                        val iconDest = RectF(left, top, right, bottom)

                        c.drawBitmap(icon,null,iconDest,p)
                    }

                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerview)

        val post = findViewById<Button>(R.id.btnPost)
        post.setOnClickListener {
            val intent = Intent(this, PostNews::class.java)
            startActivityForResult(intent, NewsActivityRequestCode)
        }


    }

    fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
        var drawable = ContextCompat.getDrawable(context, drawableId)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable!!).mutate()
        }

        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NewsActivityRequestCode && resultCode == Activity.RESULT_OK) {

            val title = data?.getStringExtra(PostNews.EXTRA_REPLY1)
            val content = data?.getStringExtra(PostNews.EXTRA_REPLY2)
            val datepublish = data?.getStringExtra(PostNews.EXTRA_REPLY3)

            val news = News(0, title!!, content!!, datepublish!!)
            newsViewModel.insert(news)

            Toast.makeText(applicationContext, "News posted successfully", Toast.LENGTH_LONG).show()


        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }

}

