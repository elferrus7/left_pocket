package com.example.left_poecket;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

@SuppressLint("NewApi")
public class GastoFragment extends Fragment {
	
	Spending mGasto;
	EditText mCantidad;
	EditText mFecha;
	Spinner mModoPago;
	Button mGuardar;
	Button mCalendario;
	Context context;
	
	private String initialDate;
	private String initialMonth;
	private String initialYear;
	private DatePickerDialog dialog = null;
	static final int DATE_PICKER_ID = 1111;
	

	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	
	@SuppressLint("NewApi")
	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.ingresa_gasto,container,false);
		addListenerOnSpinnerItemSelection(v);
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		//datetimepicker 
		//tipo ingreso o egreso, falta ver como guardar el numero
		//guardar
		//back button
		
		mCantidad = (EditText) v.findViewById(R.id.Cantidad_texto);
		mCantidad.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				//
						
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				mGasto.setAmount(Double.parseDouble(s.toString()));	
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
			
		});
			
		mGuardar = (Button) v.findViewById(R.id.btnAgregar);
		mGuardar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ver que onda con el modelo
				
			}
		});
		
		mFecha = (EditText) v.findViewById(R.id.txtFecha);
		
		mCalendario = (Button) v.findViewById(R.id.btnCalendario);
		mCalendario.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar dtTxt = null;
				String preExistingDate = (String)mFecha.getText().toString();
				if(preExistingDate != null && !preExistingDate.equals("")){
					
					String Tokenizer st = new String Tokenizer(preExistingDate,"/");
				}
				
			}
		});
		
		
		
		

		
		
		
		
		
		
		return v;
		
	}
	
	

	private void addListenerOnSpinnerItemSelection(View v) {
		// TODO Auto-generated method stub
		mModoPago = (Spinner) v.findViewById(R.id.modo_pago);		
	}

	


	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(getActivity());
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}


}
