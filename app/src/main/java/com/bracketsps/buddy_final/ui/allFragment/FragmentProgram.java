package com.bracketsps.buddy_final.ui.allFragment;


import android.database.sqlite.SQLiteDatabase;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bracketsps.buddy_final.R;
import com.bracketsps.buddy_final.metadata.SavingProgram;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;


public class FragmentProgram extends Fragment {



    Button homeBtn, calBtn, proBtn, nearBtn;
    private GestureDetectorCompat mgestureDetector;
    private RelativeLayout pro_layout;
    float x;
    /////popup
    ImageButton img_fit, img_slim;
    ImageView popup_img;
    Dialog popup;
    TextView title_popup;
    Spinner kg_spinner, days_spinner;
    ArrayAdapter<String> adapter;
    Button cancel_popup, confirm_popup;
    EditText weight_text;
    SavingProgram saving_pro = new SavingProgram();
    String[] Spinner_Fiting_items = {
            "maintain my current weight",
            "Gian 0.75 Kg per week",
            "Gian 0.50 Kg per week",
            "Gian 0.25 Kg per week"};
    String[] Spinner_Slimming_items = {
            "maintain my current weight",
            "Lose 0.75 Kg per week",
            "Lose 0.50 Kg per week",
            "Lose 0.25 Kg per week"};
    String[] Days_per_week = {"1", "2", "3", "4", "5", "6", "7"};


    public FragmentProgram() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static FragmentProgram newInstance() {
        FragmentProgram fragment = new FragmentProgram();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_pro, container, false);

        ///img_fit+slim
        img_fit = v.findViewById(R.id.img_fit);

        img_slim = v.findViewById(R.id.img_slim);
        popup_img = v.findViewById(R.id.popup_image);
        ////// dialog
        popup = new Dialog(v.getContext());
        popup.setContentView(R.layout.popup_layout);
        days_spinner = popup.findViewById(R.id.spinner_day);
        kg_spinner = popup.findViewById(R.id.spinner_kg);
        weight_text = popup.findViewById(R.id.weight_edittext);
        adapter = new ArrayAdapter<String>(v.getContext(), R.layout.custom_spinner, Days_per_week);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        days_spinner.setAdapter(adapter);
        days_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0: {
                        saving_pro.setTrain_day(1);

                        break;
                    }
                    case 1: {

                        saving_pro.setTrain_day(2);

                        break;
                    }
                    case 2: {
                        saving_pro.setTrain_day(3);

                        break;
                    }
                    case 3: {
                        saving_pro.setTrain_day(4);


                        break;
                    }
                    case 4: {
                        saving_pro.setTrain_day(5);

                        break;
                    }
                    case 5: {
                        saving_pro.setTrain_day(6);

                        break;
                    }
                    case 6: {
                        saving_pro.setTrain_day(7);


                        break;
                    }


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        kg_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        saving_pro.setKg_week("0");
                        break;
                    }


                    case 1: {
                        saving_pro.setKg_week("0.75");

                        break;
                    }
                    case 2: {
                        saving_pro.setKg_week("0.50");

                        break;
                    }
                    case 3: {
                        saving_pro.setKg_week("0.25");

                        break;
                    }


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ////pop up button
        cancel_popup = popup.findViewById(R.id.dismis_popup);
        confirm_popup = popup.findViewById(R.id.confirm_popup);


        img_fit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title_popup = popup.findViewById(R.id.popup_title);
                title_popup.setText("Gain weight");
                popup_img = popup.findViewById(R.id.popup_image);
                popup_img.setImageResource(R.drawable.fiting);
                adapter = new ArrayAdapter<String>(v.getContext(), R.layout.custom_spinner, Spinner_Fiting_items);
                adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
                kg_spinner.setAdapter(adapter);
                saving_pro.setType_pro('+');
                //to make white corner disappear
                popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                popup.show();

            }
        });

        img_slim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title_popup = popup.findViewById(R.id.popup_title);
                title_popup.setText("Lose weight");
                popup_img = popup.findViewById(R.id.popup_image);
                popup_img.setImageResource(R.drawable.slimming);
                adapter = new ArrayAdapter<String>(v.getContext(), R.layout.custom_spinner, Spinner_Slimming_items);
                adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
                kg_spinner.setAdapter(adapter);
                saving_pro.setType_pro('-');
                //to make white corner disappear
                popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popup.show();
            }
        });


        cancel_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                popup.dismiss();

            }
        });



        confirm_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (weight_text.getText().toString().equals("") || weight_text.getText().toString().equals("0")) {
                    Toast.makeText(getContext(), "There is no weight", Toast.LENGTH_LONG).show();


                } else {

                    //code of the progrmas activity
                    saving_pro.setIsthere(true);
                    saving_pro.setWeight(Double.parseDouble(weight_text.getText().toString()));


                    //write to file

                    try {
                        FileOutputStream write_file = getActivity().openFileOutput(saving_pro.getFile_name(), MODE_PRIVATE);
                        ObjectOutputStream is = new ObjectOutputStream(write_file);
                        is.writeObject(saving_pro);
                        is.close();
                        write_file.close();
                        System.out.println("done");

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    popup.dismiss();


                    PagerAdapter s = new SectionsPagerAdapter(getContext(), getChildFragmentManager());
                    s.notifyDataSetChanged();
                    ViewPager vv = getActivity().findViewById(R.id.view_pager);
                    s = vv.getAdapter();
                    vv.setCurrentItem(0, true);
                    Fragment f = new FragmentCalculator();


                }


            }
        });


        return v;
    }


}


