
public class RadishFarmer extends Farmer{


	int produce=10;
	int nutritionPerProduce=1;
	int produceTime=3;

	public RadishFarmer(Grid grid, int xCoordinate, int yCoordinate) {
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
public String toString()
{
	return "Radish("+getStock()+")";
}
boolean checkLeft()
{
	if(yCoordinate==0
			||(yCoordinate>0&&(grid.grid[xCoordinate][yCoordinate-1]==null||grid.grid[xCoordinate][yCoordinate-1].toString().equals("HT")||grid.grid[xCoordinate][yCoordinate-1].toString().equals("VT"))))
		return true;
	else if((yCoordinate>1)
			&&(grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][yCoordinate-1].getClass().getSuperclass()))
		return true;
	
		
	else return false;
	
}
boolean checkRight()
{
	int y=grid.getWidth()-1;
	if(yCoordinate==y
			||(yCoordinate<y&&(grid.grid[xCoordinate][yCoordinate+1]==null||grid.grid[xCoordinate][yCoordinate+1].toString().equals("HT")||grid.grid[xCoordinate][yCoordinate+1].toString().equals("VT"))))
		
		return true;
	else if((yCoordinate<y)
			&&(grid.grid[xCoordinate][yCoordinate].getClass().getSuperclass()!=grid.grid[xCoordinate][yCoordinate+1].getClass().getSuperclass()))
		return true;
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
