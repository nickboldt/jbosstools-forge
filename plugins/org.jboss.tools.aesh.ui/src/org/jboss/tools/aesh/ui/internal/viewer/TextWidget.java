package org.jboss.tools.aesh.ui.internal.viewer;

import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.jboss.tools.aesh.core.console.Console;
import org.jboss.tools.aesh.ui.internal.util.CharacterConstants;

public class TextWidget extends StyledText {
	
	Console console = null;

	public TextWidget(Composite parent, int style) {
		super(parent, style);
	}
	
	public void setConsole(Console console) {
		this.console = console;
	}

	public void invokeAction(int action) {
		switch (action) {
			case ST.LINE_END:
				console.sendInput(CharacterConstants.END_LINE);
				break;
			case ST.LINE_START:
				console.sendInput(CharacterConstants.START_LINE);
				break;
			case ST.LINE_UP:
				console.sendInput(CharacterConstants.PREV_HISTORY);
				break;
			case ST.LINE_DOWN:
				console.sendInput(CharacterConstants.NEXT_HISTORY);
				break;
			case ST.COLUMN_PREVIOUS:
				console.sendInput(CharacterConstants.PREV_CHAR);
				break;
			case ST.COLUMN_NEXT:
				console.sendInput(CharacterConstants.NEXT_CHAR);
				break;
			case ST.DELETE_PREVIOUS:
				console.sendInput(CharacterConstants.DELETE_PREV_CHAR);
				break;
			case ST.DELETE_NEXT:
				console.sendInput(CharacterConstants.DELETE_NEXT_CHAR);
				break;
			default: super.invokeAction(action);
		}
	}

}
