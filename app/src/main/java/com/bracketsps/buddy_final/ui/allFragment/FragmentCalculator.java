package com.bracketsps.buddy_final.ui.allFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bracketsps.buddy_final.FoodActivity;
import com.bracketsps.buddy_final.InfoActivity;
import com.bracketsps.buddy_final.R;
import com.bracketsps.buddy_final.metadata.SavingFoodList;
import com.bracketsps.buddy_final.metadata.SavingProgram;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class FragmentCalculator extends Fragment implements Serializable {

    TextView quan_tv;
    EditText etquan;
    String fname, funit;
    int fquan = 0, caloriesPG, proteinPG, caloriesPU = 0, proteinPU = 0;
    public static int sumcal = 0, sumpro = 0;
    public static ArrayList<Food> foo = new ArrayList<Food>();
    CustomAdapterResult radapter;
    public static CustomAdapter fadapter;
    Button add, clr, show;
    FloatingActionButton infoBtn;
    public static ListView rlv, flv;
    SavingFoodList savedFoodList;


    public FragmentCalculator() {
        // Required empty public constructor
    }

    public static FragmentCalculator newInstance() {
        FragmentCalculator fragment = new FragmentCalculator();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_fragment_calculator, container, false);
        //initialize all UI elements
        etquan = v.findViewById(R.id.f_quan);
        show = v.findViewById(R.id.showbtn);
        add = v.findViewById(R.id.addbtn);
        clr = v.findViewById(R.id.clrbtn);
        infoBtn = v.findViewById(R.id.floatingActionButton);
        rlv = v.findViewById(R.id.reslv);
        flv = v.findViewById(R.id.foodlv);
        final Spinner spinner = v.findViewById(R.id.spinner);
        radapter = new CustomAdapterResult(getContext(), sumpro, sumcal);

        //read a foods form file at first
        FileInputStream read_file = null;
        savedFoodList = new SavingFoodList();
        try {
            read_file = getActivity().openFileInput(savedFoodList.getFile_name());
            ObjectInputStream is = new ObjectInputStream(read_file);
            savedFoodList = (SavingFoodList) is.readObject();
            is.close();
            read_file.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //init data form food file
        foo = savedFoodList.getFoods();
        sumcal=savedFoodList.getCalories();
        sumpro=savedFoodList.getProtien();
        radapter.notifyDataSetChanged();

        //set on clicks for EditText
        etquan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {

                } catch (Exception e) {

                }

            }
        });
        etquan.setCursorVisible(false);
        //set on clicks for Button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText f_quan = v.findViewById(R.id.f_quan);

                //To ensure that user enter every thing we need
                if (fname.equals("")) {
                    Toast.makeText(getContext(), "Please enter a food ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (f_quan.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter a number ", Toast.LENGTH_SHORT).show();
                    return;
                }

                int fquan;
                fquan = Integer.parseInt(etquan.getText().toString());
                foo.add(new Food(fname, fquan, funit));
                caloriesPU = caloriesPG * fquan;
                proteinPU = proteinPG * fquan;
                //put the values inside fields and to object that will write to file
                sumcal = sumcal + caloriesPU;
                sumpro = sumpro + proteinPU;
                savedFoodList.setCalories(sumcal);
                savedFoodList.setProtien(sumpro);
                radapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Added " + fquan + " " + funit + " of " + fname, Toast.LENGTH_SHORT).show();
                //To hide keyboard after adding
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                //when add a food go and write the data to a file
                try {
                    FileOutputStream write_file = getActivity().openFileOutput(savedFoodList.getFile_name(), Context.MODE_PRIVATE);
                    ObjectOutputStream is = new ObjectOutputStream(write_file);
                    is.writeObject(savedFoodList);
                    is.close();
                    write_file.close();
                    System.out.println("done");

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //when add must clear data form input fields
                etquan.setText("");
                spinner.setSelection(0);


            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent food = new Intent(getActivity(), FoodActivity.class);
                startActivity(food);
            }
        });

        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete data from food file
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Delete the food list");
                alertDialog.setMessage("are you sure?");
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        File file = new File(getActivity().getFilesDir().getPath() + "/Food.bin");
                        foo.removeAll(foo);
                        sumcal = 0;
                        sumpro = 0;
                        fadapter.notifyDataSetChanged();
                        radapter.notifyDataSetChanged();
                        if (file.delete()) {


                        }
                        dialog.dismiss();
                    }
                });

                alertDialog.show();


            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(getActivity(), InfoActivity.class);
                startActivity(info);
            }
        });

        rlv.setAdapter(radapter);

        try {
            fadapter = new CustomAdapter(getContext(), foo);
            flv.setAdapter(fadapter);

        } catch (Exception e) {
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.food_spinner, getResources().getStringArray(R.array.food));
        adapter.setDropDownViewResource(R.layout.food_spinner_dropdown);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quan_tv = v.findViewById(R.id.quantv);
                switch (i) {
                    case 0:
                        fname = "";
                        quan_tv.setText("");
                        break;
                    case 1:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Indomie";
                        quan_tv.setText("Piece");
                        funit = quan_tv.getText().toString();
                        break;
                    case 2:
                        caloriesPG = 200;
                        proteinPG = 5;
                        fname = "Oat";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 3:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Tuna";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 4:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Egg";
                        quan_tv.setText("Piece");
                        funit = quan_tv.getText().toString();
                        break;
                    case 5:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Banana";
                        quan_tv.setText("Piece");
                        funit = quan_tv.getText().toString();
                        break;
                    case 6:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Rice";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 7:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Peanut";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 8:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Chocolate";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 9:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Peanut Butter";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 10:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Milk";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                    case 11:
                        caloriesPG = 250;
                        proteinPG = 10;
                        fname = "Protein";
                        quan_tv.setText("Gram");
                        funit = quan_tv.getText().toString();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return v;
    }


}
