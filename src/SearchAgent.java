import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;


public abstract class SearchAgent implements Agent {
	Position home;
	ArrayList<Position> walls = new ArrayList<Position>();
	int roomHeight;
	int roomWidth;
	Stack<String> path = new Stack<String>();

	public void init(Collection<String> percepts) {
		
		State startState = new State();
		
		//Gather information from percepts
		for (String percept:percepts) 
		{
			String[] args = percept.replaceFirst("^\\(", "").split("[() ]+");
			
			for(String arg : args)
			{
				System.out.print(arg);
				System.out.print(" - ");
			}
			System.out.print("\n");
			
			if(args[0].equals("HOME"))
			{
				home = new Position(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
				startState.position = home; 
			}
			else if(args[0].equals("ORIENTATION"))
			{		
				startState.direction = Direction.valueOf(args[1]);
				//startState.direction = Direction.NORTH;
			}
			else if(args[0].equals("SIZE")){
				roomWidth = Integer.parseInt(args[1]);
				roomHeight = Integer.parseInt(args[2]);
			}
			else if(args[0].equals("AT")){
				Position p = new Position(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
				if(args[1].equals("DIRT"))
					startState.dirt.add(p);
				else if(args[1].equals("OBSTACLE"))
					walls.add(p);
			}
		}
		
		
		//Search
		long startTime = System.currentTimeMillis();
		
		Node goalNode = search(startState);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Time: " + (endTime - startTime) / 1000.0);
		
		//If the goal is not null we found a path
		if(goalNode != null)
		{
			System.out.println("Path length: " + (goalNode.Depth() +1));
			//The last move will always be 'TURN_OFF' so just add it here
			path.push("TURN_OFF");
			
			//Traverse the search tree back to the root adding each move to the path
			for(Node n = goalNode; n != null; n = n.parent){
				path.push(n.move);
			}
		}
		else
			System.out.println("No path found :( ");
		
	}

	public String nextAction(Collection<String> percepts) 
	{
		if(!path.isEmpty()){
			String move = path.pop();
			System.out.println(move);
			return move;
		}
		else
			System.out.println("No stuff to do");
		
		return "";
	}
	
	abstract protected Node search(State startState);
	
	protected ArrayList<String> GenerateLegalMoves(State state)
	{
		ArrayList<String> moves = new ArrayList<String>();
		
		if(state.dirt.contains(state.position)) {
			//If we found dirt the only thing to do is to suck
			moves.add("SUCK");
			return moves;
		}

		//Maybe check if the turn is useful?
		moves.add("TURN_LEFT");
		moves.add("TURN_RIGHT");
		
		//Check if moving forward hits a wall
		Position afterMove = new Position(state.position.x, state.position.y);
		switch (state.direction){
			case NORTH:
				afterMove.y += 1;
				break;
			case EAST:
				afterMove.x += 1;
				break;
			case SOUTH:
				afterMove.y -= 1;
				break;
			case WEST:
				afterMove.x -= 1;
				break;
		}
		
		if(afterMove.x > 0 && afterMove.x <= roomWidth &&
			afterMove.y > 0 && afterMove.y <= roomHeight && 
			!walls.contains(afterMove)){
			moves.add("GO");
		}
	
		return moves;
	}
}
