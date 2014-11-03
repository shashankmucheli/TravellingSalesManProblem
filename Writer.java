import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Writer {
	
	
	private static File optSoln = new File(TSP.outputPath);
	private static File logFile = new File(TSP.outputLog);
	private static FileWriter logfile = null;
	private static FileWriter Solution = null;


	protected static void init() {

		// creates the file
		try {
			optSoln.createNewFile();
			logFile.createNewFile();
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			Solution = new FileWriter(optSoln);
			logfile = new FileWriter(logFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	protected static void output(NodeInfo tourcomplete) {

		try {
			for (String s : tourcomplete.path) {
				Solution.write(" " + s + ",");
				logfile.write(s);
			}
			
			logfile.write("," + (tourcomplete.g));
			logfile.write("," + (tourcomplete.h));
			logfile.write("," + (tourcomplete.f));
			
			Solution.write("\nTotal Tour Cost:" + (tourcomplete.f) + "\n");
			if(TSP.wVal != Double.NaN){
				Solution.write("wVal:" + (TSP.wVal) + "\n");
			}
			long stopTime = System.currentTimeMillis();
			double elapsedTime = (stopTime - TSP.startTime);
			Solution.write("Total Execution Time " + elapsedTime + " milliseconds");


			Solution.close();
			logfile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	protected static void log(NodeInfo node) {

		try {
			for (String s : node.path) {
				logfile.write(s);
			}
			logfile.write(node.nodeName);
			logfile.write("," + (node.g));
			logfile.write("," + (node.h));
			logfile.write("," + (node.f));
			logfile.write("\n");
			//log.write("," + (node.path));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
