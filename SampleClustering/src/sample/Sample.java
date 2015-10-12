package sample;

import java.util.LinkedList;

/**
 * This class represents a Sample, it is made of sequential colored elements
 * referred to as SampleElementColor objects.
 * 
 * 
 * @author Julien Jorda
 * @version 0.1
 * @see SampleElementColor
 */

@SuppressWarnings("serial")
public class Sample extends LinkedList<SampleElementColor> {

	
	private String label;
	
	/**
	 * Constructor.
	 */
	public Sample() {
		super();
	}

	/** A method for calculating the distance between the current sample and another one
	 * 
	 * @param sp2 the other Sample for which the distance is being calculated
	 * @return the distance between the two samples
	 * @throws SampleException
	 * @see SampleException
	 */
	public double calcDistance (Sample sp2) throws SampleException {
		double distance = 0.0;
		
		/* Testing if samples are the same length, impossible to compare them if they have different lengths */
		if (this.size()!=sp2.size()) {
			throw new SampleException("Error: The two samples should be of identical length.");
		}else {
			int sampleLength = sp2.size();
			//sum the individual distances of elements with the same index in both samples
			for (int i=0;i<sampleLength;i++) {
				distance += sp2.get(i).calcDistance(this.get(i));
			}
			// and average them
			distance/=(double)sampleLength;
		}		
		
		return distance;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		String str = this.label+"\n";
		 for (SampleElementColor se:this) {
			 str+="[ "+se.getH()+","+se.getS()+","+se.getV()+"]";
		 }
		
		return str;
	}
}
