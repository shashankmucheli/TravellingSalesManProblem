import java.io.*;
import java.util.*;

class Parser{


	private String FilePath;
	
	
	
	
	List<Node> value =null;
	List<Point> points = new ArrayList<Point>();
	

	public boolean parseFile(String path){


		
		String[] str = null;
		FilePath = path;
		
		try{
			
			FileReader file = new FileReader(FilePath);
			Scanner scanner = new Scanner(file);														
			
			while(scanner.hasNextLine()){

				String line = scanner.nextLine();
				str = line.split(",");
				
				
				Point p = new Point(str[0],Double.parseDouble(str[1]),Double.parseDouble(str[2]));
				
				points.add(p);
			}
			
			TSP.numNodes = points.size();
			scanner.close();
			createMap(points);
			}catch(IOException e){
				
				return false;
				
			}
		
		
		return true;
	}
	
	public void createMap(List<Point> list){
		
		double sdistance = 0;
		
		for(Point p:list){
			
			for(Point q:list){
				
				if(!p.name.equals(q.name)){
					
					sdistance = Math.sqrt(Math.pow(p.x - q.x, 2.0) + Math.pow(p.y - q.y, 2.0));
					

					Node n = new Node(q.name,sdistance);
					
					value = TSP.graph.get(p.name);
					

					if (value == null){
						value = new ArrayList<Node>();
						value.add(n);
						TSP.graph.put(p.name,value);
					}else{
						value.add(n);
					}
					}
				}
			}
		}
	}