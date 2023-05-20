package com.mindorks.framework.taskpracticecricket.repository

import com.mindorks.framework.taskpracticecricket.model2.Team1
import com.mindorks.framework.taskpracticecricket.network.RequestInterface
import retrofit2.Response


class CricketRepository (val service : RequestInterface) {

    suspend fun getList(): Response<Team1> {
       return service.getMatchList()
    }

}