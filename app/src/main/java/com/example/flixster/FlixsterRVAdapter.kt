package com.codepath.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.R
import com.example.flixster.R.id

/**
 * [RecyclerView.Adapter] that can display a [Flixster] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class FlixsterRVAdapter(
    private val movies: List<Flixster>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<FlixsterRVAdapter.MovieBookHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieBookHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_flixster, parent, false)
        return MovieBookHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class MovieBookHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Flixster? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(id.movie_image) as ImageView
        val mDescription: TextView = mView.findViewById<View>(id.movie_description) as TextView

        override fun toString(): String {
            return "$mMovieTitle $mDescription'"
       }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: MovieBookHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mDescription.text = movie.overview
        Glide.with(holder.mView)
            .load( "https://image.tmdb.org/t/p/w500/" + movie.movie_image)
            .centerInside()
            .into(holder.mMovieImage)


        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }
}