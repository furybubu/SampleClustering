package sample;

/**
 * A generic abstract class representing a SampleElement. SampleElements are the building blocks of samples.
 * 
 * @author Julien Jorda
 * @version 0.1
 *
 */
public abstract class SampleElement<T> {
	
	/**
	 * A method calculating the distance between the current element and another one, typically from another 
	 * sample.
	 * @param otherElement the SampleElement that we would like to evaluate the distance of.
	 * @return the distance between the two SampleElements.
	 */
	public abstract double calcDistance (T otherElement);

}
