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

        LatLng Location0 = new LatLng(32.73138, -97.112969);
        mMap.addMarker(new MarkerOptions()
                .position(Location0)
                .title("Academic Buildings - A - SWCA"));

        LatLng Location1 = new LatLng(32.734707, -97.113682);
        mMap.addMarker(new MarkerOptions()
                .position(Location1)
                .title("Academic Buildings - B - SWCB"));

        LatLng Location2 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(Location2)
                .title("Accounting - ACCT"));

        LatLng Location3 = new LatLng(32.736982, -97.109236);
        mMap.addMarker(new MarkerOptions()
                .position(Location3)
                .title("Accounting Services"));

        LatLng Location4 = new LatLng(32.729178, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(Location4)
                .title("Admissions"));

        LatLng Location5 = new LatLng(32.729178, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(Location5)
                .title("Admissions (Graduate)"));

        LatLng Location6 = new LatLng(32.727495, -97.107354);
        mMap.addMarker(new MarkerOptions()
                .position(Location6)
                .title("Aerodynamics Research Building - ARB"));

        LatLng Location7 = new LatLng(32.73138, -97.112969);
        mMap.addMarker(new MarkerOptions()
                .position(Location7)
                .title("Aerospace Engineering - AE"));

        LatLng Location8 = new LatLng(32.72249, -97.129295);
        mMap.addMarker(new MarkerOptions()
                .position(Location8)
                .title("Allan Saxe Softball Field"));

        LatLng Location9 = new LatLng(32.727804, -97.124741);
        mMap.addMarker(new MarkerOptions()
                .position(Location9)
                .title("Amphibian and Reptile Diversity Research Center - ARC"));

        LatLng Location10 = new LatLng(32.730108, -97.12105);
        mMap.addMarker(new MarkerOptions()
                .position(Location10)
                .title("Arbor Oaks"));

        LatLng Location11 = new LatLng(32.729585, -97.120965);
        mMap.addMarker(new MarkerOptions()
                .position(Location11)
                .title("Arbor Oaks Parking"));

        LatLng Location12 = new LatLng(32.731265, -97.116128);
        mMap.addMarker(new MarkerOptions()
                .position(Location12)
                .title("Architecture Courtyard"));

        LatLng Location13 = new LatLng(32.731096, -97.10941);
        mMap.addMarker(new MarkerOptions()
                .position(Location13)
                .title("Arlington Hall"));

        LatLng Location14 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(Location14)
                .title("Art and Art History - ART"));

        LatLng Location15 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(Location15)
                .title("Asset Management"));

        LatLng Location16 = new LatLng(32.733211, -97.112513);
        mMap.addMarker(new MarkerOptions()
                .position(Location16)
                .title("Bioengineering - BME"));

        LatLng Location17 = new LatLng(32.728641, -97.112895);
        mMap.addMarker(new MarkerOptions()
                .position(Location17)
                .title("Biology - BIOL"));

        LatLng Location18 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location18)
                .title("Bluebonnet Ballroom"));

        LatLng Location19 = new LatLng(32.733351, -97.109269);
        mMap.addMarker(new MarkerOptions()
                .position(Location19)
                .title("Bookstore - BOOK"));

        LatLng Location20 = new LatLng(32.736982, -97.109236);
        mMap.addMarker(new MarkerOptions()
                .position(Location20)
                .title("Budgets and Financial Planning"));

        LatLng Location21 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(Location21)
                .title("Business Building - COBA"));

        LatLng Location22 = new LatLng(32.736982, -97.109236);
        mMap.addMarker(new MarkerOptions()
                .position(Location22)
                .title("Business Services"));

        LatLng Location23 = new LatLng(32.731265, -97.116128);
        mMap.addMarker(new MarkerOptions()
                .position(Location23)
                .title("CAPPA Building - ARCH"));

        LatLng Location24 = new LatLng(32.7321, -97.116078);
        mMap.addMarker(new MarkerOptions()
                .position(Location24)
                .title("CAPPA North - CMPC"));

        LatLng Location25 = new LatLng(32.73065, -97.112586);
        mMap.addMarker(new MarkerOptions()
                .position(Location25)
                .title("Carlisle Hall"));

        LatLng Location26 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location26)
                .title("Carlisle Suite"));

        LatLng Location27 = new LatLng(32.730801, -97.111507);
        mMap.addMarker(new MarkerOptions()
                .position(Location27)
                .title("Carolyn A Barros Reading Room"));

        LatLng Location28 = new LatLng(32.725776, -97.115986);
        mMap.addMarker(new MarkerOptions()
                .position(Location28)
                .title("Centennial Court"));

        LatLng Location29 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(Location29)
                .title("Central Receiving"));

        LatLng Location30 = new LatLng(32.730396, -97.111744);
        mMap.addMarker(new MarkerOptions()
                .position(Location30)
                .title("Chemistry & Physics Building - CPB"));

        LatLng Location31 = new LatLng(32.730396, -97.111744);
        mMap.addMarker(new MarkerOptions()
                .position(Location31)
                .title("Chemistry & Physics Building - CPB"));

        LatLng Location32 = new LatLng(32.730396, -97.111744);
        mMap.addMarker(new MarkerOptions()
                .position(Location32)
                .title("Chemistry and Biochemistry - CHEM"));

        LatLng Location33 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(Location33)
                .title("Civil Engineering - CE"));

        LatLng Location34 = new LatLng(32.727543, -97.125545);
        mMap.addMarker(new MarkerOptions()
                .position(Location34)
                .title("Civil Engineering Lab Building - CELB"));

        LatLng Location35 = new LatLng(32.722201, -97.130803);
        mMap.addMarker(new MarkerOptions()
                .position(Location35)
                .title("Clay Gould Ballpark"));

        LatLng Location36 = new LatLng(32.730801, -97.111507);
        mMap.addMarker(new MarkerOptions()
                .position(Location36)
                .title("College Hall - CH"));

        LatLng Location37 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(Location37)
                .title("College of Business- BUSA"));

        LatLng Location38 = new LatLng(32.728415, -97.111283);
        mMap.addMarker(new MarkerOptions()
                .position(Location38)
                .title("College of Nursing and Health Innovation- NURS"));

        LatLng Location39 = new LatLng(32.728641, -97.112895);
        mMap.addMarker(new MarkerOptions()
                .position(Location39)
                .title("College of Science - SCIE"));

        LatLng Location40 = new LatLng(32.730187, -97.108181);
        mMap.addMarker(new MarkerOptions()
                .position(Location40)
                .title("College Park Center - CPC"));

        LatLng Location41 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(Location41)
                .title("Communication - COMM"));

        LatLng Location42 = new LatLng(32.733211, -97.112513);
        mMap.addMarker(new MarkerOptions()
                .position(Location42)
                .title("Computer Science and Engineering - CSE"));

        LatLng Location43 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location43)
                .title("Concho Room"));

        LatLng Location44 = new LatLng(32.726832, -97.108455);
        mMap.addMarker(new MarkerOptions()
                .position(Location44)
                .title("Continuing Ed & Workforce Development - CEWF"));

        LatLng Location45 = new LatLng(32.726832, -97.108455);
        mMap.addMarker(new MarkerOptions()
                .position(Location45)
                .title("Continuing Education"));

        LatLng Location46 = new LatLng(32.729055, -97.114052);
        mMap.addMarker(new MarkerOptions()
                .position(Location46)
                .title("Criminology and Criminal Justice - CRCJ"));

        LatLng Location47 = new LatLng(32.729641, -97.112045);
        mMap.addMarker(new MarkerOptions()
                .position(Location47)
                .title("Curriculum and Instruction - EDUC"));

        LatLng Location48 = new LatLng(32.731223, -97.107478);
        mMap.addMarker(new MarkerOptions()
                .position(Location48)
                .title("Dan Dipert University Welcome Center"));

        LatLng Location49 = new LatLng(32.726782, -97.107216);
        mMap.addMarker(new MarkerOptions()
                .position(Location49)
                .title("DED Technical Training Ctr. - DE"));

        LatLng Location50 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(Location50)
                .title("Distance Education"));

        LatLng Location51 = new LatLng(32.730934, -97.11218);
        mMap.addMarker(new MarkerOptions()
                .position(Location51)
                .title("Divsion of Student Success"));

        LatLng Location52 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location52)
                .title("E.H. Hereford University Center - UC"));

        LatLng Location53 = new LatLng(32.731329, -97.114177);
        mMap.addMarker(new MarkerOptions()
                .position(Location53)
                .title("Earth & Environmental Sciences - EES"));

        LatLng Location54 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(Location54)
                .title("Economics - ECON"));

        LatLng Location55 = new LatLng(32.729641, -97.112045);
        mMap.addMarker(new MarkerOptions()
                .position(Location55)
                .title("Educational Leadership and Policy Studies - EDAD"));

        LatLng Location56 = new LatLng(32.734088, -97.106824);
        mMap.addMarker(new MarkerOptions()
                .position(Location56)
                .title("Educational Opportunity Center"));

        LatLng Location57 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(Location57)
                .title("Electrical Engineering - EE"));

        LatLng Location58 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(Location58)
                .title("Employee Services"));

        LatLng Location59 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(Location59)
                .title("Employment Services"));

        LatLng Location60 = new LatLng(32.732326, -97.11267);
        mMap.addMarker(new MarkerOptions()
                .position(Location60)
                .title("Engineering Lab Building - ELAB"));

        LatLng Location61 = new LatLng(32.73217483, -97.11328268);
        mMap.addMarker(new MarkerOptions()
                .position(Location61)
                .title("Engineering Mall"));

        LatLng Location62 = new LatLng(32.733211, -97.112513);
        mMap.addMarker(new MarkerOptions()
                .position(Location62)
                .title("Engineering Research Building - ERB"));

        LatLng Location63 = new LatLng(32.73065, -97.112586);
        mMap.addMarker(new MarkerOptions()
                .position(Location63)
                .title("English - ENGL"));

        LatLng Location64 = new LatLng(32.732313, -97.122161);
        mMap.addMarker(new MarkerOptions()
                .position(Location64)
                .title("Environmental Health & Safety - EH"));

        LatLng Location65 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(Location65)
                .title("Facilities Management (Physical Plant)"));

        LatLng Location66 = new LatLng(32.736982, -97.109236);
        mMap.addMarker(new MarkerOptions()
                .position(Location66)
                .title("Finance and Administration Annex (Watson building) - FAAA"));

        LatLng Location67 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(Location67)
                .title("Finance and Real Estate - FINA"));

        LatLng Location68 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(Location68)
                .title("Fine Arts Building - FA"));

        LatLng Location69 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(Location69)
                .title("Fine Arts Room 148"));

        LatLng Location70 = new LatLng(32.749654, -97.324825);
        mMap.addMarker(new MarkerOptions()
                .position(Location70)
                .title("Fort Worth Center - UTASF"));

        LatLng Location71 = new LatLng(32.734244, -97.114176);
        mMap.addMarker(new MarkerOptions()
                .position(Location71)
                .title("General Academic Classroom Building - GACB"));

        LatLng Location72 = new LatLng(32.729701, -97.127416);
        mMap.addMarker(new MarkerOptions()
                .position(Location72)
                .title("Gilstrap Athletic Center - GILS"));

        LatLng Location73 = new LatLng(32.736982, -97.109236);
        mMap.addMarker(new MarkerOptions()
                .position(Location73)
                .title("Grant and Contracting Accounting"));

        LatLng Location74 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location74)
                .title("Guadalupe Room"));

        LatLng Location75 = new LatLng(32.729641, -97.112045);
        mMap.addMarker(new MarkerOptions()
                .position(Location75)
                .title("Hammond Hall - HH"));

        LatLng Location76 = new LatLng(32.730456, -97.110756);
        mMap.addMarker(new MarkerOptions()
                .position(Location76)
                .title("Health Center - HLTH"));

        LatLng Location77 = new LatLng(32.729055, -97.114052);
        mMap.addMarker(new MarkerOptions()
                .position(Location77)
                .title("History - HIST"));

        LatLng Location78 = new LatLng(32.730801, -97.111507);
        mMap.addMarker(new MarkerOptions()
                .position(Location78)
                .title("Honors College - HONR"));

        LatLng Location79 = new LatLng(32.73093, -97.117653);
        mMap.addMarker(new MarkerOptions()
                .position(Location79)
                .title("Indoor Pool"));

        LatLng Location80 = new LatLng(32.73138, -97.112969);
        mMap.addMarker(new MarkerOptions()
                .position(Location80)
                .title("Industrial, Manufacturing, and Systems Engineering - IE"));

        LatLng Location81 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(Location81)
                .title("Information Systems and Operations Management - INSY"));

        LatLng Location82 = new LatLng(32.733882, -97.121148);
        mMap.addMarker(new MarkerOptions()
                .position(Location82)
                .title("Institutional Compliance"));

        LatLng Location83 = new LatLng(32.733882, -97.121148);
        mMap.addMarker(new MarkerOptions()
                .position(Location83)
                .title("International Education"));

        LatLng Location84 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(Location84)
                .title("Irons Recital Hall"));

        LatLng Location85 = new LatLng(32.728357, -97.109528);
        mMap.addMarker(new MarkerOptions()
                .position(Location85)
                .title("Kalpana Chawla Hall"));

        LatLng Location86 = new LatLng(32.731925, -97.11705);
        mMap.addMarker(new MarkerOptions()
                .position(Location86)
                .title("Kinesiology - KINE"));

        LatLng Location87 = new LatLng(32.731265, -97.116128);
        mMap.addMarker(new MarkerOptions()
                .position(Location87)
                .title("Library - Architecture and Fine Arts"));

        LatLng Location88 = new LatLng(32.72966, -97.112687);
        mMap.addMarker(new MarkerOptions()
                .position(Location88)
                .title("Library - Central"));

        LatLng Location89 = new LatLng(32.72966, -97.112687);
        mMap.addMarker(new MarkerOptions()
                .position(Location89)
                .title("Library - LIBR"));

        LatLng Location90 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(Location90)
                .title("Library - Science and Engineering"));

        LatLng Location91 = new LatLng(32.72966, -97.112687);
        mMap.addMarker(new MarkerOptions()
                .position(Location91)
                .title("Library Atrium"));

        LatLng Location92 = new LatLng(32.727747, -97.124157);
        mMap.addMarker(new MarkerOptions()
                .position(Location92)
                .title("Library Collection Depository & OIT Office Building - LCDO"));

        LatLng Location93 = new LatLng(32.72966, -97.112687);
        mMap.addMarker(new MarkerOptions()
                .position(Location93)
                .title("Library Parlor"));

        LatLng Location94 = new LatLng(32.728641, -97.112895);
        mMap.addMarker(new MarkerOptions()
                .position(Location94)
                .title("Life Science Building - LS"));

        LatLng Location95 = new LatLng(32.729641, -97.112045);
        mMap.addMarker(new MarkerOptions()
                .position(Location95)
                .title("Linguistics & TESOL - LING"));

        LatLng Location96 = new LatLng(32.731925, -97.11705);
        mMap.addMarker(new MarkerOptions()
                .position(Location96)
                .title("Lone Star Auditorium"));

        LatLng Location97 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(Location97)
                .title("Mail Services"));

        LatLng Location98 = new LatLng(32.73121039, -97.11486452);
        mMap.addMarker(new MarkerOptions()
                .position(Location98)
                .title("Mainstage Theatre"));

        LatLng Location99 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(Location99)
                .title("Management - MANA"));

        LatLng Location100 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(Location100)
                .title("Marketing - MARK"));

        LatLng Location101 = new LatLng(32.729151, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(Location101)
                .title("Marketing, Messaging, and Engagement"));

        LatLng Location102 = new LatLng(32.732326, -97.11267);
        mMap.addMarker(new MarkerOptions()
                .position(Location102)
                .title("Materials Science and Engineering - MSE"));

        LatLng Location103 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(Location103)
                .title("Mathematical Sciences - MSCI"));

        LatLng Location104 = new LatLng(32.728415, -97.111283);
        mMap.addMarker(new MarkerOptions()
                .position(Location104)
                .title("Mathematics - MATH"));

        LatLng Location105 = new LatLng(32.731925, -97.11705);
        mMap.addMarker(new MarkerOptions()
                .position(Location105)
                .title("Maverick Activities Center - MAC"));

        LatLng Location106 = new LatLng(32.729459, -97.111647);
        mMap.addMarker(new MarkerOptions()
                .position(Location106)
                .title("Maverick Parking Garage - GARA"));

        LatLng Location107 = new LatLng(32.729459, -97.111647);
        mMap.addMarker(new MarkerOptions()
                .position(Location107)
                .title("Maverick Parking Garage - GARA"));

        LatLng Location108 = new LatLng(32.729209, -97.126382);
        mMap.addMarker(new MarkerOptions()
                .position(Location108)
                .title("Maverick Stadium - STAD"));

        LatLng Location109 = new LatLng(32.731083, -97.121437);
        mMap.addMarker(new MarkerOptions()
                .position(Location109)
                .title("Meadow Run"));

        LatLng Location110 = new LatLng(32.73138, -97.112969);
        mMap.addMarker(new MarkerOptions()
                .position(Location110)
                .title("Mechanical and Aerospace Engineering - MAE"));

        LatLng Location111 = new LatLng(32.733867, -97.122085);
        mMap.addMarker(new MarkerOptions()
                .position(Location111)
                .title("Military & Veteran Services - VAC"));

        LatLng Location112 = new LatLng(32.729178, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(Location112)
                .title("Military and Veteran Services"));

        LatLng Location113 = new LatLng(32.730801, -97.111507);
        mMap.addMarker(new MarkerOptions()
                .position(Location113)
                .title("Military Science - MILS"));

        LatLng Location114 = new LatLng(32.729641, -97.112045);
        mMap.addMarker(new MarkerOptions()
                .position(Location114)
                .title("Modern Languages - MODL"));

        LatLng Location115 = new LatLng(32.729567, -97.112124);
        mMap.addMarker(new MarkerOptions()
                .position(Location115)
                .title("Motorcycle Parking"));

        LatLng Location116 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(Location116)
                .title("Music - MUSI"));

        LatLng Location117 = new LatLng(32.732407, -97.115512);
        mMap.addMarker(new MarkerOptions()
                .position(Location117)
                .title("Nanofab Building - NANO"));

        LatLng Location118 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location118)
                .title("Neches Room"));

        LatLng Location119 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(Location119)
                .title("Nedderman Hall - NH"));

        LatLng Location120 = new LatLng(32.73093, -97.117653);
        mMap.addMarker(new MarkerOptions()
                .position(Location120)
                .title("Outdoor Pool"));

        LatLng Location121 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location121)
                .title("Palo Duro Lounge"));

        LatLng Location122 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location122)
                .title("Palo Pinto Room"));

        LatLng Location123 = new LatLng(32.729851, -97.124376);
        mMap.addMarker(new MarkerOptions()
                .position(Location123)
                .title("Parking Lot 27"));

        LatLng Location124 = new LatLng(32.73356, -97.12197);
        mMap.addMarker(new MarkerOptions()
                .position(Location124)
                .title("Parking Lot 28"));

        LatLng Location125 = new LatLng(32.72984, -97.12264);
        mMap.addMarker(new MarkerOptions()
                .position(Location125)
                .title("Parking Lot 29"));

        LatLng Location126 = new LatLng(32.73119, -97.11958);
        mMap.addMarker(new MarkerOptions()
                .position(Location126)
                .title("Parking Lot 30"));

        LatLng Location127 = new LatLng(32.73306, -97.12157);
        mMap.addMarker(new MarkerOptions()
                .position(Location127)
                .title("Parking Lot 31"));

        LatLng Location128 = new LatLng(32.73339322, -97.11583614);
        mMap.addMarker(new MarkerOptions()
                .position(Location128)
                .title("Parking Lot 34 (student)"));

        LatLng Location129 = new LatLng(32.73409, -97.11544);
        mMap.addMarker(new MarkerOptions()
                .position(Location129)
                .title("Parking Lot 35"));

        LatLng Location130 = new LatLng(32.7345, -97.11315);
        mMap.addMarker(new MarkerOptions()
                .position(Location130)
                .title("Parking Lot 36"));

        LatLng Location131 = new LatLng(32.732599, -97.109571);
        mMap.addMarker(new MarkerOptions()
                .position(Location131)
                .title("Parking Lot 38"));

        LatLng Location132 = new LatLng(32.732563, -97.10897);
        mMap.addMarker(new MarkerOptions()
                .position(Location132)
                .title("Parking Lot 39"));

        LatLng Location133 = new LatLng(32.727256, -97.108047);
        mMap.addMarker(new MarkerOptions()
                .position(Location133)
                .title("Parking Lot 46"));

        LatLng Location134 = new LatLng(32.72606, -97.11284);
        mMap.addMarker(new MarkerOptions()
                .position(Location134)
                .title("Parking Lot 49"));

        LatLng Location135 = new LatLng(32.7246, -97.11238);
        mMap.addMarker(new MarkerOptions()
                .position(Location135)
                .title("Parking Lot 50"));

        LatLng Location136 = new LatLng(32.72322, -97.11049);
        mMap.addMarker(new MarkerOptions()
                .position(Location136)
                .title("Parking Lot 51"));

        LatLng Location137 = new LatLng(32.72571, -97.11049);
        mMap.addMarker(new MarkerOptions()
                .position(Location137)
                .title("Parking Lot 52"));

        LatLng Location138 = new LatLng(32.72696, -97.10913);
        mMap.addMarker(new MarkerOptions()
                .position(Location138)
                .title("Parking Lot 53"));

        LatLng Location139 = new LatLng(32.72799, -97.10735);
        mMap.addMarker(new MarkerOptions()
                .position(Location139)
                .title("Parking Lot 55"));

        LatLng Location140 = new LatLng(32.72503, -97.10828);
        mMap.addMarker(new MarkerOptions()
                .position(Location140)
                .title("Parking Lot 56"));

        LatLng Location141 = new LatLng(32.728772, -97.110418);
        mMap.addMarker(new MarkerOptions()
                .position(Location141)
                .title("Parking Lot F10"));

        LatLng Location142 = new LatLng(32.73269829, -97.11038053);
        mMap.addMarker(new MarkerOptions()
                .position(Location142)
                .title("Parking Lot F11"));

        LatLng Location143 = new LatLng(32.733, -97.11148);
        mMap.addMarker(new MarkerOptions()
                .position(Location143)
                .title("Parking Lot F12"));

        LatLng Location144 = new LatLng(32.729914, -97.10919);
        mMap.addMarker(new MarkerOptions()
                .position(Location144)
                .title("Parking Lot F13"));

        LatLng Location145 = new LatLng(32.733326, -97.11412);
        mMap.addMarker(new MarkerOptions()
                .position(Location145)
                .title("Parking Lot F14"));

        LatLng Location146 = new LatLng(32.72627, -97.10822);
        mMap.addMarker(new MarkerOptions()
                .position(Location146)
                .title("Parking Lot F17"));

        LatLng Location147 = new LatLng(32.73247, -97.12188);
        mMap.addMarker(new MarkerOptions()
                .position(Location147)
                .title("Parking Lot F4"));

        LatLng Location148 = new LatLng(32.73399, -97.12035);
        mMap.addMarker(new MarkerOptions()
                .position(Location148)
                .title("Parking Lot F5"));

        LatLng Location149 = new LatLng(32.73214, -97.11499);
        mMap.addMarker(new MarkerOptions()
                .position(Location149)
                .title("Parking Lot F7"));

        LatLng Location150 = new LatLng(32.73106, -97.11682);
        mMap.addMarker(new MarkerOptions()
                .position(Location150)
                .title("Parking Lot F8"));

        LatLng Location151 = new LatLng(32.72874, -97.11568);
        mMap.addMarker(new MarkerOptions()
                .position(Location151)
                .title("Parking Lot F9"));

        LatLng Location152 = new LatLng(32.73147, -97.12251);
        mMap.addMarker(new MarkerOptions()
                .position(Location152)
                .title("Parking Lot GR"));

        LatLng Location153 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(Location153)
                .title("Payroll Services"));

        LatLng Location154 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location154)
                .title("Pedernales Room"));

        LatLng Location155 = new LatLng(32.730801, -97.111507);
        mMap.addMarker(new MarkerOptions()
                .position(Location155)
                .title("Philosophy and Humanities - PHIL"));

        LatLng Location156 = new LatLng(32.73093, -97.117653);
        mMap.addMarker(new MarkerOptions()
                .position(Location156)
                .title("Physical Education - PE"));

        LatLng Location157 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(Location157)
                .title("Physical Plant (Facilities Management)"));

        LatLng Location158 = new LatLng(32.730396, -97.111744);
        mMap.addMarker(new MarkerOptions()
                .position(Location158)
                .title("Physics - PHYS"));

        LatLng Location159 = new LatLng(32.728415, -97.111283);
        mMap.addMarker(new MarkerOptions()
                .position(Location159)
                .title("Pickard Hall - PKH"));

        LatLng Location160 = new LatLng(32.730396, -97.111744);
        mMap.addMarker(new MarkerOptions()
                .position(Location160)
                .title("Planetarium"));

        LatLng Location161 = new LatLng(32.729055, -97.114052);
        mMap.addMarker(new MarkerOptions()
                .position(Location161)
                .title("Political Science - POLS"));

        LatLng Location162 = new LatLng(32.730905, -97.112856);
        mMap.addMarker(new MarkerOptions()
                .position(Location162)
                .title("Preston Hall - PH"));

        LatLng Location163 = new LatLng(32.736982, -97.109236);
        mMap.addMarker(new MarkerOptions()
                .position(Location163)
                .title("Procurement Services"));

        LatLng Location164 = new LatLng(32.728641, -97.112895);
        mMap.addMarker(new MarkerOptions()
                .position(Location164)
                .title("Psychology - PSYC"));

        LatLng Location165 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(Location165)
                .title("Rady Room"));

        LatLng Location166 = new LatLng(32.730934, -97.11218);
        mMap.addMarker(new MarkerOptions()
                .position(Location166)
                .title("Ransom Hall - RH"));

        LatLng Location167 = new LatLng(32.729178, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(Location167)
                .title("Records and Registration (Registrar)"));

        LatLng Location168 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location168)
                .title("Red River Room"));

        LatLng Location169 = new LatLng(32.72920179, -97.11520236);
        mMap.addMarker(new MarkerOptions()
                .position(Location169)
                .title("Research Administration"));

        LatLng Location170 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location170)
                .title("Rio Grande Ballroom"));

        LatLng Location171 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location171)
                .title("Rosebud Theater"));

        LatLng Location172 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location172)
                .title("San Jacinto Room"));

        LatLng Location173 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location173)
                .title("San Saba Room"));

        LatLng Location174 = new LatLng(32.72752087, -97.11141429);
        mMap.addMarker(new MarkerOptions()
                .position(Location174)
                .title("School of Social Work - SOCW"));

        LatLng Location175 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(Location175)
                .title("Science and Engineering Library"));

        LatLng Location176 = new LatLng(32.730438, -97.114186);
        mMap.addMarker(new MarkerOptions()
                .position(Location176)
                .title("Science Hall - SH"));

        LatLng Location177 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location177)
                .title("Sierras Lounge"));

        LatLng Location178 = new LatLng(32.729055, -97.114052);
        mMap.addMarker(new MarkerOptions()
                .position(Location178)
                .title("Sociology and Anthropology - ANTH"));

        LatLng Location179 = new LatLng(32.72966, -97.112687);
        mMap.addMarker(new MarkerOptions()
                .position(Location179)
                .title("Special Collections"));

        LatLng Location180 = new LatLng(32.729055, -97.114052);
        mMap.addMarker(new MarkerOptions()
                .position(Location180)
                .title("Student Access & Resource Center"));

        LatLng Location181 = new LatLng(32.729219, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(Location181)
                .title("Student Accounts"));

        LatLng Location182 = new LatLng(32.728687, -97.124942);
        mMap.addMarker(new MarkerOptions()
                .position(Location182)
                .title("Studio Arts Center - SAC"));

        LatLng Location183 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .position(Location183)
                .title("Studio Theatre"));

        LatLng Location184 = new LatLng(32.733882, -97.121148);
        mMap.addMarker(new MarkerOptions()
                .position(Location184)
                .title("Swift Center - SC"));

        LatLng Location185 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(Location185)
                .title("Talent, Culture and Engagement"));

        LatLng Location186 = new LatLng(32.732003, -97.119913);
        mMap.addMarker(new MarkerOptions()
                .position(Location186)
                .title("Tennis Center - TENN"));

        LatLng Location187 = new LatLng(32.729723, -97.115497);
        mMap.addMarker(new MarkerOptions()
                .position(Location187)
                .title("Texas Hall"));

        LatLng Location188 = new LatLng(32.729723, -97.115497);
        mMap.addMarker(new MarkerOptions()
                .position(Location188)
                .title("Texas Hall - TEX"));

        LatLng Location189 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location189)
                .title("The Gallery at UC"));

        LatLng Location190 = new LatLng(32.731457, -97.115053);
        mMap.addMarker(new MarkerOptions()
                .position(Location190)
                .title("The Gallery at UTA"));

        LatLng Location191 = new LatLng(32.729404, -97.107291);
        mMap.addMarker(new MarkerOptions()
                .position(Location191)
                .title("The Green at College Park"));

        LatLng Location192 = new LatLng(32.73121039, -97.11486452);
        mMap.addMarker(new MarkerOptions()
                .position(Location192)
                .title("Theatre Arts and Dance - THEA"));

        LatLng Location193 = new LatLng(32.733863, -97.119913);
        mMap.addMarker(new MarkerOptions()
                .position(Location193)
                .title("Timber Brook"));

        LatLng Location194 = new LatLng(32.736982, -97.109236);
        mMap.addMarker(new MarkerOptions()
                .position(Location194)
                .title("Travel Services"));

        LatLng Location195 = new LatLng(32.729887, -97.111534);
        mMap.addMarker(new MarkerOptions()
                .position(Location195)
                .title("Trimble Hall - TH"));

        LatLng Location196 = new LatLng(32.73018927, -97.11674809);
        mMap.addMarker(new MarkerOptions()
                .position(Location196)
                .title("Trinity Hall - TRN"));

        LatLng Location197 = new LatLng(32.729178, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(Location197)
                .title("University Administration Building - UA"));

        LatLng Location198 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(Location198)
                .title("University Center Mall"));

        LatLng Location199 = new LatLng(32.729055, -97.114052);
        mMap.addMarker(new MarkerOptions()
                .position(Location199)
                .title("University Hall - UH"));

        LatLng Location200 = new LatLng(32.73326, -97.105225);
        mMap.addMarker(new MarkerOptions()
                .position(Location200)
                .title("University Police Department - UPD"));

        LatLng Location201 = new LatLng(32.730198, -97.119741);
        mMap.addMarker(new MarkerOptions()
                .position(Location201)
                .title("University Village"));

        LatLng Location202 = new LatLng(32.784965, -97.219874);
        mMap.addMarker(new MarkerOptions()
                .position(Location202)
                .title("UT Arlington Research Institute - UTARI"));

        LatLng Location203 = new LatLng(32.73038, -97.112816);
        mMap.addMarker(new MarkerOptions()
                .position(Location203)
                .title("W. A. Baker Chemistry Research Building - CRB"));

        LatLng Location204 = new LatLng(32.734088, -97.106824);
        mMap.addMarker(new MarkerOptions()
                .position(Location204)
                .title("Wade Building - WDB"));

        LatLng Location205 = new LatLng(32.731265, -97.116128);
        mMap.addMarker(new MarkerOptions()
                .position(Location205)
                .title("West Campus Library"));

        LatLng Location206 = new LatLng(32.726268, -97.117751);
        mMap.addMarker(new MarkerOptions()
                .position(Location206)
                .title("West Mitchell Center - SAB"));

        LatLng Location207 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(Location207)
                .title("Wetsel Service Center - WET"));

        LatLng Location208 = new LatLng(32.729055, -97.114052);
        mMap.addMarker(new MarkerOptions()
                .position(Location208)
                .title("Women's and Gender Studies - WOMS"));

        LatLng Location209 = new LatLng(32.73138, -97.112969);
        mMap.addMarker(new MarkerOptions()
                .position(Location209)
                .title("Woolf Hall - WH"));

    }


}
