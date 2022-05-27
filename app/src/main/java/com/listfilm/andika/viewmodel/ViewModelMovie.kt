package com.listfilm.andika.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.listfilm.andika.model.movie.GetMoviee


import com.listfilm.andika.network.ApiClient
import com.listfilm.andika.repository.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelMovie @Inject constructor(private val repository : FilmRepository) : ViewModel() {
    var liveDataFilm : MutableLiveData<List<GetMoviee>> = MutableLiveData()
    var liveDataNewFilm : MutableLiveData<List<GetMoviee>> = MutableLiveData()
    var liveDataRecFilm : MutableLiveData<List<GetMoviee>> = MutableLiveData()

    fun getLiveFilmObserver() : MutableLiveData<List<GetMoviee>> {
        return liveDataFilm
    }

    fun getLiveNewFilmObserver() : MutableLiveData<List<GetMoviee>> {
        return liveDataNewFilm
    }

    fun getLiveRecFilmObserver() : MutableLiveData<List<GetMoviee>> {
        return liveDataRecFilm
    }



    fun PopularMovieApi(){
        repository.PopularMovieApi(liveDataFilm)
            }

    fun NewMovieApi(){
        ApiClient.instance.getNewMovie()
            .enqueue(object : retrofit2.Callback<List<GetMoviee>>{
                override fun onResponse(
                    call: Call<List<GetMoviee>>,
                    response: Response<List<GetMoviee>>
                ) {
                    if (response.isSuccessful){
                        liveDataNewFilm.postValue(response.body())

                    }else{
                        liveDataNewFilm.postValue(null)

                    }
                }

                override fun onFailure(call: Call<List<GetMoviee>>, t: Throwable) {
                    liveDataNewFilm.postValue(null)
                }
            })
    }

    fun RecMovieApi(){
        ApiClient.instance.getRecMovie()
            .enqueue(object : retrofit2.Callback<List<GetMoviee>>{
                override fun onResponse(
                    call: Call<List<GetMoviee>>,
                    response: Response<List<GetMoviee>>
                ) {
                    if (response.isSuccessful){
                        liveDataRecFilm .postValue(response.body())

                    }else{
                        liveDataRecFilm .postValue(null)

                    }
                }

                override fun onFailure(call: Call<List<GetMoviee>>, t: Throwable) {
                    liveDataRecFilm .postValue(null)
                }
            })
    }
}

