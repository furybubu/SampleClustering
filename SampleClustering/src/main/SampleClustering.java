package main;

import java.util.LinkedList;

import clustering.NeighborJoining;
import sample.Sample;
import sample.SampleElementColor;
import sample.SampleElementException;
import sample.SampleException;
import util.SquareMatrix;

/**
 * This is the main class for clustering samples from a given dataset 
 * based on their similarity (or in fact their pairwise distance).
 * The program implements a clustering method called Neighbour Joining,
 * an algorithm widely used for bulding phylogenetic trees. 
 * 
 * @author Julien Jorda
 * @version: 0.1
 */

public class SampleClustering {
	
	private LinkedList<Sample> dataset;
	
	public SampleClustering() {
		
	}
	
	public SampleClustering(LinkedList<Sample> dataset) {
		this.dataset = dataset;
	}
	
	/**
	 * The main method launching the clustering process. It computes a distance matrix from the dataset and cluster
	 * the samples using the NeighborJoining method.
	 *  
	 */
	public void clusterData () {
		
		//first, build  the pairwise Distance Matrix 
		SquareMatrix distanceMatrix = this.buildDistanceMatrix();
		
		//THen compute the clustering with the Neighbour Joining algorithm
		NeighborJoining NJCluster = new NeighborJoining(distanceMatrix);
		NJCluster.cluster();
		
		//Prints a basic tree in the console
		NJCluster.printTree();
	}
	
	/**
	 * This method computes a distance matrix where pairwise distances between two different sample are 
	 * calculated. THis distance Matrix can be further processed by any clustering method based on distances.
	 * @return a square distance matrix
	 * @see SquareMatrix
	 */
	private SquareMatrix buildDistanceMatrix () {
		
	SquareMatrix distanceMatrix  = new SquareMatrix(dataset.size());	
	
		for (int i=0;i<dataset.size();i++) {
			//Assign the name of a sample to its corresponding index in the matrix
			distanceMatrix.setLabel(dataset.get(i).getLabel(),i);
			for (int j=0;j<dataset.size();j++) {
				if (i!=j) {
					try {
						distanceMatrix.set(i,j,dataset.get(i).calcDistance(dataset.get(j)));
					} catch (SampleException e) {
						e.printStackTrace();
					}
				}else {
					//distance is 0 when a sample is compared to itself
					distanceMatrix.set(i,j,0);
				}
			}
		}
				
		return distanceMatrix;
	}

	/**
	 * Main method running an example of clustering on a dataset of four randomlt generated samples.
	 * @param args
	 */
	public static void main (String[] args) {
		
		try {
			
			//initialize a set of elements with different colors, could be any color here
			
			SampleElementColor redElemt = new SampleElementColor(255,0,0);//red
			SampleElementColor blueElemt = new SampleElementColor(0,0,255);//blue
			SampleElementColor whiteElemt = new SampleElementColor(255,255,255);//white
			SampleElementColor darkRedElemt = new SampleElementColor(153,0,0);//dark red
			SampleElementColor lightRedElemt = new SampleElementColor(255,102,102);//light red
			
			SampleElementColor[] elementColors = {redElemt,blueElemt,whiteElemt,darkRedElemt,lightRedElemt};
			
			//An example of how the distances are calculated between two elements
			System.out.println("distance between red and blue "+redElemt.calcDistance(blueElemt));
			System.out.println("distance between red and white "+redElemt.calcDistance(whiteElemt));
			System.out.println("distance between blue and white "+blueElemt.calcDistance(whiteElemt));
			System.out.println("distance between red and dark red "+redElemt.calcDistance(darkRedElemt));
			// ....
			
			
			/*
			 * Build a random sample dataset each with different colored regions
			 */
			LinkedList<Sample> dataset = new LinkedList<Sample>(); 
			Sample sample1 = new Sample(), sample2 = new Sample(), sample3 = new Sample(), sample4 = new Sample();
			sample1.setLabel("sp1");
			sample2.setLabel("sp2");
			sample3.setLabel("sp3");
			sample4.setLabel("sp4");
			
			//define the number of elements in a sample
			int sampleSize = 10;
			int randColor;
			
			for (int i=0;i<sampleSize;i++) {			
				randColor =(int)( Math.random()*(elementColors.length-1));
				sample1.add(elementColors[randColor]);
				randColor =(int)( Math.random()*(elementColors.length-1));
				sample2.add(elementColors[randColor]);
				randColor =(int) (Math.random()*(elementColors.length-1));
				sample3.add(elementColors[randColor]);
				randColor =(int)( Math.random()*(elementColors.length-1));
				sample4.add(elementColors[randColor]);
			}
			
			dataset.add(sample1);
			dataset.add(sample2);
			dataset.add(sample3);
			dataset.add(sample4);
			
			//Run the clustering on this dataset
			SampleClustering clusterColorSample = new SampleClustering(dataset);
			clusterColorSample.clusterData();
			
			
		} catch (SampleElementException e) {
			e.printStackTrace();
		}
		
	}

}
