package users.example.com.hackathoncontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import dev.businesscard.BusinessCard;
//import dev.businesscard.database.DatabaseHandler;

public class CreateBusinessCard extends Activity {

	EditText nameTextEditable, designationTextEditable,
			companynameTextEditable, phonemobileTextEditable,
			phoneworkTextEditable, emailTextEditable, webaddressTextEditable,
			streetaddressTextEditable, cityTextEditable, stateTextEditable,
			postalcodeTextEditable;

	String nameText, designationText, companynameText, phonemobileText,
			phoneworkText, emailText, webaddressText, streetaddressText,
			cityText, stateText, postalcodeText;

	DatabaseHandler databaseHandler = new DatabaseHandler(this);

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.createbusinesscard);

		nameTextEditable = (EditText) findViewById(R.id.name);
		designationTextEditable = (EditText) findViewById(R.id.designation);
		companynameTextEditable = (EditText) findViewById(R.id.companyname);
		phonemobileTextEditable = (EditText) findViewById(R.id.phonemobile);
		phoneworkTextEditable = (EditText) findViewById(R.id.phonework);
		emailTextEditable = (EditText) findViewById(R.id.email);
		webaddressTextEditable = (EditText) findViewById(R.id.weburl);
		streetaddressTextEditable = (EditText) findViewById(R.id.streetaddress);
		cityTextEditable = (EditText) findViewById(R.id.city);
		stateTextEditable = (EditText) findViewById(R.id.state);
		postalcodeTextEditable = (EditText) findViewById(R.id.postalcode);


		Intent intent = getIntent();
		final int businessCardID = intent.getIntExtra("BusinessCardID", -1);

		if (businessCardID != -1) {
			BusinessCard businesscard = databaseHandler
					.getBusinessCard(businessCardID);

			nameTextEditable.setText(businesscard.get_name());
			designationTextEditable.setText(businesscard.get_designation());
			companynameTextEditable.setText(businesscard.get_company_name());
			phonemobileTextEditable.setText(businesscard
					.get_phone_number_mobile());
			phoneworkTextEditable.setText(businesscard.get_phone_number_work());
			emailTextEditable.setText(businesscard.get_email());
			webaddressTextEditable.setText(businesscard.get_web_address());
			streetaddressTextEditable
					.setText(businesscard.get_street_address());
			cityTextEditable.setText(businesscard.get_city());
			stateTextEditable.setText(businesscard.get_state());
			postalcodeTextEditable.setText(businesscard.get_postalcode());
		}

		Button createButton = (Button) findViewById(R.id.savePreview);
		createButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Inserting Contacts
				Log.d("Insert: ", "Inserting ..");

				nameText = nameTextEditable.getText().toString().trim();
				designationText = designationTextEditable.getText().toString()
						.trim();
				companynameText = companynameTextEditable.getText().toString()
						.trim();
				phonemobileText = phonemobileTextEditable.getText().toString()
						.trim();
				phoneworkText = phoneworkTextEditable.getText().toString()
						.trim();
				emailText = emailTextEditable.getText().toString().trim();
				webaddressText = webaddressTextEditable.getText().toString()
						.trim();
				streetaddressText = streetaddressTextEditable.getText()
						.toString().trim();
				cityText = cityTextEditable.getText().toString().trim();
				stateText = stateTextEditable.getText().toString().trim();
				postalcodeText = postalcodeTextEditable.getText().toString()
						.trim();

				if (isEditTextEmpty(nameText)
						|| isEditTextEmpty(phonemobileText)
						|| isEditTextEmpty(emailText)) {
					Toast.makeText(getBaseContext(),
							"Fields are missing, All fields are mandatory",
							Toast.LENGTH_LONG).show();

				} else {
					if (businessCardID != -1) {
						DatabaseHandler database = new DatabaseHandler(
								getBaseContext());
						BusinessCard businesscard = database
								.getBusinessCard(businessCardID);

						long currentposition = databaseHandler
								.updateContact(new BusinessCard(businesscard
										.get_id(), nameText, designationText,
										companynameText, phonemobileText,
										phoneworkText, emailText,
										webaddressText, streetaddressText,
										cityText, stateText, postalcodeText));
						Intent intent = new Intent(getBaseContext(),
								PreviewBusinessCard.class);

						intent.putExtra("BusinessCardID", businesscard.get_id());
						startActivity(intent);
						finish();

					} else {
						long currentposition = databaseHandler
								.addBusinessCard(new BusinessCard(1, nameText,
										designationText, companynameText,
										phonemobileText, phoneworkText,
										emailText, webaddressText,
										streetaddressText, cityText, stateText,
										postalcodeText));
						Intent intent = new Intent(getBaseContext(),
								PreviewBusinessCard.class);

						intent.putExtra("BusinessCardID", Integer.parseInt(Long
								.toString(currentposition)));
						startActivity(intent);
						finish();
					}
				}
			}
		});


	}



	public boolean isEditTextEmpty(String text) {
		if (text.length() == 0 || text == null || text.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (databaseHandler != null) {
			databaseHandler.close();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getBaseContext(),
					ViewBusinessCards.class);
			startActivity(intent);
			finish();
			return true;

		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

}
