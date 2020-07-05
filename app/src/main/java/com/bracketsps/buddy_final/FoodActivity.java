package com.bracketsps.buddy_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bracketsps.buddy_final.ui.allFragment.CustomAdapter;

import static com.bracketsps.buddy_final.ui.allFragment.FragmentCalculator.fadapter;
import static com.bracketsps.buddy_final.ui.allFragment.FragmentCalculator.foo;
import static com.bracketsps.buddy_final.ui.allFragment.FragmentCalculator.flv;

public class FoodActivity extends AppCompatActivity {

    Button gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        flv = (ListView) findViewById(R.id.foodlv);
        fadapter = new CustomAdapter(this, foo);
        flv.setAdapter(fadapter);
        fadapter.notifyDataSetChanged();


        gen = (Button) findViewById(R.id.backbtn);
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
