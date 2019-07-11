package video.nts.nl.moviesapp

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import video.nts.nl.moviesapp.MainActivity.Companion.MOVIE_ITEM
import video.nts.nl.moviesapp.databinding.ActivityMovieDetailBinding
import video.nts.nl.moviesapp.model.Movie

class MovieDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_movie_detail)

        val item: Movie = intent.getParcelableExtra(MOVIE_ITEM)
        binding.movieItem = item

        bindThumb(item.multimedia.src)
    }

    private fun bindThumb(path: String?){
        Picasso.get()
            .load(path)
            .into(binding.movieThumb)
    }
}
