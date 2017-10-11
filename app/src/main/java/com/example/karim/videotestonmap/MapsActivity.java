package com.example.karim.videotestonmap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import jp.shts.android.storiesprogressview.StoriesProgressView;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    StoriesProgressView storiesProgressView;

    ImageView imageView;
    int counter=0;
    int []resurese=new int[]{
            R.drawable.img,
            R.drawable.got1,
            R.drawable.got2
    };
    private GoogleMap mMap;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //checking the network provider is enabled
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //get loatiuide
                    double latiuide = location.getLatitude();
                    //get longlatuide
                    double longttuide = location.getLongitude();
                    LatLng latLng =new LatLng(latiuide,longttuide);
                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    try {
                        List<Address>addressList= geocoder.getFromLocation(latiuide,longttuide,1);
                        String str=addressList.get(0).getCountryName();
                        str+=","+addressList.get(0).getLocality();
                       mMap.addMarker(new MarkerOptions().position(latLng).title(str)/*.icon(BitmapDescriptorFactory.fromResource(R.drawable.img))*/);
mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,30.0f));
                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
    @Override
    public boolean onMarkerClick(Marker marker) {
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(MapsActivity.this);
        final View mView=getLayoutInflater().inflate(R.layout.dialog,null);
        storiesProgressView=(StoriesProgressView) mView.findViewById(R.id.Stories2);
        storiesProgressView.setStoriesCount(3);
        storiesProgressView.setStoryDuration(50000L);
        storiesProgressView.setStoriesListener(new StoriesProgressView.StoriesListener() {
            @Override
            public void onNext() {
                imageView.setImageResource(resurese[++counter]);
            }

            @Override
            public void onComplete() {
                Toast.makeText(MapsActivity.this, "Compelete", Toast.LENGTH_SHORT).show();
                storiesProgressView.destroy();

            }
        });
        storiesProgressView.startStories();
        imageView=(ImageView)mView.findViewById(R.id.image2);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storiesProgressView.skip();
            }
        });
        imageView.setImageResource(resurese[counter]);
        mBuilder.setView(mView);
        AlertDialog alertDialog=mBuilder.create();
        alertDialog.show();
        return false;
    }
});

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
            });
        }
        else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //get loatiuide
                    double latiuide = location.getLatitude();
                    //get longlatuide
                    double longttuide = location.getLongitude();
                    LatLng latLng =new LatLng(latiuide,longttuide);
                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    try {
                        List<Address>addressList= geocoder.getFromLocation(latiuide,longttuide,1);
                        String str=addressList.get(0).getCountryName();
                        str+=","+addressList.get(0).getLocality();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str)/*.icon(BitmapDescriptorFactory.fromResource(R.drawable.img))*/);
                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                Toast.makeText(getApplicationContext(),"Her",Toast.LENGTH_LONG).show();
                                return false;
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
            });
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    GPSTracker gps;

    @Override

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
       // mMap.setMinZoomPreference(50.0f);
        // Add a marker in Sydney and move the camera
      /*  LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

}
