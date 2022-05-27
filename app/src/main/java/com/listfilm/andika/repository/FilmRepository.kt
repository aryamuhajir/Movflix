package com.listfilm.andika.repository

import androidx.lifecycle.MutableLiveData
import com.listfilm.andika.model.movie.GetMoviee
import com.listfilm.andika.network.ApiClient
import com.listfilm.andika.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FilmRepository@Inject constructor(private val apiService: ApiService) {
    fun PopularMovieApi(liveData : MutableLiveData<List<GetMoviee>>){
        val call : Call<List<GetMoviee>> = apiService.getPopularMovie()
        call?.enqueue(object : Callback<List<GetMoviee>>{
            override fun onResponse(
                call: Call<List<GetMoviee>>,
                response: Response<List<GetMoviee>>
            ) {
                if (response.isSuccessful){
                    liveData.postValue(response.body())
                }else{
                    liveData.postValue(null)

                }
            }

            override fun onFailure(call: Call<List<GetMoviee>>, t: Throwable) {
                liveData.postValue(null)
            }

        })

    }

}