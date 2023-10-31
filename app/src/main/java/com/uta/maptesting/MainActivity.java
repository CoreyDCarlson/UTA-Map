package com.uta.maptesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MapStyleOptions;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
{
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap)
    {
        googleMap.setMapStyle
        (
            MapStyleOptions.loadRawResourceStyle
            (
    this,
                R.raw.theme
            )
        );

        LatLng UTA = new LatLng(32.731636,-97.111128);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(UTA));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        googleMap.setOnMapLoadedCallback
        {
            val bounds = LatLngBounds.builder();
            places.forEach
            {
                bounds.include(it.latLng);
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }
}
