package com.mindorks.framework.taskpracticecricket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mindorks.framework.taskpracticecricket.databinding.ActivityMainBinding
import com.mindorks.framework.taskpracticecricket.databinding.ActivityTestMainBinding
import com.mindorks.framework.taskpracticecricket.mainviewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        viewModel.getAllCricketList()
        viewModel.mainMatchDetail

        binding.cardView1.setOnClickListener{
            val intent = Intent(this, AnotherActivity::class.java)
            // start your next activity
            startActivity(intent)
        }


    }
}