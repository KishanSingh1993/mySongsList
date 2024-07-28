package com.example.mysonglist.views

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysonglist.R
import com.example.mysonglist.databinding.FragmentDetailBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var exoPlayer: ExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val id = arguments?.getString("id")
        val title = arguments?.getString("title")
        val imageUrl = arguments?.getString("imageUrl")
        val link = arguments?.getString("link")
        val amount = arguments?.getString("amount")
        val currency = arguments?.getString("currency")
        // Log the data
        Log.d("DetailFragment", "ID: $id")
        Log.d("DetailFragment", "Title: $title")
        Log.d("DetailFragment", "Image URL: $imageUrl")
        Log.d("DetailFragment", "Link: $link")
        Log.d("DetailFragment", "AMount: $amount")

        // Display the data
        binding.songTitle.text = title
        "Price: $amount $currency".also { binding.songPrice.text = it }
        imageUrl?.let { Picasso.get().load(it).into(binding.albumImage) }

        // Initialize ExoPlayer
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = exoPlayer
        binding.playerView.visibility = View.GONE

        // Prepare and play the audio
        link?.let {
            val mediaItem = MediaItem.fromUri(Uri.parse(it))
            exoPlayer?.setMediaItem(mediaItem)
            exoPlayer?.prepare()
        }

        // Set up button to play/pause
        binding.playButton.setOnClickListener {
            binding.playerView.visibility = View.VISIBLE
            binding.playButton.visibility = View.GONE
            if (exoPlayer?.isPlaying == true) {
                exoPlayer?.pause()
                binding.playButton.text = getString(R.string.play)
            } else {
                exoPlayer?.play()
                binding.playButton.text = getString(R.string.pause)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}