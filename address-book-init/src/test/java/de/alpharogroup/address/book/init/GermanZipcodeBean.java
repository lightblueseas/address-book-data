package de.alpharogroup.address.book.init;

public class GermanZipcodeBean {
	
	public GermanZipcodeBean(String zipcode, String city, String circleKey,
			String circle, String federalStateKey, String federalState) {
		this.zipcode = zipcode;
		this.city = city;
		this.circleKey = circleKey;
		this.circle = circle;
		this.federalStateKey = federalStateKey;
		this.federalState = federalState;
	}

	public GermanZipcodeBean() {
	}

	String zipcode;
	
	String city;
	
	String circleKey;
	
	String circle;
	
	String federalStateKey;
	
	String federalState;

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCircleKey() {
		return circleKey;
	}

	public void setCircleKey(String circleKey) {
		this.circleKey = circleKey;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getFederalStateKey() {
		return federalStateKey;
	}

	public void setFederalStateKey(String federalStateKey) {
		this.federalStateKey = federalStateKey;
	}

	public String getFederalState() {
		return federalState;
	}

	public void setFederalState(String federalState) {
		this.federalState = federalState;
	}

}
