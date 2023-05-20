package com.mindorks.framework.taskpracticecricket.mainviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.mindorks.framework.taskpracticecricket.model2.MainMatchDetail
import com.mindorks.framework.taskpracticecricket.model2.PlayerList
import com.mindorks.framework.taskpracticecricket.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MainActivityViewModel @Inject constructor(val repository: CricketRepository) : ViewModel() {

    val mainMatchDetail = MutableLiveData<MainMatchDetail>()
    val teamAPlayerList = MutableLiveData<PlayerList>()
    val teamBPlayerList = MutableLiveData<PlayerList>()



    private val errormessage = MutableLiveData<String>()
    var job: Job? = null
    val loading = MutableLiveData<Boolean>()

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    fun getAllCricketList() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d(TAG, "getAllCricketList: ${Gson().toJson(response.body())}")
                    val body = response.body()
//                    val list = ArrayList( response.body()?.content?.NewsItems)
//                       itemlist.postValue(list)
                    body?.let { team->
                        val matchdetail = team.Matchdetail

                        matchdetail.let {
                            var teamb = " "
                            var teama = " "
                            team.Teams.values.forEachIndexed { index, entry ->
                                if(index == 1){
                                    teamb = entry.Name_Full
                                    teamBPlayerList.value = entry
                                    Log.d(TAG, "getAllCricketList: ${Gson().toJson(entry.Players)}")
                                }
                                if(index == 0){
                                    teama = entry.Name_Full
                                    teamAPlayerList.value = entry
                                    Log.d(TAG,"cricket" + teamAPlayerList.value?.Name_Short)
                                }
                            }

                            Log.d(TAG,it.Match.Date)
                            mainMatchDetail.value = MainMatchDetail(it.Match.Date,it.Match.Time,it.Venue.Name,teama,teamb,it.Result)

//                            val nlist = ArrayList(it.Match)
//                            matchcontent.postValue(nlist)
                        }
                    }
                    loading.value = false
                } else {
                    Log.d(TAG, "getAllCricketList Error: ${Gson().toJson(response.body())}")
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }


    private fun onError(message: String) {
        errormessage.value = message
        loading.value = false
    }


    companion object{
        private const val TAG = "MainActivityViewModel"
    }
}