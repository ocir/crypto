//-----BEGIN DISCLAIMER-----
/*******************************************************************************
* Copyright (c) 2011, 2021 JCrypTool Team and Contributors
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
//-----END DISCLAIMER-----
package org.jcryptool.analysis.viterbi.algorithm;

/**
 *
 * This class provides methods to combine characters or strings by an bitwise
 * xor operation. In this case, the add and the substract method will provide
 * the same result. If one string is longer than the other, the shorter one will
 * be reapeated.
 *
 * @author Georg Chalupar, Niederwieser Martin, Scheuchenpflug Simon
 *
 */
public class BitwiseXOR implements Combination {

	/**
	 * Two strings are combined with the xor operation. The shorter string will
	 * be repeated.
	 *
	 * @param String
	 *            a
	 * @param String
	 *            b
	 *
	 * @return xor
	 */
	@Override
	public String add(String a, String b) {

		if (a.length() == 0 || b.length() == 0) {
			return "";
		}

		StringBuilder cipher = new StringBuilder("");

		double maxlength = a.length() > b.length() ? a.length() : b.length();

		for (int i = 0; i < maxlength; i++) {
			cipher.append((char) (a.charAt(i % a.length()) ^ b.charAt(i
					% b.length())));

			// by calculating the modulo of the length, the shorter string will
			// be reapeted

		}
		return cipher.toString();
	}

	/**
	 * combines two strings with the xor operation. Will provide the same
	 * result as the add method. The shorter string will be repeated.
	 *
	 * @param String
	 *            a
	 * @param Strinb
	 *            b
	 * @return xor
	 */
	@Override
	public String subtract(String a, String b) {
		return add(a, b);
	}

	/**
	 * This method provides a simple xor combination of two characters.
	 *
	 * @param char a
	 * @param char b
	 * @return xor
	 */
	@Override
	public char add(char a, char b) {
		return (char) (a ^ b);
	}

	/**
	 * This method provides a simple xor combination of two characters. The
	 * result will be the same as the add method.
	 *
	 * @param char a
	 * @param char b
	 * @return xor
	 */
	@Override
	public char subtract(char a, char b) {
		return (char) (a ^ b);
	}


	/**
	 * Calculates last position until two equal strings could generate the given ciphertext
	 * @param cipher a ciphertext generated by xor-ing two plaintexts
	 */
	@Override
	public int getMaxDuplicatePathLength(String cipher) {
		for (int i = 0; i < cipher.length(); i++) {
			if (cipher.charAt(i)!=0) {
				return i;
			}
		}
		return 0;
	}

}
