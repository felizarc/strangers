package com.example.strangers.utilities;

import java.util.List;

import com.example.strangers.R;
import com.example.strangers.model.SearchResponse;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ResponseAdapter extends BaseAdapter {

	List<SearchResponse> listResponse;
	LayoutInflater inflater;

	public ResponseAdapter(Context context,List<SearchResponse> listResponse) {
		inflater = LayoutInflater.from(context);
		this.listResponse = listResponse;
	}
	
	public int getCount() {
		return listResponse.size();
	}

	public Object getItem(int position) {
		return listResponse.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	private class ViewHolder {
		TextView responseAccountContent;
		TextView responseDateContent;
		TextView responseFromContent;
		TextView responseBeforeContent;
		TextView responseNumberContent;
		TextView responseAfterContent;

	}
	
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
	
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.response_element, null);
			holder.responseAccountContent = (TextView)convertView.findViewById(R.id.responseAccount);
			holder.responseDateContent = (TextView)convertView.findViewById(R.id.responseDate);
			holder.responseFromContent = (TextView)convertView.findViewById(R.id.responseFrom);
			holder.responseBeforeContent = (TextView)convertView.findViewById(R.id.responseBefore);
			holder.responseNumberContent = (TextView)convertView.findViewById(R.id.responseNumber);
			holder.responseAfterContent = (TextView)convertView.findViewById(R.id.responseAfter);

			convertView.setTag(holder);
		} 
		else {
			holder = (ViewHolder) convertView.getTag();
		}
	
		holder.responseAccountContent.setText(listResponse.get(position).getAccount());
		holder.responseDateContent.setText(listResponse.get(position).getDate());
		holder.responseFromContent.setText(listResponse.get(position).getFrom());
		holder.responseBeforeContent.setText(listResponse.get(position).getBefore());
		holder.responseNumberContent.setText(listResponse.get(position).getNumber());
		holder.responseAfterContent.setText(listResponse.get(position).getAfter());

		return convertView;

	}
}
