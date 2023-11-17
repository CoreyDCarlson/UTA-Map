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

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    // creating a variable
    // for search view.
    SearchView searchView;
    private GoogleMap mMap;

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

        LatLng UTA = new LatLng(32.731636, -97.111128);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(UTA));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));

        /*

        LatLngBounds UTAbounds = new LatLngBounds(
                new LatLng(32.721262, -97.131970), // SW bounds
                new LatLng(32.735551, -97.107085)  // NE bounds
        );
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UTA, 15));
        googleMap.setLatLngBoundsForCameraTarget(UTAbounds);

         */

        LatLng building1 = new LatLng(32.734666, -97.114125);
        mMap.addMarker(new MarkerOptions()
                .position(building1)
                .title("Academic Buildings - A - SWCA"));

        LatLng building2 = new LatLng(32.734707, -97.113682);
        mMap.addMarker(new MarkerOptions()
                .position(building2)
                .title("Academic Buildings - B - SWCB"));

        LatLng building3 = new LatLng(32.727495, -97.107354);
        mMap.addMarker(new MarkerOptions()
                .position(building3)
                .title("Aerodynamics Research Building - ARB"));

        LatLng building4 = new LatLng(32.727804, -97.124741);
        mMap.addMarker(new MarkerOptions()
                .position(building4)
                .title("Amphibian and Reptile Diversity Research Center - ARC"));

        LatLng building5 = new LatLng(32.733351, -97.109269);
        mMap.addMarker(new MarkerOptions()
                .position(building5)
                .title("Bookstore - BOOK"));

        LatLng building6 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(building6)
                .title("Business Building - COBA"));

        LatLng building7 = new LatLng(32.729701, -97.127416);
        mMap.addMarker(new MarkerOptions()
                .position(building7)
                .title("C.R. Gilstrap Athletic Center - GILS"));

        LatLng building8 = new LatLng(32.731265, -97.116128);
        mMap.addMarker(new MarkerOptions()
                .position(building8)
                .title("CAPPA Building - ARCH"));

        LatLng building9 = new LatLng(32.73803198, -97.11480618);
        mMap.addMarker(new MarkerOptions()
                .position(building9)
                .title("CAPPA Community Design Lab - DBI"));

        LatLng building10 = new LatLng(32.7321, -97.116078);
        mMap.addMarker(new MarkerOptions()
                .position(building10)
                .title("CAPPA North - CMPC"));

        LatLng building11 = new LatLng(32.73041985, -97.11588374);
        mMap.addMarker(new MarkerOptions()
                .position(building11)
                .title("CAPPA South - A - SHC"));

        LatLng building12 = new LatLng(32.73034154, -97.11553463);
        mMap.addMarker(new MarkerOptions()
                .position(building12)
                .title("CAPPA South - B - SHO"));

        LatLng building13 = new LatLng(32.73034154, -97.11553463);
        mMap.addMarker(new MarkerOptions()
                .position(building13)
                .title("CAPPA South - B - SHO"));

        LatLng building14 = new LatLng(32.73065, -97.112586);
        mMap.addMarker(new MarkerOptions()
                .position(building14)
                .title("Carlisle Hall"));

        LatLng building15 = new LatLng(32.73065, -97.112586);
        mMap.addMarker(new MarkerOptions()
                .position(building15)
                .title("Carlisle Hall - CARH"));

        LatLng building16 = new LatLng(32.823796, -96.845489);
        mMap.addMarker(new MarkerOptions()
                .position(building16)
                .title("Center for Addiction and Recovery Studies - CARS"));

        LatLng building17 = new LatLng(32.73359974, -97.10744466);
        mMap.addMarker(new MarkerOptions()
                .position(building17)
                .title("Center for Entrepreneurship and Technology Development - CEEI"));

        LatLng building18 = new LatLng(32.730396, -97.111744);
        mMap.addMarker(new MarkerOptions()
                .position(building18)
                .title("Chemistry & Physics Building - CPB"));

        LatLng building19 = new LatLng(32.727543, -97.125545);
        mMap.addMarker(new MarkerOptions()
                .position(building19)
                .title("Civil Engineering Lab Building - CELB"));

        LatLng building20 = new LatLng(32.730801, -97.111507);
        mMap.addMarker(new MarkerOptions()
                .position(building20)
                .title("College Hall - CH"));

        LatLng building21 = new LatLng(32.730187, -97.108181);
        mMap.addMarker(new MarkerOptions()
                .position(building21)
                .title("College Park Center - CPC"));

        LatLng building22 = new LatLng(32.726832, -97.108455);
        mMap.addMarker(new MarkerOptions()
                .position(building22)
                .title("Continuing Ed & Workforce Development - CEWF"));

        LatLng building23 = new LatLng(32.726782, -97.107216);
        mMap.addMarker(new MarkerOptions()
                .position(building23)
                .title("DED Technical Training Ctr. - DE"));

        LatLng building24 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(building24)
                .title("E.H. Hereford University Center - UC"));

        LatLng building25 = new LatLng(32.731329, -97.114177);
        mMap.addMarker(new MarkerOptions()
                .position(building25)
                .title("Earth & Environmental Sciences - EES"));

        LatLng building26 = new LatLng(32.732326, -97.11267);
        mMap.addMarker(new MarkerOptions()
                .position(building26)
                .title("Engineering Lab Building - ELAB"));

        LatLng building27 = new LatLng(32.733211, -97.112513);
        mMap.addMarker(new MarkerOptions()
                .position(building27)
                .title("Engineering Research Building - ERB"));

        LatLng building28 = new LatLng(32.732313, -97.122161);
        mMap.addMarker(new MarkerOptions()
                .position(building28)
                .title("Environmental Health & Safety - EH"));

        LatLng building29 = new LatLng(32.733159, -97.123303);
        mMap.addMarker(new MarkerOptions()
                .position(building29)
                .title("Environmental Health & Safety (West) - EHW"));

        LatLng building30 = new LatLng(32.736982, -97.109236);
        mMap.addMarker(new MarkerOptions()
                .position(building30)
                .title("Finance and Administration Annex (Watson building) - FAAA"));

        LatLng building31 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(building31)
                .title("Fine Arts Building - FA"));

        LatLng building32 = new LatLng(32.749654, -97.324825);
        mMap.addMarker(new MarkerOptions()
                .position(building32)
                .title("Fort Worth Center - UTASF"));

        LatLng building33 = new LatLng(32.734244, -97.114176);
        mMap.addMarker(new MarkerOptions()
                .position(building33)
                .title("General Academic Classroom Building - GACB"));

        LatLng building34 = new LatLng(32.729701, -97.127416);
        mMap.addMarker(new MarkerOptions()
                .position(building34)
                .title("Gilstrap Athletic Center - GILS"));

        LatLng building35 = new LatLng(32.729641, -97.112045);
        mMap.addMarker(new MarkerOptions()
                .position(building35)
                .title("Hammond Hall - HH"));

        LatLng building36 = new LatLng(32.730456, -97.110756);
        mMap.addMarker(new MarkerOptions()
                .position(building36)
                .title("Health Center - HLTH"));

        LatLng building37 = new LatLng(32.72966, -97.112687);
        mMap.addMarker(new MarkerOptions()
                .position(building37)
                .title("Library - LIBR"));

        LatLng building38 = new LatLng(32.727747, -97.124157);
        mMap.addMarker(new MarkerOptions()
                .position(building38)
                .title("Library Collection Depository & OIT Office Building - LCDO"));

        LatLng building39 = new LatLng(32.728641, -97.112895);
        mMap.addMarker(new MarkerOptions()
                .position(building39)
                .title("Life Science Building - LS"));

        LatLng building40 = new LatLng(32.731925, -97.11705);
        mMap.addMarker(new MarkerOptions()
                .position(building40)
                .title("Maverick Activities Center - MAC"));

        LatLng building41 = new LatLng(32.729459, -97.111647);
        mMap.addMarker(new MarkerOptions()
                .position(building41)
                .title("Maverick Parking Garage - GARA"));

        LatLng building42 = new LatLng(32.729209, -97.126382);
        mMap.addMarker(new MarkerOptions()
                .position(building42)
                .title("Maverick Stadium - STAD"));

        LatLng building43 = new LatLng(32.733867, -97.122085);
        mMap.addMarker(new MarkerOptions()
                .position(building43)
                .title("Military & Veteran Services - VAC"));

        LatLng building44 = new LatLng(32.732407, -97.115512);
        mMap.addMarker(new MarkerOptions()
                .position(building44)
                .title("Nanofab Building - NANO"));

        LatLng building45 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(building45)
                .title("Nedderman Hall - NH"));

        LatLng building46 = new LatLng(32.729425, -97.124411);
        mMap.addMarker(new MarkerOptions()
                .position(building46)
                .title("Parking & Transportation Services - PATS"));

        LatLng building47 = new LatLng(32.729425, -97.124411);
        mMap.addMarker(new MarkerOptions()
                .position(building47)
                .title("Parking at UT Arlington - PATS"));

        LatLng building48 = new LatLng(32.73093, -97.117653);
        mMap.addMarker(new MarkerOptions()
                .position(building48)
                .title("Physical Education - PE"));

        LatLng building49 = new LatLng(32.728415, -97.111283);
        mMap.addMarker(new MarkerOptions()
                .position(building49)
                .title("Pickard Hall - PKH"));

        LatLng building50 = new LatLng(32.730905, -97.112856);
        mMap.addMarker(new MarkerOptions()
                .position(building50)
                .title("Preston Hall - PH"));

        LatLng building51 = new LatLng(32.730934, -97.11218);
        mMap.addMarker(new MarkerOptions()
                .position(building51)
                .title("Ransom Hall - RH"));

        LatLng building52 = new LatLng(32.72764243, -97.11166903);
        mMap.addMarker(new MarkerOptions()
                .position(building52)
                .title("School of Social Work and College of Nursing and Health Innovation Smart Hospital Building - SWSH"));

        LatLng building53 = new LatLng(32.728122, -97.112746);
        mMap.addMarker(new MarkerOptions()
                .position(building53)
                .title("Science & Engineering Innovation & Research Building - SEIR"));

        LatLng building54 = new LatLng(32.730438, -97.114186);
        mMap.addMarker(new MarkerOptions()
                .position(building54)
                .title("Science Hall - SH"));

        LatLng building55 = new LatLng(32.728687, -97.124942);
        mMap.addMarker(new MarkerOptions()
                .position(building55)
                .title("Studio Arts Center - SAC"));

        LatLng building56 = new LatLng(32.733882, -97.121148);
        mMap.addMarker(new MarkerOptions()
                .position(building56)
                .title("Swift Center - SC"));

        LatLng building57 = new LatLng(32.732003, -97.119913);
        mMap.addMarker(new MarkerOptions()
                .position(building57)
                .title("Tennis Center - TENN"));

        LatLng building58 = new LatLng(32.729723, -97.115497);
        mMap.addMarker(new MarkerOptions()
                .position(building58)
                .title("Texas Hall - TEX"));

        LatLng building59 = new LatLng(32.733095, -97.117113);
        mMap.addMarker(new MarkerOptions()
                .position(building59)
                .title("The Commons - COM"));

        LatLng building60 = new LatLng(32.73051, -97.110214);
        mMap.addMarker(new MarkerOptions()
                .position(building60)
                .title("Thermal Energy Plant - TEP"));

        LatLng building61 = new LatLng(32.734048, -97.123047);
        mMap.addMarker(new MarkerOptions()
                .position(building61)
                .title("Transforming Lives Child Development Center - DAYC"));

        LatLng building62 = new LatLng(32.729887, -97.111534);
        mMap.addMarker(new MarkerOptions()
                .position(building62)
                .title("Trimble Hall - TH"));

        LatLng building63 = new LatLng(32.729178, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(building63)
                .title("University Administration Building - UA"));

        LatLng building64 = new LatLng(32.729055, -97.114052);
        mMap.addMarker(new MarkerOptions()
                .position(building64)
                .title("University Hall - UH"));

        LatLng building65 = new LatLng(32.73326, -97.105225);
        mMap.addMarker(new MarkerOptions()
                .position(building65)
                .title("University Police Department - UPD"));

        LatLng building66 = new LatLng(32.784965, -97.219874);
        mMap.addMarker(new MarkerOptions()
                .position(building66)
                .title("UT Arlington Research Institute - UTARI"));

        LatLng building67 = new LatLng(32.73038, -97.112816);
        mMap.addMarker(new MarkerOptions()
                .position(building67)
                .title("W. A. Baker Chemistry Research Building - CRB"));

        LatLng building68 = new LatLng(32.734088, -97.106824);
        mMap.addMarker(new MarkerOptions()
                .position(building68)
                .title("Wade Building - WDB"));

        LatLng building69 = new LatLng(32.733092, -97.118528);
        mMap.addMarker(new MarkerOptions()
                .position(building69)
                .title("West Hall -"));

        LatLng building70 = new LatLng(32.726268, -97.117751);
        mMap.addMarker(new MarkerOptions()
                .position(building70)
                .title("West Mitchell Center - SAB"));

        LatLng building71 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(building71)
                .title("Wetsel Service Center - WET"));

        LatLng building72 = new LatLng(32.73138, -97.112969);
        mMap.addMarker(new MarkerOptions()
                .position(building72)
                .title("Woolf Hall - WH"));


    }
}
