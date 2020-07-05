package com.bracketsps.buddy_final;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bracketsps.buddy_final.ui.allFragment.AllFoodListview;
import com.bracketsps.buddy_final.ui.allFragment.FragmentCalculator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.bracketsps.buddy_final.MainActivity.sectionsPagerAdapter;

public class InfoActivity extends AppCompatActivity {

    Button back;
    String urladdress = "http://bracketsps.com/getFood.php";
    String[] foodname;
    int[] foodcal;
    int[] foodprot;
    String[] foodunit;
    String line = null;
    String result = null;
    BufferedInputStream bis;
    ListView allfoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        allfoods = (ListView) findViewById(R.id.foodlv1);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        collectData();
        AllFoodListview allFoodListview = new AllFoodListview(this,foodname,foodcal,foodprot,foodunit);
        allfoods.setAdapter(allFoodListview);


        back = (Button) findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void collectData() {
        try{
            URL url = new URL(urladdress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            bis = new BufferedInputStream(connection.getInputStream());

        }
        catch (Exception e){
            e.printStackTrace();
        }


        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            StringBuilder sb = new StringBuilder();
            while((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            bis.close();
            result = sb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            foodname = new String[ja.length()];
            foodcal = new int[ja.length()];
            foodprot = new int[ja.length()];
            foodunit = new String[ja.length()];

            for (int i = 0; i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                foodname[i] = jo.getString("Name");
                foodcal[i] = jo.getInt("Calories");
                foodprot[i] = jo.getInt("Protein");
                foodunit[i] = jo.getString("Unit");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
