package zoconutintermediatetask.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import zoconutintermediatetask.model.Podcast
import com.example.zoconutintermediatetask.R
import zoconutintermediatetask.activity.VideoPlayerActivity

class RecentRecyclerViewAdapter(private val context: Context, private val arrPodcast: ArrayList<Podcast>) :
    RecyclerView.Adapter<RecentRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileImage : ImageView
        var podcast_title : TextView
        var publisher : TextView
        var movieBtn: ImageView

        init {
            profileImage = itemView.findViewById(R.id.profile_image)
            podcast_title = itemView.findViewById(R.id.podcast_title)
            publisher = itemView.findViewById(R.id.podcast_publisher)
            movieBtn = itemView.findViewById(R.id.movie_btn)
        }
        fun bind(info: Podcast) {
            podcast_title.text = info.title
            publisher.text = info.subtitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return arrPodcast.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = arrPodcast[position]
        Glide.with(context).load(pos.image).into(holder.profileImage)
        holder.bind(pos)

        holder.movieBtn.setOnClickListener {
            context.startActivity(Intent(it.context, VideoPlayerActivity::class.java))
        }
    }
}