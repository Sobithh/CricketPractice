package com.mindorks.framework.taskpracticecricket.network

import com.mindorks.framework.taskpracticecricket.model2.Match
import com.mindorks.framework.taskpracticecricket.model2.Team1
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

interface RequestInterface {
    @GET("/nzin01312019187360.json")
    suspend fun getMatchList() : Response<Team1>
}

