import java.util.*;

public class NodeInfo {
	String nodeName;
	ArrayList<String> path = new ArrayList<String>();

	double g;

	double h;
	
	double f;
	
	String parentName;
	public NodeInfo(String nodeName, ArrayList<String>path,double g){
		this.nodeName = nodeName;
		this.g = g;
		for(String s:path){
			this.path.add(s);
		}
		this.h = 0.0;
	}

	public NodeInfo(String nodeName, ArrayList<String>path,double g,double h){

		this.nodeName = nodeName;
		this.g = g;
		for(String s:path){
			this.path.add(s);
		}
		this.h = h;
	}
	public NodeInfo(String nodeName){
		this.nodeName = nodeName;
		this.g = 0.0;
		this.h = 0.0;
	}
	
	public NodeInfo(String nodeName,double g){
		this.nodeName = nodeName;
		this.g = g;
		this.h = 0.0;
		this.f= 0.0;
		
	}
	
}
