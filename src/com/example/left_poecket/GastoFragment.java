package com.example.left_poecket;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class GastoFragment extends Fragment {
	
	Spending mGasto;
	EditText mCantidad;
	EditText mFecha;
	EditText mComentarios;
	Spinner mModoPago;
	Spinner mTipoPago;
	Button mGuardar;
	Calendar mCalendar;
	
	private String initialDate;
	private String initialMonth;
	private String initialYear;
	private DatePickerDialog dialog = null;
	static final int DATE_PICKER_ID = 1111;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		setHasOptionsMenu(true);
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
		
		mCantidad = (EditText) v.findViewById(R.id.Cantidad_texto);			
		mComentarios = (EditText) v.findViewById(R.id.txt_comentario);
		mGuardar = (Button) v.findViewById(R.id.btnAgregar);
		
		mGuardar.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try{
					double cantidad = Double.parseDouble(mCantidad.getText().toString());
					int tipo = mTipoPago.getSelectedItemPosition();
					int modo = mModoPago.getSelectedItemPosition();
					String fechastring = mFecha.getText().toString();
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					Date date = format.parse(fechastring);
					Log.d("SpendingLab","Date: " + date.toString() + " datepicker: " + mFecha.getText().toString());
					String comentarios = mComentarios.getText().toString();
					mGasto = new Spending(cantidad,tipo,modo,date,comentarios);
					SpendingLab.get(getActivity()).addSpending(mGasto);	
					Toast.makeText(getActivity().getBaseContext(), "Spending Saved", Toast.LENGTH_LONG).show();
					mComentarios.setText("");
					mCantidad.setText("");
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					Log.e("SpendingLab","You faild to click!",e);
					//e.printStackTrace();
				}
				
				
			}
		});
		
		final Calendar calendar = Calendar.getInstance();
		
		mFecha = (EditText) v.findViewById(R.id.txtFecha);
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		mFecha.setText(formatter.format(today));
		mFecha.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar dtTxt = null;
				String preExistingDate = mFecha.getText().toString();
				if(preExistingDate != null && !preExistingDate.equals("")){
					
					StringTokenizer st = new StringTokenizer(preExistingDate,"/");
					initialMonth = st.nextToken();
					initialDate = st.nextToken();
					initialYear = st.nextToken();
					if(dialog == null)
						dialog = new DatePickerDialog(v.getContext(), new PickDate(),Integer.parseInt(initialYear),Integer.parseInt(initialMonth)-1,Integer.parseInt(initialDate));
					
						dialog.updateDate(Integer.parseInt(initialYear),Integer.parseInt(initialMonth),Integer.parseInt(initialDate));
					} else {
						dtTxt = Calendar.getInstance();
						if(dialog == null)
							dialog = new DatePickerDialog(v.getContext(),new PickDate(),calendar.get(Calendar.YEAR),dtTxt.getTime().getMonth(),
                                    dtTxt.getTime().getDay());
						
							dialog.updateDate(dtTxt.getTime().getYear(),dtTxt.getTime().getMonth(),
                                    dtTxt.getTime().getDay());
				
					}
					
					dialog.show();
						
				}
				
			});		
		
		return v;
		
	}
	
	private void addListenerOnSpinnerItemSelection(View v) {
		// TODO Auto-generated method stub
		mModoPago = (Spinner) v.findViewById(R.id.modo_pago);
		mTipoPago = (Spinner) v.findViewById(R.id.tipo_pago);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
		inflater.inflate(R.menu.add_gasto, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case android.R.id.home:
			Intent i = new Intent(getActivity(),SpendingListActivity.class);			
			startActivityForResult(i,0);
			return true;
		case R.id.List_Gasto:
			Intent j = new Intent(getActivity(),DetallesActivity.class);
			startActivityForResult(j,0);
  			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class PickDate implements DatePickerDialog.OnDateSetListener{
	
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
			view.updateDate(year, monthOfYear, dayOfMonth);
			mFecha.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
			dialog.hide();
		}
	}

}
