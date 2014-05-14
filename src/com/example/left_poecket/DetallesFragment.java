package com.example.left_poecket;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class DetallesFragment extends Fragment {
	
	XYMultipleSeriesDataset dataset;
	XYMultipleSeriesRenderer mCanvas;
	
	Spending mSpending;
	TextView mIngreso;
	TextView mEgreso;
	TextView mGasto;
	TextView mCeroPesos;
	
	XYSeriesRenderer ingresos;
	XYSeriesRenderer egresos;
	
	XYSeriesRenderer tarjeta;
	XYSeriesRenderer efectivo;
	XYSeriesRenderer cheques;
	XYSeriesRenderer vales;
	
	private GraphicalView mChart;
	private String m30[] = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
	private String m31[] = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private String m28[] = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
	private String m29[] = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"};
	
	private ArrayList<Integer> calculaProm(ArrayList<Integer> ingreso, ArrayList<Integer> egreso ){
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i = 0; i< ingreso.size(); i++){
			array.add(i,((ingreso.get(i) - egreso.get(i))));
		}
		return array;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	
	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.fragment_detalles, parent, false);
		OpenChart();
		
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		mGasto = (TextView) v.findViewById(R.id.Detalles_gastotitleTextView);
		mCeroPesos = (TextView) v.findViewById(R.id.Detalles_gastosubtitleTextView);
		
		LinearLayout chart_container = (LinearLayout)v.findViewById(R.id.Chart_layout);
		mChart = ChartFactory.getLineChartView(getActivity().getBaseContext(), dataset, mCanvas);
		mChart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SeriesSelection series_selection = mChart.getCurrentSeriesAndPoint();
				
				if(series_selection != null){
					int series_index = series_selection.getSeriesIndex();
					String select_series="Gasto de ";
					if(series_index == 0){
						select_series+="Egresos";
					}
					else if(series_index == 1){
						select_series+="Ingresos";
					}
					else if(series_index == 2){
						select_series+="Tarjeta";
					}
					else if(series_index == 3){
						select_series+="Efectivo";
					}
					else if(series_index == 4){
						select_series+="Cheques";
					}
					else if(series_index == 5){
						select_series+="Vales";
					}
					
					String dia = m30[(int)series_selection.getXValue()];
					int cantidad = (int)series_selection.getValue();
					
					Toast.makeText(getActivity().getBaseContext(), select_series+" el "+dia+" "+cantidad, Toast.LENGTH_LONG).show();
				}
				
				
			}
		});
		
		chart_container.addView(mChart);
		return v;
	}
	
	public void OpenChart(){
		
		ArrayList<Integer> Ingreso;
		ArrayList<Integer> Egreso ;
		//ArrayList<Integer> Saldo = calculaProm(Ingreso,Egreso);
		
		XYSeries sIngreso = new XYSeries("Ingreso");
		
		XYSeries sEgreso = new XYSeries("Egreso");
		
		dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(sIngreso);
		dataset.addSeries(sEgreso);
		
		//Caracteristicas de cada trazo
		egresos = new XYSeriesRenderer();
		egresos.setColor(Color.RED);
		egresos.setPointStyle(PointStyle.CIRCLE);
		egresos.setDisplayChartValues(true);
		egresos.setLineWidth(4);
		egresos.setFillPoints(true);
		
		ingresos = new XYSeriesRenderer();
		ingresos.setColor(Color.GREEN);
		ingresos.setPointStyle(PointStyle.DIAMOND);
		ingresos.setDisplayChartValues(true);
		ingresos.setLineWidth(4);
		ingresos.setFillPoints(true);
		
		tarjeta = new XYSeriesRenderer();
		tarjeta.setColor(Color.YELLOW);
		tarjeta.setPointStyle(PointStyle.SQUARE);
		tarjeta.setDisplayChartValues(true);
		tarjeta.setLineWidth(4);
		tarjeta.setFillPoints(true);
		
		efectivo = new XYSeriesRenderer();
		efectivo.setColor(Color.BLUE);
		efectivo.setPointStyle(PointStyle.TRIANGLE);
		efectivo.setDisplayChartValues(true);
		efectivo.setLineWidth(4);
		efectivo.setFillPoints(true);
		
		cheques = new XYSeriesRenderer();
		cheques.setColor(Color.MAGENTA);
		cheques.setPointStyle(PointStyle.CIRCLE);
		cheques.setDisplayChartValues(true);
		cheques.setLineWidth(4);
		cheques.setFillPoints(true);
		
		vales = new XYSeriesRenderer();
		vales.setColor(Color.GREEN);
		vales.setPointStyle(PointStyle.DIAMOND);
		vales.setDisplayChartValues(true);
		vales.setLineWidth(4);
		vales.setFillPoints(true);
		
		//Customize del Render del canvas
		mCanvas = new XYMultipleSeriesRenderer();
		mCanvas.setChartTitle("Gasto del mes");
		mCanvas.setXTitle("Dias");
		mCanvas.setYTitle("Cantidad");
		mCanvas.setXLabels(0);
		mCanvas.setPanEnabled(false);
		mCanvas.setBackgroundColor(Color.BLACK);
		mCanvas.setShowGrid(true);
		mCanvas.setClickEnabled(true);
		//Checar que mes es para cargar el numero de dias
		for(int i = 0; i< m30.length;i++){
			mCanvas.addXTextLabel(i, m30[i]);
		}
		
		mCanvas.addSeriesRenderer(ingresos);
		mCanvas.addSeriesRenderer(egresos);
		
		
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(getActivity());
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	
}
