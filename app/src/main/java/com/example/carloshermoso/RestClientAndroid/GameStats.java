package com.example.carloshermoso.RestClientAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class GameStats extends AppCompatActivity {
    String alimentos;
    String dias;
    String entretenimiento;
    String puntuacion;
    String id;
    String salud;
    int alimentosint;
    int diasint;
    int entretenimientoint;
    int puntuacionint;
    int saludint;

    Stats stats;
    NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stats);
        final Button btupdate = (Button) findViewById(R.id.btupdate);
        final EditText etDias = (EditText) findViewById(R.id.etDias);
        final EditText etSalud = (EditText) findViewById(R.id.etSalud);
        final EditText etEntretenimiento = (EditText) findViewById(R.id.etEntretenimiento);
        final EditText etPuntuacion = (EditText) findViewById(R.id.etPuntuacion);


        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            alimentos = extras.get("id").toString();
            dias = extras.get("dias").toString();
            entretenimiento = extras.get("entretenimiento").toString();
            puntuacion = extras.get("puntuacion").toString();
            salud = extras.get("salud").toString();
            id=extras.getString("id");

            etDias.setText(dias);
            etEntretenimiento.setText(entretenimiento);
            etSalud.setText(alimentos);
            etPuntuacion.setText(puntuacion);

        }

        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    puntuacionint = format.parse(etPuntuacion.getText().toString()).intValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    diasint = format.parse(etDias.getText().toString()).intValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                     saludint = format.parse(etSalud.getText().toString()).intValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                     entretenimientoint = format.parse(etEntretenimiento.getText().toString()).intValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Stats s = new Stats(id,puntuacionint,diasint,saludint,alimentosint,entretenimientoint);


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

                 Stats stats = service.updateStats(s);

            }
        });


    }
}
