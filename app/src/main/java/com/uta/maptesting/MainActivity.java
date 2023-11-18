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

        LatLng parking1 = new LatLng(32.73323979, -97.11843252);
        mMap.addMarker(new MarkerOptions()
                .position(parking1)
                .title("Accessible Parking"));

        LatLng parking2 = new LatLng(32.73237338, -97.11068094);
        mMap.addMarker(new MarkerOptions()
                .position(parking2)
                .title("F11 Visitor Parking"));

        LatLng parking3 = new LatLng(32.73069, -97.126391);
        mMap.addMarker(new MarkerOptions()
                .position(parking3)
                .title("Maverick Stadium"));

        LatLng parking4 = new LatLng(32.731877, -97.12059975);
        mMap.addMarker(new MarkerOptions()
                .position(parking4)
                .title("Meadow Run General Parking"));

        LatLng parking5 = new LatLng(32.73352, -97.117231);
        mMap.addMarker(new MarkerOptions()
                .position(parking5)
                .title("Park West Parking Garage"));

        LatLng parking6 = new LatLng(32.72922, -97.125163);
        mMap.addMarker(new MarkerOptions()
                .position(parking6)
                .title("Parking and Transportation Parking Lot"));

        LatLng parking7 = new LatLng(32.729851, -97.124376);
        mMap.addMarker(new MarkerOptions()
                .position(parking7)
                .title("Parking Lot 27"));

        LatLng parking8 = new LatLng(32.73356, -97.12197);
        mMap.addMarker(new MarkerOptions()
                .position(parking8)
                .title("Parking Lot 28"));

        LatLng parking9 = new LatLng(32.72984, -97.12264);
        mMap.addMarker(new MarkerOptions()
                .position(parking9)
                .title("Parking Lot 29"));

        LatLng parking10 = new LatLng(32.73119, -97.11958);
        mMap.addMarker(new MarkerOptions()
                .position(parking10)
                .title("Parking Lot 30"));

        LatLng parking11 = new LatLng(32.73306, -97.12157);
        mMap.addMarker(new MarkerOptions()
                .position(parking11)
                .title("Parking Lot 31"));

        LatLng parking12 = new LatLng(32.73339322, -97.11583614);
        mMap.addMarker(new MarkerOptions()
                .position(parking12)
                .title("Parking Lot 34 (student)"));

        LatLng parking13 = new LatLng(32.73409, -97.11544);
        mMap.addMarker(new MarkerOptions()
                .position(parking13)
                .title("Parking Lot 35"));

        LatLng parking14 = new LatLng(32.7345, -97.11315);
        mMap.addMarker(new MarkerOptions()
                .position(parking14)
                .title("Parking Lot 36"));

        LatLng parking15 = new LatLng(32.732599, -97.109571);
        mMap.addMarker(new MarkerOptions()
                .position(parking15)
                .title("Parking Lot 38"));

        LatLng parking16 = new LatLng(32.732563, -97.10897);
        mMap.addMarker(new MarkerOptions()
                .position(parking16)
                .title("Parking Lot 39"));

        LatLng parking17 = new LatLng(32.72853761, -97.1075213);
        mMap.addMarker(new MarkerOptions()
                .position(parking17)
                .title("Parking Lot 45"));

        LatLng parking18 = new LatLng(32.727256, -97.108047);
        mMap.addMarker(new MarkerOptions()
                .position(parking18)
                .title("Parking Lot 46"));

        LatLng parking19 = new LatLng(32.72606, -97.11284);
        mMap.addMarker(new MarkerOptions()
                .position(parking19)
                .title("Parking Lot 49"));

        LatLng parking20 = new LatLng(32.7246, -97.11238);
        mMap.addMarker(new MarkerOptions()
                .position(parking20)
                .title("Parking Lot 50"));

        LatLng parking21 = new LatLng(32.72322, -97.11049);
        mMap.addMarker(new MarkerOptions()
                .position(parking21)
                .title("Parking Lot 51"));

        LatLng parking22 = new LatLng(32.72571, -97.11049);
        mMap.addMarker(new MarkerOptions()
                .position(parking22)
                .title("Parking Lot 52"));

        LatLng parking23 = new LatLng(32.72696, -97.10913);
        mMap.addMarker(new MarkerOptions()
                .position(parking23)
                .title("Parking Lot 53"));

        LatLng parking24 = new LatLng(32.72799, -97.10735);
        mMap.addMarker(new MarkerOptions()
                .position(parking24)
                .title("Parking Lot 55"));

        LatLng parking25 = new LatLng(32.72503, -97.10828);
        mMap.addMarker(new MarkerOptions()
                .position(parking25)
                .title("Parking Lot 56"));

        LatLng parking26 = new LatLng(32.728772, -97.110418);
        mMap.addMarker(new MarkerOptions()
                .position(parking26)
                .title("Parking Lot F10"));

        LatLng parking27 = new LatLng(32.73269829, -97.11038053);
        mMap.addMarker(new MarkerOptions()
                .position(parking27)
                .title("Parking Lot F11"));

        LatLng parking28 = new LatLng(32.733, -97.11148);
        mMap.addMarker(new MarkerOptions()
                .position(parking28)
                .title("Parking Lot F12"));

        LatLng parking29 = new LatLng(32.729914, -97.10919);
        mMap.addMarker(new MarkerOptions()
                .position(parking29)
                .title("Parking Lot F13"));

        LatLng parking30 = new LatLng(32.733326, -97.11412);
        mMap.addMarker(new MarkerOptions()
                .position(parking30)
                .title("Parking Lot F14"));

        LatLng parking31 = new LatLng(32.72627, -97.10822);
        mMap.addMarker(new MarkerOptions()
                .position(parking31)
                .title("Parking Lot F17"));

        LatLng parking32 = new LatLng(32.73247, -97.12188);
        mMap.addMarker(new MarkerOptions()
                .position(parking32)
                .title("Parking Lot F4"));

        LatLng parking33 = new LatLng(32.73399, -97.12035);
        mMap.addMarker(new MarkerOptions()
                .position(parking33)
                .title("Parking Lot F5"));

        LatLng parking34 = new LatLng(32.73214, -97.11499);
        mMap.addMarker(new MarkerOptions()
                .position(parking34)
                .title("Parking Lot F7"));

        LatLng parking35 = new LatLng(32.73106, -97.11682);
        mMap.addMarker(new MarkerOptions()
                .position(parking35)
                .title("Parking Lot F8"));

        LatLng parking36 = new LatLng(32.72874, -97.11568);
        mMap.addMarker(new MarkerOptions()
                .position(parking36)
                .title("Parking Lot F9"));

        LatLng parking37 = new LatLng(32.73147, -97.12251);
        mMap.addMarker(new MarkerOptions()
                .position(parking37)
                .title("Parking Lot GR"));

        LatLng parking38 = new LatLng(32.726122, -97.118165);
        mMap.addMarker(new MarkerOptions()
                .position(parking38)
                .title("Student & Administration Parking Lot"));

        LatLng venue1 = new LatLng(32.72249, -97.129295);
        mMap.addMarker(new MarkerOptions()
                .position(venue1)
                .title("Allan Saxe Softball Field"));

        LatLng venue2 = new LatLng(32.731265, -97.116128);
        mMap.addMarker(new MarkerOptions()
                .position(venue2)
                .title("Architecture Courtyard"));

        LatLng venue3 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue3)
                .title("Bluebonnet Ballroom"));

        LatLng venue4 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue4)
                .title("Carlisle Suite"));

        LatLng venue5 = new LatLng(32.730801, -97.111507);
        mMap.addMarker(new MarkerOptions()
                .position(venue5)
                .title("Carolyn A Barros Reading Room"));

        LatLng venue6 = new LatLng(32.722201, -97.130803);
        mMap.addMarker(new MarkerOptions()
                .position(venue6)
                .title("Clay Gould Ballpark"));

        LatLng venue7 = new LatLng(32.730187, -97.108181);
        mMap.addMarker(new MarkerOptions()
                .position(venue7)
                .title("College Park Center"));

        LatLng venue8 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue8)
                .title("Concho Room"));

        LatLng venue9 = new LatLng(32.731223, -97.107478);
        mMap.addMarker(new MarkerOptions()
                .position(venue9)
                .title("Dan Dipert University Welcome Center"));

        LatLng venue10 = new LatLng(32.73217483, -97.11328268);
        mMap.addMarker(new MarkerOptions()
                .position(venue10)
                .title("Engineering Mall"));

        LatLng venue11 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(venue11)
                .title("Fine Arts Room 148"));

        LatLng venue12 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue12)
                .title("Guadalupe Room"));

        LatLng venue13 = new LatLng(32.73093, -97.117653);
        mMap.addMarker(new MarkerOptions()
                .position(venue13)
                .title("Indoor Pool"));

        LatLng venue14 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(venue14)
                .title("Irons Recital Hall"));

        LatLng venue15 = new LatLng(32.72966, -97.112687);
        mMap.addMarker(new MarkerOptions()
                .position(venue15)
                .title("Library Atrium"));

        LatLng venue16 = new LatLng(32.72966, -97.112687);
        mMap.addMarker(new MarkerOptions()
                .position(venue16)
                .title("Library Parlor"));

        LatLng venue17 = new LatLng(32.731925, -97.11705);
        mMap.addMarker(new MarkerOptions()
                .position(venue17)
                .title("Lone Star Auditorium"));

        LatLng venue18 = new LatLng(32.73121039, -97.11486452);
        mMap.addMarker(new MarkerOptions()
                .position(venue18)
                .title("Mainstage Theatre"));

        LatLng venue19 = new LatLng(32.73196499, -97.10753202);
        mMap.addMarker(new MarkerOptions()
                .position(venue19)
                .title("Maverick Pantry"));

        LatLng venue20 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue20)
                .title("Neches Room"));

        LatLng venue21 = new LatLng(32.73093, -97.117653);
        mMap.addMarker(new MarkerOptions()
                .position(venue21)
                .title("Outdoor Pool"));

        LatLng venue22 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue22)
                .title("Palo Duro Lounge"));

        LatLng venue23 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue23)
                .title("Palo Pinto Room"));

        LatLng venue24 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue24)
                .title("Pedernales Room"));

        LatLng venue25 = new LatLng(32.730396, -97.111744);
        mMap.addMarker(new MarkerOptions()
                .position(venue25)
                .title("Planetarium"));

        LatLng venue26 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(venue26)
                .title("Rady Room"));

        LatLng venue27 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue27)
                .title("Red River Room"));

        LatLng venue28 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue28)
                .title("Rio Grande Ballroom"));

        LatLng venue29 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue29)
                .title("Rosebud Theater"));

        LatLng venue30 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue30)
                .title("San Jacinto Room"));

        LatLng venue31 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue31)
                .title("San Saba Room"));

        LatLng venue32 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(venue32)
                .title("Science and Engineering Library"));

        LatLng venue33 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue33)
                .title("Sierras Lounge"));

        LatLng venue34 = new LatLng(32.72764243, -97.11166903);
        mMap.addMarker(new MarkerOptions()
                .position(venue34)
                .title("Smart Hospital"));

        LatLng venue35 = new LatLng(32.72966, -97.112687);
        mMap.addMarker(new MarkerOptions()
                .position(venue35)
                .title("Special Collections"));

        LatLng venue36 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(venue36)
                .title("Studio Theatre"));

        LatLng venue37 = new LatLng(32.729723, -97.115497);
        mMap.addMarker(new MarkerOptions()
                .position(venue37)
                .title("Texas Hall"));

        LatLng venue38 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue38)
                .title("The Gallery at UC"));

        LatLng venue39 = new LatLng(32.731457, -97.115053);
        mMap.addMarker(new MarkerOptions()
                .position(venue39)
                .title("The Gallery at UTA"));

        LatLng venue40 = new LatLng(32.729404, -97.107291);
        mMap.addMarker(new MarkerOptions()
                .position(venue40)
                .title("The Green at College Park"));

        LatLng venue41 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(venue41)
                .title("University Center Mall"));

        LatLng venue42 = new LatLng(32.731265, -97.116128);
        mMap.addMarker(new MarkerOptions()
                .position(venue42)
                .title("West Campus Library"));


    }
}