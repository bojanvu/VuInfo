package com.algebra.vuinfo.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

import com.algebra.vuinfo.activities.MapsActivity;

public class ShowDialog extends Activity {

	public static void showDialogPhone(final Context context, String text,
			final String phoneNumber, final double lat, final double lng) {
		AlertDialog.Builder menu = new AlertDialog.Builder(context);
		menu.setTitle("Izbornik");
		String[] name = new String[] { text, "Navigacija", "Izlaz iz izbornika" };
		menu.setItems(name, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					callPhone(context, phoneNumber);
					break;
				case 1:
					latLng(context, lat, lng);
					break;
				case 2:
					// close dialog
					break;
				}

			}
		});
		menu.show();

	}

	public static void showDialogMail(final Context context, String text,
			final String mailAddress, final double lat, final double lng) {
		AlertDialog.Builder menu = new AlertDialog.Builder(context);
		menu.setTitle("Izbornik");
		String[] name = new String[] { text, "Navigacija", "Izlaz iz izbornika" };
		menu.setItems(name, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					sendMail(context, mailAddress);
					break;
				case 1:
					latLng(context, lat, lng);
					break;
				case 2:
					// close dialog
					break;
				}

			}
		});
		menu.show();

	}

	public static void showDialogPhoneMail(final Context context, String text,
			String secondText, final String phoneNumber,
			final String mailAddress, final double lat, final double lng) {
		AlertDialog.Builder menu = new AlertDialog.Builder(context);
		menu.setTitle("Izbornik");
		String[] name = new String[] { text, secondText, "Navigacija",
				"Izlaz iz izbornika" };
		menu.setItems(name, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					callPhone(context, phoneNumber);
					break;
				case 1:
					sendMail(context, mailAddress);
					break;
				case 2:
					latLng(context, lat, lng);
					break;
				case 3:
					// close dialog
					break;
				}

			}
		});
		menu.show();

	}

	public static void showDialogTwoPhone(final Context context, String text,
			String secondText, final String phoneNumber,
			final String secondPhoneNumber, final double lat, final double lng) {
		AlertDialog.Builder menu = new AlertDialog.Builder(context);
		menu.setTitle("Izbornik");
		String[] name = new String[] { text, secondText, "Navigacija",
				"Izlaz iz izbornika" };
		menu.setItems(name, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					callPhone(context, phoneNumber);
					break;
				case 1:
					callPhone(context, secondPhoneNumber);
					break;
				case 2:
					latLng(context, lat, lng);
					break;
				case 3:
					// close dialog
					break;
				}
			}
		});
		menu.show();

	}

	public static void showAlertDialogCall(final Context context,
			String message, String phone, final String number) {
		AlertDialog.Builder alertDialogCall = new AlertDialog.Builder(context);

		// set title
		alertDialogCall.setTitle(message);

		// set dialog message
		alertDialogCall.setMessage(phone).setCancelable(false)
				.setPositiveButton("Da", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, call
						// current number
						callPhone(context, number);
					}
				})
				.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogCall.create();

		// show it
		alertDialog.show();
	}

	public static void callPhone(Context context, String phoneNumber) {

		Intent callIntent = new Intent(Intent.ACTION_DIAL);
		callIntent.setData(Uri.parse(phoneNumber));
		context.startActivity(callIntent);

	}

	public static void sendMail(Context context, String mailAddress) {

		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		String[] recipients = new String[] { mailAddress, "", };
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
		emailIntent.setType("text/plain");
		context.startActivity(Intent.createChooser(emailIntent,
				"Pošalji mail..."));

	}

	public static void latLng(Context context, double lat, double lng) {

		Intent latLng = new Intent(context, MapsActivity.class);
		latLng.putExtra("latitude", lat);
		latLng.putExtra("longitude", lng);
		context.startActivity(latLng);

	}
}