package com.example.tarea6_downloadmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tarea6_downloadmanager.Adapatador.AdaptarRecyclerFicheros;
import com.example.tarea6_downloadmanager.Modelos.ficheros;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView Recyclerficheritos;
    List<ficheros> listaFiche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExtracciónDeDatosVolley();
        setContentView(R.layout.activity_main);

        ArrayList<String> Listapermisos = new ArrayList<String>();

        Listapermisos.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Listapermisos.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        getPermission(Listapermisos);



    }

    public void getPermission(ArrayList<String> permisosSolicitados){

        ArrayList<String> listPermisosNOAprob = getPermisosNoAprobados(permisosSolicitados);
        if (listPermisosNOAprob.size()>0)
            if (Build.VERSION.SDK_INT >= 23)
                requestPermissions(listPermisosNOAprob.toArray(new String[listPermisosNOAprob.size()]), 1);

    }


    public ArrayList<String> getPermisosNoAprobados(ArrayList<String>  listaPermisos) {
        ArrayList<String> list = new ArrayList<String>();
        for(String permiso: listaPermisos) {
            if (Build.VERSION.SDK_INT >= 23)
                if(checkSelfPermission(permiso) != PackageManager.PERMISSION_GRANTED)
                    list.add(permiso);

        }
        return list;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        String s="";
        if(requestCode==1)    {
            for(int i =0; i<permissions.length;i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED)
                    s=s + "OK " + permissions[i] + "\n";
                else
                    s=s + "NO  " + permissions[i] + "\n";
            }
            Toast.makeText(this.getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }


    public void AdapterRecyclerFicheritos(List ListitaE)
    {
        AdaptarRecyclerFicheros ARFicheros = new AdaptarRecyclerFicheros(this,ListitaE);
        Recyclerficheritos = (RecyclerView) findViewById(R.id.idlistview);
        Recyclerficheritos.setLayoutManager(new LinearLayoutManager(this));
        Recyclerficheritos.setAdapter(ARFicheros);
    }


    public void ExtracciónDeDatosVolley() {
        listaFiche= new ArrayList<>();
        RequestQueue RequestQueue = Volley.newRequestQueue(getApplicationContext());
        String urlJSON = "https://my-json-server.typicode.com/mmendezg2/jsonFicheritos/db";
        JsonObjectRequest JsonOR = new JsonObjectRequest(Request.Method.GET,
                urlJSON, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int contadorrr=0;
                    JSONArray JSFiche = response.getJSONArray("ficheros");
                    for (int i = 0; i < JSFiche.length(); i++) {
                        JSONObject info= new JSONObject(JSFiche.get(i).toString());
                        String idfiche, descripcionFich, Fecha, Tema, url, numeroarch;
                        idfiche=info.getString("idfiche");
                        descripcionFich=info.getString("descripcionFich");
                        Fecha=info.getString("Fecha");
                        Tema=info.getString("Tema");
                        url=info.getString("url");
                        contadorrr=contadorrr+1;
                        numeroarch=String.valueOf(contadorrr);

                        ficheros ListFiche = new ficheros(idfiche,descripcionFich, Fecha, Tema, url,numeroarch);

                        listaFiche.add(ListFiche);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                AdapterRecyclerFicheritos(listaFiche);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        RequestQueue.add(JsonOR);


    }





}