/*
 * TSP with MST heuristic
 * 
 * Project submitted by 
 * 1. Amol Gade, ID: 01457224
 * 2. Shashank Mucheli, ID: 01442857
 * */

import java.util.*;
class TSP{
	protected static int numNodes;
	protected static String initialState;
	protected static HashMap<String, List<Node>> graph = new HashMap<String, List<Node>>();
	public static int option;
	protected static String outputPath,outputLog;
	public static double wVal;
	public static int nLimit;
	public static long startTime = System.currentTimeMillis();
	public static void main(String[] args) {
		
		
		int i;
		

		if(args.length < 10 ){
			System.err.println("Invalid number of arguments");
			return;
		}
		
		
			for(i = 0;i < args.length;i++){
				
				if(args[i].equals("-algo")){
					option = Integer.parseInt(args[i+1]);
				}else if(args[i].equals("-start")){
					TSP.initialState = args[i+1];	
					TSP.initialState = "a";	

				}else if(args[i].equals("-input")){
					Parser p = new Parser();
					if(!(p.parseFile(args[i+1]))){
						System.err.println("Give correct Input filename");
						return;
					}
					
					
				}else if(args[i].equals("-output")){
					TSP.outputPath = args[i+1];
					
					
				}else if(args[i].equals("-log")){
					TSP.outputLog = args[i+1];

				}else if(args[i].equals("-w")){
					TSP.wVal = Double.parseDouble(args[i+1]);
				}else if(args[i].equals("-nl")){
					TSP.nLimit = Integer.parseInt(args[i+1]);
				}
			}
		
		if(option == 1){
			/*
			 * Creates a A* search object
			 * 
			 * */
			AstarMST A = new AstarMST();
			A.search();
			System.out.println("Completed A* Search with MST heuristics");

		}else if(option == 2){
			
			SMAstar SMA = new SMAstar();
			SMA.search();
			System.out.println("Completed SMA* Search");

		}else if(option == 3){
			/*
			 * Creates a Anytime A* Search object
			 * 
			 * */
			AnytimeAstar AA = new AnytimeAstar();
			AA.search();
			System.out.println("Completed Anytime A* Search");

		}
		
	}
}