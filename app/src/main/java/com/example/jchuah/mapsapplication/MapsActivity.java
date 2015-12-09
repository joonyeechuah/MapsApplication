package com.example.jchuah.mapsapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    Marker correctCity;
    Marker paris;
    Marker berlin;
    Marker tokyo;

    View interfaceView;

    // a bunch of marker variables with cities in your game

    GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    void visitParis() {
        mMap.clear();
        TextView tv = (TextView)interfaceView.findViewById(R.id.textView);
        tv.setText("Welcome to Paris!");
        createBerlinMarker();
    }

    Marker createBerlinMarker() {
        berlin = mMap.addMarker( new MarkerOptions()
                .position(new LatLng(1,1))
                .title("Berlin")
                .snippet("The city of eating sausages and potatoes!"));
        return berlin;
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }

        if (interfaceView == null) {
            interfaceView = getSupportFragmentManager().findFragmentById(R.id.interact).getView();
        }
    }

    public void onTestButtonClick(View view) {
        Toast.makeText(this, "Test button click", Toast.LENGTH_SHORT).show();
        TextView tv = (TextView)interfaceView.findViewById(R.id.textView);
        tv.setText("NBLAHBLAKSJFLJKDF");
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        paris = mMap.addMarker(
                new MarkerOptions()
                        .position(new LatLng(0, 0))
                        .title("Paris")
                        .snippet("The city of lights! Our nefarious criminal has left a clue! They went to a city where there is another tower that looks like the Eiffel tower, but they wear Kimonos there.")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.cast_ic_notification_0))
        );
        // a whole bunch of other city marker creations
        tokyo = mMap.addMarker( new MarkerOptions()
                .position(new LatLng(1,1))
                .title("Tokyo")
                .snippet("The city of sushi!"));
        correctCity = paris;


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (marker.equals(paris)) {
                        visitParis();
                    }
                    if (marker.equals(berlin)) {
                    }
                    return false;
                }
        });
    }

    public static class InteractiveFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
          return inflater.inflate(R.layout.interactive_fragment, container, false);
        }
    }
}
