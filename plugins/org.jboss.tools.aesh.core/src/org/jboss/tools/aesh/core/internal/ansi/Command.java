package org.jboss.tools.aesh.core.internal.ansi;

import org.jboss.tools.aesh.core.document.Document;


public interface Command {
	
	void handle(Document document);

}
