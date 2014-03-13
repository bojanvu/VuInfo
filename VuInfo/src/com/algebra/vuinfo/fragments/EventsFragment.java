package com.algebra.vuinfo.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.activities.WebViewActivity;

public class EventsFragment extends Fragment implements OnClickListener {

	private Button btnGradVu, btnPanorama, btnEvukovar, btnHrVu, btnRadDu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_events, container,
				false);

		btnGradVu = (Button) rootView.findViewById(R.id.button_web_vukovar);
		btnPanorama = (Button) rootView.findViewById(R.id.button_web_panorama);
		btnEvukovar = (Button) rootView.findViewById(R.id.button_web_e_vukovar);
		btnHrVu = (Button) rootView.findViewById(R.id.button_hr_radio_vu);
		btnRadDu = (Button) rootView.findViewById(R.id.button_web_radio_dunav);

		btnGradVu.setOnClickListener(this);
		btnPanorama.setOnClickListener(this);
		btnEvukovar.setOnClickListener(this);
		btnHrVu.setOnClickListener(this);
		btnRadDu.setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_web_vukovar:

			goToWeb("http://www.vukovar.hr/");
			break;

		case R.id.button_web_panorama:

			goToWeb("http://www.panorama-vukovar.com/");
			break;

		case R.id.button_web_e_vukovar:

			goToWeb("http://www.evukovar.net/");
			break;

		case R.id.button_hr_radio_vu:

			goToWeb("http://www.hrv.hr/");
			break;

		case R.id.button_web_radio_dunav:

			goToWeb("http://www.radio-dunav.hr/");
			break;

		}
	}

	private void goToWeb(String url) {
		Intent intent = new Intent(getActivity(), WebViewActivity.class);
		intent.setData(Uri.parse(url));
		startActivity(intent);

	}
}
