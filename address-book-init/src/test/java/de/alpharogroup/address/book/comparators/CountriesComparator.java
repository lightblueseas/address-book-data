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
package de.alpharogroup.address.book.comparators;

import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import de.alpharogroup.address.book.entities.Countries;

public class CountriesComparator implements Comparator<Countries>
{

	@Override
	public int compare(final Countries o1, final Countries o2)
	{
		return new CompareToBuilder().append(o1.getId(), o2.getId())
			.append(o1.getName(), o2.getName()).append(o1.getIso3166A3name(), o2.getIso3166A3name())
			.append(o1.getIso3166A2name(), o2.getIso3166A2name())
			.append(o1.getIso3166Number(), o2.getIso3166Number()).toComparison();
	}

}