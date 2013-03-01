import java.io.IOException;

public class Main {
	
	/**
	 * starts the game player and waits for messages from the game master <br>
	 * Command line options: [port]
	 */
	public static void main(String[] args){
		try{
			Agent agent = new AStar_agent();
			//Agent agent = new BFS_agent();
			//Agent agent = new DFS_agent();
			//Agent agent = new UCS_agent();

			int port=4001;
			if(args.length>=1){
				port=Integer.parseInt(args[0]);
			}
			GamePlayer gp=new GamePlayer(port, agent);
			gp.waitForExit();
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}
