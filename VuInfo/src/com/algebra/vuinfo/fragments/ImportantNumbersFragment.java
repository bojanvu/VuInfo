package com.algebra.vuinfo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.dialog.ShowDialog;

public class ImportantNumbersFragment extends Fragment implements
		OnClickListener {

	private ImageButton firefighters, police, ambulance, cityVukovar, museum,
			library, hrDom, tourist, bus, train;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_important_numbers,
				container, false);

		firefighters = (ImageButton) rootView
				.findViewById(R.id.phone_firefighters);
		police = (ImageButton) rootView.findViewById(R.id.phone_police);
		ambulance = (ImageButton) rootView.findViewById(R.id.phone_ambulance);
		cityVukovar = (ImageButton) rootView
				.findViewById(R.id.phone_city_vukovar);
		museum = (ImageButton) rootView.findViewById(R.id.phone_museum);
		library = (ImageButton) rootView.findViewById(R.id.phone_library);
		hrDom = (ImageButton) rootView.findViewById(R.id.phone_hr_dom);
		tourist = (ImageButton) rootView.findViewById(R.id.phone_tourist);
		bus = (ImageButton) rootView.findViewById(R.id.phone_bus);
		train = (ImageButton) rootView.findViewById(R.id.phone_train);

		firefighters.setOnClickListener(this);
		police.setOnClickListener(this);
		ambulance.setOnClickListener(this);
		cityVukovar.setOnClickListener(this);
		museum.setOnClickListener(this);
		library.setOnClickListener(this);
		hrDom.setOnClickListener(this);
		tourist.setOnClickListener(this);
		bus.setOnClickListener(this);
		train.setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.phone_firefighters:

			ShowDialog.showAlertDialogCall(getActivity(),
					"Da li želite nazvati vatrogasce?", "193", "tel:193");
			break;
		case R.id.phone_police:

			ShowDialog.showAlertDialogCall(getActivity(),
					"Da li želite nazvati policiju?", "192", "tel:192");
			break;
		case R.id.phone_ambulance:

			ShowDialog.showAlertDialogCall(getActivity(),
					"Da li želite nazvati hitnu?", "194", "tel:194");
			break;
		case R.id.phone_city_vukovar:

			ShowDialog.showAlertDialogCall(getActivity(),
					"Da li želite nazvati Grad Vukovar?", "032/456-501",
					"tel:032456501");
			break;
		case R.id.phone_museum:

			ShowDialog.showAlertDialogCall(getActivity(),
					"Da li želite nazvati gradski muzej?", "032/441-271",
					"tel:032441271");
			break;
		case R.id.phone_library:

			ShowDialog.showAlertDialogCall(getActivity(),
					"Da li želite nazvati gradsku knjižnicu?", "032/450-357",
					"tel:032450357");
			break;
		case R.id.phone_hr_dom:

			ShowDialog.showAlertDialogCall(getActivity(),
					"Da li želite nazvati hrvatski dom?", "032/450-697",
					"tel:032450697");
			break;
		case R.id.phone_tourist:

			ShowDialog.showAlertDialogCall(getActivity(),
					"Da li želite nazvati turistièku zajednicu?",
					"032/442-889", "tel:032442889");
			break;
		case R.id.phone_bus:

			ShowDialog.showAlertDialogCall(getActivity(),
					"Da li želite nazvati autobusni kolodvor?", "032/337-799",
					"tel:032337799");
			break;
		case R.id.phone_train:

			ShowDialog.showAlertDialogCall(getActivity(),
					"Da li želite nazvati željeznièki kolodvor?",
					"032/430-340", "tel:032430340");
			break;
		}
	}
}
