
public class VerticalTransporter extends AbstractItem{
	int capacity=0;
	int topX=-1,topY=-1,bottomX=-1,bottomY=-1;
	String topType=null;String bottomType=null;
	 int xCoordinate=0,yCoordinate=0;
	public VerticalTransporter(Grid grid, int xCoordinate, int yCoordinate,int capacity) {
		this.grid=grid;
		grid.registerItem(xCoordinate, yCoordinate, this);
		this.xCoordinate=xCoordinate;this.yCoordinate=yCoordinate;
		this.capacity=capacity;
	}
	@Override
	public void process(TimeStep timeStep) {
		
          if(checkTransportStatus())
          {
        	 if(topType.equals("Producer"))
        	 { 
        		 if(capacity<grid.getStockAt(topX,topY))
        		 {
        		 grid.addToStockAt(bottomX, bottomY, capacity);

        		 grid.reduceStockAt(topX,topY,capacity);
        		 }
        		 else
        		 {
        		 grid.addToStockAt(bottomX, bottomY,grid.getStockAt(topX,topY));
        		 grid.reduceStockAt(topX,topY,grid.getStockAt(topX,topY));
        		 }
        	 }
        	 else
        	 {
        		 if(capacity<grid.getStockAt(bottomX,bottomY))
        		 {
            	 grid.addToStockAt(topX, topY, capacity);
        		 grid.reduceStockAt(bottomX,bottomY,capacity);
        		 }
        		 else
        		 {
        		 grid.addToStockAt(topX, topY,grid.getStockAt(bottomX,bottomY));
        		 grid.reduceStockAt(bottomX,bottomY,grid.getStockAt(bottomX,bottomY));
        		 } 
        	 }
          }
	}
	@Override
	protected int getStock() {
		return grid.getStockAt(xCoordinate,yCoordinate);
	}
	@Override
	protected void addToStock(int nutrition) {
		grid.addToStockAt(xCoordinate,yCoordinate,nutrition);
		
	}
	@Override
	protected void reduceStock(int nutrition) {
		grid.reduceStockAt(xCoordinate,yCoordinate,nutrition);
		
	}
	public boolean checkTransportStatus()
	{
		getTop();getBottom();
		if(!(topX==-1||topY==-1||bottomX==-1||bottomY==-1))
		{
			if((topType.equals("Producer")&&bottomType.equals("Consumer"))||(topType.equals("Consumer")&&bottomType.equals("Producer")))
				return true;
			else return false;
		}
		else return false;
		
	}
	public String toString()
	{
		return "VT";
	}
    public void getTop()
    {
    	for(int i=xCoordinate-1;i>=0;i--)
    	{
    		
    		if(grid.grid[i][yCoordinate]!=null)
    		{  
    			topX=i;topY=yCoordinate;
    			if(grid.grid[i][yCoordinate].toString().equals("HT")||grid.grid[i][yCoordinate].toString().equals("VT"))
    				topType="Transporter";
    			else if(grid.grid[i][yCoordinate].toString().substring(0,grid.grid[i][yCoordinate].toString().indexOf("(")).equals("Rabbit")||
    					grid.grid[i][yCoordinate].toString().substring(0,grid.grid[i][yCoordinate].toString().indexOf("(")).equals("Beaver"))
    				topType="Consumer";
    			else topType="Producer";
    			break;
    		}
    	}
    }
    public void getBottom()
    {
    	for(int i=xCoordinate+1;i<grid.getHeight();i++)
    	{
    		if(grid.grid[i][yCoordinate]!=null)
    		{
    			bottomX=i;bottomY=yCoordinate;
    			if(grid.grid[i][yCoordinate].toString().equals("HT")||grid.grid[i][yCoordinate].toString().equals("VT"))
    				bottomType="Transporter";
    			else if(grid.grid[i][yCoordinate].toString().substring(0,grid.grid[i][yCoordinate].toString().indexOf("(")).equals("Rabbit")||
    					grid.grid[i][yCoordinate].toString().substring(0,grid.grid[i][yCoordinate].toString().indexOf("(")).equals("Beaver"))
    				bottomType="Consumer";
    			else bottomType="Producer";
    			break;
    			
    		}
    	}
    }
    
    
}
