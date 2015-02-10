package com.demomapas;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MapView extends FragmentActivity implements LocationListener,OnMarkerClickListener{
	 GoogleMap mMap;
	 SupportMapFragment mMapFragment;
	 private LocationManager mLocationManager;
	 public Location location;
	 RelativeLayout contenedor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//LinearLayout rLGreen = ((LinearLayout) button.getParent());
		Display display = getWindowManager().getDefaultDisplay();
		contenedor = (RelativeLayout) findViewById(R.id.contenedorMain);
		//Point size = new Point();
		//display.getSize(size);
		//int width = size.x;
		//int height = size.y;
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);    
		Criteria criteria = new Criteria();
		String provider = null;
		provider = locationManager.getBestProvider(criteria, false);
		if(provider!=null && !provider.equals("")){
			
			// Get the location from the given provider 
		    location = locationManager.getLastKnownLocation(provider);
		                
		    locationManager.requestLocationUpdates(provider, 2000, 1, this);

		    
		    
		    if(location!=null)
		    	onLocationChanged(location);
		    else
		    	Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();
		    
		}else{
			Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
		}
		// Getting the name of the provider that meets the criteria
	
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		Log.i("latitud", latitude+"\n");
		Log.i("longitud", longitude+"\n");
		
		

		  
		CameraUpdate center=
		        CameraUpdateFactory.newLatLng(new LatLng(latitude,
		                                                 longitude));
		    CameraUpdate zoom=CameraUpdateFactory.zoomTo(17);

		 
		
		mMapFragment = (SupportMapFragment) (getSupportFragmentManager()
	            .findFragmentById(R.id.frag_ubicacion));
		
		if (mMap == null) {
		//mMap =((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.frag_ubicacion)).getMap();
		}

		//ViewGroup.LayoutParams params = mMapFragment.getView().getLayoutParams();
		//params.height = (int)height/2;
		//mMapFragment.getView().setLayoutParams(params);
		
		mMap=  mMapFragment.getMap();
		mMap.moveCamera(center);
		mMap.animateCamera(zoom);
		mMap.setMyLocationEnabled(true);
		mMap.setOnMarkerClickListener((OnMarkerClickListener) this);
		Geocoder geocoder;
		List<Address> addresses = null;
		geocoder = new Geocoder(this, Locale.getDefault());
		try {
			addresses = geocoder.getFromLocation(latitude, longitude, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LatLng parametros = new LatLng(latitude, longitude);
		LatLng parametros2 = new LatLng(19.428524, -99.170938);
		

		String address = addresses.get(0).getAddressLine(0);
		String city = addresses.get(0).getAddressLine(1);
		String country = addresses.get(0).getAddressLine(2);
		//TextView direccion = (TextView) findViewById(R.id.direccion);
		//direccion.setText(address+" "+city+" "+country);
		mMap.addMarker(new MarkerOptions().position(parametros).title("marcador1"));
		mMap.addMarker(new MarkerOptions().position(parametros2).title("marcador2"));
		
	///////esto no lo ocupe
		//mMapFragment.getView().setLayoutParams(params);
	//ViewGroup.LayoutParams params = mMap.getView().getLayoutParams();

		//params.height = 900;
	//mMapFragment.getView().setLayoutParams(params);
		//mMap.getView()
		//mMapFragment.setMyLocationEnabled(true);
		///////// hasta aqui

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		Log.i("marker clicked", marker.getTitle());
		  Intent intent = new Intent(MapView.this,LevantarInformacion.class);
          startActivity(intent);

		return false;
	}
	
}
