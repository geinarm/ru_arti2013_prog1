
public class Node {

	public State state;		//The state at this node
	public String move;		//The move that brought us to this move
	public Node parent;		//The node above in the tree
	public int depth;		//The depth of the tree
	
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
		this.depth = (this.parent == null) ? 1 : this.parent.depth + 1;
	}
}
