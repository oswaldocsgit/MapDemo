package com.demomapas;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LevantarInformacion extends Activity{

	private EditText nombre;
	private EditText apm;
	private EditText app;
	private EditText delito;
	LinearLayout contenedor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.infouser);
	contenedor = (LinearLayout)findViewById(R.id.contenedorInfo);
	nombre = (EditText)findViewById(R.id.nombre);
	app = (EditText)findViewById(R.id.app);
	apm = (EditText)findViewById(R.id.apm);
	delito = (EditText)findViewById(R.id.delito);
	Button enviar = new Button(getApplicationContext());
	enviar.setText("enviar informacion");
	enviar.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "listo para enviar informacion", Toast.LENGTH_LONG).show();
		}
	});
	contenedor.addView(enviar);
	
	
	
	
	
	}

}
