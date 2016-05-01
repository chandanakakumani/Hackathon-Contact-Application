package users.example.com.hackathoncontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by user on 5/1/2016.
 */
public class PreviewBusinessCard extends Activity {

DatabaseHandler databaseHandler = new DatabaseHandler(this);

    TextView name, designation, companyName, phoneMobile, phoneWork, email,
            webAddress, streetAddress, city, state, postalCode;

    Button deletecon;


    private BusinessCard businesscard;

    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.previewbusinesscard);

        name = (TextView) findViewById(R.id.previewname);
        designation = (TextView) findViewById(R.id.previewdesignation);
        companyName = (TextView) findViewById(R.id.previewcompanyname);
        phoneMobile = (TextView) findViewById(R.id.previewphonemobile);
        phoneWork = (TextView) findViewById(R.id.previewphonework);
        email = (TextView) findViewById(R.id.previewemail);
        webAddress = (TextView) findViewById(R.id.previewwebaddress);
        streetAddress = (TextView) findViewById(R.id.previewstreetaddress);
        city = (TextView) findViewById(R.id.previewcity);
        state = (TextView) findViewById(R.id.previewstate);
        postalCode = (TextView) findViewById(R.id.previewpostalcode);


        deletecon=(Button)findViewById(R.id.deletec);

        Intent intent = getIntent();
        final int businessCardID = intent.getIntExtra("BusinessCardID", -1);

        if (businessCardID != -1) {
            businesscard = databaseHandler.getBusinessCard(businessCardID);
            populateDataInScreen(businesscard);
        }

        deletecon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                databaseHandler.deleteBusinessCard(businessCardID);
                Intent viewBusinessCardIntent = new Intent(getBaseContext(),
                        ViewBusinessCards.class);
                startActivity(viewBusinessCardIntent);
                finish();
            }
        });

    }


    private void populateDataInScreen(BusinessCard businesscard) {
        name.setText(businesscard.get_name());
        designation.setText(businesscard.get_designation());
        companyName.setText(businesscard.get_company_name());
        phoneMobile.setText(businesscard.get_phone_number_mobile());
        phoneWork.setText(businesscard.get_phone_number_work());
        email.setText(businesscard.get_email());
        webAddress.setText(businesscard.get_web_address());
        streetAddress.setText(businesscard.get_street_address());
        city.setText(businesscard.get_city());
        state.setText(businesscard.get_state());
        postalCode.setText(businesscard.get_postalcode());


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
