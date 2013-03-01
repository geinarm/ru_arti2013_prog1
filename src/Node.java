
public class Node implements Comparable<Node>{

	public State state;		//The state at this node
	public String move;		//The move that brought us to this move
	public Node parent;		//The node above in the tree
	public int h;
	public int g;
	
	Node()
	{
		state = null;
		parent = null;
	}
	
	Node(Node parent, State state, String move)
	{
		this(parent, state, move, 0, 1);
	}

	Node(Node parent, State state, String move, int h, int g)
	{
		this.state = state;
		this.parent = parent;
		this.move = move;
		this.g = (this.parent == null) ? g : this.parent.g + g;
		this.h = h;
	}
	
	public int Depth()
	{
		if(this.parent == null)
			return 1;
		
		return this.parent.Depth() +1;
	}

	@Override
	public int compareTo(Node n) {
		return (this.g + this.h) - (n.g + n.h);
	}

	@Override
	public boolean equals(Object obj) {
		// Nodes are considered equal if their states are equal
		Node n = (Node)obj;
		return (n!=null && n.state.equals(this.state));
	}
}
