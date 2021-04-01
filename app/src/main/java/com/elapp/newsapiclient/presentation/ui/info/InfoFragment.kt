package com.elapp.newsapiclient.presentation.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.elapp.newsapiclient.databinding.FragmentInfoBinding

class InfoFragment: Fragment() {
    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        binding.wvNews.apply {
            webViewClient = WebViewClient()
            if (article.url.isNotEmpty()){
                loadUrl(article.url)
            }
        }
    }

}