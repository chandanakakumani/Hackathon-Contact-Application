package users.example.com.hackathoncontact;

public class BusinessCard {

	int _id;
	String _name;
	String _designation;
	String _company_name;
	String _phone_number_mobile;
	String _phone_number_work;
	String _email;
	String _web_address;
	String _street_address;
	String _city;
	String _state;
	String _postalcode;
	boolean is_selected;

	// constructor
	public BusinessCard(int id, String name, String designation,
						String company_name, String phone_number_mobile,
						String phone_number_work, String email, String web_address,
						String street_address, String city, String state,
						String postalcode) {
		this._id = id;
		this._name = name;
		this._designation = designation;
		this._company_name = company_name;
		this._phone_number_mobile = phone_number_mobile;
		this._phone_number_work = phone_number_work;
		this._email = email;
		this._web_address = web_address;
		this._street_address = street_address;
		this._city = city;
		this._state = state;
		this._postalcode = postalcode;
	}

	public BusinessCard() {
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_designation() {
		return _designation;
	}

	public void set_designation(String _designation) {
		this._designation = _designation;
	}

	public String get_company_name() {
		return _company_name;
	}

	public void set_company_name(String _company_name) {
		this._company_name = _company_name;
	}

	public String get_phone_number_mobile() {
		return _phone_number_mobile;
	}

	public void set_phone_number_mobile(String _phone_number_mobile) {
		this._phone_number_mobile = _phone_number_mobile;
	}

	public String get_phone_number_work() {
		return _phone_number_work;
	}

	public void set_phone_number_work(String _phone_number_work) {
		this._phone_number_work = _phone_number_work;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_web_address() {
		return _web_address;
	}

	public void set_web_address(String _web_address) {
		this._web_address = _web_address;
	}

	public String get_street_address() {
		return _street_address;
	}

	public void set_street_address(String _street_address) {
		this._street_address = _street_address;
	}

	public String get_city() {
		return _city;
	}

	public void set_city(String _city) {
		this._city = _city;
	}

	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}

	public String get_postalcode() {
		return _postalcode;
	}

	public void set_postalcode(String _postalcode) {
		this._postalcode = _postalcode;
	}


	public boolean is_selected() {
		return is_selected;
	}

	public void set_selected(boolean selected) {
		this.is_selected = selected;
	}

}
