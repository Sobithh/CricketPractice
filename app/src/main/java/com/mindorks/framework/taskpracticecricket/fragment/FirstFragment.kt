package com.mindorks.framework.taskpracticecricket.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mindorks.framework.taskpracticecricket.BottomSheetFragment
import com.mindorks.framework.taskpracticecricket.R
import com.mindorks.framework.taskpracticecricket.adapter.DataAdapter
import com.mindorks.framework.taskpracticecricket.databinding.FragmentDetailsBinding
import com.mindorks.framework.taskpracticecricket.mainviewmodel.MainActivityViewModel
import com.mindorks.framework.taskpracticecricket.model2.Player
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment() : Fragment() {

    val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView : RecyclerView = view.findViewById(R.id.rv_1_list)
        val adapter = DataAdapter(
            onPlayerClick = {player->

                val bottomSheetDialog = BottomSheetFragment(player)
                bottomSheetDialog.show(childFragmentManager, "bottomSheetTag")
//               Toast.makeText(requireActivity(), "Player clicked", Toast.LENGTH_SHORT).show()
//                val dialog = BottomSheetDialog(requireActivity())
//            val inflater = LayoutInflater.from(context)
//                 val view = DataBindingUtil.inflate<FragmentDetailsBinding>(
//                layoutInflater,
//                R.layout.fragment_details,
//                null,
//                false
//                )
//            val btnClose = view.findViewById<Button>(R.id.dismiss)
////                btnClose.text = player.Batting.Style
//            btnClose.setOnClickListener {
//                dialog.dismiss()
//            }
//            dialog.setCancelable(true)
//            dialog.setCanceledOnTouchOutside(true)
//            dialog.setContentView(view.root)
//            dialog.show()
            }
        )
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }


        mainActivityViewModel.teamAPlayerList.observe(viewLifecycleOwner){
            val playerList = mutableListOf<Player>()
            it.Players.values.forEachIndexed { index, entry ->
                playerList.add(entry)
            }
            adapter.setData(playerList)
        }



    }




}