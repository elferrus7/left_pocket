package com.example.left_poecket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SpendingListFragment extends ListFragment {
	
	private ArrayList<Spending> mSpendings;
	
	TextView mDesc;
	TextView mDate;
	TextView mAmount;
	
	@Override
    public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		Date today = new Date();
		SimpleDateFormat todayformat = new SimpleDateFormat("MMMM");
		getActivity().setTitle(todayformat.format(today));
		mSpendings = SpendingLab.get(getActivity()).getSpendingsByMonth(today.getMonth() + 1);
		SpendingAdapter adapter = new SpendingAdapter(mSpendings);
		setListAdapter(adapter);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, parent, savedInstanceState);
        return v;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
		inflater.inflate(R.menu.list_mes, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(getActivity());
				return true;
			case R.id.add_Gasto:
				/*Intent i = new Intent(SpendingListFragment.this, MainActivity.class);
	            startActivityForResult(i, 0);
	            */
				return true;
			case R.id.detalles_Gasto:
				/*Intent i = new Intent(SpendingListFragment.this, DetallesActivity.class);
				 startActivityForResult(i,0);
				 */
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	 public void onListItemClick(ListView l, View v, int position, long id){
		 Log.d("SpendingLab", "You clicke me!");
	 }
	 
	 
	 private class SpendingAdapter extends ArrayAdapter<Spending>{
		 public SpendingAdapter(ArrayList<Spending> spendings){
			 super(getActivity(), android.R.layout.simple_list_item_1, spendings);
		 }
		 
		 @Override
	        public View getView(int position, View convertView, ViewGroup parent){
			 if (null == convertView) {
	                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_spending, null);
	         }
			 
			 Spending s =  getItem(position);
			 
			 mDesc = (TextView) convertView.findViewById(R.id.spending_list_item_descTextView);
			 mDesc.setText(s.getmComment());
			 mDate = (TextView) convertView.findViewById(R.id.spending_list_item_dateTextView);
			 SimpleDateFormat  format = new SimpleDateFormat("MM/dd/yyyy");
			 mDate.setText(format.format(s.getDate()));
			 
			 mAmount = (TextView) convertView.findViewById(R.id.spending_list_item_amountTextView);
			 mAmount.setText("" + s.getAmount());
			 return convertView;
		 }
	 }
	 
	 
}
