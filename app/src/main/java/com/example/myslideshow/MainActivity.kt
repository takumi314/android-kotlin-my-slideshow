package com.example.myslideshow

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var player: MediaPlayer

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

    // 画像を切り替える周期 (ms)
    private val INTERVAL_TIME: Long = 5000

    // Life cycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ViewPagerとViewAdapterを関連付ける
        pager.adapter = MainPagerAdapter(supportFragmentManager)
        // 定期処理を行う
        val handler = Handler()
        timer(period = INTERVAL_TIME) {
            handler.post {
                pager.currentItem = ( pager.currentItem + 1 ) % 10
            }
        }

        // 音楽を再生する
        player = MediaPlayer.create(this, R.raw.getdown)
        // 繰り返し再生を有効にする
        player.isLooping = true
    }

    // アクティビティが表示される時にコールされる
    override fun onResume() {
        super.onResume()
        // 音楽を再生する
        player.start()
    }

    // アプリを中断する時にコールされる（ホーム押下 or 他アプリ起動など）
    override fun onPause() {
        super.onPause()
        // 音楽を停止する
        player.pause()
    }

}
