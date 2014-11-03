 Project submitted by 
 1. Amol Gade, ID: 01457224
 2. Shashank Mucheli, ID: 01442857

Problem Description: The traveling salesperson problem (TSP) can be solved with the minimum-spanning-tree (MST) heuristic, which estimates the cost of completing a tour, given that a partial tour has already been constructed. The MST cost of a set of cities is the smallest sum of the link costs of any tree that connects all the cities_
a. Show how this heuristic can be derived from a relaxed version of the TSP. h. Show that the MST heuristic dominates straight-line distance.
b. Write a problem generator for instances of the TSP where cities are represented by random points in the unit square.
c. Find an efficient algorithm in the literature for constructing the MST, and use it with A graph search to solve instances of the TSP.


Solution
=========
Programmed TSP with MST heuristic using the following algorithms:
-> A* Search
-> SMA* Search
-> Anytime A* Search


Execution
==========
	1. Compile the main class i.e., TSP.java 
	2. Execute with the following arguments tsp -algo <task> -start <start_node> -input <input_file> -output <op_file> -log <op_log> -w <Weight for anytime search> -nl <node_limit for SMA*>
		task numbers: 
			1. A* Search
			2. SMA* Search
			3. Anytime A* Search