package com.example.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class BuyAdapter extends BaseAdapter {

	private List<ReadTextView> data;
	private LayoutInflater inflater = null;
	private int index = 0;
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	class ViewHolder{
		TextView moneyTv;
	}
	
	public BuyAdapter(Context context,List<ReadTextView> data){
		this.data = data;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = data.get(position);
		return convertView;
	}

}
