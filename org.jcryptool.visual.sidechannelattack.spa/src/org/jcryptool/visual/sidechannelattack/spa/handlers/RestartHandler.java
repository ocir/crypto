package org.jcryptool.visual.sidechannelattack.spa.handlers;

// -----BEGIN DISCLAIMER-----
/*******************************************************************************
 * Copyright (c) 2009, 2021 JCrypTool Team and Contributors
 *
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// -----END DISCLAIMER-----

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.jcryptool.visual.sidechannelattack.spa.views.SPAView;
/**
 * This handler starts a new game.
 *
 * @author Johannes Späth
 * @version 0.9.5
 */
public class RestartHandler extends AbstractHandler {
    public Object execute(ExecutionEvent event) throws ExecutionException {
        if (HandlerUtil.getActivePart(event) instanceof SPAView) {
        		SPAView view = ((SPAView) HandlerUtil.getActivePart(event));
                
                view.reset();
        }

        return null;
    }
}
