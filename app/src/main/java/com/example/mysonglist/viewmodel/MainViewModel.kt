package com.example.mysonglist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mysonglist.repositories.SongRepository
import com.example.mysonglist.retrofit.RetrofitInstance
import com.example.mysonglist.roomDB.AppDatabase
import com.example.mysonglist.roomDB.SongEntity
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val songRepository: SongRepository
    private val _songs = MutableLiveData<List<SongEntity>>()
    val songs: LiveData<List<SongEntity>> get() = _songs

    init {
        // Initialize the database and repository
        val songDao = AppDatabase.getDatabase(application).songDao()
        val apiService = RetrofitInstance.apiService
        songRepository = SongRepository(songDao, apiService)

        // Fetch songs
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            try {
                val songList = songRepository.getSongs()
                _songs.postValue(songList)
                Log.d("Songs List", songList.toString())
            } catch (e: Exception) {
                // Handle exceptions here
                e.printStackTrace()
            }
        }
    }
}
