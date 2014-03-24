package org.jboss.tools.aesh.ui.internal.document;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;
import org.jboss.tools.aesh.core.document.Document;
import org.jboss.tools.aesh.core.document.Style;
import org.jboss.tools.aesh.ui.internal.AeshUIPlugin;

public class DelegatingDocument implements Document {
	
	private DelegateDocument delegate;
	private DelegatingStyleRange currentStyle;
	private int savedCursor = 0;
	private int cursorOffset = 0;
	
	public DelegatingDocument(DelegateDocument document) {
		this.delegate = document;
		this.currentStyle = DelegatingStyleRange.getDefault();
	}

	@Override
	public int getCursorOffset() {
		return cursorOffset;
	}
	
	@Override
	public int getLineOfOffset(int offset) {
		int result = -1;
		try {
			result = delegate.getLineOfOffset(offset);
		} catch (BadLocationException e) {
			AeshUIPlugin.log(e);
		}
		return result;
	}
	
	@Override
	public int getLineOffset(int line) {
		int result = -1;
		try {
			result = delegate.getLineOffset(line);
		} catch (BadLocationException e) {
			AeshUIPlugin.log(e);
		}
		return result;
	}
	
	@Override 
	public int getLineLength(int line) {
		int result = -1;
		try {
			result = delegate.getLineLength(line);
		} catch (BadLocationException e) {
			AeshUIPlugin.log(e);
		}
		return result;
	}

	@Override
	public void moveCursorTo(final int offset) {
		cursorOffset = offset;
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				delegate.moveCursorTo(offset);
			}				
		});
	}
	
	@Override
	public void reset() {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				delegate.set("");
			}				
		});
		moveCursorTo(0);
		setDefaultStyle();
	}

	@Override
	public int getLength() {
		return delegate.getLength();
	}

	@Override
	public void replace(final int pos, final int length, final String text) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
			  	try {
		        	delegate.replace(pos, length, text);
		        } catch (BadLocationException e) {
		        	AeshUIPlugin.log(e);
		        }
			}				
		});
	}

	@Override
	public void restoreCursor() {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				moveCursorTo(savedCursor);
			}				
		});
	}

	@Override
	public void saveCursor() {
		savedCursor = getCursorOffset();
	}

	@Override
	public Style newStyleFromCurrent() {
		StyleRange oldStyleRange = currentStyle.getDelegate();
		StyleRange newStyleRange = new StyleRange(oldStyleRange);
		newStyleRange.start = oldStyleRange.start + oldStyleRange.length;
		newStyleRange.length = 0;
		return new DelegatingStyleRange(newStyleRange);
	}

	@Override
	public void setCurrentStyle(Style styleRangeProxy) {
		if (styleRangeProxy instanceof DelegatingStyleRange) {
			currentStyle = (DelegatingStyleRange)styleRangeProxy;
		}
	}
	
	@Override
	public Style getCurrentStyle() {
		return currentStyle;
	}
	
	@Override
	public void setDefaultStyle() {
		DelegatingStyleRange defaultStyle = DelegatingStyleRange.getDefault();
		StyleRange styleRange = defaultStyle.getStyleRange();
		styleRange.start = delegate.getLength();
		styleRange.length = 0;
		setCurrentStyle(defaultStyle);
	}
	
	public DelegateDocument getDelegate() {
		return delegate;
	}
	
	public DelegatingStyleRange getCurrentStyleRange() {
		return currentStyle;
	}
	
}