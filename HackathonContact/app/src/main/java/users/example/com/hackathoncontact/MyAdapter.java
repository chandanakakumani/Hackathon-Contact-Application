package users.example.com.hackathoncontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<BusinessCard> {

	Context context;
	List<BusinessCard> businessCardList;

	public MyAdapter(Context context, int resourceId,
					 List<BusinessCard> businessCardList) {
		super(context, resourceId);
		this.context = context;
		this.businessCardList = businessCardList;

	}

	static class ViewHolder {
		protected TextView name;
		protected ImageView image;
		protected CheckBox checkbox;
	}

	public int getCount() {
		return businessCardList.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflator.inflate(R.layout.listrow, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.name = (TextView) view.findViewById(R.id.name);
			viewHolder.image = (ImageView) view.findViewById(R.id.image);
			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.checkbox);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							BusinessCard element = (BusinessCard) viewHolder.checkbox
									.getTag();
							element.set_selected(buttonView.isChecked());

						}
					});
			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(businessCardList.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(businessCardList
					.get(position));
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.name.setText(businessCardList.get(position).get_name());
		holder.checkbox
				.setChecked(businessCardList.get(position).is_selected());



		return view;
	}

}