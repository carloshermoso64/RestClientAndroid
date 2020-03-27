package com.example.carloshermoso.RestClientAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final Button bt = (Button) findViewById(R.id.button);
        final TextView tv = (TextView) findViewById(R.id.textView);
        final EditText et = (EditText) findViewById(R.id.editText1);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://147.83.7.204:8080/dsaApp/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

                RestExampleService service = retrofit.create(RestExampleService.class);


                    final String user = et.getText().toString();


                    Call<List<Stats>> stats = service.listStats();


                    stats.enqueue(new Callback<List<Stats>>() {
                        @Override
                        public void onResponse(Call<List<Stats>> call, Response<List<Stats>> response) {
                            List<String> input = new ArrayList<>();
                            List<Stats> stats = response.body();

                            try{
                                for (Stats s : stats) {

                                    input.add(s.id);

                                }            } catch (NullPointerException e) {
                                tv.setText("El usuario introducido es incorrecto");
                            }
                            mAdapter = new MyAdapter(input);
                            recyclerView.setAdapter(mAdapter);
                        }

                        @Override
                        public void onFailure(Call<List<Stats>> call, Throwable t) {
                            tv.setText("Fallo");
                        }
                    });

            }
        });


    }
}
