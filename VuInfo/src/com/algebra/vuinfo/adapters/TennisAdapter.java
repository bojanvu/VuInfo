package com.algebra.vuinfo.adapters;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.data.TennisData;

public class TennisAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] Ids;
	private final int rowResourceId;

	public TennisAdapter(Context context, int textViewResourceId,
			String[] objects) {

		super(context, textViewResourceId, objects);

		this.context = context;
		this.Ids = objects;
		this.rowResourceId = textViewResourceId;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(rowResourceId, parent, false);
		ImageView imageView = (ImageView) rowView
				.findViewById(R.id.all_sports_icon_imageview);
		TextView textViewName = (TextView) rowView
				.findViewById(R.id.all_sports_name_textview);
		TextView textViewAddress = (TextView) rowView
				.findViewById(R.id.all_sports_address_textview);
		TextView textViewMail = (TextView) rowView
				.findViewById(R.id.all_sports_mail_textview);
		TextView textViewPhone = (TextView) rowView
				.findViewById(R.id.all_sports_phone_textview);

		int id = Integer.parseInt(Ids[position]);
		String imageFile = TennisData.GetbyId(id).IconFile;

		textViewName.setText(TennisData.GetbyId(id).Name);
		textViewAddress.setText(TennisData.GetbyId(id).Address);
		textViewMail.setText(TennisData.GetbyId(id).Mail);
		textViewPhone.setText(TennisData.GetbyId(id).Phone);

		InputStream ims = null;
		try {
			ims = context.getAssets().open(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// load image as Drawable

		Drawable d = Drawable.createFromStream(ims, null);
		// set image to ImageView
		imageView.setImageDrawable(d);
		return rowView;

	}
}
