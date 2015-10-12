package clustering;

import java.util.LinkedList;

import clustering.Tree.Node;
import util.SquareMatrix;

/**
 * This class implements the Neighbor Joining method as defined by 
 * Saitou & Nei in "The neighbor-joining method: a new method for reconstructing phylogenetic trees." 
 * Molecular Biology and Evolution, volume 4, issue 4, pp. 406-425, July 1987. 
 * Neighbor Joining is an agglomerative clustering method using distance matrices that gained popularity in the field of phylogenetics.
 * It has a polynomial complexity similar to other agglomerative methods.
 * 
 * @author Julien Jorda
 * @version 0.1
 *
 */
public class NeighborJoining {

	//the initial list of nodes/leaves, unclustered, as found in the distance matrix
	private LinkedList<Node> nodes;

	//the square pairwise distance matrix
	private SquareMatrix distanceMatrix;
	
	//the tree representing the hierarchy of the nodes after clustering
	private Tree NJTree;
	
	/**
	 * Constructor. Takes a distance matrix and consider the labels of each entry in the matrix as a node to be clustered and reordered into a tree.
	 * @param distMatrix the distance matrix
	 */
	public NeighborJoining(SquareMatrix distMatrix) {
		
		nodes = new LinkedList<Node>();
		
		for (int i = 0;i<distMatrix.size();i++) {
			Node leaf = new Node();
			leaf.setLabel(distMatrix.getLabel(i));
			nodes.add(leaf);
		}
		this.distanceMatrix = distMatrix;
		
		this.NJTree = new Tree("root");
	}
	
	/**
	 * A recursive method for joining a pair of closest nodes (neighbors) and assign them to a parent node. At each step the Q Matrix is calculated from the distance
	 * matrix to identify the closest pair. As a consequence, the new distance matrix integrates a new node encompassing the pair while its size is decremented
	 * from N to N-1 entries. 
	 * @param distMatrix 
	 */
	private void recursiveJoining (SquareMatrix distMatrix) {
		
		//Instantiate the matrix called Q-Matrix
		SquareMatrix QMatrix = this.computeQMatrix(distMatrix);
		
		
		/* Get the pair of nodes with the minimal value in the Q Matrix;
		 * it corresponds to the next pair of nodes to be collapsed into a cluster
		 * */
		int[] pair = QMatrix.getMinRowCol();
		
		int indexA = pair[0];
		int indexB = pair[1];
		
		Node A = nodes.get(indexA);
		Node B = nodes.get(indexB);
		
		//Create a new parent node encompassing the pair
		Node AB = new Node(A.getLabel()+"_"+B.getLabel());
		A.setParent(AB);
		B.setParent(AB);
		AB.addChild(A);
		AB.addChild(B);
		
		//save this new node
		
		/*	Insert the new node, Recalculate the distances to the new node,
		 *  and reduce the matrix from N to N-1 nodes
		 */
		double[] entry = new double [distMatrix.size()+1];
		
		for (int ni=0;ni<nodes.size();ni++) {
			if (ni == indexA || ni == indexB) {
				entry[ni] = 0;
			}else {
				entry[ni] = (distMatrix.get(indexA,ni)+distMatrix.get(indexB,ni)-distMatrix.get(indexA, indexB))/2;
			}
		}
		
		//insert the new node
		distMatrix.insertEntry(entry);
		nodes.add(AB);
		//remove the nodes A and B
		if (indexA>indexB) {
			distMatrix.removeEntry(indexA);
			distMatrix.removeEntry(indexB);
			nodes.remove(indexA);
			nodes.remove(indexB);
		}else {
			distMatrix.removeEntry(indexB);
			distMatrix.removeEntry(indexA);
			nodes.remove(indexB);
			nodes.remove(indexA);
		}
		
		if (distMatrix.size()>2) {
			//call recursively
			this.recursiveJoining(distMatrix);
		}else {
			this.buildTree();
		}
		
	}
	
	
	/**
	 * This method initiates the clustering calling the first iteration of the recursive joining.
	 */
	public void cluster () {
		this.recursiveJoining(distanceMatrix);
	}
	
	
	/**
	 * This method assign the current nodes in the nodes list as children of the root node.
	 * If the clustering has been done 
	 */
	private void buildTree() {
		//Assign the nodes the root node. 
		for(Node child:nodes) {
			NJTree.addChildToRoot(child);
		}
	}
	
	/**
	 * 
	 */
	public void printTree() {
		NJTree.print();
	}

	
	
	/**
	 * A method for calculating the QMatrix, as found in this link:
	 * <a href="https://en.wikipedia.org/wiki/Neighbor_joining">https://en.wikipedia.org/wiki/Neighbor_joining</a>  
	 * @param distMatrix a distance Matrix
	 * @return the corresponding QMatrix
	 */
	private SquareMatrix computeQMatrix(SquareMatrix distMatrix) {
		
		int N = distMatrix.size();
		
		SquareMatrix QMatrix = new SquareMatrix(N);

		double[] sumRows = new double[N];
		double[] sumCols = new double[N];
		
		double qValue;
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (i!=j) {
					
					if (sumRows[i] == 0) {
						sumRows[i] = distMatrix.getSumRow(i);
					}
					if (sumCols[j] == 0) {
						sumCols[j] = distMatrix.getSumCol(j);
					}
					
					qValue=(N-2)*distMatrix.get(i,j)-sumCols[j]-sumRows[i];
					
					QMatrix.set(i,j,qValue);
					
				}
			}
		}
		return QMatrix;
	}
	
	/**
	 * Just a method to test the implementation of the NJ method
	 * @param args
	 */
	public static void main (String[] args) {
		
		double [][] entry = {{0.0,5.0,9.0,9.0,8.0},{5.0,0.0,10.0,10.0,9},{9.0,10.0,0.0,8.0,7.0},{9.0,10.0,8.0,0.0,3.0},{8.0,9.0,7.0,3.0,0.0}};
		SquareMatrix wikiMatrix = new SquareMatrix(entry);
		wikiMatrix.setLabel("sampleA", 0);
		wikiMatrix.setLabel("sampleB", 1);
		wikiMatrix.setLabel("sampleC", 2);
		wikiMatrix.setLabel("sampleD", 3);
		wikiMatrix.setLabel("sampleE", 4);
		
		System.out.println(wikiMatrix);
		
		NeighborJoining NJcluster = new NeighborJoining(wikiMatrix);
		NJcluster.recursiveJoining(wikiMatrix);
	}

}
