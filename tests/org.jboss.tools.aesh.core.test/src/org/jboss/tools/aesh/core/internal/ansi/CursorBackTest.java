package org.jboss.tools.aesh.core.internal.ansi;

import org.jboss.tools.aesh.core.document.Document;
import org.jboss.tools.aesh.core.test.util.TestDocument;
import org.junit.Assert;
import org.junit.Test;

public class CursorBackTest {
	
	private int cursorOffset = 0;
	
	private Document testDocument = new TestDocument() {
		@Override public int getCursorOffset() { return cursorOffset; }
		@Override public void moveCursorTo(int offset) { cursorOffset = offset; }
	};
	
	@Test
	public void testGetType() {
		CursorBack cursorBack = new CursorBack("5");
		Assert.assertEquals(CommandType.CURSOR_BACK, cursorBack.getType());
	}
	
	@Test
	public void testHandle() {
		CursorBack cursorBack = new CursorBack("5");
		cursorOffset = 8;
		cursorBack.handle(testDocument);
		Assert.assertEquals(3, testDocument.getCursorOffset());
	}

}
