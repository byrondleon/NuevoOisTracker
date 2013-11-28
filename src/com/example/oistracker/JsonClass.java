package com.example.oistracker; 

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonClass
{
    public void registrar(String Strlatitud, String Strlongitud, String idUsuario)
    {
        try {
            // Crear objeto json  o cuerpo de lo que vas a enviar
            JSONObject jsonObj = new JSONObject();

            // Datos a enviar
            jsonObj.put("latitud", Strlatitud);
            jsonObj.put("longitud", Strlongitud);
            jsonObj.put("idUsuario", idUsuario);

            //url del POST
            HttpPost httpPost = new HttpPost("http://locator.buzzcoapp.com/json/v1/setLocation");

            //agregas el objeto json a una entidad
            StringEntity entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);

            //tipo de contenedor de la entidad en este caso un aplicativo json
            entity.setContentType("application/json");

            //agregar la entidad al httpost
            httpPost.setEntity(entity);

            //tiempos de coneccion
            HttpClient client = new DefaultHttpClient(timeOut(3000, 5000));

            //obtener en un httprespond el resultado de la peticion
            HttpResponse response = client.execute(httpPost);

            //paja para desconponer el httpresponse
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();

            //obtener ya la respuesta en un string
            String page = sb.toString();

            //pasar el string al objeto json para manipularlo mejor
            JSONObject jsonObject = new JSONObject(page);

            //obtener cada tags del json en un string
            String jsonObject1 = jsonObject.getString("status");

        } catch (ClientProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }



    public HttpParams timeOut(int timeout, int timesocket)
    {
        HttpParams httpParameters = new BasicHttpParams();

        int timeoutConnection = timeout;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);

        int timeoutSocket = timesocket;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

        return httpParameters;
    }

    }