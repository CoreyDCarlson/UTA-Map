package com.uta.maptesting;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    // creating a variable
    // for search view.
    SearchView searchView;
    private GoogleMap mMap;
    //private Marker marker;
    private List<CustomLocation> customLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        searchView = findViewById(R.id.idSearchView);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
        initializeCustomLocations();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                for (CustomLocation location : customLocations)
                {
                    boolean markerFound = false;
                    for (CustomLocation searchLocation : customLocations) {
                        if (location.getName().toLowerCase().contains(query.toLowerCase())) {
                            // Update camera position to the selected location
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(searchLocation.getLatLng(), 17));

                        }
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                if (newText.isEmpty()) {
                    ListView suggestionsListView = findViewById(R.id.suggestionsListView);
                    suggestionsListView.setVisibility(View.GONE); // Hide the list when search bar is empty
                } else {
                    List<String> filteredSuggestions = filterSuggestions(newText.toLowerCase());
                    updateSuggestionsUI(filteredSuggestions); // Show suggestions list when typing
                }
                return true; // Return true to handle text changes
            }

            private List<String> filterSuggestions(String input) {
                // Filter suggestions based on the entered text
                List<String> filteredSuggestions = new ArrayList<>();
                for (CustomLocation location : customLocations) {
                    if (location.getName().toLowerCase().contains(input)) {
                        filteredSuggestions.add(location.getName());
                    }
                }
                return filteredSuggestions;
            }

            private void updateSuggestionsUI(List<String> filteredSuggestions) {
                ListView suggestionsListView = findViewById(R.id.suggestionsListView);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, filteredSuggestions);
                suggestionsListView.setAdapter(adapter);

                // Show the suggestions ListView when there are suggestions, hide it otherwise
                if (filteredSuggestions.size() > 0) {
                    suggestionsListView.setVisibility(View.VISIBLE);
                } else {
                    suggestionsListView.setVisibility(View.GONE);
                }

                // Handle item click on the suggestions ListView
                suggestionsListView.setOnItemClickListener((adapterView, view, position, id) -> {
                    String selectedSuggestion = filteredSuggestions.get(position);
                    // Handle the selected suggestion (e.g., perform search based on the suggestion)
                    performSearch(selectedSuggestion);
                });
            }

            private void performSearch(String query) {
                // Implement your search functionality based on the selected suggestion
                // For example, update the map or perform a search operation using the query
                // Replace this example with your search logic
                for (CustomLocation location : customLocations) {
                    if (location.getName().equalsIgnoreCase(query)) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location.getLatLng(), 17));
                        break;
                    }
                }
            }

        });

        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }

    private void initializeCustomLocations()
    {

        customLocations = new ArrayList<>();
        // Add your custom locations here. Example:
        customLocations.add(new CustomLocation("Academic Buildings - A - SWCA", new LatLng(32.734666, -97.114125)));
        customLocations.add(new CustomLocation("Academic Buildings - B - SWCB", new LatLng(32.734707, -97.113682)));
        customLocations.add(new CustomLocation("Bookstore - BOOK", new LatLng(32.733351, -97.109269)));
        customLocations.add(new CustomLocation("Business Building - COBA", new LatLng(32.729576, -97.110579)));
        customLocations.add(new CustomLocation("C.R. Gilstrap Athletic Center - GILS", new LatLng(32.729701, -97.127416)));
        customLocations.add(new CustomLocation("CAPPA Building - ARCH", new LatLng(32.731265, -97.116128)));
        customLocations.add(new CustomLocation("CAPPA Community Design Lab - DBI", new LatLng(32.73803198, -97.11480618)));
        customLocations.add(new CustomLocation("CAPPA North - CMPC", new LatLng(32.7321, -97.116078)));
        customLocations.add(new CustomLocation("CAPPA South - A - SHC", new LatLng(32.73041985, -97.11588374)));
        customLocations.add(new CustomLocation("CAPPA South - B - SHO", new LatLng(32.73034154, -97.11553463)));
        customLocations.add(new CustomLocation("Carlisle Hall - CARH", new LatLng(32.73065, -97.112586)));
        customLocations.add(new CustomLocation("Center for Addiction and Recovery Studies - CARS", new LatLng(32.823796, -96.845489)));
        customLocations.add(new CustomLocation("Center for Entrepreneurship and Technology Development - CEEI", new LatLng(32.73359974, -97.10744466)));
        customLocations.add(new CustomLocation("Chemistry & Physics Building - CPB", new LatLng(32.730396, -97.111744)));
        customLocations.add(new CustomLocation("College Hall - CH", new LatLng(32.730801, -97.111507)));
        customLocations.add(new CustomLocation("College Park Center - CPC", new LatLng(32.730187, -97.108181)));
        customLocations.add(new CustomLocation("E.H. Hereford University Center - UC", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Earth & Environmental Sciences - EES", new LatLng(32.731329, -97.114177)));
        customLocations.add(new CustomLocation("Engineering Lab Building - ELAB", new LatLng(32.732326, -97.11267)));
        customLocations.add(new CustomLocation("Engineering Research Building - ERB", new LatLng(32.733211, -97.112513)));
        customLocations.add(new CustomLocation("Environmental Health & Safety - EH", new LatLng(32.732313, -97.122161)));
        customLocations.add(new CustomLocation("Environmental Health & Safety (West) - EHW", new LatLng(32.733159, -97.123303)));
        customLocations.add(new CustomLocation("Finance and Administration Annex (Watson building) - FAAA", new LatLng(32.736982, -97.109236)));
        customLocations.add(new CustomLocation("Fine Arts Building - FA", new LatLng(32.730577, -97.115085)));
        customLocations.add(new CustomLocation("Fort Worth Center - UTASF", new LatLng(32.749654, -97.324825)));
        customLocations.add(new CustomLocation("General Academic Classroom Building - GACB", new LatLng(32.734244, -97.114176)));
        customLocations.add(new CustomLocation("Hammond Hall - HH", new LatLng(32.729641, -97.112045)));
        customLocations.add(new CustomLocation("Health Center - HLTH", new LatLng(32.730456, -97.110756)));
        customLocations.add(new CustomLocation("Library - LIBR", new LatLng(32.72966, -97.112687)));
        customLocations.add(new CustomLocation("Maverick Activities Center - MAC", new LatLng(32.731925, -97.11705)));
        customLocations.add(new CustomLocation("Maverick Parking Garage - GARA", new LatLng(32.729459, -97.111647)));
        customLocations.add(new CustomLocation("Maverick Stadium - STAD", new LatLng(32.729209, -97.126382)));
        customLocations.add(new CustomLocation("Military & Veteran Services - VAC", new LatLng(32.733867, -97.122085)));
        customLocations.add(new CustomLocation("Nanofab Building - NANO", new LatLng(32.732407, -97.115512)));
        customLocations.add(new CustomLocation("Nedderman Hall - NH", new LatLng(32.732585, -97.113819)));
        customLocations.add(new CustomLocation("Parking & Transportation Services - PATS", new LatLng(32.729425, -97.124411)));
        customLocations.add(new CustomLocation("Physical Education - PE", new LatLng(32.73093, -97.117653)));
        customLocations.add(new CustomLocation("Preston Hall - PH", new LatLng(32.730905, -97.112856)));
        customLocations.add(new CustomLocation("Ransom Hall - RH", new LatLng(32.730934, -97.11218)));
        customLocations.add(new CustomLocation("Science Hall - SH", new LatLng(32.730438, -97.114186)));
        customLocations.add(new CustomLocation("Swift Center - SC", new LatLng(32.733882, -97.121148)));
        customLocations.add(new CustomLocation("Tennis Center - TENN", new LatLng(32.732003, -97.119913)));
        customLocations.add(new CustomLocation("Texas Hall - TEX", new LatLng(32.729723, -97.115497)));
        customLocations.add(new CustomLocation("The Commons - COM", new LatLng(32.733095, -97.117113)));
        customLocations.add(new CustomLocation("Thermal Energy Plant - TEP", new LatLng(32.73051, -97.110214)));
        customLocations.add(new CustomLocation("Transforming Lives Child Development Center - DAYC", new LatLng(32.734048, -97.123047)));
        customLocations.add(new CustomLocation("Trimble Hall - TH", new LatLng(32.729887, -97.111534)));
        customLocations.add(new CustomLocation("University Administration Building - UA", new LatLng(32.729178, -97.115171)));
        customLocations.add(new CustomLocation("University Hall - UH", new LatLng(32.729055, -97.114052)));
        customLocations.add(new CustomLocation("University Police Department - UPD", new LatLng(32.73326, -97.105225)));
        customLocations.add(new CustomLocation("UT Arlington Research Institute - UTARI", new LatLng(32.784965, -97.219874)));
        customLocations.add(new CustomLocation("W. A. Baker Chemistry Research Building - CRB", new LatLng(32.73038, -97.112816)));
        customLocations.add(new CustomLocation("Wade Building - WDB", new LatLng(32.734088, -97.106824)));
        customLocations.add(new CustomLocation("West Hall -", new LatLng(32.733092, -97.118528)));
        customLocations.add(new CustomLocation("West Mitchell Center - SAB", new LatLng(32.726268, -97.117751)));
        customLocations.add(new CustomLocation("Wetsel Service Center - WET", new LatLng(32.726656, -97.125937)));
        customLocations.add(new CustomLocation("Woolf Hall - WH", new LatLng(32.73138, -97.112969)));
        customLocations.add(new CustomLocation("Accounting - ACCT", new LatLng(32.729576, -97.110579)));
        customLocations.add(new CustomLocation("Aerospace Engineering - AE", new LatLng(32.73138, -97.112969)));
        customLocations.add(new CustomLocation("Architecture, Planning and Public Affairs, College of - CAPPA", new LatLng(32.731265, -97.116128)));
        customLocations.add(new CustomLocation("Art and Art History - ART", new LatLng(32.730577, -97.115085)));
        customLocations.add(new CustomLocation("Bioengineering - BME", new LatLng(32.733211, -97.112513)));
        customLocations.add(new CustomLocation("Chemistry and Biochemistry - CHEM", new LatLng(32.730396, -97.111744)));
        customLocations.add(new CustomLocation("Civil Engineering - CE", new LatLng(32.732585, -97.113819)));
        customLocations.add(new CustomLocation("College of Business - BUSA/BCOL", new LatLng(32.729576, -97.110579)));
        customLocations.add(new CustomLocation("College of Education - COED", new LatLng(32.729641, -97.112045)));
        customLocations.add(new CustomLocation("College of Engineering - COE", new LatLng(32.732585, -97.113819)));
        customLocations.add(new CustomLocation("College of Liberal Arts - LIBA", new LatLng(32.729055, -97.114052)));
        customLocations.add(new CustomLocation("Communication - COMM", new LatLng(32.730577, -97.115085)));
        customLocations.add(new CustomLocation("Computer Science and Engineering - CSE", new LatLng(32.733211, -97.112513)));
        customLocations.add(new CustomLocation("Criminology and Criminal Justice - CRCJ", new LatLng(32.729055, -97.114052)));
        customLocations.add(new CustomLocation("Criminology and Criminal Justice - CRJ", new LatLng(32.729055, -97.114052)));
        customLocations.add(new CustomLocation("Curriculum and Instruction - EDUC", new LatLng(32.729641, -97.112045)));
        customLocations.add(new CustomLocation("Economics - ECON", new LatLng(32.729576, -97.110579)));
        customLocations.add(new CustomLocation("Educational Leadership and Policy Studies - EDAD", new LatLng(32.729641, -97.112045)));
        customLocations.add(new CustomLocation("Electrical Engineering - EE", new LatLng(32.732585, -97.113819)));
        customLocations.add(new CustomLocation("English - ENGL", new LatLng(32.73065, -97.112586)));
        customLocations.add(new CustomLocation("Finance and Real Estate - FINA", new LatLng(32.729576, -97.110579)));
        customLocations.add(new CustomLocation("Graduate School - GRADS", new LatLng(32.729178, -97.115171)));
        customLocations.add(new CustomLocation("History - HIST", new LatLng(32.729055, -97.114052)));
        customLocations.add(new CustomLocation("Honors College - HCOL/HONR", new LatLng(32.730801, -97.111507)));
        customLocations.add(new CustomLocation("Industrial, Manufacturing, and Systems Engineering - IE", new LatLng(32.73138, -97.112969)));
        customLocations.add(new CustomLocation("Information Systems and Operations Management - INSY", new LatLng(32.729576, -97.110579)));
        customLocations.add(new CustomLocation("Kinesiology - KINE", new LatLng(32.731925, -97.11705)));
        customLocations.add(new CustomLocation("Linguistics & TESOL - LING", new LatLng(32.729641, -97.112045)));
        customLocations.add(new CustomLocation("Management - MANA", new LatLng(32.729576, -97.110579)));
        customLocations.add(new CustomLocation("Marketing - MARK", new LatLng(32.729576, -97.110579)));
        customLocations.add(new CustomLocation("Materials Science and Engineering - MSE", new LatLng(32.732326, -97.11267)));
        customLocations.add(new CustomLocation("Mathematical Sciences - MSCI", new LatLng(32.729576, -97.110579)));
        customLocations.add(new CustomLocation("Mechanical and Aerospace Engineering - MAE", new LatLng(32.73138, -97.112969)));
        customLocations.add(new CustomLocation("Military Science - MILS", new LatLng(32.730801, -97.111507)));
        customLocations.add(new CustomLocation("Modern Languages - MODL", new LatLng(32.729641, -97.112045)));
        customLocations.add(new CustomLocation("Music - MUSI", new LatLng(32.730577, -97.115085)));
        customLocations.add(new CustomLocation("Philosophy and Humanities - PHIL", new LatLng(32.730801, -97.111507)));
        customLocations.add(new CustomLocation("Physics - PHYS", new LatLng(32.730396, -97.111744)));
        customLocations.add(new CustomLocation("Political Science - POLS", new LatLng(32.729055, -97.114052)));
        customLocations.add(new CustomLocation("Sociology and Anthropology - ANTH", new LatLng(32.729055, -97.114052)));
        customLocations.add(new CustomLocation("Theatre Arts and Dance - THEA", new LatLng(32.73121039, -97.11486452)));
        customLocations.add(new CustomLocation("University Studies", new LatLng(32.730934, -97.11218)));
        customLocations.add(new CustomLocation("Women's and Gender Studies - WOMS", new LatLng(32.729055, -97.114052)));
        customLocations.add(new CustomLocation("AND BEYOND - Chemistry and Physics", new LatLng(32.7303975199634, -97.1120949251301)));
        customLocations.add(new CustomLocation("BLAZE - Downtown Arlington", new LatLng(32.7352555094134, -97.1067876656048)));
        customLocations.add(new CustomLocation("BLOSSOM - West Hall", new LatLng(32.733108989237, -97.1176348809494)));
        customLocations.add(new CustomLocation("BRILLIANCE - UTA Bookstore", new LatLng(32.7336937627704, -97.1087586944409)));
        customLocations.add(new CustomLocation("DYNAMIC - Woolf Hall", new LatLng(32.7314838682373, -97.1130889)));
        customLocations.add(new CustomLocation("GOLDEN RATIO - MAC", new LatLng(32.7317211181812, -97.1165019558192)));
        customLocations.add(new CustomLocation("HARMONY - Health Center", new LatLng(32.7304447944971, -97.1110155441807)));
        customLocations.add(new CustomLocation("LASTING IMPRINT - University Center", new LatLng(32.7313713432175, -97.1109548153445)));
        customLocations.add(new CustomLocation("LEGACY - GREEK ROW", new LatLng(32.730800412657, -97.1194903)));
        customLocations.add(new CustomLocation("MOSAIC - Library Mall", new LatLng(32.7314200936548, -97.1072614404747)));
        customLocations.add(new CustomLocation("PRIDE - College Park Center", new LatLng(32.730275070991, -97.1087600171975)));
        customLocations.add(new CustomLocation("TRIUMPH - Science Hall", new LatLng(32.730384918498, -97.1139562711638)));
        customLocations.add(new CustomLocation("Academic Coaching", new LatLng(32.730934, -97.11218)));
        customLocations.add(new CustomLocation("Academic Support Programs", new LatLng(32.72966, -97.112687)));
        customLocations.add(new CustomLocation("Academic Testing and TSI Services", new LatLng(32.729055, -97.114052)));
        customLocations.add(new CustomLocation("Accounting Services", new LatLng(32.736982, -97.109236)));
        customLocations.add(new CustomLocation("Admissions", new LatLng(32.729178, -97.115171)));
        customLocations.add(new CustomLocation("Admissions (Graduate)", new LatLng(32.729178, -97.115171)));
        customLocations.add(new CustomLocation("Alumni Relations", new LatLng(32.72909323, -97.11503508)));
        customLocations.add(new CustomLocation("Budgets and Financial Planning", new LatLng(32.736982, -97.109236)));
        customLocations.add(new CustomLocation("Business Services", new LatLng(32.736982, -97.109236)));
        customLocations.add(new CustomLocation("Distance Education", new LatLng(32.732585, -97.113819)));
        customLocations.add(new CustomLocation("Division of Student Success", new LatLng(32.730934, -97.11218)));
        customLocations.add(new CustomLocation("Educational Opportunity Center", new LatLng(32.734088, -97.106824)));
        customLocations.add(new CustomLocation("Grant and Contracting Accounting", new LatLng(32.736982, -97.109236)));
        customLocations.add(new CustomLocation("Institutional Compliance", new LatLng(32.733882, -97.121148)));
        customLocations.add(new CustomLocation("International Education", new LatLng(32.733882, -97.121148)));
        customLocations.add(new CustomLocation("Library - Architecture and Fine Arts", new LatLng(32.731265, -97.116128)));
        customLocations.add(new CustomLocation("Library - Central", new LatLng(32.72966, -97.112687)));
        customLocations.add(new CustomLocation("Library - Science and Engineering", new LatLng(32.732585, -97.113819)));
        customLocations.add(new CustomLocation("Marketing, Messaging, and Engagement", new LatLng(32.729151, -97.115171)));
        customLocations.add(new CustomLocation("Mav ID Office", new LatLng(32.73262281, -97.10849757)));
        customLocations.add(new CustomLocation("Military and Veteran Services", new LatLng(32.729178, -97.115171)));
        customLocations.add(new CustomLocation("OIT Help Desk", new LatLng(32.72971612, -97.11302218)));
        customLocations.add(new CustomLocation("Procurement Services", new LatLng(32.736982, -97.109236)));
        customLocations.add(new CustomLocation("Records and Registration (Registrar)", new LatLng(32.729178, -97.115171)));
        customLocations.add(new CustomLocation("Research Administration", new LatLng(32.72920179, -97.11520236)));
        customLocations.add(new CustomLocation("Student Access & Resource Center", new LatLng(32.729055, -97.114052)));
        customLocations.add(new CustomLocation("Student Accounts", new LatLng(32.729219, -97.115171)));
        customLocations.add(new CustomLocation("Terry Scholars Foundation", new LatLng(32.730934, -97.11218)));
        customLocations.add(new CustomLocation("Travel Services", new LatLng(32.736982, -97.109236)));
        customLocations.add(new CustomLocation("TRiO Support Services", new LatLng(32.730934, -97.11218)));
        customLocations.add(new CustomLocation("Accessible Parking", new LatLng(32.73323979, -97.11843252)));
        customLocations.add(new CustomLocation("F11 Visitor Parking", new LatLng(32.73237338, -97.11068094)));
        customLocations.add(new CustomLocation("Maverick Stadium", new LatLng(32.73069, -97.126391)));
        customLocations.add(new CustomLocation("Meadow Run General Parking", new LatLng(32.731877, -97.12059975)));
        customLocations.add(new CustomLocation("Park West Parking Garage", new LatLng(32.73352, -97.117231)));
        customLocations.add(new CustomLocation("Parking and Transportation Parking Lot", new LatLng(32.72922, -97.125163)));
        customLocations.add(new CustomLocation("Parking Lot 34 (student)", new LatLng(32.73339322, -97.11583614)));
        customLocations.add(new CustomLocation("Parking Lot 35", new LatLng(32.73409, -97.11544)));
        customLocations.add(new CustomLocation("Parking Lot 36", new LatLng(32.7345, -97.11315)));
        customLocations.add(new CustomLocation("Modern Languages Computer Lab", new LatLng(32.729887, -97.111534)));
        customLocations.add(new CustomLocation("Architecture Courtyard", new LatLng(32.731265, -97.116128)));
        customLocations.add(new CustomLocation("Bluebonnet Ballroom", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Carlisle Suite", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Carolyn A Barros Reading Room", new LatLng(32.730801, -97.111507)));
        customLocations.add(new CustomLocation("Clay Gould Ballpark", new LatLng(32.722201, -97.130803)));
        customLocations.add(new CustomLocation("Concho Room", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Dan Dipert University Welcome Center", new LatLng(32.731223, -97.107478)));
        customLocations.add(new CustomLocation("Engineering Mall", new LatLng(32.73217483, -97.11328268)));
        customLocations.add(new CustomLocation("Fine Arts Room 148", new LatLng(32.730577, -97.115085)));
        customLocations.add(new CustomLocation("Guadalupe Room", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Indoor Pool", new LatLng(32.73093, -97.117653)));
        customLocations.add(new CustomLocation("Irons Recital Hall", new LatLng(32.730577, -97.115085)));
        customLocations.add(new CustomLocation("Library Atrium", new LatLng(32.72966, -97.112687)));
        customLocations.add(new CustomLocation("Library Parlor", new LatLng(32.72966, -97.112687)));
        customLocations.add(new CustomLocation("Lone Star Auditorium", new LatLng(32.731925, -97.11705)));
        customLocations.add(new CustomLocation("Mainstage Theatre", new LatLng(32.73121039, -97.11486452)));
        customLocations.add(new CustomLocation("Maverick Pantry", new LatLng(32.73196499, -97.10753202)));
        customLocations.add(new CustomLocation("Neches Room", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Outdoor Pool", new LatLng(32.73093, -97.117653)));
        customLocations.add(new CustomLocation("Palo Duro Lounge", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Palo Pinto Room", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Pedernales Room", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Planetarium", new LatLng(32.730396, -97.111744)));
        customLocations.add(new CustomLocation("Rady Room", new LatLng(32.732585, -97.113819)));
        customLocations.add(new CustomLocation("Red River Room", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Rio Grande Ballroom", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Rosebud Theater", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("San Jacinto Room", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("San Saba Room", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Science and Engineering Library", new LatLng(32.732585, -97.113819)));
        customLocations.add(new CustomLocation("Sierras Lounge", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("Special Collections", new LatLng(32.72966, -97.112687)));
        customLocations.add(new CustomLocation("Studio Theatre", new LatLng(32.730577, -97.115085)));
        customLocations.add(new CustomLocation("The Gallery at UC", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("The Gallery at UTA", new LatLng(32.731457, -97.115053)));
        customLocations.add(new CustomLocation("The Green at College Park", new LatLng(32.729404, -97.107291)));
        customLocations.add(new CustomLocation("University Center Mall", new LatLng(32.731544, -97.110941)));
        customLocations.add(new CustomLocation("West Campus Library", new LatLng(32.731265, -97.116128)));

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap)
    {

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

        int colorGeneral = ContextCompat.getColor(this, R.color.general);
        int colorStemm = ContextCompat.getColor(this, R.color.stemm);
        int colorAdmin = ContextCompat.getColor(this, R.color.admin);
        int colorArts = ContextCompat.getColor(this, R.color.arts);
        int colorBusiness = ContextCompat.getColor(this, R.color.business);
        int colorAthletics = ContextCompat.getColor(this, R.color.athletics);
        int colorParking = ContextCompat.getColor(this, R.color.parking);
        int colorFacultyParking = ContextCompat.getColor(this,R.color.faculty_parking);

        LatLng building1 = new LatLng(32.726268, -97.117751);
        mMap.addMarker(new MarkerOptions()
                .position(building1)
                .title("West Mitchell Center - SAB")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building2 = new LatLng(32.726656, -97.125937);
        mMap.addMarker(new MarkerOptions()
                .position(building2)
                .title("Wetsel Service Center - WET")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building3 = new LatLng(32.726782, -97.107216);
        mMap.addMarker(new MarkerOptions()
                .position(building3)
                .title("DED Technical Training Ctr. - DE")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building4 = new LatLng(32.726832, -97.108455);
        mMap.addMarker(new MarkerOptions()
                .position(building4)
                .title("Continuing Ed & Workforce Development - CEWF")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building5 = new LatLng(32.727495, -97.107354);
        mMap.addMarker(new MarkerOptions()
                .position(building5)
                .title("Aerodynamics Research Building - ARB")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building6 = new LatLng(32.727543, -97.125545);
        mMap.addMarker(new MarkerOptions()
                .position(building6)
                .title("Civil Engineering Lab Building - CELB")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building7 = new LatLng(32.72764243, -97.11166903);
        mMap.addMarker(new MarkerOptions()
                .position(building7)
                .title("School of Social Work and College of Nursing and Health Innovation Smart Hospital Building - SWSH")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building8 = new LatLng(32.727747, -97.124157);
        mMap.addMarker(new MarkerOptions()
                .position(building8)
                .title("Library Collection Depository & OIT Office Building - LCDO")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAdmin))));

        LatLng building9 = new LatLng(32.727804, -97.124741);
        mMap.addMarker(new MarkerOptions()
                .position(building9)
                .title("Amphibian and Reptile Diversity Research Center - ARC")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building10 = new LatLng(32.728122, -97.112746);
        mMap.addMarker(new MarkerOptions()
                .position(building10)
                .title("Science & Engineering Innovation & Research Building - SEIR")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building11 = new LatLng(32.728415, -97.111283);
        mMap.addMarker(new MarkerOptions()
                .position(building11)
                .title("Pickard Hall - PKH")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building12 = new LatLng(32.728641, -97.112895);
        mMap.addMarker(new MarkerOptions()
                .position(building12)
                .title("Life Science Building - LS")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building13 = new LatLng(32.728687, -97.124942);
        mMap.addMarker(new MarkerOptions()
                .position(building13)
                .title("Studio Arts Center - SAC")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorArts))));

        LatLng building14 = new LatLng(32.729055, -97.114052);
        mMap.addMarker(new MarkerOptions()
                .position(building14)
                .title("University Hall - UH")
                .snippet("College of Liberal Arts - LIBA\nCriminology and Criminal Justice - CRCJ\nCriminology and Criminal Justice - CRJ\nHistory - HIST\nPolitical Science - POLS\nSociology and Anthropology - ANTH\nWomen's and Gender Studies - WOMS\nAcademic Testing and TSI Services\nStudent Access & Resource Center\n")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building15 = new LatLng(32.729178, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(building15)
                .title("University Administration Building - UA")
                .snippet("Graduate School - GRADS\nAdmissions\nAdmissions (Graduate)\nMilitary and Veteran Services\nRecords and Registration (Registrar)\n")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAdmin))));


        LatLng building16 = new LatLng(32.729209, -97.126382);
        mMap.addMarker(new MarkerOptions()
                .position(building16)
                .title("Maverick Stadium - STAD")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAthletics))));

        LatLng building17 = new LatLng(32.729425, -97.124411);
        mMap.addMarker(new MarkerOptions()
                .position(building17)
                .title("Parking & Transportation Services - PATS")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng building18 = new LatLng(32.729425, -97.124411);
        mMap.addMarker(new MarkerOptions()
                .position(building18)
                .title("Parking at UT Arlington - PATS")
                        .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng building19 = new LatLng(32.729459, -97.111647);
        mMap.addMarker(new MarkerOptions()
                .position(building19)
                .title("Maverick Parking Garage - GARA")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng building20 = new LatLng(32.729576, -97.110579);
        mMap.addMarker(new MarkerOptions()
                .position(building20)
                .title("Business Building - COBA")
                .snippet("Accounting - ACCT\nCollege of Business - BUSA/BCOL\nEconomics - ECON\nFinance and Real Estate - FINA\nInformation Systems and Operations Management - INSY\nManagement - MANA\nMarketing - MARK\nMathematical Sciences - MSCI")

                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorBusiness))));


        LatLng building21 = new LatLng(32.729641, -97.112045);
        mMap.addMarker(new MarkerOptions()
                .position(building21)
                .title("Hammond Hall - HH")
                .snippet("College of Education - COED\nCurriculum and Instruction - EDUC\nEducational Leadership and Policy Studies - EDAD\nLinguistics & TESOL - LING\nModern Languages - MODL")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorArts))));


        LatLng building22 = new LatLng(32.72966, -97.112687);
        mMap.addMarker(new MarkerOptions()
                .position(building22)
                .title("Central Library - LIBR")
                .snippet("Academic Support Programs\nLibrary - Central\nLibrary Atrium\nLibrary Parlor\nSpecial Collections")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building23 = new LatLng(32.729701, -97.127416);
        mMap.addMarker(new MarkerOptions()
                .position(building23)
                .title("C.R. Gilstrap Athletic Center - GILS")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAthletics))));

        LatLng building25 = new LatLng(32.729723, -97.115497);
        mMap.addMarker(new MarkerOptions()
                .position(building25)
                .title("Texas Hall - TEX")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorArts))));

        LatLng building26 = new LatLng(32.729887, -97.111534);
        mMap.addMarker(new MarkerOptions()
                .position(building26)
                .title("Trimble Hall - TH")
                .snippet("Modern Languages Computer Lab")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorArts))));


        LatLng building27 = new LatLng(32.730187, -97.108181);
        mMap.addMarker(new MarkerOptions()
                .position(building27)
                .title("College Park Center - CPC")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAthletics))));


        LatLng building29 = new LatLng(32.73034154, -97.11553463);
        mMap.addMarker(new MarkerOptions()
                .position(building29)
                .title("CAPPA South - B - SHO")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building30 = new LatLng(32.73038, -97.112816);
        mMap.addMarker(new MarkerOptions()
                .position(building30)
                .title("W. A. Baker Chemistry Research Building - CRB")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building31 = new LatLng(32.730396, -97.111744);
        mMap.addMarker(new MarkerOptions()
                .position(building31)
                .title("Chemistry & Physics Building - CPB")
                .snippet("Chemistry and Biochemistry - CHEM\nPhysics - PHYS\nPlanetarium")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));


        LatLng building32 = new LatLng(32.73041985, -97.11588374);
        mMap.addMarker(new MarkerOptions()
                .position(building32)
                .title("CAPPA South - A - SHC")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building33 = new LatLng(32.730438, -97.114186);
        mMap.addMarker(new MarkerOptions()
                .position(building33)
                .title("Science Hall - SH")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building34 = new LatLng(32.730456, -97.110756);
        mMap.addMarker(new MarkerOptions()
                .position(building34)
                .title("Health Center - HLTH")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building35 = new LatLng(32.73051, -97.110214);
        mMap.addMarker(new MarkerOptions()
                .position(building35)
                .title("Thermal Energy Plant - TEP")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building36 = new LatLng(32.730577, -97.115085);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(building36)
                .title("Fine Arts Building - FA")
                .snippet("Art and Art History - ART\nCommunication - COMM\nMusic - MUSI\nFine Arts Room 148\nIrons Recital Hall\nStudio Theatre")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorArts))));


        LatLng building38 = new LatLng(32.73065, -97.112586);
        mMap.addMarker(new MarkerOptions()
                .position(building38)
                .title("Carlisle Hall - CARH")
                .snippet("English - ENGL")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorArts))));


        LatLng building39 = new LatLng(32.730801, -97.111507);
        mMap.addMarker(new MarkerOptions()
                .position(building39)
                .title("College Hall - CH")
                .snippet("Honors College - HCOL/HONR\nMilitary Science - MILS\nPhilosophy and Humanities - PHIL\nCarolyn A Barros Reading Room")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building40 = new LatLng(32.730905, -97.112856);
        mMap.addMarker(new MarkerOptions()
                .position(building40)
                .title("Preston Hall - PH")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building41 = new LatLng(32.73093, -97.117653);
        mMap.addMarker(new MarkerOptions()
                .position(building41)
                .title("Physical Education - PE")
                .snippet("Indoor Pool\nOutdoor Pool")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building42 = new LatLng(32.730934, -97.11218);
        mMap.addMarker(new MarkerOptions()
                .position(building42)
                .title("Ransom Hall - RH")
                .snippet("University Studies\nAcademic Coaching\nDivision of Student Success\nTerry Scholars Foundation\nTRiO Support Services\nTheatre Arts and Dance - THEA\nMainstage Theatre")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building43 = new LatLng(32.731265, -97.116128);
        mMap.addMarker(new MarkerOptions()
                .position(building43)
                .title("CAPPA Building - ARCH")
                .snippet("Architecture, Planning and Public Affairs, College of - CAPPA\nLibrary - Architecture and Fine Arts\nArchitecture Courtyard\nWest Campus Library")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building44 = new LatLng(32.731329, -97.114177);
        mMap.addMarker(new MarkerOptions()
                .position(building44)
                .title("Earth & Environmental Sciences - EES")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building45 = new LatLng(32.73138, -97.112969);
        mMap.addMarker(new MarkerOptions()
                .position(building45)
                .title("Woolf Hall - WH")
                .snippet("Aerospace Engineering - AE\nIndustrial, Manufacturing, and Systems Engineering - IE\nMechanical and Aerospace Engineering - MAE")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));


        LatLng building46 = new LatLng(32.731544, -97.110941);
        mMap.addMarker(new MarkerOptions()
                .position(building46)
                .title("E.H. Hereford University Center - UC")
                .snippet("Bluebonnet Ballroom\nCarlisle Suite\nConcho Room\nGuadalupe Room\nNeches Room\nPalo Duro Lounge\nPalo Pinto Room\nPedernales Room\nRed River Room\nRio Grande Ballroom\nRosebud Theater\nSan Jacinto Room\nSan Saba Room\nSierras Lounge\nThe Gallery at UC\nUniversity Center Mall")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building47 = new LatLng(32.731925, -97.11705);
        mMap.addMarker(new MarkerOptions()
                .position(building47)
                .title("Maverick Activities Center - MAC")
                .snippet("Kinesiology - KINE\nLone Star Auditorium")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAthletics))));


        LatLng building48 = new LatLng(32.732003, -97.119913);
        mMap.addMarker(new MarkerOptions()
                .position(building48)
                .title("Tennis Center - TENN")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAthletics))));

        LatLng building49 = new LatLng(32.7321, -97.116078);
        mMap.addMarker(new MarkerOptions()
                .position(building49)
                .title("CAPPA North - CMPC")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building50 = new LatLng(32.732313, -97.122161);
        mMap.addMarker(new MarkerOptions()
                .position(building50)
                .title("Environmental Health & Safety - EH")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building51 = new LatLng(32.732326, -97.11267);
        mMap.addMarker(new MarkerOptions()
                .position(building51)
                .title("Engineering Lab Building - ELAB")
                .snippet("Materials Science and Engineering - MSE")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));


        LatLng building52 = new LatLng(32.732407, -97.115512);
        mMap.addMarker(new MarkerOptions()
                .position(building52)
                .title("Nanofab Building - NANO")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building53 = new LatLng(32.732585, -97.113819);
        mMap.addMarker(new MarkerOptions()
                .position(building53)
                .title("Nedderman Hall - NH")
                .snippet("Civil Engineering - CE\nCollege of Engineering - COE\nElectrical Engineering - EE\nDistance Education\nLibrary - Science and Engineering\nRady Room")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));


        LatLng building54 = new LatLng(32.733092, -97.118528);
        mMap.addMarker(new MarkerOptions()
                .position(building54)
                .title("West Hall")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building55 = new LatLng(32.733095, -97.117113);
        mMap.addMarker(new MarkerOptions()
                .position(building55)
                .title("The Commons - COM")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building56 = new LatLng(32.733159, -97.123303);
        mMap.addMarker(new MarkerOptions()
                .position(building56)
                .title("Environmental Health & Safety (West) - EHW")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building57 = new LatLng(32.733211, -97.112513);
        mMap.addMarker(new MarkerOptions()
                .position(building57)
                .title("Engineering Research Building - ERB")
                .snippet("Bioengineering - BME\nComputer Science and Engineering - CSE")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));


        LatLng building58 = new LatLng(32.73326, -97.105225);
        mMap.addMarker(new MarkerOptions()
                .position(building58)
                .title("University Police Department - UPD")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building59 = new LatLng(32.733351, -97.109269);
        mMap.addMarker(new MarkerOptions()
                .position(building59)
                .title("Bookstore - BOOK")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building60 = new LatLng(32.73359974, -97.10744466);
        mMap.addMarker(new MarkerOptions()
                .position(building60)
                .title("Center for Entrepreneurship and Technology Development - CEEI")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building61 = new LatLng(32.733867, -97.122085);
        mMap.addMarker(new MarkerOptions()
                .position(building61)
                .title("Military & Veteran Services - VAC")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building62 = new LatLng(32.733882, -97.121148);
        mMap.addMarker(new MarkerOptions()
                .position(building62)
                .title("Swift Center - SC")
                .snippet("Institutional Compliance\nInternational Education")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building63 = new LatLng(32.734048, -97.123047);
        mMap.addMarker(new MarkerOptions()
                .position(building63)
                .title("Transforming Lives Child Development Center - DAYC")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building64 = new LatLng(32.734088, -97.106824);
        mMap.addMarker(new MarkerOptions()
                .position(building64)
                .title("Wade Building - WDB")
                .snippet("Educational Opportunity Center")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        LatLng building65 = new LatLng(32.734244, -97.114176);
        mMap.addMarker(new MarkerOptions()
                .position(building65)
                .title("General Academic Classroom Building - GACB")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building66 = new LatLng(32.734666, -97.114125);
        mMap.addMarker(new MarkerOptions()
                .position(building66)
                .title("Academic Buildings - A - SWCA")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building67 = new LatLng(32.734707, -97.113682);
        mMap.addMarker(new MarkerOptions()
                .position(building67)
                .title("Academic Buildings - B - SWCB")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building68 = new LatLng(32.736982, -97.109236);
        mMap.addMarker(new MarkerOptions()
                .position(building68)
                .title("Finance and Administration Annex (Watson building) - FAAA")
                .snippet("Accounting Services\nBudgets and Financial Planning\nBusiness Services\nGrant and Contracting Accounting\nProcurement Services\nTravel Services")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAdmin))));


        LatLng building69 = new LatLng(32.73803198, -97.11480618);
        mMap.addMarker(new MarkerOptions()
                .position(building69)
                .title("CAPPA Community Design Lab - DBI")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng building70 = new LatLng(32.749654, -97.324825);
        mMap.addMarker(new MarkerOptions()
                .position(building70)
                .title("Fort Worth Center - UTASF")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building71 = new LatLng(32.784965, -97.219874);
        mMap.addMarker(new MarkerOptions()
                .position(building71)
                .title("UT Arlington Research Institute - UTARI")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng building72 = new LatLng(32.823796, -96.845489);
        mMap.addMarker(new MarkerOptions()
                .position(building72)
                .title("Center for Addiction and Recovery Studies - CARS")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng department1 = new LatLng(32.72752087, -97.11141429);
        mMap.addMarker(new MarkerOptions()
                .position(department1)
                .title("School of Social Work - SOCW")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorStemm))));

        LatLng department2 = new LatLng(32.73121039, -97.11486452);
        mMap.addMarker(new MarkerOptions()
                .position(department2)
                .title("Theatre Arts and Dance - THEA")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorArts))));

        LatLng horse1 = new LatLng(32.73027507099109, -97.10876001719758);
        mMap.addMarker(new MarkerOptions()
                .position(horse1)
                .title("PRIDE - College Park Center")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse2 = new LatLng(32.73038491849808, -97.11395627116386);
        mMap.addMarker(new MarkerOptions()
                .position(horse2)
                .title("TRIUMPH - Science Hall")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse3 = new LatLng(32.735255509413456, -97.10678766560486);
        mMap.addMarker(new MarkerOptions()
                .position(horse3)
                .title("BLAZE - Downtown Arlington")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse4 = new LatLng(32.73310898923705, -97.11763488094941);
        mMap.addMarker(new MarkerOptions()
                .position(horse4)
                .title("BLOSSOM - West Hall")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse6 = new LatLng(32.731371343217525, -97.11095481534456);
        mMap.addMarker(new MarkerOptions()
                .position(horse6)
                .title("LASTING IMPRINT - University Center")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse7 = new LatLng(32.73044479449717, -97.1110155441807);
        mMap.addMarker(new MarkerOptions()
                .position(horse7)
                .title("HARMONY - Health Center")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse8 = new LatLng(32.73142009365488, -97.10726144047472);
        mMap.addMarker(new MarkerOptions()
                .position(horse8)
                .title("MOSAIC - Library Mall")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse9 = new LatLng(32.733693762770464, -97.10875869444097);
        mMap.addMarker(new MarkerOptions()
                .position(horse9)
                .title("BRILLIANCE - UTA Bookstore")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse10 = new LatLng(32.731721118181255, -97.11650195581929);
        mMap.addMarker(new MarkerOptions()
                .position(horse10)
                .title("GOLDEN RATIO - MAC")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse11 = new LatLng(32.73039751996346, -97.11209492513012);
        mMap.addMarker(new MarkerOptions()
                .position(horse11)
                .title("AND BEYOND - Chemistry and Physics")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse12 = new LatLng(32.73148386823739, -97.1130889);
        mMap.addMarker(new MarkerOptions()
                .position(horse12)
                .title("DYNAMIC - Woolf Hall")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng horse13 = new LatLng(32.73080041265708, -97.1194903);
        mMap.addMarker(new MarkerOptions()
                .position(horse13)
                .title("LEGACY - GREEK ROW")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng office1 = new LatLng(32.72909323, -97.11503508);
        mMap.addMarker(new MarkerOptions()
                .position(office1)
                .title("Alumni Relations")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAdmin))));

        LatLng office2 = new LatLng(32.729151, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(office2)
                .title("Marketing, Messaging, and Engagement")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAdmin))));

        LatLng office3 = new LatLng(32.72920179, -97.11520236);
        mMap.addMarker(new MarkerOptions()
                .position(office3)
                .title("Research Administration")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAdmin))));

        LatLng office4 = new LatLng(32.729219, -97.115171);
        mMap.addMarker(new MarkerOptions()
                .position(office4)
                .title("Student Accounts")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAdmin))));

        LatLng office5 = new LatLng(32.72971612, -97.11302218);
        mMap.addMarker(new MarkerOptions()
                .position(office5)
                .title("OIT Help Desk")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng office6 = new LatLng(32.73262281, -97.10849757);
        mMap.addMarker(new MarkerOptions()
                .position(office6)
                .title("Mav ID Office")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng parking1 = new LatLng(32.726122, -97.118165);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking1)
                .title("Student & Administration Parking Lot")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng parking2 = new LatLng(32.72853761, -97.1075213);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking2)
                .title("Parking Lot 45")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng parking3 = new LatLng(32.72922, -97.125163);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking3)
                .title("Parking and Transportation Parking Lot")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng parking4 = new LatLng(32.73069, -97.126391);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking4)
                .title("Maverick Stadium")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng parking5 = new LatLng(32.731877, -97.12059975);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking5)
                .title("Meadow Run General Parking")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng parking6 = new LatLng(32.73237338, -97.11068094);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking6)
                .title("F11 Visitor Parking")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng parking7 = new LatLng(32.73323979, -97.11843252);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking7)
                .title("Accessible Parking")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng parking8 = new LatLng(32.73339322, -97.11583614);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking8)
                .title("Parking Lot 34 (student)")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng parking9 = new LatLng(32.73352, -97.117231);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking9)
                .title("Park West Parking Garage")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng parking10 = new LatLng(32.73409, -97.11544);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking10)
                .title("Parking Lot 35")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng parking11 = new LatLng(32.7345, -97.11315);
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .position(parking11)
                .title("Parking Lot 36")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorParking))));

        LatLng venue1 = new LatLng(32.722201, -97.130803);
        mMap.addMarker(new MarkerOptions()
                .position(venue1)
                .title("Clay Gould Ballpark")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAthletics))));

        LatLng venue2 = new LatLng(32.72249, -97.129295);
        mMap.addMarker(new MarkerOptions()
                .position(venue2)
                .title("Allan Saxe Softball Field")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorAthletics))));

        LatLng venue3 = new LatLng(32.72764243, -97.11166903);
        mMap.addMarker(new MarkerOptions()
                .position(venue3)
                .title("Smart Hospital")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng venue4 = new LatLng(32.729404, -97.107291);
        mMap.addMarker(new MarkerOptions()
                .position(venue4)
                .title("The Green at College Park")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng venue5 = new LatLng(32.731223, -97.107478);
        mMap.addMarker(new MarkerOptions()
                .position(venue5)
                .title("Dan Dipert University Welcome Center")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng venue6 = new LatLng(32.731457, -97.115053);
        mMap.addMarker(new MarkerOptions()
                .position(venue6)
                .title("The Gallery at UTA")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng venue7 = new LatLng(32.73196499, -97.10753202);
        mMap.addMarker(new MarkerOptions()
                .position(venue7)
                .title("Maverick Pantry")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));

        LatLng venue8 = new LatLng(32.73217483, -97.11328268);
        mMap.addMarker(new MarkerOptions()
                .position(venue8)
                .title("Engineering Mall")
                .icon(BitmapDescriptorFactory.defaultMarker(getMarkerColor(colorGeneral))));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            boolean isCustomInfoWindowOpen = false;

            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                if (!isCustomInfoWindowOpen) {
                    mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoWindow(@NonNull Marker marker) {
                            return null; // Return null to use the default info window
                        }

                        @Override
                        public View getInfoContents(@NonNull Marker marker) {
                            // Inflate the custom layout for the info window
                            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_info_window, null);

                            // Get references to your views in the custom layout
                            TextView infoTitle = v.findViewById(R.id.info_title);
                            TextView infoSnippet = v.findViewById(R.id.info_snippet);

                            // Set marker title and snippet in the custom info window
                            infoTitle.setText(marker.getTitle());
                            infoSnippet.setText(marker.getSnippet());

                            // Return the custom info window layout
                            return v;
                        }
                    });

                    marker.showInfoWindow(); // Open the custom info window
                    isCustomInfoWindowOpen = true;
                } else {
                    marker.hideInfoWindow(); // Close the info window
                    mMap.setInfoWindowAdapter(null); // Revert to default info window
                    isCustomInfoWindowOpen = false;
                }
                return true;
            }
        });
    }


    private float getMarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv[0];
    }
    private class CustomLocation
    {
        private String name;
        private LatLng latLng;

        public CustomLocation(String name, LatLng latLng) {
            this.name = name;
            this.latLng = latLng;
        }

        public String getName() {
            return name;
        }

        public LatLng getLatLng() {
            return latLng;
        }
    }

    /* COLOR VALUES
    Hex: #ffffff, Hue: 350
    Hex: #03a1fc, Hue: 201.93
    Hex: #8e9394, Hue: 190.00
    Hex: #e5eb71, Hue: 62.95
    Hex: #71eb87, Hue: 130.82
    Hex: #d42c2c, Hue: 305
    Hex: #f7a01e, Hue: 35.94

    General - White
    STEMM - Blue
    Administrative - Grey
    Arts - Yellow
    Business - Green
    Athletics - Red
    Parking - Orange
    */
}