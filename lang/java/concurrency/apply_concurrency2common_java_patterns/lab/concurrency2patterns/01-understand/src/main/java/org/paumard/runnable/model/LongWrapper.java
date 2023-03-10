package org.paumard.runnable.model;

public class LongWrapper {

	private Object key = new Object();
	private long l;

	public LongWrapper(long l) {
		this.l = l;
	}

	public long getValue() {
		synchronized (key) {
			return l;
		}
		//non-synchronized
		//return l;
	}

	public void incrementValue() {
		synchronized (key) {
			l+=1; //this is a read and write operation
		}
		// non-synchronized
		/*l+=1; */
	}
}
