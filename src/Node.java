
public class Node implements Comparable{

	public State state;		//The state at this node
	public String move;		//The move that brought us to this move
	public Node parent;		//The node above in the tree
	public int pathCost;		//The depth of the tree
	
	Node()
	{
		state = null;
		parent = null;
	}
	
	Node(Node parent, State state, String move)
	{
		this.state = state;
		this.parent = parent;
		this.move = move;
		this.pathCost = (this.parent == null) ? 1 : this.parent.pathCost + 1;
	}
	
	Node(Node parent, State state, String move, int cost)
	{
		this.state = state;
		this.parent = parent;
		this.move = move;
		this.pathCost = (this.parent == null) ? cost : this.parent.pathCost + cost;
	}

	@Override
	public int compareTo(Object obj) {
		Node n = (Node)obj;
		
		if(n.pathCost > this.pathCost)
			return -1;
		
		if(n.pathCost < this.pathCost)
			return 1;
		
		return 0;
	}
}
