package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.webservice.model.CEP;
import com.example.webservice.service.HTTPService;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etCep = findViewById(R.id.edtMainCep);
        final TextView tvResposta = findViewById(R.id.tvResposta);

        Button butao = findViewById(R.id.btnBuscarCep);
        butao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etCep.getText().toString().length() > 0 && !etCep.getText().toString().equals("")
                        && etCep.length() == 8){
                    Log.i("Teste", "CEP Valido");
                    HTTPService service = new HTTPService(etCep.getText().toString());
                    try {
                        CEP retorno = service.execute().get();
                        tvResposta.setText(retorno.toString());


                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }
}