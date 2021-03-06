package org.jboss.tools.forge.ui.internal.viewer;

import org.eclipse.swt.widgets.Composite;
import org.jboss.tools.aesh.core.console.Console;
import org.jboss.tools.aesh.ui.view.AbstractTextViewer;
import org.jboss.tools.forge.core.furnace.FurnaceRuntime;
import org.jboss.tools.forge.core.runtime.ForgeRuntimeState;
import org.jboss.tools.forge.ui.internal.cli.AeshConsole;

public class F2TextViewer extends AbstractTextViewer implements ForgeTextViewer {
	private AeshConsole console;

	public F2TextViewer(Composite parent) {
		super(parent);
		if (ForgeRuntimeState.RUNNING
				.equals(FurnaceRuntime.INSTANCE.getState())) {
			startConsole();
		}
	}

	protected Console createConsole() {
		return console = new AeshConsole();
	}

	@Override
	public Console getConsole() {
		return console;
	}

}
