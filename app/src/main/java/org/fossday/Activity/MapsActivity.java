package org.fossday.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.fossday.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng fossDay = new LatLng(-29.1479186, -51.5199421);
        mMap.addMarker(new MarkerOptions().position(fossDay).title("FOSSDay 2019"));
        mMap.setBuildingsEnabled(true);
        mMap.setTrafficEnabled(false);

        mMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(fossDay, 18f)
        );

        progressBar.setVisibility(View.GONE);
    }
}
