
import java.util.LinkedList;


public class BFS_agent extends SearchAgent{
	
	@Override
	protected Node search(State startState)
	{
		//fifo queue for the frontier
		LinkedList<Node> frontier = new LinkedList<Node>();
		
		int nodeCounter = 0;
		
		//Create the root of the search tree
		Node root = new Node(null, startState, "TURN_ON");
		frontier.add(root);
		
		//Look while we don't find a goal and there are still nodes in the frontier
		while(!frontier.isEmpty())
		{
			Node n = frontier.poll();
			nodeCounter ++;
			
			//Is this the goal we want
			if(State.isGoal(n.state, home))
			{
				System.out.print("Nodes looked at: ");
				System.out.println(nodeCounter);
				return n;
			}
			
			//If its not the goal, expand the node and keep looking
			for(String move : GenerateLegalMoves(n.state))
			{
				//Get generate the next state and add the node
				State nextState = n.state.getNext(move);
				if(nextState != null)
					frontier.add(new Node(n, nextState, move));
			}
		}
		
		//No goal was found
		System.out.print("Nodes looked at: ");
		System.out.println(nodeCounter);
		return null;
		
	}

}
