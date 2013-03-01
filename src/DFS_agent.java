import java.util.ArrayList;
import java.util.LinkedList;


public class DFS_agent extends SearchAgent {

	@Override
	protected Node search(State startState) {
		//fifo queue for the frontier
		LinkedList<Node> frontier = new LinkedList<Node>();
		int nodeCounter = 0;
		int fontierSize = 0;
		
		//Create the root of the search tree
		Node root = new Node(null, startState, "TURN_ON");
		frontier.add(root);
		
		//Look while we don't find a goal and there are still nodes in the frontier
		while(!frontier.isEmpty())
		{
			Node n = frontier.pollLast();
			nodeCounter ++;
			if(frontier.size() > fontierSize)
				fontierSize = frontier.size(); 
			
			//Is this the goal we want
			if(State.isGoal(n.state, home))
			{
				System.out.println("Nodes looked at: " + nodeCounter);
				System.out.println("Max frontier size: " + fontierSize);
				return n;
			}
			
			//If its not the goal, expand the node and keep looking
			for(String move : GenerateLegalMoves(n.state))
			{
				//Get generate the next state and add the node
				State nextState = n.state.getNext(move);
				Node nextNode = new Node(n, nextState, move);
				
				if (!frontier.contains(nextNode) && nextNode.Depth() < 50)
					frontier.add(nextNode);
			}
		}
		
		//No goal was found
		System.out.println("Nodes looked at: " + nodeCounter);
		System.out.println("Max frontier size: " + fontierSize);
		return null;
	}

}
