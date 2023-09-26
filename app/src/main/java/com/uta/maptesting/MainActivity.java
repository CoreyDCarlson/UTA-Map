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
//    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            intent -> {
//            });

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }
}