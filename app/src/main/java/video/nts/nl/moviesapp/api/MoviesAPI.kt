package video.nts.nl.moviesapp.api

import retrofit2.Call
import retrofit2.http.GET
import video.nts.nl.moviesapp.api.MoviesRetrofitService.Companion.API_KEY
import video.nts.nl.moviesapp.model.MoviesList

interface MoviesAPI {


    @GET("reviews/all.json?api-key=$API_KEY")
    fun getMovies(): Call<MoviesList>
}