package com.elapp.newsapiclient.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elapp.newsapiclient.data.model.news.APIResponse
import com.elapp.newsapiclient.data.utils.Resource
import com.elapp.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import com.elapp.newsapiclient.domain.usecase.GetSearchedNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
) : AndroidViewModel(app) {

    val newsHeadlines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadlines(apiKey: String, country: String, page: Int) {
        newsHeadlines.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                viewModelScope.launch(Dispatchers.IO) {
                    newsHeadlines.postValue(Resource.Loading())
                    val apiResult = getNewsHeadlinesUseCase.execute(apiKey, country, page)
                    newsHeadlines.postValue(apiResult)
                }
            } else {
                newsHeadlines.postValue(Resource.Error("Internet is not available"))
            }
        } catch (ex: Exception) {
            newsHeadlines.postValue(Resource.Error(ex.message.toString()))
        }

    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    // Search Function
    val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    fun searchNews(
        apiKey: String,
        country: String,
        searchQuery: String,
        page: Int
    ) {
        viewModelScope.launch {
            searchedNews.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    val response =
                        getSearchedNewsUseCase.execute(apiKey, country, searchQuery, page)
                    searchedNews.postValue(response)
                } else {
                    searchedNews.postValue(Resource.Error("No Internet Connection"))
                }
            } catch (ex: Exception) {
                searchedNews.postValue(Resource.Error(ex.message.toString()))
            }
        }
    }

}