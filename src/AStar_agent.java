import java.util.PriorityQueue;


public class AStar_agent extends SearchAgent {

	@Override
	protected Node search(State startState) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		int nodeCounter = 0;
		
		//Create the root of the search tree
		Node root = new Node(null, startState, "TURN_ON", Evaluate(startState), 0);
		frontier.add(root);
		
		//Look while we don't find a goal and there are still nodes in the frontier
		while(!frontier.isEmpty())
		{
			Node n = frontier.poll();
			nodeCounter ++;
			System.out.println("Evaluation: " + (n.g + n.h));
			
			//Is this the goal we want
			if(State.isGoal(n.state, home))
			{
				System.out.println("Nodes looked at: " + nodeCounter);
				return n;
			}
			
			//If its not the goal, expand the node and keep looking
			for(String move : GenerateLegalMoves(n.state))
			{
				//Get generate the next state and add the node
				State nextState = n.state.getNext(move);
				int h = Evaluate(nextState);
				int g = 1;
				
				Node nextNode = new Node(n, nextState, move, h, g);
				
				if (!frontier.contains(nextNode))
					frontier.add(nextNode);
			}
		}
		
		//No goal was found
		System.out.println("Nodes looked at: " + nodeCounter);
		return null;
		
	}
	
	private int Evaluate(State state)
	{
		int val = 0;
		if(!state.dirt.isEmpty())
		{
			int maxDistance = 0;
			for(Position p : state.dirt)
			{
				int dist = Math.abs(p.x - state.position.x) + Math.abs(p.y - state.position.y);
				dist += Math.abs(p.x - home.x) + Math.abs(p.y - home.y);
				
				if(dist > maxDistance)
					maxDistance = dist;
			}
			
			val = maxDistance + state.dirt.size();
		}
		else
			val = Math.abs(home.x - state.position.x) + Math.abs(home.y - state.position.y);
		
		
		return val;
	}

}
