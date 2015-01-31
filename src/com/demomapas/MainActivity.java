package com.demomapas;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;

/**
 * The Main Activity.
 * 
 * This activity starts up the RegisterActivity immediately, which communicates
 * with your App Engine backend using Cloud Endpoints. It also receives push
 * notifications from backend via Google Cloud Messaging (GCM).
 * 
 * Check out RegisterActivity.java for ramore details.
 */
public class MainActivity extends FragmentActivity implements LocationListener{

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
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
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
		mMap =((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.frag_ubicacion)).getMap();
		}
//			mMap = ((SupportMapFragment) getSupportFragmentManager()
//					.findFragmentById(R.id.activity_main)).getMap();
//		}
		ViewGroup.LayoutParams params = mMapFragment.getView().getLayoutParams();
		params.height = (int)height/2;
		mMapFragment.getView().setLayoutParams(params);
		mMap=  mMapFragment.getMap();
		mMap.moveCamera(center);
		mMap.animateCamera(zoom);
		mMap.setMyLocationEnabled(true);
		Geocoder geocoder;
		List<Address> addresses = null;
		geocoder = new Geocoder(this, Locale.getDefault());
		try {
			addresses = geocoder.getFromLocation(latitude, longitude, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String address = addresses.get(0).getAddressLine(0);
		String city = addresses.get(0).getAddressLine(1);
		String country = addresses.get(0).getAddressLine(2);
		TextView direccion = (TextView) findViewById(R.id.direccion);
		direccion.setText(address+" "+city+" "+country);
	
		//mMapFragment.getView().setLayoutParams(params);
	//ViewGroup.LayoutParams params = mMap.getView().getLayoutParams();

		//params.height = 900;
	//mMapFragment.getView().setLayoutParams(params);
		//mMap.getView()
		//mMapFragment.setMyLocationEnabled(true);
//
//		// Start up RegisterActivity right away
//		Intent intent = new Intent(this, RegisterActivity.class);
//		startActivity(intent);
//		// Since this is just a wrapper to start the main activity,
//		// finish it after launching RegisterActivity
//		finish();
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
	
}
