package com.bracketsps.buddy_final;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BackgroundWorker extends AsyncTask<String,String,String> {
    public static boolean loginStatus=false;
    String result="";
    Context context;
    public BackgroundWorker(Context context){
        this.context=context;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        if(type.equals("Signin")){
            try {
                String Username = params[1];
                String Password = params[2];
                String login_url = "http://bracketsps.com/Signin.php";
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String post_data = URLEncoder.encode("Username","UTF-8")+"="+URLEncoder.encode(Username,"UTF-8")+"&"+
                        URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(Password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String line="";
                while ((line = bufferedReader.readLine()) != null ){
                    result = result + line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.contains("success")) {
            loginStatus=true;
            Toast.makeText(context, "done "+result, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "error "+result, Toast.LENGTH_SHORT).show();
            loginStatus=false;
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
