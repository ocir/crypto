// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2011, 2021 JCrypTool Team and Contributors
 *
 * All rights reserved. This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----
package org.jcryptool.visual.he.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.jcryptool.core.util.ui.auto.LayoutAdvisor;
import org.jcryptool.core.util.ui.auto.SmoothScroller;
import org.jcryptool.visual.he.Messages;


public class HEView extends ViewPart {

	/** Handles the tab choice */
	public final int GENTRY_HALEVI = 1, RSA = 2, PAILLIER = 3;
	private Composite parent;

	public HEView() {
	}

	@Override
	public void createPartControl(final Composite parent) {
		this.parent = parent;
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		final TabFolder tf = new TabFolder(parent, SWT.TOP);

		// Gentry & Halevi
        TabItem ti = new TabItem(tf, SWT.NONE);
        ti.setText(Messages.HEComposite_GentryHalevi);
        ScrolledComposite sc = new ScrolledComposite(tf, SWT.H_SCROLL | SWT.V_SCROLL);
        sc.setExpandHorizontal(true);
        sc.setExpandVertical(true);
        GHComposite ghc = new GHComposite(sc, SWT.NONE);
        sc.setContent(ghc);
        sc.setMinSize(ghc.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        ti.setControl(sc);
        
		// This makes the ScrolledComposite scrolling, when the mouse 
		// is on a Text with one or more of the following tags: SWT.READ_ONLY,
		// SWT.V_SCROLL or SWT.H_SCROLL.
		SmoothScroller.scrollSmooth(sc);

        // RSA
        ti = new TabItem(tf, SWT.NONE);
        ti.setText(Messages.HEComposite_RSA);
        sc = new ScrolledComposite(tf, SWT.H_SCROLL | SWT.V_SCROLL);
        sc.setExpandHorizontal(true);
        sc.setExpandVertical(true);
        HEComposite c = new HEComposite(sc, RSA, SWT.NONE);
        sc.setContent(c);
        sc.setMinSize(c.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        ti.setControl(sc);
		// This makes the ScrolledComposite scrolling, when the mouse 
		// is on a Text with one or more of the following tags: SWT.READ_ONLY,
		// SWT.V_SCROLL or SWT.H_SCROLL.
		SmoothScroller.scrollSmooth(sc);

        // Paillier
        ti = new TabItem(tf, SWT.NONE);
        ti.setText(Messages.HEComposite_Paillier);
        sc = new ScrolledComposite(tf, SWT.H_SCROLL | SWT.V_SCROLL);
        sc.setExpandHorizontal(true);
        sc.setExpandVertical(true);
        c = new HEComposite(sc, PAILLIER, SWT.NONE);
        sc.setContent(c);
        sc.setMinSize(c.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        ti.setControl(sc);
		// This makes the ScrolledComposite scrolling, when the mouse 
		// is on a Text with one or more of the following tags: SWT.READ_ONLY,
		// SWT.V_SCROLL or SWT.H_SCROLL.
		SmoothScroller.scrollSmooth(sc);
        
        
        
        /**
         * This sets all labels and text a widthHint.
         * It is necessary, because the TitleAndDescriptionComposite (
         * the GUI part which contains the title and the description of the
         * plugin adds a resize listener to sc (the scrolledComposite that 
         * contains all content).
         * Unfortunately, if a textfield gets a long input and the resize listener
         * is triggered it resizes the textfield to its preferrred size.
         * Its prefferred size is the size to show the complete input.
         * By setting a width hint this can be avoided and 
         * LayoutAdvisor.addPreLayoutRootComposite(parent); does this.
         */
        LayoutAdvisor.addPreLayoutRootComposite(parent);

        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "org.jcryptool.visual.he.view");
	}

	@Override
	public void setFocus() {
	}

	public void reset() {
		Control[] children = parent.getChildren();
		for (Control control : children) {
			control.dispose();
		}
		createPartControl(parent);
		parent.layout();
	}

}
