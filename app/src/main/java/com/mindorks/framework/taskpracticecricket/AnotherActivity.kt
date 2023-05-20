package com.mindorks.framework.taskpracticecricket

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.mindorks.framework.taskpracticecricket.adapter.ViewPagerAdapter
import com.mindorks.framework.taskpracticecricket.databinding.AnotherActivityBinding
import com.mindorks.framework.taskpracticecricket.fragment.FirstFragment
import com.mindorks.framework.taskpracticecricket.fragment.SecondFragment
import com.mindorks.framework.taskpracticecricket.mainviewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnotherActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding2: AnotherActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding2 = DataBindingUtil.setContentView(this, R.layout.another_activity)
        binding2.lifecycleOwner = this
        binding2.viewmodel = viewModel
        viewModel.getAllCricketList()
        viewModel.mainMatchDetail.observe(this) {
            val adapter= ViewPagerAdapter(supportFragmentManager)
            adapter.addFragment(FirstFragment(),it.teamA)
            Log.d(TAG,"nameshort"+viewModel.teamAPlayerList.value?.Name_Short?:"")
            adapter.addFragment(SecondFragment(),it.teamB)
            binding2.viewPager.adapter=adapter
            binding2.tbLayout.setupWithViewPager(binding2.viewPager)
        }


    }



}