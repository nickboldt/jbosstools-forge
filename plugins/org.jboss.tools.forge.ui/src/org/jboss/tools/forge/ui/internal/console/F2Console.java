package org.jboss.tools.forge.ui.internal.console;

import java.beans.PropertyChangeEvent;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Composite;
import org.jboss.forge.addon.resource.Resource;
import org.jboss.tools.aesh.core.console.Console;
import org.jboss.tools.forge.core.runtime.ForgeRuntime;
import org.jboss.tools.forge.core.runtime.ForgeRuntimeState;
import org.jboss.tools.forge.ui.internal.actions.StartAction;
import org.jboss.tools.forge.ui.internal.actions.StopAction;
import org.jboss.tools.forge.ui.internal.viewer.F2TextViewer;
import org.jboss.tools.forge.ui.internal.viewer.ForgeTextViewer;

public class F2Console extends AbstractForgeConsole {

	private F2TextViewer textViewer;

	public F2Console(ForgeRuntime runtime) {
		super(runtime);
	}

	@Override
	public ForgeTextViewer createTextViewer(Composite parent) {
		return textViewer = new F2TextViewer(parent);
	}

	@Override
	public IAction[] createActions() {
		return new IAction[] { new StartAction(getRuntime()),
				new StopAction(getRuntime()) };
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (ForgeRuntimeState.RUNNING.equals(evt.getNewValue())) {
			getTextViewer().startConsole();
		}
		if (ForgeRuntimeState.STOPPED.equals(evt.getNewValue())) {
			getTextViewer().stopConsole();
		}
	}

	@Override
	public Resource<?> getCurrentResource() {
		Resource<?> currentResource = null;
		if (textViewer != null) {
			Console console = textViewer.getConsole();
			if (console != null) {
				currentResource = (Resource<?>) console.getCurrentResource();
			}
		}
		return currentResource;
	}
}
