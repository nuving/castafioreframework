/*
 * Copyright (C) 2007-2008 Castafiore
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */

package org.castafiore.ui;

/**
 * Interface that marks a Form component. All component that wants its value to
 * be automatically retrieved or set to the browser should implement this
 * interface. The engine automatically manages the value attribute components
 * which implements this interface. A valid {@link Encoder} and {@link Decoder}
 * are required to serialize and deserialize the value attribute to and from the
 * browser.
 * The component does not necessary need to an HTML form component. It can be any component actually.
 * 
 * @author Kureem Rossaye<br>
 *         kureem@gmail.com June 27 2008
 */
public interface StatefullComponent extends Container {

	/**
	 * 
	 * @return The raw value of the component the raw value has not been passed
	 *         through the decoder
	 */
	public String getRawValue();

	/**
	 * sets the raw value of the component. The raw value has not been passed
	 * through the encoder
	 * 
	 * @param rawValue
	 *            The raw value
	 */
	public void setRawValue(String rawValue);

	/**
	 * @return the value after the raw value has been passed through the encoder
	 */
	public Object getValue();

	/**
	 * sets the raw value by passing the specified value through the decoder
	 * 
	 * @param value
	 *            The value
	 */
	public void setValue(Object value);

	/**
	 * 
	 * @return The encoder
	 */
	public Encoder getEncoder();

	/**
	 * 
	 * @return The decoder
	 */
	public Decoder getDecoder();

	/**
	 * sets the encoder
	 * 
	 * @param encoder
	 */
	public void setEncoder(Encoder encoder);

	/**
	 * sets the decoder
	 * 
	 * @param decoder
	 */
	public void setDecoder(Decoder decoder);

}
