package video.nts.nl.moviesapp.api

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import video.nts.nl.moviesapp.model.Movie
import video.nts.nl.moviesapp.model.MoviesList
import java.util.concurrent.TimeUnit

class MoviesRetrofitService {

    private val service: MoviesAPI


    init {
        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        service = retrofit.create(MoviesAPI::class.java)


    }

    fun loadMovies(): MutableLiveData<List<Movie>>? {
        val call = service.getMovies()

        call.enqueue(resultCallBack)

        return moviesLiveData

    }

    companion object {

        const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/" //Our api base path

        const val API_KEY = "doy8uRAkFgC97g1uOLO2eHm1EM3IJOFo" //Our api key

        val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

        private val resultCallBack = object : Callback<MoviesList> {

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {

                Log.e("Movies", "Api call failed")

            }

            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {

                response.isSuccessful.let {

                    val list = response.body()?.results
                    Log.e("Movies Found", "${list?.size}")

                    moviesLiveData.value = list


                }

            }
        }

    }

}

