package com.bracketsps.buddy_final.ui.allFragment;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bracketsps.buddy_final.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

import static android.content.Context.LOCATION_SERVICE;


public class FragmentMap extends Fragment implements View.OnClickListener, OnMapReadyCallback, Serializable {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    FloatingActionButton Gps_button;
    private GoogleMap map;
    LocationManager manager;
    double latitude, longitude;
    Location location;
    LocationListener locationListener;



    public FragmentMap() {
        // Required empty public constructor
    }

    public static FragmentMap newInstance() {
        FragmentMap fragment = new FragmentMap();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment__map, container, false);


        //gps manage init
        manager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);


        ///map init
        final SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //button init
        Gps_button = v.findViewById(R.id.Locationbutton);
        Gps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (map == null) {
                    mapFragment.getMapAsync(FragmentMap.this);
                }

                /// take permission for using location
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {


                        ///if we need to explian why and users deny for first time
                        new AlertDialog.Builder(getActivity()).setTitle("Required Location Permission")
                                .setMessage("You have to give the Permission to know GYM locations ")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(getActivity(),
                                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();


                    } else {
/////////////////////////request permission for first time
//

                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);


                    }
                } else {

                    //Gps on
                    if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //there is a internet


                            manager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
                            locationListener = new LocationListener() {
                                @Override
                                public void onLocationChanged(Location location) {



                                }

                                @Override
                                public void onStatusChanged(String provider, int status, Bundle extras) {

                                }

                                @Override
                                public void onProviderEnabled(String provider) {

                                }

                                @Override
                                public void onProviderDisabled(String provider) {

                                }
                            };
                            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
                            location=manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if(location!=null){
                            longitude = location.getLongitude();
                            latitude = location.getLatitude();


                                //Set your marker icon using this method.

                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15.0f));
                                map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("It's Me!"));




                            }


                            map.clear();
                            map.getUiSettings().setMyLocationButtonEnabled(false);

                        } else {
                            Alert_Internet(getActivity());

                        }


                    } else
                        //to let user turn on Gps
                        Alert_Gps(getActivity());
                }


            }
        });


        return v;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng damas = new LatLng(33.510414, 36.278336);
        map.addMarker(new MarkerOptions().position(damas).title("Marker in Damascus"));
        map.moveCamera(CameraUpdateFactory.newLatLng(damas));
        map.setMinZoomPreference(8);

        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));


    }

    private void Alert_Gps(final Context context) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is Off");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");


        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();


    }

    private void Alert_Internet(final Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Internet if Off ");
        alertDialog.setMessage("There is no internet connection,You need internet connection to know your location..! ");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    //define a class for get my current location after user turn on GPS
    class MyLocationListener implements LocationListener {


        @Override
        public void onLocationChanged(Location location) {

            String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }


        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }


}

