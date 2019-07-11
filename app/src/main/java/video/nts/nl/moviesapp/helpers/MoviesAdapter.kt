package video.nts.nl.moviesapp.helpers

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import video.nts.nl.moviesapp.R
import video.nts.nl.moviesapp.databinding.MovieItemListBinding
import video.nts.nl.moviesapp.model.Movie
import java.util.zip.Inflater

class MoviesAdapter(
    var movie: List<Movie>,
    private val clickListener: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    lateinit var binding: MovieItemListBinding


    fun update(item: List<Movie>){

        movie = item

        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.movie_item_list,
            parent,
            false
        )

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = movie.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(movie[position].multimedia.src)
            .into(binding.moviesImageView)

        holder.bind(movie[position], binding, clickListener)

    }




    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Movie, binding: MovieItemListBinding, clickListener: (Movie) -> Unit) {
            binding.movieItem = item

        }

    }
}