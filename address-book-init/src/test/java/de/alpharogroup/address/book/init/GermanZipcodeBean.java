/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.address.book.init;

public class GermanZipcodeBean {

	String zipcode;

	String city;

	String circleKey;

	String circle;

	String federalStateKey;

	String federalState;

	public GermanZipcodeBean() {
	}

	public GermanZipcodeBean(String zipcode, String city, String circleKey, String circle, String federalStateKey,
			String federalState) {
		this.zipcode = zipcode;
		this.city = city;
		this.circleKey = circleKey;
		this.circle = circle;
		this.federalStateKey = federalStateKey;
		this.federalState = federalState;
	}

	public String getCircle() {
		return circle;
	}

	public String getCircleKey() {
		return circleKey;
	}

	public String getCity() {
		return city;
	}

	public String getFederalState() {
		return federalState;
	}

	public String getFederalStateKey() {
		return federalStateKey;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public void setCircleKey(String circleKey) {
		this.circleKey = circleKey;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setFederalState(String federalState) {
		this.federalState = federalState;
	}

	public void setFederalStateKey(String federalStateKey) {
		this.federalStateKey = federalStateKey;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
