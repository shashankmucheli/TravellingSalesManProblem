import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class AnytimeAstar {

	int count = 0;
	
	
	PriorityQueue<NodeInfo> FIFO = new PriorityQueue<NodeInfo>(1,new Compare() {
        @Override
        public int compare(NodeInfo o1, NodeInfo o2) {
        return Double.valueOf(o1.f).compareTo(o2.f);
        }
    });
	
	Node child;
	List<Node> childList;
	NodeInfo childData, endTour;
	NodeInfo node;
	double gcost, hcost;
	List<String> explored = new ArrayList<String>();
	List<String> current = new ArrayList<String>();

	
	private double mheuristicCost(NodeInfo child) {
		double h = 0.0;
		explored.clear();
		current.clear();
		List<Node> pNodes;
		double min = 0.0;
		String minNode = null;
		
		for(String s: child.path){
			if(!s.equals(TSP.initialState)){
				explored.add(s);
			}
		}
		
		current.add(child.nodeName);
		explored.add(child.nodeName);
		
		
		while(explored.size() <= (TSP.numNodes - 1)){
			
			min = 99999999.99999;
			for(String s:current){
				pNodes = TSP.graph.get(s);
				for(Node p: pNodes){
					if(!explored.contains(p.name)){
						if(p.sld < min){
							min = p.sld;
							minNode = p.name;
						}
					}
					
				}
				
			}
			
			
			explored.add(minNode);
			current.add(minNode);
			h += min;
		}
		
		return h;
	}

	private void addChildren(List<Node> children, boolean last) {
		int i = 0;
		if (last == false) {
			while (i < children.size()) {

				child = children.get(i);


				if (!(node.path.contains(child.name))) {
					

					gcost = child.sld + node.g;
					hcost = 0.0;

					
					childData = new NodeInfo(child.name, node.path, gcost,hcost);
					childData.path.add(node.nodeName);
					
					hcost = mheuristicCost(childData);
					childData.h = hcost;
					childData.f = (((1-TSP.wVal)*gcost) + (TSP.wVal*hcost));
					FIFO.add(childData);
				}
				i++;
			}
		} else {

			int j = 0;
			while (j < children.size()) {
				double gcost = 0.0;
				child = children.get(j);
				if (child.name.equals(TSP.initialState)) {
					gcost = child.sld + node.g;
					endTour = new NodeInfo(child.name, node.path, gcost);
					endTour.path.add(node.nodeName);
					endTour.path.add(child.name);
					endTour.f = endTour.g + endTour.h;
					break;
				}
				j++;
			}
		}
	}

	public void search() {

		Writer.init();
		node = new NodeInfo(TSP.initialState); 

		FIFO.add(node);
		node.h = mheuristicCost(node);
		node.f = (((1-TSP.wVal)*node.g) + (TSP.wVal*node.h));


		while (true) {


			if (FIFO.peek() == null) {
				return;
			}
			node = FIFO.remove();
		
			if (node.path.size() == (TSP.numNodes - 1)) {

				Writer.log(node);

				childList = TSP.graph.get(node.nodeName);
				addChildren(childList, true);


				Writer.output(endTour);
				return;
			} else {
				
				Writer.log(node);
			}

			childList = TSP.graph.get(node.nodeName);
			addChildren(childList, false);

		}

	}
}