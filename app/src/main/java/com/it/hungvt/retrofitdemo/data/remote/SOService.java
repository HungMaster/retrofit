package com.it.hungvt.retrofitdemo.data.remote;

import com.it.hungvt.retrofitdemo.data.model.SOAnswerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 10/28/2017.
 */

public interface SOService {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<List<SOAnswerResponse>> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<List<SOAnswerResponse>> getAnswers(@Query("tagged") String tags);

}

