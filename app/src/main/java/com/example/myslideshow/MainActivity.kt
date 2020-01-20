package com.example.myslideshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val resource = listOf(
            R.drawable.slide00, R.drawable.slide01,
            R.drawable.slide02, R.drawable.slide03,
            R.drawable.slide04, R.drawable.slide05,
            R.drawable.slide06, R.drawable.slide07,
            R.drawable.slide08, R.drawable.slide09
        )

        override fun getCount(): Int {
            return resource.size
        }

        override fun getItem(position: Int): Fragment {
            return ImageFragment.newInstance(resource[position])
        }

    }

    // Life cycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ViewPagerとViewAdapterを関連付ける
        pager.adapter = MainPagerAdapter(supportFragmentManager)
    }

}
