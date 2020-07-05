package com.bracketsps.buddy_final.ui.allFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bracketsps.buddy_final.BackgroundWorker;
import com.bracketsps.buddy_final.R;

import static com.bracketsps.buddy_final.BackgroundWorker.loginStatus;
import static com.bracketsps.buddy_final.MainActivity.loginSectionsPagerAdapter;
import static com.bracketsps.buddy_final.MainActivity.s;
import static com.bracketsps.buddy_final.MainActivity.sectionsPagerAdapter;
import static com.bracketsps.buddy_final.MainActivity.viewPager;


public class FragmentMain extends Fragment {

    TextView title_popup;
    Dialog  popupChoose, signupCoachFirst, signupPlayerFirst, popupSignin;
    EditText etUser, etPass;

    public FragmentMain() {
        // Required empty public constructor
    }
    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_fragment_main, container, false);




        /**/
        popupChoose = new Dialog(this.getContext());
        popupChoose.setContentView(R.layout.first_signup_popup);
        Button signup = (Button) v.findViewById(R.id.signupbtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_popup = popupChoose.findViewById(R.id.popup_title);
                title_popup.setText("Sign-up");
                popupChoose.show();
            }
        });

        /**/
        Button cancelSignup = (Button) popupChoose.findViewById(R.id.dismis_popupchoose);
        cancelSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupChoose.dismiss();
            }
        });

        /**/
        signupPlayerFirst = new Dialog(this.getContext());
        signupPlayerFirst.setContentView(R.layout.signup_player_1);
        Button player = (Button) popupChoose.findViewById(R.id.playerbtn);
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_popup = signupPlayerFirst.findViewById(R.id.popup_title);
                title_popup.setText("Sign-up as Player");
                signupPlayerFirst.show();
            }
        });

        /**/
        signupCoachFirst = new Dialog(this.getContext());
        signupCoachFirst.setContentView(R.layout.signup_coach_1);
        Button coach = (Button) popupChoose.findViewById(R.id.coachbtn);
        coach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_popup = signupCoachFirst.findViewById(R.id.popup_title);
                title_popup.setText("Sign-up as Coach");
                signupCoachFirst.show();
            }
        });

        /**/
        Button playerBack = (Button) signupPlayerFirst.findViewById(R.id.player_signup_back);
        playerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupPlayerFirst.dismiss();
                popupChoose.show();
            }
        });

        /**/
        Button coachBack = (Button) signupCoachFirst.findViewById(R.id.coach_signup_back);
        coachBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupCoachFirst.dismiss();
                popupChoose.show();
            }
        });

        /**/
        popupSignin = new Dialog(this.getContext());
        popupSignin.setContentView(R.layout.signin_popup);
        final Button signin = (Button) v.findViewById(R.id.signinbtn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_popup = popupSignin.findViewById(R.id.popup_title);
                title_popup.setText("Sign-in");
                popupSignin.show();
            }
        });

        /**/
        Button cancelSignin = (Button) popupSignin.findViewById(R.id.player_signin_back);
        cancelSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupSignin.dismiss();
            }
        });

        /**/
        Button confirmSignin = (Button) popupSignin.findViewById(R.id.confirm_signin);
        confirmSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etUser = (EditText) popupSignin.findViewById(R.id.etplayerusername);
                etPass = (EditText) popupSignin.findViewById(R.id.etplayerpassword);
                String Username = etUser.getText().toString();
                String Password = etPass.getText().toString();
                String type = "Signin" ;
                BackgroundWorker bgw = new BackgroundWorker(v.getContext());
                bgw.execute(type,Username,Password);
                check();
                //viewPager.setAdapter(loginSectionsPagerAdapter);


                /*FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Fragment f = getActivity().getSupportFragmentManager().findFragmentByTag("yaman");
                transaction.detach(f);
                transaction.commit();*/
            }
        });

        return v;


    }

    public void check(){
        if(loginStatus==true){
            popupSignin.dismiss();
            viewPager.setAdapter(loginSectionsPagerAdapter);
        }
    }
}
