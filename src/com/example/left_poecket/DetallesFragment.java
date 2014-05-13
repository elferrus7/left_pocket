package com.example.left_poecket;

import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.support.v4.app.Fragment;

public class DetallesFragment extends Fragment {
	
	XYMultipleSeriesDataset dataset;
	XYMultipleSeriesRenderer mCanvas;
	XYSeriesRenderer ingresos;
	XYSeriesRenderer egresos;
	XYSeriesRenderer tarjeta;
	XYSeriesRenderer efectivo;
	XYSeriesRenderer cheques;
	XYSeriesRenderer vales;
	
	private GraphicalView mChart;
	private String mMonth[] = new String[] {"Ene", "Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"};

	
}
