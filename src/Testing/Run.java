package Testing;

import java.io.*;
import java.util.ArrayList;

import Network.NNetwork;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 

public class Run extends ApplicationFrame{
	public Run(String appTitle, String chartTitle) throws IOException {
		super(appTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Error", "Epoch", createDataset(), PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(lineChart);
		setContentPane(chartPanel);
	}

	private DefaultCategoryDataset createDataset() throws IOException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		BufferedReader in = new BufferedReader(new FileReader("/home/dango/workspace/FNNetwork/src/Testing/data2.txt"));
		String temp;
		ArrayList<double[]> tempData = new ArrayList<double[]>();
		ArrayList<double[]> outputs = new ArrayList<double[]>();
		while((temp = in.readLine()) != null){
			String[] tempString = temp.split(" ");
			tempData.add(new double[10]);
			outputs.add(new double[3]);
			if(tempString[0].equals("A")){
				tempData.get(tempData.size() - 1)[0] = 0;
			}
			else if(tempString[0].equals("B")){
				tempData.get(tempData.size() - 1)[0] = 1;
			}
			else if(tempString[0].equals("C")){
				tempData.get(tempData.size() - 1)[0] = 2;
			}
			else if(tempString[0].equals("D")){
				tempData.get(tempData.size() - 1)[0] = 3;
			}
			else if(tempString[0].equals("E")){
				tempData.get(tempData.size() - 1)[0] = 4;
			}
			else if(tempString[0].equals("F")){
				tempData.get(tempData.size() - 1)[0] = 5;
			}
			else if(tempString[0].equals("H")){
				tempData.get(tempData.size() - 1)[0] = 6;
			}
			else{
				System.out.println(tempString[0]);
			}
			if(tempString[1].equals("X")){
				tempData.get(tempData.size() - 1)[1] = 0;
			}
			else if(tempString[1].equals("R")){
				tempData.get(tempData.size() - 1)[1] = 1;
			}
			else if(tempString[1].equals("S")){
				tempData.get(tempData.size() - 1)[1] = 2;
			}
			else if(tempString[1].equals("A")){
				tempData.get(tempData.size() - 1)[1] = 3;
			}
			else if(tempString[1].equals("H")){
				tempData.get(tempData.size() - 1)[1] = 4;
			}
			else if(tempString[1].equals("K")){
				tempData.get(tempData.size() - 1)[1] = 5;
			}
			else{
				System.out.println(tempString[1]);
			}
			if(tempString[2].equals("X")){
				tempData.get(tempData.size() - 1)[2] = 0;
			}
			else if(tempString[2].equals("O")){
				tempData.get(tempData.size() - 1)[2] = 1;
			}
			else if(tempString[2].equals("I")){
				tempData.get(tempData.size() - 1)[2] = 2;
			}
			else if(tempString[2].equals("C")){
				tempData.get(tempData.size() - 1)[2] = 3;
			}
			else{
				System.out.println(tempString[2]);
			}
			tempData.get(tempData.size() - 1)[3] = Double.parseDouble(tempString[3]);
			tempData.get(tempData.size() - 1)[4] = Double.parseDouble(tempString[4]);
			tempData.get(tempData.size() - 1)[5] = Double.parseDouble(tempString[5]);
			tempData.get(tempData.size() - 1)[6] = Double.parseDouble(tempString[6]);
			tempData.get(tempData.size() - 1)[7] = Double.parseDouble(tempString[7]);
			tempData.get(tempData.size() - 1)[8] = Double.parseDouble(tempString[8]);
			tempData.get(tempData.size() - 1)[9] = Double.parseDouble(tempString[9]);
			outputs.get(outputs.size() - 1)[0] = Double.parseDouble(tempString[10]);
			outputs.get(outputs.size() - 1)[1] = Double.parseDouble(tempString[11]);
			outputs.get(outputs.size() - 1)[2] = Double.parseDouble(tempString[12]);
		}
		double[][] data = new double[tempData.size()][10];
		double[][] expectedOutput = new double[outputs.size()][3];
		for(int i = 0; i < tempData.size(); i++){
			data[i][0] = (tempData.get(i)[0]/6);
			data[i][1] = (tempData.get(i)[1]/5);
			data[i][2] = (tempData.get(i)[2]/3);
			data[i][3] = (tempData.get(i)[3] - 1);
			data[i][4] = ((tempData.get(i)[4] - 1)/2);
			data[i][5] = ((tempData.get(i)[5] - 1)/2);
			data[i][6] = (tempData.get(i)[6] - 1);
			data[i][7] = (tempData.get(i)[7] - 1);
			data[i][8] = (tempData.get(i)[8] - 1);
			data[i][9] = (tempData.get(i)[9] - 1);
			expectedOutput[i][0] = outputs.get(i)[0]/5;
			expectedOutput[i][1] = outputs.get(i)[1]/5;
			expectedOutput[i][2] = outputs.get(i)[2]/5;
		}
		in.close();
		NNetwork solarFlareNetwork = new NNetwork(10, 10, true);
		solarFlareNetwork.addRandom(7, false);
		solarFlareNetwork.addRandom(3, false);
		int epochs = 1000;
		double[][][] results = solarFlareNetwork.trainWithEpochs(epochs, data, expectedOutput);
		int counter = 0;
		double error = 0;
		for(int i = 0; i < epochs; i++){
			for(int j = 0; j < data.length; j++){
				for(int k = 0; k < 3; k++){
					if(Math.abs((results[i][j][k]*5) - (expectedOutput[j][k]*5)) < 0.1){
						counter++;
					}
					error = Math.abs(results[i][j][k] - expectedOutput[j][k]*5) + error;
				}
			}
			dataset.addValue(counter, "Good guesses", "" + i);
			dataset.addValue(error, "Error", "" + i);
			counter = 0;
			error = 0;
		}
		return dataset;
	}

	public static void main(String[] args) throws IOException {
		Run chart = new Run("Error vs Epoch", "Change in error vs Epoch");
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
	}
}
