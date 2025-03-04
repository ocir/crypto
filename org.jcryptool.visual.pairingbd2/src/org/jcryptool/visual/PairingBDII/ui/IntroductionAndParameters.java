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
package org.jcryptool.visual.PairingBDII.ui;

import java.math.BigInteger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.jcryptool.core.util.colors.ColorService;
import org.jcryptool.core.util.ui.TitleAndDescriptionComposite;

public class IntroductionAndParameters {
	private static final int PARAMETER_u = 0;
	private static final int PARAMETER_q = 1;
	private static final int PARAMETER_E = 2;
	private static final int PARAMETER_E2 = 3;
	private static final int PARAMETER_l = 4;
	private static final int PARAMETER_P = 5;
	private static final int PARAMETER_Q2 = 6;
	private static final int PARAMETER_k = 7;
	private static final int PARAMETER_e = 8;

	private final Text parameters_u;
	private final Text parameters_q;
	private final Text parameters_E;
	private final Text parameters_E2;
	private final Text parameters_l;
	private final Text parameters_P;
	private final Text parameters_Q2;
	private final Text parameters_k;
	private final Text parameters_e;

	private final Spinner spinner_NumberOfUsers;

	private final Button radio_PenAndPaper;
	private final Button radio_IndustrialSecurity;
	private final Button radio_EmbeddedDegreeSmall;
	private final Button radio_EmbeddedDegreeLarge;
	private final Button radio_TatePairing;
	private final Button radio_WeilPairing;

	public IntroductionAndParameters(final Composite parent) {

		// This creates the area where the title and the description of the plugin are shown.
		TitleAndDescriptionComposite titleAndDescription = new TitleAndDescriptionComposite(parent);
		titleAndDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		titleAndDescription.setTitle(Messages.IntroductionAndParameters_0);
		titleAndDescription.setDescription(Messages.IntroductionAndParameters_1);

		Group groupParameters = new Group(parent, SWT.NONE);
		groupParameters.setLayout(new GridLayout(1, false));
		groupParameters.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		groupParameters.setText(Messages.IntroductionAndParameters_6);
		
		parameters_u = new Text(groupParameters, SWT.READ_ONLY);
		parameters_u.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		displayParameter(PARAMETER_u, ""); //$NON-NLS-1$
		parameters_q = new Text(groupParameters, SWT.READ_ONLY);
		parameters_q.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		displayParameter(PARAMETER_q, ""); //$NON-NLS-1$
		parameters_E = new Text(groupParameters, SWT.READ_ONLY);
		parameters_E.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		displayParameter(PARAMETER_E, ""); //$NON-NLS-1$
		parameters_E2 = new Text(groupParameters, SWT.READ_ONLY);
		parameters_E2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		displayParameter(PARAMETER_E2, ""); //$NON-NLS-1$
		parameters_l = new Text(groupParameters, SWT.READ_ONLY);
		parameters_l.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		displayParameter(PARAMETER_l, ""); //$NON-NLS-1$
		parameters_P = new Text(groupParameters, SWT.READ_ONLY);
		parameters_P.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		displayParameter(PARAMETER_P, ""); //$NON-NLS-1$
		parameters_Q2 = new Text(groupParameters, SWT.READ_ONLY);
		parameters_Q2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		displayParameter(PARAMETER_Q2, ""); //$NON-NLS-1$
		parameters_k = new Text(groupParameters, SWT.READ_ONLY);
		parameters_k.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		displayParameter(PARAMETER_k, ""); //$NON-NLS-1$
		parameters_e = new Text(groupParameters, SWT.READ_ONLY);
		parameters_e.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		displayParameter(PARAMETER_e, ""); //$NON-NLS-1$

		groupParameters = new Group(parent, SWT.NONE);
		groupParameters.setLayout(new GridLayout(2, true));
		groupParameters.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		groupParameters.setText(Messages.IntroductionAndParameters_16);

		Group group = new Group(groupParameters, SWT.NONE);
		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		group.setText(Messages.IntroductionAndParameters_17);
		
		radio_EmbeddedDegreeSmall = new Button(group, SWT.RADIO);
		radio_EmbeddedDegreeSmall.setText(Messages.IntroductionAndParameters_18);
		radio_EmbeddedDegreeSmall.setSelection(true);
		radio_EmbeddedDegreeSmall.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (((Button) e.widget).getSelection()) {
					Model.getDefault().reset();
					Model.getDefault().setParameter(Model.DEGREE_SMALL);
					radio_IndustrialSecurity.setEnabled(false);
					radio_IndustrialSecurity.setSelection(false);
					radio_PenAndPaper.setEnabled(false);
					radio_PenAndPaper.setSelection(false);
					radio_WeilPairing.setEnabled(false);
					radio_WeilPairing.setSelection(false);
					radio_TatePairing.setEnabled(false);
					radio_TatePairing.setSelection(false);
					parent.layout();
					parent.getParent().layout();
					Model.getDefault().setupStep1();
				}
			}
		});
		radio_EmbeddedDegreeSmall.setSelection(true);
		radio_EmbeddedDegreeLarge = new Button(group, SWT.RADIO);
		radio_EmbeddedDegreeLarge.setText(Messages.IntroductionAndParameters_19);
		radio_EmbeddedDegreeLarge.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (((Button) e.widget).getSelection()) {
					Model.getDefault().reset();
					Model.getDefault().setParameter(Model.DEGREE_LARGE);
					radio_IndustrialSecurity.setEnabled(true);
					radio_PenAndPaper.setEnabled(true);
					radio_WeilPairing.setEnabled(true);
					radio_PenAndPaper.setSelection(true);
					radio_TatePairing.setSelection(true);
					radio_TatePairing.setEnabled(true);
					parent.layout();
					parent.getParent().layout();
					Model.getDefault().setupStep1();
				}
			}
		});

		group = new Group(groupParameters, SWT.NONE);
		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		group.setText(Messages.IntroductionAndParameters_20);
		
		radio_PenAndPaper = new Button(group, SWT.RADIO);
		radio_PenAndPaper.setText(Messages.IntroductionAndParameters_21);
		radio_PenAndPaper.setSelection(false);
		radio_PenAndPaper.setEnabled(false);
		radio_PenAndPaper.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				Model.getDefault().setParameter(Model.PENANDPAPER);
				Model.getDefault().setupStep1();
			}
		});
		radio_IndustrialSecurity = new Button(group, SWT.RADIO);
		radio_IndustrialSecurity.setText(Messages.IntroductionAndParameters_22);
		radio_IndustrialSecurity.setEnabled(false);
		radio_IndustrialSecurity.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				Model.getDefault().setParameter(Model.INDUSTRIALSECURITY);
				Model.getDefault().setupStep1();
			}
		});

		final Composite panel = new Composite(groupParameters, SWT.NONE);
		panel.setLayout(new GridLayout(3, false));
		panel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		Text users = new Text(panel, SWT.READ_ONLY);
		users.setText(Messages.IntroductionAndParameters_23);
		
		spinner_NumberOfUsers = new Spinner(panel, SWT.BORDER);
		spinner_NumberOfUsers.setSelection(4);
		spinner_NumberOfUsers.setMinimum(3);
		spinner_NumberOfUsers.setMaximum(101);
		spinner_NumberOfUsers.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (spinner_NumberOfUsers.getSelection() == spinner_NumberOfUsers.getMinimum()) {
					spinner_NumberOfUsers.setSelection(spinner_NumberOfUsers.getMaximum() - 1);
				} else if (spinner_NumberOfUsers.getSelection() == spinner_NumberOfUsers.getMaximum()) {
					spinner_NumberOfUsers.setSelection(spinner_NumberOfUsers.getMinimum() + 1);
				}
				Model.getDefault().setNumberOfUsers(spinner_NumberOfUsers.getSelection());
				Model.getDefault().setupStep1();
			}
		});

		Text maxUserHint = new Text(panel, SWT.WRAP | SWT.READ_ONLY);
		GridData gd_maxUserHint = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
		gd_maxUserHint.widthHint = 200;
		maxUserHint.setForeground(ColorService.GRAY);
		maxUserHint.setLayoutData(gd_maxUserHint);
		maxUserHint.setText(Messages.IntroductionAndParameters_24);
		maxUserHint.setForeground(ColorService.GRAY);
		
		Label horizontalSeparator = new Label(panel, SWT.SEPARATOR | SWT.HORIZONTAL);
		horizontalSeparator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		horizontalSeparator.setForeground(ColorService.GRAY);
		
		Text perforamceHint = new Text(panel, SWT.WRAP | SWT.READ_ONLY);
		GridData gd_perforamceHint = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
		gd_perforamceHint.widthHint = 200;
		perforamceHint.setForeground(ColorService.GRAY);
		perforamceHint.setLayoutData(gd_perforamceHint);
		perforamceHint.setText(Messages.IntroductionAndParameters_3);

		group = new Group(groupParameters, SWT.NONE);
		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		group.setText(Messages.IntroductionAndParameters_25);
		
		radio_TatePairing = new Button(group, SWT.RADIO);
		radio_TatePairing.setText(Messages.IntroductionAndParameters_26);
		radio_TatePairing.setSelection(false);
		radio_TatePairing.setEnabled(false);
		radio_TatePairing.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				Model.getDefault().setParameter(Model.TATEPAIRING);
				Model.getDefault().setupStep1();
			}
		});
		radio_WeilPairing = new Button(group, SWT.RADIO);
		radio_WeilPairing.setText(Messages.IntroductionAndParameters_27);
		radio_WeilPairing.setEnabled(false);
		radio_WeilPairing.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				Model.getDefault().setParameter(Model.WEILPARING);
				Model.getDefault().setupStep1();
			}
		});
	}

	public void cancelPair() {
		displayParameter(PARAMETER_e, Messages.IntroductionAndParameters_28);
	}

	// this method resets the size of the parameters.
	// which means K = 2.

	public void changePairRedraw() {
		displayParameter(PARAMETER_e, Messages.IntroductionAndParameters_28);

		cancelPair();
	}

	private void displayParameter(int parameter, String value) {
		String text = ""; //$NON-NLS-1$
		switch (parameter) {
		case PARAMETER_e:
			text += "e : "; //$NON-NLS-1$
			break;
		case PARAMETER_E:
			text += "E : "; //$NON-NLS-1$
			break;
		case PARAMETER_E2:
			text += "E': "; //$NON-NLS-1$
			break;
		case PARAMETER_k:
			text += "k : "; //$NON-NLS-1$
			break;
		case PARAMETER_l:
			text += "l : "; //$NON-NLS-1$
			break;
		case PARAMETER_P:
			text += "P : "; //$NON-NLS-1$
			break;
		case PARAMETER_q:
			text += "q : "; //$NON-NLS-1$
			break;
		case PARAMETER_Q2:
			text += "Q': "; //$NON-NLS-1$
			break;
		case PARAMETER_u:
			text += "u : "; //$NON-NLS-1$
			break;
		}

		text += value;

		switch (parameter) {
		case PARAMETER_e:
			parameters_e.setText(text);
			break;
		case PARAMETER_E:
			parameters_E.setText(text);
			break;
		case PARAMETER_E2:
			parameters_E2.setText(text);
			break;
		case PARAMETER_k:
			parameters_k.setText(text);
			break;
		case PARAMETER_l:
			parameters_l.setText(text);
			break;
		case PARAMETER_P:
			parameters_P.setText(text);
			break;
		case PARAMETER_q:
			parameters_q.setText(text);
			break;
		case PARAMETER_Q2:
			parameters_Q2.setText(text);
			break;
		case PARAMETER_u:
			parameters_u.setText(text);
			break;
		}

		parameters_u.getParent().layout();
	}

	public Button getEmbeddedDegreeLarge() {
		return radio_EmbeddedDegreeLarge;
	}

	public Button getEmbeddedDegreeSmall() {
		return radio_EmbeddedDegreeSmall;
	}

	public Button getIndustrialSecurity() {
		return radio_IndustrialSecurity;
	}

	public Button getPenAndPaper() {
		return radio_PenAndPaper;
	}

	public Button getTatePairing() {
		return radio_TatePairing;
	}

	public Spinner getText() {
		return spinner_NumberOfUsers;
	}

	public Button getWeilPairing() {
		return radio_WeilPairing;
	}

	public void redraw() {
		cancelPair();
	}

	public void redrawWOEDeg() {
		cancelPair();
	}

	public void resetpars() {
		displayParameter(PARAMETER_u, ""); //$NON-NLS-1$
		displayParameter(PARAMETER_E2, ""); //$NON-NLS-1$
		displayParameter(PARAMETER_Q2, ""); //$NON-NLS-1$
		displayParameter(PARAMETER_q, ""); //$NON-NLS-1$
		displayParameter(PARAMETER_E, ""); //$NON-NLS-1$
		displayParameter(PARAMETER_l, ""); //$NON-NLS-1$
		displayParameter(PARAMETER_P, ""); //$NON-NLS-1$
		displayParameter(PARAMETER_k, ""); //$NON-NLS-1$
	}

	public void setBNCurves() {
		final BigInteger u = new BigInteger("110100001000000000000100010000000000011", 2); //$NON-NLS-1$
		displayParameter(PARAMETER_u, u.toString(16) + Messages.IntroductionAndParameters_49);
		displayParameter(PARAMETER_q, Messages.IntroductionAndParameters_50);
		displayParameter(PARAMETER_E, Messages.IntroductionAndParameters_51);
		displayParameter(PARAMETER_E2, Messages.IntroductionAndParameters_52);
		displayParameter(PARAMETER_l, Messages.IntroductionAndParameters_53);
		displayParameter(PARAMETER_Q2, Messages.IntroductionAndParameters_54);
		displayParameter(PARAMETER_k, Messages.IntroductionAndParameters_55);
		displayParameter(PARAMETER_e, Messages.IntroductionAndParameters_56);
	}

	public void setHighSecurity() {
		displayParameter(PARAMETER_q, Messages.IntroductionAndParameters_57);
		displayParameter(PARAMETER_l, Messages.IntroductionAndParameters_58);
		displayParameter(PARAMETER_P, Messages.IntroductionAndParameters_59);
	}

	public void setLowSecurity() {
		displayParameter(PARAMETER_q, Messages.IntroductionAndParameters_60);
		displayParameter(PARAMETER_l, Messages.IntroductionAndParameters_61);
		displayParameter(PARAMETER_P, Messages.IntroductionAndParameters_62);
	}

	public void setTatePairing() {
		displayParameter(PARAMETER_e, Messages.IntroductionAndParameters_63);
	}

	public void setTextIndestrialSecurity(String s) {
		radio_IndustrialSecurity.setText(s);
	}

	public void setTextPenAndPaper(String s) {
		radio_PenAndPaper.setText(s);
	}

	public void setWeilPairing() {
		displayParameter(PARAMETER_e, Messages.IntroductionAndParameters_64);
	}
}
