package sample;

import java.awt.Color;

/**
 * A class defining the elementary blocks of a sample. Each element presents a color information
 * encoded in the HSV (Hue, Saturation, Value) color model. HSV is considered to be a more 
 * perceptually relevant than the usual RGB color model because it separates the hue information 
 * from the brightness.
 * The similarity (or distance) between two colors is calculated based on the euclidean distance in the
 * space defined by these three attributes.
 * 
 * 
 * @author Julien Jorda
 * @version 0.1
 *
 */
public class SampleElementColor extends SampleElement<SampleElementColor> {
	private float h;
	private float s;
	private float v;
	
	/**
	 * Empty Constructor
	 */
	public SampleElementColor() {
		
	}
	
	/**
	 * Constructor for the SampleElementColor object. Requires the HSV values of its associated color.
	 * HSV values are comprised between 0 and 1.
	 * 
	 * @param ih the Hue 
	 * @param is the Saturation
	 * @param iv the Value
	 * @throws SampleElementException
	 * 
	 */
	public SampleElementColor(float ih, float is,float iv) throws Exception {
		
		
		if (ih>=1 || ih<0 || is>1 || is<0 || iv>0 || iv< 1) {
			throw new SampleElementException("HSV parameters should be comprised beween 0 and 1.");
		}else {
			this.h = ih;
			this.s = is;
			this.v = iv;
		}
	}
	
	/**
	 * Constructor for the SampleElementColor object. Requires the RGB values of its associated color which are
	 * then converted to HSV model. RGB values range from 0 to 255.
	 * 
	 * @param r the red channel 
	 * @param g the g channel
	 * @param b
	 * @throws SampleElementException
	 */
	public SampleElementColor(int r,int g, int b) throws SampleElementException {
		
		if (r<0 || g<0 || b<0 || r>255 || g>255 || b>255) {
			throw new SampleElementException("RGB parameters should be comprised beween 0 and 255.");
		}else {
			float[] hsv = new float[3];
			Color.RGBtoHSB(r,g,b,hsv);
			 this.h = hsv[0];
			 this.s = hsv[1];
			 this.v = hsv[2];
		}
	}
	

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getS() {
		return s;
	}

	public void setS(float s) {
		this.s = s;
	}

	public float getV() {
		return v;
	}


	public void setV(float v) {
		this.v = v;
	}
	

	/**
	 * A method calculating the color "distance" between the current element and another one, typically from another 
	 * sample.
	 *  For the sake of simplicity, the hue differences between two colors are considered as binary (0 when identical,
	 * 1 when different).
	 */
	public double calcDistance (SampleElementColor otherElement) {
		double distance = 0.0, hDistance = 0.0, sDistance =0.0, vDistance = 0.0;
		
		//when hues are different, consider the hDistance as 1 
		if (otherElement.getH()-this.h != 0.0) { 
			hDistance = 1;
		}
		
		sDistance = otherElement.getS()-this.s;
		vDistance = otherElement.getV()-this.v;
		
		//Calculate the euclidean distance between the two elements in the HSV space
		if (this.s == 0 || otherElement.getS() == 0 ) { //do  not consider the hue when the saturation is zero
			distance = Math.sqrt(power2(sDistance)+power2(vDistance));
		}else {
			distance = Math.sqrt(power2(hDistance)+power2(sDistance)+power2(vDistance));
		}
		return distance;
	}
	
	
	private double power2 (double value) {
		return Math.pow(value,2);
	}

	@Override
	public String toString () {
		return "Element with HSV values: "+this.h+","+this.s+","+this.v;
	}
}
