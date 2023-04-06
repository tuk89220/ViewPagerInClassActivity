package edu.temple.viewpagerinclasssctivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private val addButton: Button by lazy{
        findViewById(R.id.addButton)
    }
    private val viewPager: ViewPager2 by lazy{
        findViewById(R.id.viewPager)
    }
    private var numberOfPages = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton.setOnClickListener() {
            numberOfPages++
            viewPager.adapter?.notifyItemInserted(numberOfPages - 1)
            viewPager.setCurrentItem(numberOfPages,true)
            //personal notes for assignment 8:
            //used to contact a specific fragment - supportFragmentManager.findFragmentByTag("f" + position)
        }

            viewPager.adapter = object : FragmentStateAdapter(this) {
                override fun getItemCount() = numberOfPages

                override fun createFragment(position: Int) =
                    TextFragment.newInstance((position + 1).toString())

                //personal notes for assignment 8:
                //do not have webView be destroyed in certain circumstances on rotation. Stop activity from restarting
                //in manifest in <activity - android.configChanges = "orientation|keyboardHidden|screenSize">
                //with previous adapters, we always used collections, with fragment state adapter there is no defined collection
                //fragment manager hold the collections,

            }

        }

}