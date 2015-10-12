package sample;

/**
 * A custom Exception for the Sample objects.
 * 
 * @author Julien Jorda
 * @version 0.1
 *
 */

@SuppressWarnings("serial")
public class SampleException extends Exception {

	 	public SampleException () {

	    }

	    public SampleException (String message) {
	        super (message);
	    }

	    public SampleException (Throwable cause) {
	        super (cause);
	    }

	    public SampleException (String message, Throwable cause) {
	        super (message, cause);
	    }
}
