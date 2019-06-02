
public class Grid extends AbstractGrid{
	
	int totalProduction=0;
	int totalConsumption=0;
    int height=0,width=0; 
	public Grid(int x,int y) {
		grid=new AbstractItem[x][y];
		stock=new int[x][y];
		height=x;
		width=y;
		
	}
	@Override
	public int getWidth() {
		
		return this.width;
	}

	@Override
	public int getHeight() {
		
		return this.height;
	}

	@Override
	public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
		
		grid[xCoordinate][yCoordinate]=item;
		
	}

	@Override
	public AbstractItem getItem(int xCoordinate, int yCoordinate) {
		return grid[xCoordinate][yCoordinate];
	}

	@Override
	public int getStockAt(int xCoordinate, int yCoordinate) {
		
		return stock[xCoordinate][yCoordinate];
		
	}

	@Override
	public void emptyStockAt(int xCoordinate, int yCoordinate) {
		setStockAt(xCoordinate,yCoordinate,0);
		
	}

	@Override
	public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		stock[xCoordinate][yCoordinate]+=nutrition;
		
	}

	@Override
	public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		stock[xCoordinate][yCoordinate]-=nutrition;
		
	}

	@Override
	public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		stock[xCoordinate][yCoordinate]=nutrition;
		
	}

	@Override
	public void processItems(TimeStep timeStep) {
		processProducers(timeStep);
		processTransporters(timeStep);
		processConsumers(timeStep);
	}

	@Override
	public void recordProduction(int nutrition) {
		totalProduction+=nutrition;
	}

	@Override
	public int getTotalProduction() {
		return totalProduction;
	}

	@Override
	public void recordConsumption(int nutrition) {
		totalConsumption+=nutrition;
		
	}

	@Override
	public int getTotalConsumption() {
		return totalConsumption;
	}
	public boolean checkProducer(int x,int y)
	{
		if(!(grid[x][y]==null||grid[x][y].toString().equals("HT")||grid[x][y].toString().equals("VT")))
		{
				if(grid[x][y].toString().substring(0,(grid[x][y].toString().indexOf("("))).equals("Corn")
						||grid[x][y].toString().substring(0,grid[x][y].toString().indexOf("(")).equals("Radish"))
		return true;
		
		else return false;
		}
		else return false;
	}
	public boolean checkConsumer(int x,int y)
	{
		if(!(grid[x][y]==null||grid[x][y].toString().equals("HT")||grid[x][y].toString().equals("VT")))
		{
				if(grid[x][y].toString().substring(0,(grid[x][y].toString().indexOf("("))).equals("Beaver")
						||grid[x][y].toString().substring(0,grid[x][y].toString().indexOf("(")).equals("Rabbit"))
		return true;
		
		else return false;
		}
		else return false;
	}
	public boolean checkTransporter(int x,int y)
	{
		if((grid[x][y]!=null)&&(grid[x][y].toString().equals("HT")||grid[x][y].toString().equals("VT")))
			
		return true;
		
		else return false;
		
	}
	public void processProducers(TimeStep timeStep)
	{     
		for(int i=0;i<getHeight();i++)
		{
			for(int j=0;j<getWidth();j++)
			{if(checkProducer(i,j))
			{
				if(grid[i][j]!=null)
				grid[i][j].process(timeStep);	
			}
			}
		}	
	}
	public void processTransporters(TimeStep timeStep)
	{     
		for(int i=0;i<getHeight();i++)
		{
			for(int j=0;j<getWidth();j++)
			{if(checkTransporter(i,j))
			{
				if(grid[i][j]!=null)
				grid[i][j].process(timeStep);	
			}
			}
		}	
	}
	public void processConsumers(TimeStep timeStep)
	{     
		for(int i=0;i<getHeight();i++)
		{
			for(int j=0;j<getWidth();j++)
			{if(checkConsumer(i,j))
			{
				if(grid[i][j]!=null)
				grid[i][j].process(timeStep);	
			}
			}
		}	
	}
}
