//-----BEGIN DISCLAIMER-----
/*******************************************************************************
* Copyright (c) 2014, 2021 JCrypTool Team and Contributors
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
//-----END DISCLAIMER-----
package org.jcryptool.visual.ssl.views;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.jcryptool.core.logging.utils.LogUtil;
import org.jcryptool.visual.ssl.SslPlugin;
import org.jcryptool.visual.ssl.protocol.Crypto;
import org.jcryptool.visual.ssl.protocol.Message;
import org.jcryptool.visual.ssl.protocol.ProtocolStep;

public class ClientFinishedComposite extends Composite implements ProtocolStep {
	private Button btnInformationen;
	private boolean infoText = false;
	private String strText;
	private SslView sslView;
	private Group grpClientFinished;
	private Label lblFinished;

	/**
	 * The random number generated for the message clientHello
	 */
	private String clientRandom = Message.getClientHelloRandom();

	/**
	 * The random number generated for the message serverHello
	 */
	private String serverRandom = Message.getServerHelloRandom();

	/**
	 * The master secret needed for the encryption.
	 */
	private String masterSecret = Message.getMasterSecret();

	/**
	 * The object which provides crypto functions
	 */
	private Crypto c = null;

	/**
	 * Create the composite.
	 *
	 * @param parent
	 * @param style
	 */
	public ClientFinishedComposite(Composite parent, int style,
			final SslView sslView) {
		super(parent, style);
		this.sslView = sslView;

		grpClientFinished = new Group(this, SWT.NONE);
		grpClientFinished.setLayout(new GridLayout(2, false));
		grpClientFinished.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));
		grpClientFinished.setText(Messages.ClientFinishedCompositeGrpServerFinished);

		lblFinished = new Label(grpClientFinished, SWT.NONE);
		lblFinished.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		lblFinished.setText(Messages.ClientFinishedCompositeLblFinished);

		btnInformationen = new Button(grpClientFinished, SWT.NONE);
		btnInformationen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				infoText = !infoText;
				if (btnInformationen.getText().equals(
						Messages.btnInformationToggleParams)) {
					btnInformationen
							.setText(Messages.ClientCertificateCompositeBtnInfo);
				} else {
					btnInformationen
							.setText(Messages.btnInformationToggleParams);
				}
				refreshInformations();

			}
		});
		btnInformationen.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		btnInformationen
				.setText(Messages.ClientFinishedCompositeBtnInformation);
	}

	public void startStep() {
		c = new Crypto();
		String finished = null;
		String cFinished = null;
		Key key = null;
		String hashMessages = Message.getMessageClientHello()
				+ Message.getMessageServerHello()
				+ Message.getMessageServerRequest()
				+ Message.getMessageServerCertificate()
				+ Message.getMessageServerKeyExchange()
				+ Message.getMessageServerHelloDone()
				+ Message.getMessageClientCertfificate()
				+ Message.getMessageClientKeyExchange()
				+ Message.getMessageClientVerify()
				+ Message.getMessageServerFinished();
		try {
			finished = PRF(masterSecret, "client finished",
					c.generateHash(Message.getServerHelloHash(), hashMessages));

			//The part beyond is haunted by a demon we summoned, he is doing black magic that we don�t understand.
			//But he says that it works that way so we trust him.
			//Do not touch it or he kills you!
			if(Message.getServerHelloCipherMode().equals("CBC")) {
				if(Message.getServerHelloCipher().startsWith("AES")) {
					key = c.generateKey("AES", 128);
					cFinished = c.encryptCBC(key, finished);
				}else if(Message.getServerHelloCipher().equals("3DES")) {
					key = c.generateKey("DESede", 168);
					cFinished = c.encryptCBC(key, finished);
				}else if(Message.getServerHelloCipher().equals("RC4_128")) {
					key = c.generateKey("RC4", 128);
					cFinished = c.encryptCBC(key, finished);
				}else if(Message.getServerHelloCipher().equals("DES")) {
					key = c.generateKey("DES", 56);
					cFinished = c.encryptCBC(key, finished);
				}
			}else { //GCM
				key = c.generateKey("AES", 128);
				cFinished = c.encryptGCM(key, finished);
			}
		} catch (NoSuchAlgorithmException e) {
            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
		} catch (UnsupportedEncodingException e) {
            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
		} catch (InvalidKeyException e) {
            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
		} catch (NoSuchPaddingException e) {
            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
		} catch (IllegalBlockSizeException e) {
            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
		} catch (BadPaddingException e) {
            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
		} catch (NoSuchProviderException e) {
            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
		} catch (IOException e) {
            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
		} catch (InvalidAlgorithmParameterException e) {
            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
		}

		strText = Messages.ClientFinishedInitationText + cFinished;
		refreshInformations();
	}

	/**
	 * The pseudorandom-function to generate the master secret
	 *
	 * @param secret
	 * @param string
	 * @param seed
	 * @return
	 */
	private String PRF(String secret, String string, String seed) {
		String hash = "";
		int hash_length;
		String S1;
		String S2;
		byte[] b1;
		byte[] b2;
		int b_length;
		int i;

		if (Message.getServerHelloHash().equals("MD5")) {
			hash = P_hash(secret, seed, 5, "MD5");
		} else if (Message.getServerHelloHash().equals("SHA1")) {
			hash = P_hash(secret, seed, 4, "SHA1");
		} else if (Message.getServerHelloHash().equals("SHA256")) {
			hash = P_hash(secret, seed, 4, "SHA256");
		} else { // SHA384
			hash = P_hash(secret, seed, 4, "SHA384");
		}

		hash_length = hash.length();
		S1 = hash.substring(0, (hash_length / 2) - 1);
		S2 = hash.substring(hash_length / 2);

		b1 = P_hash(S1, string + seed, 5, "MD5").getBytes();
		b2 = P_hash(S2, string + seed, 4, "SHA1").getBytes();
		b_length = b1.length;

		byte[] b3 = new byte[b_length];

		for (i = 0; i < b_length; i++) {
			b3[i] = (byte) (b1[i] ^ b2[i]);
		}
		return bytArrayToHex(b3);
	}

	/**
	 * The hashfunction for the PRF of the master secret
	 *
	 * @param secret
	 * @param seed
	 * @param count
	 * @param Hash
	 * @return
	 */
	private String P_hash(String secret, String seed, int max, String Hash) {
		String hash = "";
		for (int i = 0; i < max; i++) {
			try {
				hash = hash + c.generateHash(Hash, secret + A(i, Hash) + seed);
			} catch (NoSuchAlgorithmException e) {
	            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
			} catch (UnsupportedEncodingException e) {
	            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
			}
		}
		return hash;
	}

	/**
	 * A hashfunction to generate more bytes for the master secret
	 *
	 * @param i
	 * @param Hash
	 * @return
	 */
	private String A(int i, String Hash) {
		if (i == 0) {
			return clientRandom + serverRandom;
		} else {
			try {
				return c.generateHash(Hash, masterSecret + A(i - 1, Hash)
						+ clientRandom + serverRandom);
			} catch (NoSuchAlgorithmException e) {
	            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
			} catch (UnsupportedEncodingException e) {
	            LogUtil.logError(SslPlugin.PLUGIN_ID, e);
			}
			return clientRandom + serverRandom;
		}
	}

	/**
	 * @param a
	 * @return
	 */
	private String bytArrayToHex(byte[] a) {
		StringBuilder sb = new StringBuilder();
		for (byte b : a)
			sb.append(String.format("%02x", b & 0xff));
		return sb.toString();
	}

	@Override
	public void refreshInformations() {
		if (infoText) {
			sslView.setStxInformationText(Messages.ClientFinishedInformationText);
		} else {
			sslView.setStxInformationText(strText);
		}
	}

	@Override
	public void enableControls() {
		btnInformationen.setEnabled(true);
		refreshInformations();
	}

	@Override
	public void disableControls() {
		btnInformationen.setEnabled(false);
		btnInformationen.setText(Messages.ClientCertificateCompositeBtnInfo);
	}

	@Override
	public String checkParameters() {
		return ProtocolStep.OK;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	@Override
	public void resetStep() {
		infoText = false;
		btnInformationen.setText(Messages.ClientCertificateCompositeBtnInfo);
	}
}
