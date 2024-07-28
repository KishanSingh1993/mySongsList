package com.example.mysonglist.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mysonglist.R
import com.example.mysonglist.roomDB.SongEntity
import com.squareup.picasso.Picasso

class SongAdapter(private val onClick: (SongEntity) -> Unit) : ListAdapter<SongEntity, SongAdapter.SongViewHolder>(
    SongDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = getItem(position)
        holder.bind(song)
    }

    class SongViewHolder(itemView: View, val onClick: (SongEntity) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.songTitle)
        private val imageView: ImageView = itemView.findViewById(R.id.albumImage)
        private var currentSong: SongEntity? = null

        init {
            itemView.setOnClickListener {
                currentSong?.let {
                    onClick(it)
                }
            }
        }

        fun bind(song: SongEntity) {
            currentSong = song
            titleTextView.text = song.title
            Picasso.get().load(song.imageUrl).into(imageView)
        }
    }
}

class SongDiffCallback : DiffUtil.ItemCallback<SongEntity>() {
    override fun areItemsTheSame(oldItem: SongEntity, newItem: SongEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SongEntity, newItem: SongEntity): Boolean {
        return oldItem == newItem
    }
}


