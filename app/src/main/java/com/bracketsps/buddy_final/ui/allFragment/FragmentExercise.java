package com.bracketsps.buddy_final.ui.allFragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bracketsps.buddy_final.R;
import com.bracketsps.buddy_final.metadata.SavingProgram;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class FragmentExercise extends Fragment implements Serializable {
    RelativeLayout exercises_layout;
    float x;
    ListView plv;
    ArrayList<Programme> pro = new ArrayList<>();
    CustomAdapterProgramme padapter = null;
    GestureDetector detector;
    SavingProgram saved_pro = new SavingProgram();
    TextView day, calorise_numb;
    int number_of_day = 0;
    Programme[] Cardio, Fday, Sday;
    int[] Cardio_images_start = new int[]{R.drawable.start1
            , R.drawable.start2
            , R.drawable.start3
            , R.drawable.start4
            , R.drawable.start5
            , R.drawable.start6
            , R.drawable.start7};
    int[] Cardio_images_end = new int[]{R.drawable.end1
            , R.drawable.end2
            , R.drawable.end3
            , R.drawable.end4
            , R.drawable.end5
            , R.drawable.end6
            , R.drawable.end7};
    Programme tmp;
    Button rest;
    ImageButton next_day, back_day;

    public FragmentExercise() {
        // Required empty public constructor
    }

    public static FragmentExercise newInstance() {
        FragmentExercise fragment = new FragmentExercise();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_fragment_exercise, container, false);

        //init button fo rest
        rest = v.findViewById(R.id.rest_pro);
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Delete the programme");
                alertDialog.setMessage("are you sure...?\nit will delete all programme you choose ");
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        File file = new File(getActivity().getFilesDir().getPath() + "/pro.bin");

                        if (file.delete()) {
                            //here we must put pro fragments

                            PagerAdapter s = new SectionsPagerAdapter(getContext(), getChildFragmentManager());
                            s.notifyDataSetChanged();
                            ViewPager vv = getActivity().findViewById(R.id.view_pager);
                            s = vv.getAdapter();
                            vv.setCurrentItem(0, true);

                        } else
                            Toast.makeText(getActivity(), "The programme not here..!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                alertDialog.show();


            }
        });


        //init array of exercise
        Cardio = new Programme[6];
        Fday = new Programme[6];
        Sday = new Programme[6];
        String name_start = "start";
        String name_end = "end";

        for (int i = 0; i < 6; i++) {

            tmp = new Programme();
            tmp.setTilte("name of exerc");
            tmp.setImg1(Cardio_images_start[i]);
            tmp.setImg2(Cardio_images_end[i]);


            pro.add(tmp);


        }


        //read data from file
        FileInputStream read_file = null;
        try {
            read_file = getActivity().openFileInput(saved_pro.getFile_name());
            ObjectInputStream is = new ObjectInputStream(read_file);
            saved_pro = (SavingProgram) is.readObject();
            is.close();
            read_file.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //init the number of day
        day = v.findViewById(R.id.text_day);
        number_of_day = 1;
        day.setText("Day " + number_of_day + " ");
        //init button navigation of exer
        next_day = v.findViewById(R.id.next_button_exer);
        back_day = v.findViewById(R.id.back_button_exer);
        if(saved_pro.getTrain_day()==1)
        {
            next_day.setVisibility(View.INVISIBLE);
            back_day.setVisibility(View.INVISIBLE);
        }
        else {
        next_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_data_right();
            }
        });
        back_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_data_left();
            }
        });
        }


        //count the calories
        calorise_numb = v.findViewById(R.id.calorise_numb);
        System.out.println(saved_pro.getWeight());
        double t1 = (saved_pro.getWeight() * 2.20462) * 17;
        double t2 = (saved_pro.getWeight() * 2.20462) * 14;


        if (saved_pro.getType_pro() == '+') {
            switch (saved_pro.getKg_week()) {
                case "0": {
                    calorise_numb.setText(new DecimalFormat("##.##").format(t1) + "-" + new DecimalFormat("##.##").format(t2));

                    break;
                }
                case "0.25": {

                    calorise_numb.setText((new DecimalFormat("##.##").format(t1 + 250)) + "-" + (new DecimalFormat("##.##").format(t2 + 250)));

                    break;
                }
                case "0.50": {
                    calorise_numb.setText((new DecimalFormat("##.##").format(t1 + 550)) + "-" + (new DecimalFormat("##.##").format(t2 + 550)));


                    break;
                }
                case "0.75": {
                    calorise_numb.setText((new DecimalFormat("##.##").format(t1 + 800)) + "-" + (new DecimalFormat("##.##").format(t2 + 800)));


                    break;
                }


            }
        } else {
            switch (saved_pro.getKg_week()) {
                case "0": {
                    calorise_numb.setText((new DecimalFormat("##.##").format(t1)) + "-" + (new DecimalFormat("##.##").format(t2)));

                    break;
                }
                case "0.25": {

                    calorise_numb.setText((new DecimalFormat("##.##").format(t1 - 250)) + "-" + (new DecimalFormat("##.##").format(t2 - 250)));

                    break;
                }
                case "0.50": {
                    calorise_numb.setText((new DecimalFormat("##.##").format(t1 - 550)) + "-" + (new DecimalFormat("##.##").format(t2 - 550)));


                    break;
                }
                case "0.75": {
                    calorise_numb.setText((new DecimalFormat("##.##").format(t1 - 850)) + "-" + (new DecimalFormat("##.##").format(t2 - 850)));


                    break;
                }


            }
        }

        plv = v.findViewById(R.id.list_day_exerc);
        padapter = new CustomAdapterProgramme(getContext(), pro);
        plv.setAdapter(padapter);

        padapter.notifyDataSetChanged();
        return v;
    }

    private void change_data_right() {
        tmp = new Programme();

        if (number_of_day < saved_pro.getTrain_day()) {
            number_of_day++;
            day.setText("Day " + number_of_day + " ");
            System.out.println(saved_pro.getTrain_day());
            pro.clear();
            switch (number_of_day) {
                case 1: {
                    for (int i = 0; i < 6; i++) {
                        tmp = new Programme();


                        tmp.setTilte("name of exerc");
                        tmp.setImg1(Cardio_images_start[i]);
                        tmp.setImg2(Cardio_images_end[i]);


                        pro.add(tmp);


                    }
                    padapter.notifyDataSetChanged();
                    break;
                }
                case 2:
                case 4:
                case 6: {
                    tmp.setTilte("asdad");
                    tmp.setImg1(Cardio_images_start[6]);
                    tmp.setImg2(Cardio_images_end[6]);
                    pro.add(tmp);
                    padapter.notifyDataSetChanged();


                    break;
                }

                case 3:
                case 5:
                case 7: {
                    tmp = new Programme();

                    tmp.setTilte("name of exer");
                    tmp.setImg1(Cardio_images_start[4]);
                    tmp.setImg2(Cardio_images_end[4]);
                    pro.add(tmp);
                    tmp = new Programme();

                    tmp.setTilte("name of exer");
                    tmp.setImg1(Cardio_images_start[5]);
                    tmp.setImg2(Cardio_images_end[5]);
                    pro.add(tmp);

                    padapter.notifyDataSetChanged();


                    break;
                }


            }


        }

    }

    private void change_data_left() {

        if (number_of_day > 1) {
            number_of_day--;
            day.setText("Day " + number_of_day + " ");
            System.out.println(saved_pro.getTrain_day());
            pro.clear();
            switch (number_of_day) {
                case 1: {
                    for (int i = 0; i < 4; i++) {
                        tmp = new Programme();

                        tmp.setTilte("name of exerc");
                        tmp.setImg1(Cardio_images_start[i]);
                        tmp.setImg2(Cardio_images_end[i]);


                        pro.add(tmp);


                    }
                    padapter.notifyDataSetChanged();
                    break;
                }
                case 2:
                case 4:
                case 6: {
                    tmp = new Programme();

                    tmp.setTilte("asdad");
                    tmp.setImg1(Cardio_images_start[6]);
                    tmp.setImg2(Cardio_images_end[6]);
                    pro.add(tmp);
                    padapter.notifyDataSetChanged();


                    break;
                }

                case 3:
                case 5:
                case 7: {
                    tmp = new Programme();

                    tmp.setTilte("name of exer");
                    tmp.setImg1(Cardio_images_start[4]);
                    tmp.setImg2(Cardio_images_end[4]);
                    pro.add(tmp);
                    tmp = new Programme();

                    tmp.setTilte("name of exer");
                    tmp.setImg1(Cardio_images_start[5]);
                    tmp.setImg2(Cardio_images_end[5]);
                    pro.add(tmp);

                    padapter.notifyDataSetChanged();


                    break;
                }


            }


        }

    }


}



