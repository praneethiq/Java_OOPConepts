import java.util.Scanner;

public class gameExecution {

	public static void main(String[] args) {
		Scanner sc1=new Scanner(System.in);
		System.out.println("Welcome to Game of Farming");
		System.out.print("Enter number of rows: ");
		int rows=sc1.nextInt();
		System.out.print("Enter number of columns: ");
		int columns=sc1.nextInt();
		Grid grid=new Grid(rows,columns);
		System.out.print("Enter number of Producers you want: ");
		int farmerCount=sc1.nextInt();
		for(int i=0;i<farmerCount;i++)
		{
			System.out.print("Press 1) Corn Farmer or 2) Radish Farmer: ");
			int farmerChoice=sc1.nextInt();
			System.out.print("Enter X axis: ");
			int farmerX=sc1.nextInt();
			System.out.print("Enter Y axis: ");
			int farmerY=sc1.nextInt();
			if(farmerChoice==1)
			
				new CornFarmer(grid,farmerX,farmerY);
			
			else if(farmerChoice==2)
				new RadishFarmer(grid,farmerX,farmerY);
		}
		System.out.print("Enter number of Consumers you want: ");
		int animalCount=sc1.nextInt();
		for(int i=0;i<animalCount;i++)
		{
			System.out.print("Press 1) Rabbit or 2) Beaver: ");
			int consumerChoice=sc1.nextInt();
			System.out.print("Enter X axis: ");
			int consumerX=sc1.nextInt();
			System.out.print("Enter Y axis: ");
			int consumerY=sc1.nextInt();
			if(consumerChoice==1)
			
				new Rabbit(grid,consumerX,consumerY);
			
			else if(consumerChoice==2)
				new Beaver(grid,consumerX,consumerY);
		}
		System.out.print("Enter number of Transporters you want: ");
		int transporterCount=sc1.nextInt();
		for(int i=0;i<transporterCount;i++)
		{
			System.out.print("Press 1) Horizontal or 2) Vertical: ");
			int transporterChoice=sc1.nextInt();
			System.out.print("Enter X axis: ");
			int transporterX=sc1.nextInt();
			System.out.print("Enter Y axis: ");
			int transporterY=sc1.nextInt();
			System.out.print("Enter Limit: ");
			int limit=sc1.nextInt();
			if(transporterChoice==1)
			
				new HorizontalTransporter(grid,transporterX,transporterY,limit);
			
			else if(transporterChoice==2)
				new VerticalTransporter(grid,transporterX,transporterY,limit);
		}
       
        Game game = new Game(grid);
 	System.out.print("Enter the number of turns you would like to run the game for: ");
	int runTime= sc1.nextInt();
        game.run(runTime);
        sc1.close();
        System.out.print("Thank you for playing");
	}

}
