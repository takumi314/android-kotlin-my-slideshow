package com.example.myslideshow


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_image.*

/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    // アクティビティの onCreate が完了した後にコールされる
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // imgResIdに保持しておいた画像IDを使って画像を設定する
        imgResId?.let {
            imageView.setImageResource(it)
        }
    }

    companion object {
        fun newInstance(imageResourceId: Int) : ImageFragment {
            val bundle = Bundle()
            // 画像リソースのIDをBundleに格納する
            bundle.putInt("IMG_RES_ID", imageResourceId)
            val imageFragment = ImageFragment()
            imageFragment.arguments = bundle    // Argumentに書き込む
            return imageFragment
        }
    }

    // Argmentから取り出した画像IDを保持する
    private var imgResId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imgResId = it.getInt("IMG_RES_ID")
        }
    }

}
