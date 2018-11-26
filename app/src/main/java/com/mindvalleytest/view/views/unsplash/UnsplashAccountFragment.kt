package com.mindvalleytest.view.views.unsplash

import android.annotation.SuppressLint
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.RelativeLayout

import com.mindvalleytest.R

/**
 * This fragment show unsplash account of the user.
 *
 * @author Salahuddin
 * @version 1.0
 */
class UnsplashAccountFragment : Fragment() {
    internal var wbVwDisplay: WebView? = null
    internal var rellayMainParent: RelativeLayout? = null

    internal var webURL: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            webURL = bundle.getString(ARG_URL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, vg: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_unsplash, vg, false)
        wbVwDisplay = view.findViewById<WebView>(R.id.xwbVwDisplay)
        rellayMainParent = view.findViewById<RelativeLayout>(R.id.xrellayMainParent)
//        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        initObjects()
    }

    /**
     * This function is used to initialise the objects that are going to be used in this fragment
     */
    @SuppressLint("SetJavaScriptEnabled")
    fun initObjects() {
        wbVwDisplay!!.loadUrl(webURL)

        // Enable Javascript
        val webSettings = wbVwDisplay!!.settings
        webSettings.javaScriptEnabled = true

        // Force links and redirects to open in the WebView instead of in a browser
        wbVwDisplay!!.webViewClient = WebViewClient()
    }

    companion object {

        val ARG_URL = "arg_url"

        fun newInstance(url: String): UnsplashAccountFragment {
            val args = Bundle()
            args.putString(ARG_URL, url)
            val fragment = UnsplashAccountFragment()
            fragment.arguments = args
            return fragment
        }
    }


}
