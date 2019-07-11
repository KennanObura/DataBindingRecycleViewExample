package video.nts.nl.moviesapp.modelView

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import video.nts.nl.moviesapp.api.MoviesRetrofitService
import video.nts.nl.moviesapp.helpers.MoviesAdapter
import video.nts.nl.moviesapp.model.Movie

class MoviesViewModel(): ViewModel() {



    private val apiService = MoviesRetrofitService()

    fun getMovies(): MutableLiveData<List<Movie>> ?{

        return apiService.loadMovies()

    }
}