package clustering;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for representing a Tree of nodes that have been herarchically clustered. 
 * 
 * @author Julien Jorda
 * @version 0.1
 *
 */

public class Tree {
	
	// the root node
    private Node root;
    
    //the string reprenting the branches hwn printing the tree in the console
    private static String BRANCH_CHARS = "___";

    /**
     * Constructor.
     * @param rootLabel the name of the root node
     */
    public Tree(String rootLabel) {
        root = new Node ();
        root.label = rootLabel;
        root.children = new ArrayList<Node>();
    }
    
    
    /**
     * Assign a children node to the root node.
     * @param no
     */
    public void addChildToRoot (Node no) {
    	this.root.addChild(no);
    }
    
    /**
     * A method for printing the tree in the console.
     */
    public void print() {
    	System.out.println("\n******** TREE *******");
    	printTree( root, 0);
    }
    
    
    /**
     * A recursive method printing a specific node and its branches
     * @param n
     * @param indent
     */
    private static void printTree (Node n, int indent) {
        StringBuilder ind = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            ind.append(BRANCH_CHARS);
        }
        // print the node
        System.out.println( ind + n.getLabel());

        // traverse Descendants
        for (Node child : n.getChildren()) {
            printTree( child, indent+1);
        }
    }
    
    
    /**
     *A class representing a Node in the Tree. A node has typically a parent node, 
     *and can also have children unless it lives at the extremity (a leaf).
     * 
     * @author Julien Jorda
     * @version 0.1
     *
     */
    public static class Node {
        
    	//the name of the node
    	private String label;
    	//the reference to its parent node
        private Node parent;
        // a list of children node
        private ArrayList<Node> children;
        
        /**
         * Empty Constructor
         */
        public Node() {
        	this("");
        }
        
        /**
         * Constructor. Defines a node object and its name.
         * @param l the name of the node
         */
        public Node(String l) {
        	this.label = l;
        	children = new ArrayList<Node>();
        }
        
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public Node getParent() {
			return parent;
		}
		public void setParent(Node parent) {
			this.parent = parent;
		}
		public List<Node> getChildren() {
			return children;
		}
		public void setChildren(ArrayList<Node> children) {
			this.children = children;
		}
		public void addChild(Node child) {
			this.children.add(child);
		}
        
		@Override
		public String toString () {
			return this.label;
		}
        
    }
}
