package users.example.com.hackathoncontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewBusinessCards extends Activity {

	private MyAdapter mAdapter;
	private ListView listview;
	DatabaseHandler databaseHandler = new DatabaseHandler(this);
	List<BusinessCard> businessCardList;
	EditText inputSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewbusinesscards);

		businessCardList = databaseHandler.getAllBusinessCards();

		mAdapter = new MyAdapter(this, R.layout.listrow, businessCardList);
		listview = (ListView) findViewById(R.id.contactlist);
		listview.setAdapter(mAdapter);
		listview.setTextFilterEnabled(true);
		registerForContextMenu(listview);

		if (businessCardList.isEmpty()) {
			TextView text = (TextView) findViewById(R.id.textview);
			text.setText("No Business Cards found");
		}

		inputSearch = (EditText) findViewById(R.id.inputSearch);

		inputSearch.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
				if (!s.toString().equals("")) {
					List<BusinessCard> filteredBusinesscardList = new ArrayList<BusinessCard>();
					for (int i = 0; i < businessCardList.size(); i++) {
						if (businessCardList.get(i).get_name().contains(s) || businessCardList.get(i).get_phone_number_mobile().contains(s) || businessCardList.get(i).get_email().contains(s)) {
							filteredBusinesscardList.add(businessCardList
									.get(i));
						}
					}
					mAdapter = new MyAdapter(ViewBusinessCards.this,
							R.layout.listrow, filteredBusinesscardList);
					listview.setAdapter(mAdapter);
				} else {
					mAdapter = new MyAdapter(ViewBusinessCards.this,
							R.layout.listrow, businessCardList);
					listview.setAdapter(mAdapter);
				}

			}
		});

		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view,
									int position, long id) {

				Intent intent = new Intent(getBaseContext(),
						PreviewBusinessCard.class);
				intent.putExtra("BusinessCardID", businessCardList
						.get(position).get_id());
				startActivity(intent);
				finish();
			}
		});

		ImageView createBusinessCardImage = (ImageView) findViewById(R.id.addimage);

		createBusinessCardImage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(getBaseContext(),
						CreateBusinessCard.class);
				startActivity(intent);
				finish();
			}
		});

	}


}