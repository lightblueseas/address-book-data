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
package de.alpharogroup.address.book.domain;

import de.alpharogroup.domain.BaseDomainObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends BaseDomainObject<Integer> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** The address comment. */
	private String addressComment;

	/**
	 * The federalstate attribute is the federal state from this {@link Address}
	 * object.
	 */
	private Federalstate federalstate;
	/** The geohash from this {@link Address} object. */
	private String geohash;
	/**
	 * The latitude from the address. Latitude is a geographical term denoting
	 * the north/south angular location of a place on a sphere.
	 */
	private String latitude;
	/** The longitude from the address. */
	private String longitude;
	/** The name of the street. */
	private String street;
	/** The streetnumber. */
	private String streetnumber;
	/** The zipcode from this {@link Address} object. */
	private Zipcode zipcode;
}
