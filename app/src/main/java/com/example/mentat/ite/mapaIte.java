package com.example.mentat.ite;

import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class mapaIte  extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private static final LatLng ITE = new LatLng(31.8059831, -116.5931634);
    private static final float zoomLevel = (float) 16.0;
    public static final String TAG = mapaIte.class.getSimpleName();
    private GoogleApiClient mGoogleApiClient;
    List<Marker> markerList = new ArrayList<Marker>();

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        setUpIteMarket();
        getSupportActionBar().setSubtitle("Ubicación");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mapa, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.ver:
                setUpIteMarket();
                return true;
            case R.id.llegar:
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location == null) {
                    Toast.makeText(getApplicationContext(),"No se pudo encontrar su ubicación",Toast.LENGTH_SHORT).show();
                }
                else {
                    onGoIte();
                }
                return true;
            case R.id.regresar:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onGoIte(){
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        handleNewLocation(location);
        String url = getMapsApiDirectionsUrl();
        ReadTask downloadTask = new ReadTask();
        downloadTask.execute(url);
    }

    public void setUpIteMarket(){
            Marker marker;
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                marker =mMap.addMarker(new MarkerOptions().position(ITE).title("Instituto Tecnológico  de Ensenada."));
                markerList.add(marker);
                CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(ITE, zoomLevel);
                mMap.animateCamera(yourLocation);
                marker.showInfoWindow();
            }
    }


    private void handleNewLocation(Location location) {
        Marker marker;
        mMap.clear();
        markerList.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        marker= mMap.addMarker(new MarkerOptions().position(ITE).title("Instituto Tecnológico  de Ensenada."));
        markerList.add(marker);

        marker =  mMap.addMarker(new MarkerOptions().position(latLng).title("Usted se encuentra Aquí"));
        marker.showInfoWindow();
        markerList.add(marker);

        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel);
        mMap.animateCamera(yourLocation);

    }

    private String getMapsApiDirectionsUrl() {

        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        String waypoints = "waypoints=optimize:true|"
                + ITE.latitude + "," + ITE.longitude
                + "|" + "|" + location.getLatitude() + "," + location.getLongitude();

        String sensor = "sensor=false";
        String origin = "origin=" + ITE.latitude + "," + ITE.longitude;
        String destination = "destination=" + location.getLatitude()  + "," + location.getLongitude();
        String params = origin + "&" + destination + "&%20" + waypoints + "&" + sensor;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + params;
        return url;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected.");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            Toast.makeText(getApplicationContext(), "No se pudo encontrar su ubicación", Toast.LENGTH_SHORT).show();
        }
        else {
            handleNewLocation(location);
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpIteMarket();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /*
    *  Class to draw the path of the current locatión to ite
    **/
    private class ReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                HttpConnection http = new HttpConnection();
                data = http.readUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new ParserTask().execute(result);
        }
    }

    private class ParserTask extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;
            // traversing through routes
            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }

                polyLineOptions.addAll(points);
                polyLineOptions.width(5);
                polyLineOptions.color(Color.BLUE);
            }

            mMap.addPolyline(polyLineOptions);
        }
    }
}