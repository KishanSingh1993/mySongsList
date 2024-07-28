package com.example.mysonglist.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysonglist.R
import com.example.mysonglist.databinding.FragmentMainBinding
import com.example.mysonglist.roomDB.SongEntity
import com.example.mysonglist.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

//        val adapter = SongAdapter { song ->
//            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(song.toString())
//            findNavController().navigate(action)
//        }

        val adapter = SongAdapter { song ->
            navigateToDetailFragment(song)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.songs.observe(viewLifecycleOwner, Observer { songs ->
            adapter.submitList(songs)
            //Log.d("Songs Data", songs.toString())
        })
    }

    private fun navigateToDetailFragment(song: SongEntity) {
        val bundle = Bundle().apply {
            putString("id", song.id)
            putString("title", song.title)
            putString("imageUrl", song.imageUrl)
            putString("link", song.link)
        }
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
    }
}