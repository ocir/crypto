//-----BEGIN DISCLAIMER-----
/*******************************************************************************
* Copyright (c) 2012, 2021 JCrypTool Team and Contributors
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
//-----END DISCLAIMER-----
package org.jcryptool.crypto.ui.textmodify.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.jcryptool.core.operations.algorithm.classic.textmodify.TransformData;
import org.jcryptool.crypto.ui.alphabets.AlphabetSelectorComposite.Mode;

/**
 * @author SLeischnig
 * The wizard page for the transformation wizard
 */
public class ModifyWizardPage extends WizardPage{

	private TransformData predefinedData = new TransformData();
	private ModifySelectionComposite composite1;
	
	private Mode alphabetSelectionMode = Mode.SINGLE_COMBO_BOX_WITH_CUSTOM_ALPHABETS;

	public void setAlphabetSelectionMode(Mode alphabetSelectionMode) {
		this.alphabetSelectionMode = alphabetSelectionMode;
	}

	/**
	 * Creates a new instance of CaesarWizardPage.
	 * @param alphabets the alphabets to be displayed in the alphabet box
	 * @param defaultAlphabet the name of the default alphabet (the selected entry in the alphabet combo box) - if the alphabet is not found, the first Alphabet is used
	 */
	public ModifyWizardPage() {
		super("", Messages.ModifyWizardPage_texttransformations, null);  //$NON-NLS-1$
		setTitle(Messages.ModifyWizardPage_texttransformations);
		setMessage(Messages.ModifyWizardPage_hint1);
		predefinedData.setUnmodified();
	}

	/**
	 * for subclassing: add controls at the beginning of the page
	 */
	protected void addControlsBefore(Composite parent) {
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public final void createControl(final Composite parent) {
		Composite pageComposite = new Composite(parent, SWT.NONE);
		GridData pageCompositeLayoutData = new GridData();
		GridLayout pageCompositeLayout = new GridLayout();
		pageCompositeLayoutData.grabExcessHorizontalSpace = true; pageCompositeLayoutData.grabExcessVerticalSpace = true;
		pageCompositeLayoutData.horizontalAlignment = SWT.FILL;
		pageComposite.setLayout(pageCompositeLayout);
		pageComposite.setLayoutData(pageCompositeLayoutData);

		{
			addControlsBefore(pageComposite);

			GridData composite1LData = new GridData(GridData.FILL_BOTH);
			composite1 = new ModifySelectionComposite(pageComposite, SWT.NONE, new TransformData(), alphabetSelectionMode);
			composite1.setLayoutData(composite1LData);

			composite1.setTransformData(predefinedData);

			addControlsAfter(pageComposite);
		}

        setControl(pageComposite);
		setPageComplete(true);
	}

	/**
	 * for subclassing: add controls at the end of the page
	 */
	protected void addControlsAfter(Composite parent) {
	}


	public final void setPredefinedData(final TransformData myWizardData) {
		this.predefinedData = myWizardData;
	}

	public final void setSelectedData(final TransformData myWizardData) {
		if(composite1!=null && !composite1.isDisposed()) {
			composite1.setTransformData(myWizardData);
		} else {
			setPredefinedData(myWizardData);
		}
	}

	public final TransformData getSelectedData() {
		return composite1.getTransformData();
	}
}
