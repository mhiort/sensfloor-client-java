package hdm.stuttgart.csm.smarthome;

import java.net.URISyntaxException;

import org.json.JSONObject;

import hdm.stuttgart.csm.smarthome.Carpet;
import hdm.stuttgart.csm.smarthome.Tile;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * This is a small sample app to check whether we are able to connect to a socket via the java client
 * and fetch data from a mock-implementation of the SensFloor API
 * 
 * @author Smeep / TEST
 *
 */
public class App {
	
	public static final String MACHINE_NAME = "http://ec2-52-39-157-195.us-west-2.compute.amazonaws.com:8000/";
	public static Carpet carpet;

	/**
	 * Examplery Implementation of a socket listener
	 * @author Smeep
	 */
	public static class MyCarpetListener implements Emitter.Listener {

		public void call(Object... args) {
			//parse the json string
			Tile actTile = carpet.parseJSONAndSetWeight(args[0].toString());
			
			//if there has been a change, set the path and show the result
			if(actTile != null && actTile != carpet.getPath().getActTile()){
				carpet.getPath().setActTile(actTile);
				
				//show Carpet and direction
				System.out.println(carpet.getPath().getActDirection());
				carpet.show();
				
				//reset all tile weights
				carpet.clearTiles();
			}
		}
		
	}
	

	
	public static void main(String[] args) {
		//Create a new Carpet and fill it with tiles
		//important: new Carpet(height, width)!
		carpet = new Carpet(4,6);
		carpet.fillWithTiles();
		carpet.show();
		
		try {
			//connect to the machine
			Socket socky = IO.socket(MACHINE_NAME);
			socky.connect();

			// Receiving a carpet information
			socky.on("cluster", new MyCarpetListener());
			
		} catch (URISyntaxException e) {
			System.err.println("Could not connect to socket");
			e.printStackTrace();
		}
	}
}

