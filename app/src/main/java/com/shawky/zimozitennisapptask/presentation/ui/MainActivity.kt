package com.shawky.zimozitennisapptask.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.shawky.zimozitennisapptask.R
import com.shawky.zimozitennisapptask.data.services.PrefrencesManager.AppPreferenceManager
import com.shawky.zimozitennisapptask.databinding.ActivityMainBinding
import com.shawky.zimozitennisapptask.domain.models.TennisPlayer
import com.shawky.zimozitennisapptask.presentation.adapters.GenericRecyclerAdapter
import com.shawky.zimozitennisapptask.presentation.adapters.RecyclerViewAdaptersBinding.classesAdapterBinding
import com.shawky.zimozitennisapptask.presentation.ui.base.BaseBindingActivity
import com.shawky.zimozitennisapptask.presentation.viewModel.PlayersViewModel
import com.shawky.zimozitennisapptask.shared.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    private lateinit var playersListAdapter : GenericRecyclerAdapter<TennisPlayer>
    private val viewModel : PlayersViewModel by viewModels()

    @Inject
    lateinit var preferencesManager : AppPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playersListAdapter = GenericRecyclerAdapter(
            arrayListOf(),
            R.layout.tennis_player_item_layout,
            classesAdapterBinding()
        )

        binding.apply {
            lifecycleOwner = this@MainActivity
            playersAdapter = playersListAdapter
            playersVm = viewModel
        }


        viewModel.showAllTennisPlayers(false)



        lifecycleScope.launch {
            Log.i("SavedData",preferencesManager.getSavedDataList<TennisPlayer>(this,Constants.PLAYERS).toString())
        }

    }


    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}