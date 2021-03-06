package org.jboss.tools.aesh.core.internal.io;

import java.io.IOException;
import java.io.OutputStream;

public class AeshOutputStream extends OutputStream {
	
	private AeshOutputFilter filter = null;
	
	public AeshOutputStream(AeshOutputFilter filter) {
		this.filter = filter;
	}
	
	@Override
	public void write(int i) throws IOException {
		filter.filterOutput(new String( new char[] { (char)i }));
	}
	
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		filter.filterOutput(new String(b).substring(off, off + len));
	}
	
}
