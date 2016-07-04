package hdm.stuttgart.csm.smarthome.object;

public class Tile {

	private double posX; 
	private double posY;
	private long capacity;
	
	/**
	 * Create a new Tile instance with posX and posY
	 * @param posX
	 * @param posY
	 */
	public Tile(double posX, double posY, long capacity){
		this.posX = posX;
		this.posY = posY;
		this.capacity = capacity;
	}
	
	/**
	 * check if a tile has weight on it
	 * @return
	 */
	public boolean isActive(){
		return (this.getCapacity() > 0);
	}
	
	/**
	 * Get the tiles x position
	 * @return
	 */
	public double getPosX(){
		return this.posX;
	}
	
	/**
	 * Get the tile's y position
	 * @return
	 */
	public double getPosY(){
		return this.posY;
	}
	
	/**
	 * Set the weight of the tile
	 * @param weight
	 */
	public void setCapacity(long capacity){
		this.capacity = capacity;
	}
	
	/**
	 * Get the tile's weight
	 * @return
	 */
	public long getCapacity(){
		return this.capacity;
	}
	
	@Override
	public boolean equals(Object obj){
		
		// check reference
		if(!(obj instanceof Tile)){
			return false;
		}
		
		if(obj == this){
			return true;
		}
		
		// check attributes
		Tile tile = (Tile) obj;
		
		if(tile.getPosX() == posX && tile.getPosY() == posY){
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString(){
		return "Tile x: " + posX + " y: " + posY;
	}
	
	@Override
	public Tile clone(){
		Tile t = new Tile(posX, posY, capacity);
		return t;
	}
}
