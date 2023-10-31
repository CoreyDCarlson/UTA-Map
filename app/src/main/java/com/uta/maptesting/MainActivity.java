package com.uta.maptesting;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback
{
    private GoogleMap mMap;



    // creating a variable
    // for search view.
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // initializing our search view.
        searchView = findViewById(R.id.idSearchView);

        // Obtain the SupportMapFragment and get notified
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);

        // adding on query listener for our search view.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // on below line we are getting the
                // location name from search view.
                String location = searchView.getQuery().toString();

                // below line is to create a list of address
                // where we will store the list of all address.
                List<Address> addressList = null;

                // checking if the entered location is null or not.
                // on below line we are creating and initializing a geo coder.
                Geocoder geocoder = new Geocoder(MainActivity.this);
                try {
                    // on below line we are getting location from the
                    // location name and adding that location to address list.
                    addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // on below line we are getting the location
                // from our list a first position.
                Address address = Objects.requireNonNull(addressList).get(0);

                // on below line we are creating a variable for our location
                // where we will add our locations latitude and longitude.
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                // on below line we are adding marker to that position.
                mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                // below line is to animate camera to that position.
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        // at last we calling our map fragment to update.
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setMapStyle
                (
                        MapStyleOptions.loadRawResourceStyle
                                (
                                        this,
                                        R.raw.theme
                                )
                );

        /*LatLng UTA = new LatLng(32.731636, -97.111128);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(UTA));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));*/

        LatLngBounds UTAbounds = new LatLngBounds(
                new LatLng(32.721262, -97.131970), // SW bounds
                new LatLng(32.735551, -97.107085)  // NE bounds
        );
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UTAbounds.getCenter(), 15));

        googleMap.setLatLngBoundsForCameraTarget(UTAbounds);
    }
}
