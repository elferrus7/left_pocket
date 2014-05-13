package com.example.left_poecket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class SpendingJSONSerializer {
	private Context mContext;
	private String mFilename;
	
	public SpendingJSONSerializer(Context mContext, String mFilename) {
		super();
		this.mContext = mContext;
		this.mFilename = mFilename;
	}
	
	public void saveSpendings(ArrayList<Spending> spendings)throws JSONException,
	IOException {
		JSONArray array = new JSONArray();
		for(Spending s: spendings)
			array.put(s.toJSON());
		Writer writer = null;
		try{
			OutputStream out = mContext.openFileOutput(mFilename,
					Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());

		} finally {
			if (writer != null)
				writer.close();
		}
	}
	
	public ArrayList<Spending> loadSpendings() throws IOException, JSONException{
		ArrayList<Spending> spendings = new ArrayList<Spending>();
		BufferedReader reader = null;
		try {
			InputStream in = mContext.openFileInput(mFilename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null){
				jsonString.append(line);
			}
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for(int i = 0; i < array.length(); i++){
				spendings.add(new Spending(array.getJSONObject(i)));
			}
		} catch(FileNotFoundException e){
			//Good
		}finally{
			if(reader != null){
				reader.close();
			}
		}
		Log.d("SpendingLab","File name: " + mFilename + " elements " + spendings.toString());
		return spendings;
	}
}
