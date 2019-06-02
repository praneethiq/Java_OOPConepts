
public class Rabbit extends AbstractItem{
	int capacity=8;
	 
	public Rabbit(Grid grid, int xCoordinate, int yCoordinate) {
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
		emptyStock();
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
	public void emptyStock()
	{
		grid.emptyStockAt(xCoordinate, yCoordinate);
	}
	public String toString()
	{
		return "Rabbit("+getStock()+")";
	}

}
