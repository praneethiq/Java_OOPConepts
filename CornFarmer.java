
public class CornFarmer extends Farmer{
	int produce=5;
	int nutritionPerProduce=5;
	int produceTime=4;
  
	public CornFarmer(Grid grid, int xCoordinate, int yCoordinate) {
		this.grid=grid;
		
		grid.registerItem(xCoordinate, yCoordinate, this);
		this.xCoordinate=xCoordinate;this.yCoordinate=yCoordinate;
		
	}

	@Override
	public void process(TimeStep timeStep) {
	
		if((timeStep.getValue()%produceTime==0)&&checkAll())
		{   
			addToStock(produce*nutritionPerProduce);
			grid.recordProduction(produce*nutritionPerProduce);
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString()
	{  
		return "Corn("+getStock()+")";
	}
   boolean checkLeft()
	{
		if(yCoordinate==0
				||(yCoordinate==1&&(grid.grid[xCoordinate][0]==null||grid.grid[xCoordinate][0].toString().equals("HT")||grid.grid[xCoordinate][0].toString().equals("VT")))
				||(yCoordinate>1&&(grid.grid[xCoordinate][yCoordinate-1]==null||grid.grid[xCoordinate][yCoordinate-1].toString().equals("HT")||grid.grid[xCoordinate][yCoordinate-1].toString().equals("VT"))&&(grid.grid[xCoordinate][yCoordinate-2]==null||grid.grid[xCoordinate][yCoordinate-2].toString().equals("HT")||grid.grid[xCoordinate][yCoordinate-2].toString().equals("VT"))))
			return true;
		else if((yCoordinate==1)
				&&(grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][0].getClass().getSuperclass()))
			return true;
		else if (yCoordinate>1 && (grid.grid[xCoordinate][yCoordinate-1]==null||grid.grid[xCoordinate][yCoordinate-1].toString().equals("HT")||grid.grid[xCoordinate][yCoordinate-1].toString().equals("VT")))
		return ( grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][yCoordinate-2].getClass().getSuperclass());
	              
		else if(yCoordinate>1 && (grid.grid[xCoordinate][yCoordinate-2]==null||grid.grid[xCoordinate][yCoordinate-2].toString().equals("HT")||grid.grid[xCoordinate][yCoordinate-2].toString().equals("VT")))
				return ( grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][yCoordinate-1].getClass().getSuperclass());
		else if (yCoordinate>1 &&grid.grid[xCoordinate][yCoordinate-1]!=null&&grid.grid[xCoordinate][yCoordinate-2]!=null)  
			return (grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][yCoordinate-1].getClass().getSuperclass()
				&&grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][yCoordinate-2].getClass().getSuperclass());
			
			
		else return false;
		
	}
	boolean checkRight()
	{
		int y=grid.getWidth()-1;
		if(yCoordinate==y
				||(yCoordinate==y-1&&(grid.grid[xCoordinate][y]==null||grid.grid[xCoordinate][y].toString().equals("HT")||grid.grid[xCoordinate][y].toString().equals("VT")))
				||(yCoordinate<y-1&&(grid.grid[xCoordinate][yCoordinate+1]==null||grid.grid[xCoordinate][yCoordinate+1].toString().equals("HT")||grid.grid[xCoordinate][yCoordinate+1].toString().equals("VT"))&&(grid.grid[xCoordinate][yCoordinate+2]==null||grid.grid[xCoordinate][yCoordinate+2].toString().equals("HT")||grid.grid[xCoordinate][yCoordinate+2].toString().equals("VT"))))
			
			return true;
		else if((yCoordinate==y-1)
				&&(grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][y].getClass().getSuperclass()))
			return true;
		else if(yCoordinate<y-1&&(grid.grid[xCoordinate][yCoordinate+1]==null||grid.grid[xCoordinate][yCoordinate+1].toString().equals("HT")||grid.grid[xCoordinate][yCoordinate+1].toString().equals("VT")))
			return (grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][yCoordinate+2].getClass().getSuperclass());	
			else if(yCoordinate<y-1&&(grid.grid[xCoordinate][yCoordinate+2]==null||grid.grid[xCoordinate][yCoordinate+2].toString().equals("HT")||grid.grid[xCoordinate][yCoordinate+2].toString().equals("VT")))		
		return (grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][yCoordinate+1].getClass().getSuperclass());
		else if (yCoordinate<y-1&&grid.grid[xCoordinate][yCoordinate+1]!=null&&grid.grid[xCoordinate][yCoordinate+2]!=null)
			
			return (grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][yCoordinate+2].getClass().getSuperclass()&&grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][yCoordinate+2].getClass().getSuperclass());
		else return false;
		
	}
	 boolean checkUp()
	{
		if(xCoordinate==0||(xCoordinate>0&&(grid.grid[xCoordinate-1][yCoordinate]==null||grid.grid[xCoordinate-1][yCoordinate].toString().equals("HT")||grid.grid[xCoordinate-1][yCoordinate].toString().equals("VT"))))
			return true;
		else if(xCoordinate>0)
				return (grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate-1][yCoordinate].getClass().getSuperclass());
	    else return false;
		
	}
	 boolean checkDown()
		{
		 int x=grid.getHeight()-1;
			if(xCoordinate==x||(xCoordinate<x &&(grid.grid[xCoordinate+1][yCoordinate]==null||grid.grid[xCoordinate+1][yCoordinate].toString().equals("HT")||grid.grid[xCoordinate+1][yCoordinate].toString().equals("VT"))))
				return true;
			else if(xCoordinate<x)
				return (grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate+1][yCoordinate].getClass().getSuperclass());
		
			else return false;
			
		}
	 boolean checkAll()
	 {
	    return (checkLeft()&&checkRight()&&checkUp()&&checkDown());
						
	 }
}
