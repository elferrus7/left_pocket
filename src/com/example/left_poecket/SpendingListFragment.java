package com.example.left_poecket;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
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
		mSpendings = SpendingLab.get(getActivity()).getSpendings();
		SpendingAdapter adapter = new SpendingAdapter(mSpendings);
		setListAdapter(adapter);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, parent, savedInstanceState);
        return v;
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
			 mDate.setText(s.getDate().toString());
			 mAmount = (TextView) convertView.findViewById(R.id.spending_list_item_amountTextView);
			 mAmount.setText("" + s.getAmount());
			 return convertView;
		 }
	 }
}
