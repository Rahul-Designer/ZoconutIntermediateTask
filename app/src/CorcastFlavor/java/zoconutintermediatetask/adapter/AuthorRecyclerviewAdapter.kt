package zoconutintermediatetask.adapter

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import zoconutintermediatetask.model.Podcast
import com.example.zoconutintermediatetask.R

class AuthorRecyclerviewAdapter(
    private val context: Context,
    private val arrPodcast: ArrayList<Podcast>
) :
    RecyclerView.Adapter<AuthorRecyclerviewAdapter.ViewHolder>() {
    var play: Boolean = false
    var mediaPlayer: MediaPlayer? = null
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileImage: ImageView
        var podcast_title: TextView
        var publisher: TextView
        var musicBtn: ImageView

        init {
            profileImage = itemView.findViewById(R.id.profile_image)
            podcast_title = itemView.findViewById(R.id.podcast_title)
            publisher = itemView.findViewById(R.id.podcast_publisher)
            musicBtn = itemView.findViewById(R.id.music_btn)
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

        holder.musicBtn.setOnClickListener {
            if(!play) {
                this.play = true
                holder.musicBtn.setImageResource(R.drawable.pause_btn)
                playAudio()
                Toast.makeText(context,"Music is playing...",Toast.LENGTH_SHORT).show()
            } else {
                this.play = false
                holder.musicBtn.setImageResource(R.drawable.play_btn)
                pauseAudio()
                Toast.makeText(context,"Music is stop",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun pauseAudio() {
        if(mediaPlayer!!.isPlaying){
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
        }
    }

    private fun playAudio() {
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setDataSource(context, Uri.parse("android.resource://"+context.packageName+"/"+R.raw.bensound))
        mediaPlayer!!.prepare()
        mediaPlayer!!.start()
    }
}