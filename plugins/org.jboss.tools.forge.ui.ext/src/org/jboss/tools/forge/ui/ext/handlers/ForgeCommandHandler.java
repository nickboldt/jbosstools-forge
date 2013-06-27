package org.jboss.tools.forge.ui.ext.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.jboss.tools.forge.ext.core.FurnaceService;
import org.jboss.tools.forge.ui.ext.dialog.UICommandListDialog;

public class ForgeCommandHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			FurnaceService.INSTANCE.waitUntilContainerIsStarted();
		} catch (InterruptedException e) {
			throw new ExecutionException("Container not started", e);
		}
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		return new UICommandListDialog(window).open();
	}

}
