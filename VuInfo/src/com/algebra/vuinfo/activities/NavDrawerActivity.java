package com.algebra.vuinfo.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.adapters.NavDrawerAdapter;
import com.algebra.vuinfo.fragments.AboutVukovarFragmnet;
import com.algebra.vuinfo.fragments.EventsFragment;
import com.algebra.vuinfo.fragments.ImportantNumbersFragment;
import com.algebra.vuinfo.models.NavDrawerActivityConfiguration;
import com.algebra.vuinfo.models.NavDrawerItem;
import com.algebra.vuinfo.models.NavMenuItem;
import com.algebra.vuinfo.models.NavMenuSection;

public class NavDrawerActivity extends AbstractNavDrawerActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new EventsFragment()).commit();
		}
	}

	@Override
	protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {

		NavDrawerItem[] menu = new NavDrawerItem[] {
				NavMenuSection.create(100, "Informacije"),
				NavMenuItem.create(101, "Aktualna dogaðanja", "events", true,
						this),
				NavMenuItem.create(102, "O gradu", "history", true, this),
				NavMenuItem.create(103, "Važni brojevi", "phone", true, this),
				NavMenuItem.create(104, "Sport", "sport", true, this),
				NavMenuItem.create(105, "Smještaj", "hotel", true, this),
				NavMenuItem.create(106, "Restorani", "restaurant", true, this),
				NavMenuItem.create(107, "Ljekarne", "ambulance", true, this),
				NavMenuItem.create(108, "Benzinske postaje", "petrol", true,
						this),
				NavMenuSection.create(200, "Eu Kviz"),
				NavMenuItem
						.create(201, "Vu u EU", "european_union", true, this),
				NavMenuSection.create(300, "Autobusne stanice"),
				NavMenuItem.create(301, "Vu Bus Stop", "vubusstop_logo", true,
						this) };

		NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
		navDrawerActivityConfiguration
				.setMainLayout(R.layout.activity_nav_drawer);
		navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
		navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
		navDrawerActivityConfiguration.setNavItems(menu);
		navDrawerActivityConfiguration
				.setDrawerShadow(R.drawable.drawer_shadow);
		navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
		navDrawerActivityConfiguration
				.setDrawerCloseDesc(R.string.drawer_close);
		navDrawerActivityConfiguration.setBaseAdapter(new NavDrawerAdapter(
				this, R.layout.navdrawer_item, menu));
		return navDrawerActivityConfiguration;
	}

	@Override
	protected void onNavItemSelected(int id) {
		switch ((int) id) {
		case 101:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new EventsFragment()).commit();
			break;
		case 102:
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, new AboutVukovarFragmnet())
					.commit();
			break;
		case 103:
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.content_frame, new ImportantNumbersFragment())
					.commit();
			break;
		case 104:
			Intent sport = new Intent(this, SportsActivity.class);
			startActivity(sport);
			break;
		case 105:
			Intent hotels = new Intent(this, HotelsActivity.class);
			startActivity(hotels);
			break;
		case 106:
			Intent restaurant = new Intent(this, RestaurantActivity.class);
			startActivity(restaurant);
			break;
		case 107:
			Intent drugtore = new Intent(this, DrugstoreActivity.class);
			startActivity(drugtore);
			break;
		case 108:
			Intent gasStation = new Intent(this, GasStationActivity.class);
			startActivity(gasStation);
			break;
		case 201:
			Intent vuEu = new Intent(this, VuEuActivity.class);
			startActivity(vuEu);
			break;
		case 301:
			Intent vuBusStop = new Intent(this, VuBusStopActivity.class);
			startActivity(vuBusStop);
			break;
		}
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#336699")));

		return true;
	}
}
