package com.mindorks.framework.taskpracticecricket.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mindorks.framework.taskpracticecricket.BottomSheetFragment
import com.mindorks.framework.taskpracticecricket.R
import com.mindorks.framework.taskpracticecricket.adapter.DataAdapter
import com.mindorks.framework.taskpracticecricket.mainviewmodel.MainActivityViewModel
import com.mindorks.framework.taskpracticecricket.model2.Player

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SecondFragment : Fragment() {

    val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView : RecyclerView = view.findViewById(R.id.second_fragment)

        val adapter = DataAdapter(
            onPlayerClick = {player->
                val bottomSheetDialog = BottomSheetFragment(player)
                bottomSheetDialog.show(childFragmentManager, "bottomSheetTag")
            }
        )

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        mainActivityViewModel.teamBPlayerList.observe(viewLifecycleOwner){
            val playerList = mutableListOf<Player>()
            it.Players.values.forEachIndexed { index, entry ->
                playerList.add(entry)
            }
            adapter.setData(playerList)
        }

//        adapter.onItemClick = {
//
//            val dialog = BottomSheetDialog(requireActivity())
//
//            val view = layoutInflater.inflate(R.layout.fragment_details, null)
//            val btnClose = view.findViewById<Button>(R.id.dismiss)
//            btnClose.setOnClickListener {
//                dialog.dismiss()
//            }
//            dialog.setCancelable(true)
//            dialog.setCanceledOnTouchOutside(true)
//            dialog.setContentView(view)
//            dialog.show()
//
//        }

    }


}