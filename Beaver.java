
public class Beaver extends AbstractItem{
	int capacity=5;
	int storage=50;
	
	public Beaver(Grid grid, int xCoordinate, int yCoordinate) {
		this.grid=grid;
		grid.registerItem(xCoordinate, yCoordinate, this);
		this.xCoordinate=xCoordinate;this.yCoordinate=yCoordinate;
		
	}
	@Override
	public void process(TimeStep timeStep) {
		
		if(capacity<=grid.getStockAt(xCoordinate,yCoordinate))
		{
		reduceStock(capacity);
		grid.recordConsumption(capacity);
		storeFood();
		}
		else if(capacity>grid.getStockAt(xCoordinate,yCoordinate)&&grid.getStockAt(xCoordinate,yCoordinate)!=0)
		{
			
			grid.recordConsumption(grid.getStockAt(xCoordinate,yCoordinate));
			reduceStock(grid.getStockAt(xCoordinate,yCoordinate));
				
		}
		
	}
	@Override
	protected int getStock() {
		return grid.getStockAt(xCoordinate,yCoordinate);
	}
	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void reduceStock(int nutrition) {
		grid.reduceStockAt(xCoordinate,yCoordinate,nutrition);
		
	}
	public void storeFood()
	{
		if(grid.stock[xCoordinate][yCoordinate]>storage)
			grid.stock[xCoordinate][yCoordinate]=storage;
			
	}
	public String toString()
	{
		return "Beaver("+getStock()+")";
	}

}
