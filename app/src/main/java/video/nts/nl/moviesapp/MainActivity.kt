package video.nts.nl.moviesapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import video.nts.nl.moviesapp.helpers.MoviesAdapter
import video.nts.nl.moviesapp.model.Movie
import video.nts.nl.moviesapp.modelView.MoviesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        recyclerView = this.rv_movies

        moviesAdapter = MoviesAdapter(
            emptyList())
            {movie: Movie -> movieItemClickListener(movie)}

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = moviesAdapter
        recyclerView.hasFixedSize()


//        update adapter list
        moviesViewModel.getMovies()?.observe(this, getMoviesObserver)



    }


    private val getMoviesObserver = Observer<List<Movie>>{
        moviesAdapter.update(it!!)
    }

    //To be implimented
    private fun movieItemClickListener(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_ITEM, movie)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val MOVIE_ITEM = "MOVIE_ITEM"
    }
}
