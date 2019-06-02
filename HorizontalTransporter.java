

public class HorizontalTransporter extends AbstractItem{
	int capacity=0;
	int leftX=-1,leftY=-1,rightX=-1,rightY=-1;
	String leftType=null;String rightType=null;
	 int xCoordinate=0,yCoordinate=0;
	public HorizontalTransporter(Grid grid, int xCoordinate, int yCoordinate,int capacity) {
		this.grid=grid;
		grid.registerItem(xCoordinate, yCoordinate, this);
		this.xCoordinate=xCoordinate;this.yCoordinate=yCoordinate;
		this.capacity=capacity;
	}
	@Override
	public void process(TimeStep timeStep) {
          if(checkTransportStatus())
          {
        	 if(leftType.equals("Producer"))
        	 { 
        		 if(capacity<grid.getStockAt(leftX,leftY))
        		 {
        		 grid.addToStockAt(rightX, rightY, capacity);
        		 grid.reduceStockAt(leftX,leftY,capacity);
        		 
        		 }
        		 else
        		 {
        		 grid.addToStockAt(rightX, rightY,grid.getStockAt(leftX,leftY));	
        		 grid.reduceStockAt(leftX,leftY,grid.getStockAt(leftX,leftY));
        		 
        		 }
        	 }
        	 else
        	 {
        		 if(capacity<grid.getStockAt(rightX,rightY))
        		 {
        		 grid.addToStockAt(leftX, leftY, capacity);
        		 grid.reduceStockAt(rightX,rightY,capacity);
        		 
        		 }
        		 else
        		 {
        		 grid.addToStockAt(leftX, leftY,grid.getStockAt(rightX,rightY));
        		 grid.reduceStockAt(rightX,rightY,grid.getStockAt(rightX,rightY));
        		 
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
		getLeft();getRight();
		if(!(leftX==-1||leftY==-1||rightX==-1||rightY==-1))
		{
			if((leftType.equals("Producer")&&rightType.equals("Consumer"))||(leftType.equals("Consumer")&&rightType.equals("Producer")))
				return true;
			else return false;
		}
		else return false;
		
	}
	public String toString()
	{
		return "HT";
	}
    public void getLeft()
    {
    	for(int i=yCoordinate-1;i>=0;i--)
    	{
    		if(grid.grid[xCoordinate][i]!=null)
    		{
    			leftX=xCoordinate;leftY=i;
    			if(grid.grid[xCoordinate][i].toString().equals("HT")||grid.grid[xCoordinate][i].toString().equals("VT"))
    				leftType="Transporter";
    			else if(grid.grid[xCoordinate][i].toString().substring(0,grid.grid[xCoordinate][i].toString().indexOf("(")).equals("Rabbit")||
    					grid.grid[xCoordinate][i].toString().substring(0,grid.grid[xCoordinate][i].toString().indexOf("(")).equals("Beaver"))
    				leftType="Consumer";
    			else leftType="Producer";
    			break;
    		}
    	}
    }
    public void getRight()
    {
    	for(int i=yCoordinate+1;i<grid.getWidth();i++)
    	{
    		if(grid.grid[xCoordinate][i]!=null)
    		{
    			rightX=xCoordinate;rightY=i;
    			if(grid.grid[xCoordinate][i].toString().equals("HT")||grid.grid[xCoordinate][i].toString().equals("VT"))
    				rightType="Transporter";
    			else if(grid.grid[xCoordinate][i].toString().substring(0,grid.grid[xCoordinate][i].toString().indexOf("(")).equals("Rabbit")||
    					grid.grid[xCoordinate][i].toString().substring(0,grid.grid[xCoordinate][i].toString().indexOf("(")).equals("Beaver"))
    				rightType="Consumer";
    			else rightType="Producer";
    			break;
    		}
    	}
    }
    
    
}
