package com.it.hungvt.retrofitdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.it.hungvt.retrofitdemo.R;
import com.it.hungvt.retrofitdemo.data.model.Item;
import com.it.hungvt.retrofitdemo.data.model.SOAnswerResponse;
import com.it.hungvt.retrofitdemo.data.remote.SOService;
import com.it.hungvt.retrofitdemo.utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvAnswer;
    private SOService service;
    private AnswerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

    }

    private void initComponents() {
        rcvAnswer = (RecyclerView) findViewById(R.id.rcv_answer);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvAnswer.setLayoutManager(layoutManager);
        rcvAnswer.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvAnswer.addItemDecoration(itemDecoration);
        service = ApiUtils.getSoService();

        adapter = new AnswerAdapter(this,new ArrayList<Item>(0), new AnswerAdapter.PostItemListener() {
            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });


        rcvAnswer.setAdapter(adapter);

        loadAnswer();
    }

    private void loadAnswer() {
        service.getAnswers().enqueue(new Callback<List<SOAnswerResponse>>() {
            @Override
            public void onResponse(Call<List<SOAnswerResponse>> call, Response<List<SOAnswerResponse>> response) {
                if (response.isSuccessful()){
                    List<SOAnswerResponse> list = response.body();
                    adapter.updateAnswer(list.get(0).getItems());
                    Log.d("MainActivity", "posts loaded from API");
                }
            }

            @Override
            public void onFailure(Call<List<SOAnswerResponse>> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }
}
