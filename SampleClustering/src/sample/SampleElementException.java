package sample;

/**
 * A custom Exception for the SampleElement objects.
 * 
 * @author Julien Jorda
 * @version 0.1
 *
 */
@SuppressWarnings("serial")
public class SampleElementException extends Exception {

	 	public SampleElementException () {

	    }

	    public SampleElementException (String message) {
	        super (message);
	    }

	    public SampleElementException (Throwable cause) {
	        super (cause);
	    }

	    public SampleElementException (String message, Throwable cause) {
	        super (message, cause);
	    }
}
