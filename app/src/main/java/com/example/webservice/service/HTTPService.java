package com.example.webservice.service;

import android.os.AsyncTask;

import com.example.webservice.model.CEP;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HTTPService extends AsyncTask<Void, Void, CEP>{

    private final String cep;

    public HTTPService(String cep) {
        this.cep = cep;
    }

    @Override
    protected CEP doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        try {
           URL url = new URL("http://ws.matheuscastiglioni.com.br/ws/cep/find/13845373/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.connect();

           Scanner scanner = new Scanner(url.openStream()) ;
           while(scanner.hasNext()){
               resposta.append(scanner.next());
           }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(resposta.toString(), CEP.class);
    }
}
