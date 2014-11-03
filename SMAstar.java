import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class SMAstar {

	int count = 0;

	
	PriorityQueue<NodeInfo> FIFO = new PriorityQueue<NodeInfo>(1,new Compare() {
        @Override
        public int compare(NodeInfo o1, NodeInfo o2) {
        return Double.valueOf(o1.f).compareTo(o2.f);
        }
    });
	
	Node child;
	List<Node> childList;
	NodeInfo childData, tourcomplete;
	NodeInfo node;
	double g, h;
	List<String> checked = new ArrayList<String>();
	List<String> current = new ArrayList<String>();


	private double hcost(NodeInfo child) {
		double h = 0.0;
		checked.clear();
		current.clear();
		List<Node> pNodes;
		double min = 0.0;
		String minNode = null;
		
		for(String s: child.path){
			if(!s.equals(TSP.initialState)){
				checked.add(s);
			}
		}
		
		current.add(child.nodeName);
		checked.add(child.nodeName);
		
		
		while(checked.size() <= (TSP.numNodes - 1)){
			
			min = 99999999.99999;
			for(String s:current){
				pNodes = TSP.graph.get(s);
				for(Node p: pNodes){
					if(!checked.contains(p.name)){
						if(p.sld < min){
							min = p.sld;
							minNode = p.name;
						}
					}
					
				}
				
			}
			
			
			checked.add(minNode);
			current.add(minNode);
			h += min;
		}
		
		return h;
	}

	private void addChildren(List<Node> children, boolean last) {
		int i = 0;
		if(node.path.size() <= TSP.nLimit){
		if (last == false) {
			while (i < children.size()) {

				child = children.get(i);


				if (!(node.path.contains(child.name))) {
					

					g = child.sld + node.g;
					h = 0.0;
					
					
					childData = new NodeInfo(child.name, node.path, g,h);
					childData.path.add(node.nodeName);
					
					h = hcost(childData);
					childData.h = h;
					childData.f = g + h;
					FIFO.add(childData);
				}
				i++;
			}
		} else {

			int j = 0;
			while (j < children.size() && j <= TSP.nLimit ) {
				double gcost = 0.0;
				child = children.get(j);
				if (child.name.equals(TSP.initialState)) {
					gcost = child.sld + node.g;
					tourcomplete = new NodeInfo(child.name, node.path, gcost);
					tourcomplete.path.add(node.nodeName);
					tourcomplete.path.add(child.name);
					tourcomplete.f = tourcomplete.g + tourcomplete.h;
					break;
				}
				j++;
			}
		}
	}
}

	public void search() {

		Writer.init();

		node = new NodeInfo(TSP.initialState); 

		FIFO.add(node);
		node.h = hcost(node);
		node.f = node.g + node.h;


		while (true) {


			if (FIFO.peek() == null) {
				return;
			}

			node = FIFO.remove();
		
			if (node.path.size() <= (TSP.nLimit)) {


				Writer.log(node);

				childList = TSP.graph.get(node.nodeName);
				addChildren(childList, true);


				Writer.output(tourcomplete);
				return;
			} else {
				
				Writer.log(node);
			}

			childList = TSP.graph.get(node.nodeName);
			addChildren(childList, false);

		}

	}
}