package com.shawky.zimozitennisapptask.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.shawky.zimozitennisapptask.R
import com.shawky.zimozitennisapptask.databinding.ActivityMainBinding
import com.shawky.zimozitennisapptask.domain.models.TennisPlayer
import com.shawky.zimozitennisapptask.presentation.adapters.GenericRecyclerAdapter
import com.shawky.zimozitennisapptask.presentation.adapters.RecyclerViewAdaptersBinding.classesAdapterBinding
import com.shawky.zimozitennisapptask.presentation.viewModel.PlayersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    private lateinit var playersListAdapter : GenericRecyclerAdapter<TennisPlayer>
    private val viewModel : PlayersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.showAllTennisPlayers()

        playersListAdapter = GenericRecyclerAdapter(
            arrayListOf(),
            R.layout.tennis_player_item_layout,
            classesAdapterBinding()
        )

        binding.apply {
            lifecycleOwner = this@MainActivity
            playersAdapter = playersListAdapter
        }
    }


    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}