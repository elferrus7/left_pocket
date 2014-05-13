package com.example.left_poecket;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

@SuppressLint("NewApi")
public class GastoFragment extends Fragment {
	
	Spending mGasto;
	EditText mCantidad;
	EditText mFecha;
	EditText mComentarios;
	Spinner mModoPago;
	Spinner mTipoPago;
	Button mGuardar;
	Button mCalendario;
	
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
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
					Date date = format.parse(fechastring);
					String comentarios = mComentarios.getText().toString();
					mGasto = new Spending(cantidad,tipo,modo,date,comentarios);
					
					
					
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
		mFecha = (EditText) v.findViewById(R.id.txtFecha);
		
		mCalendario = (Button) v.findViewById(R.id.btnCalendario);
		mCalendario.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar dtTxt = null;
				String preExistingDate = (String)mFecha.getText().toString();
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
							dialog = new DatePickerDialog(v.getContext(),new PickDate(),dtTxt.getTime().getYear(),dtTxt.getTime().getMonth(),
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
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(getActivity());
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
