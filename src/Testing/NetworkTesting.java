package Testing;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import Network.NLayer;
import Network.NNetwork;
import Network.Neuron;

public class NetworkTesting {
	static NNetwork andNetwork;
	static NNetwork orNetwork;
	static NNetwork nandNetwork;
	static NNetwork norNetwork;
	static NNetwork binarySumNetwork;
	static NNetwork binaryToDecimalNetwork;
	static Neuron testNeuron;
	static NLayer testLayer;
	static double[] test00;
	static double[] test01;
	static double[] test11;
	static double[] test10;
	static double[] answer0;
	static double[] answer1;
	static double[][] binary;
	static double[][] testInput1;
	static double[][] testOutputAnd;
	static double[][] testOutputOr;
	static double[][] testOutputNand;
	static double[][] testOutputNor;
	static double[][] decimal;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		test00 = new double[2];
		test01 = new double[2];
		test11 = new double[2];
		test10 = new double[2];
		test00[0] = 0;
		test00[1] = 0;
		test01[0] = 0;
		test01[1] = 1;
		test11[0] = 1;
		test11[1] = 1;
		test10[0] = 1;
		test10[1] = 0;
		testInput1 = new double[4][2];
		testInput1[0] = test00;
		testInput1[1] = test01;
		testInput1[2] = test11;
		testInput1[3] = test10;
		answer0 = new double[1];
		answer1 = new double[1];
		answer0[0] = 0;
		answer1[0] = 1;
		testOutputAnd = new double[4][1];
		testOutputAnd[0] = answer0;
		testOutputAnd[1] = answer0;
		testOutputAnd[2] = answer1;
		testOutputAnd[3] = answer0;
		testOutputOr = new double[4][1];
		testOutputOr[0] = answer0;
		testOutputOr[1] = answer1;
		testOutputOr[2] = answer1;
		testOutputOr[3] = answer1;
		testOutputNand = new double[4][1];
		testOutputNand[0] = answer1;
		testOutputNand[1] = answer1;
		testOutputNand[2] = answer0;
		testOutputNand[3] = answer1;
		testOutputNor = new double[4][1];
		testOutputNor[0] = answer1;
		testOutputNor[1] = answer0;
		testOutputNor[2] = answer0;
		testOutputNor[3] = answer0;
		decimal = new double[16][16];
		decimal[0] = new double[]{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		decimal[1] = new double[]{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		decimal[2] = new double[]{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0};
		decimal[3] = new double[]{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0};
		decimal[4] = new double[]{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0};
		decimal[5] = new double[]{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		decimal[6] = new double[]{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0};
		decimal[7] = new double[]{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0};
		decimal[8] = new double[]{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0};
		decimal[9] = new double[]{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0};
		decimal[10] = new double[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0};
		decimal[11] = new double[]{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0};
		decimal[12] = new double[]{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0};
		decimal[13] = new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0};
		decimal[14] = new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0};
		decimal[15] = new double[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		binary = new double[16][4];
		binary[0] = new double[]{0,0,0,0};
		binary[1] = new double[]{0,0,0,1};
		binary[2] = new double[]{0,0,1,0};
		binary[3] = new double[]{0,0,1,1};
		binary[4] = new double[]{0,1,0,0};
		binary[5] = new double[]{0,1,0,1};
		binary[6] = new double[]{0,1,1,0};
		binary[7] = new double[]{0,1,1,1};
		binary[8] = new double[]{1,0,0,0};
		binary[9] = new double[]{1,0,0,1};
		binary[10] = new double[]{1,0,1,0};
		binary[11] = new double[]{1,0,1,1};
		binary[12] = new double[]{1,1,0,0};
		binary[13] = new double[]{1,1,0,1};
		binary[14] = new double[]{1,1,1,0};
		binary[15] = new double[]{1,1,1,1};
		andNetwork = new NNetwork(2, 1, false);
		testNeuron = new Neuron(new double[]{0, 0}, 0, 0.1);
		orNetwork = new NNetwork(new Neuron(new double[]{0, 0}, 0, 0.1));
		nandNetwork = new NNetwork(2, 1, false);
		testLayer = new NLayer();
		testLayer.add(new Neuron(new double[]{0, 0}, 0, 0.1));
		testLayer.add(new Neuron(new double[]{0, 0}, 0, 0.1));
		NLayer testLayer2 = new NLayer();
		testLayer2.add(new Neuron(new double[]{0, 0}, 0, 0.1));
		testLayer2.add(new Neuron(new double[]{0, 0}, 0, 0.1));
		norNetwork = new NNetwork(testLayer2);
		norNetwork.add(new Neuron(new double[]{0, 0}, 0, 0.1), false);
		binaryToDecimalNetwork = new NNetwork(4, 16, false);
		andNetwork.trainWithEpochs(3000, testInput1,testOutputAnd);
		orNetwork.trainWithEpochs(3000, testInput1,testOutputOr);
		nandNetwork.trainWithEpochs(3000, testInput1,testOutputNand);
		norNetwork.trainWithEpochs(3000, testInput1,testOutputNor);
		binaryToDecimalNetwork.trainWithEpochs(3000, binary, decimal);
	}
	
	@Test
	public void testNeuron() {
		assertEquals(testNeuron.inputs(), 2);
		assertTrue(Math.pow(testNeuron.getBias(), 2) < 0.01);
		assertTrue(Math.pow(testNeuron.getW()[0], 2) < 0.01);
		assertTrue(Math.pow(testNeuron.getW()[1], 2) < 0.01);
		assertTrue(Math.pow(testNeuron.getLearningRate() - 0.1, 2) < 0.01);
		assertTrue(Math.pow(testNeuron.process(test00) - 0.5, 2) < 0.01);
	}
	
	@Test
	public void testNLayer() {
		assertEquals(testLayer.getInputs(), 2);
		assertEquals(testLayer.getOutputs(), 2);
		assertTrue(Math.pow(testLayer.feed(test00)[0] - 0.5, 2) < 0.01);
		testLayer.add(testNeuron);
		assertEquals(testLayer.getInputs(), 2);
		assertEquals(testLayer.getOutputs(), 3);
		assertTrue(Math.pow(testLayer.feed(test00)[0] - 0.5, 2) < 0.01);
		assertTrue(Math.pow(testLayer.feed(test00)[1] - 0.5, 2) < 0.01);
		testLayer.add(new double[]{1, 1, 1}, 1, 0.1);
		assertEquals(testLayer.getInputs(), 2);
		assertEquals(testLayer.getOutputs(), 3);
		assertTrue(Math.pow(testLayer.feed(test00)[0] - 0.5, 2) < 0.01);
		assertTrue(Math.pow(testLayer.feed(test00)[1] - 0.5, 2) < 0.01);
		NLayer secondTestLayer = new NLayer(testLayer);
		assertEquals(secondTestLayer.getInputs(), 3);
		assertTrue(secondTestLayer.isEmpty());
		secondTestLayer.add(testNeuron);
		assertFalse(secondTestLayer.isEmpty());
	}
	
	
	@Test
	public void testAND() {
		assertEquals(andNetwork.inputs(), 2);
		assertEquals(andNetwork.outputs(), 1);
		assertTrue(andNetwork.feed(test00)[0] < 0.5);
		assertTrue(andNetwork.feed(test01)[0] < 0.5);
		assertTrue(andNetwork.feed(test11)[0] > 0.5);
		assertTrue(andNetwork.feed(test10)[0] < 0.5);
	}
	
	@Test
	public void testOR() {
		assertEquals(orNetwork.inputs(), 2);
		assertEquals(orNetwork.outputs(), 1);
		assertTrue(orNetwork.feed(test00)[0] < 0.5);
		assertTrue(orNetwork.feed(test01)[0] > 0.5);
		assertTrue(orNetwork.feed(test11)[0] > 0.5);
		assertTrue(orNetwork.feed(test10)[0] > 0.5);
	}
	
	@Test
	public void testNAND() {
		assertEquals(nandNetwork.inputs(), 2);
		assertEquals(nandNetwork.outputs(), 1);
		assertTrue(nandNetwork.feed(test00)[0] > 0.5);
		assertTrue(nandNetwork.feed(test01)[0] > 0.5);
		assertTrue(nandNetwork.feed(test11)[0] < 0.5);
		assertTrue(nandNetwork.feed(test10)[0] > 0.5);
	}
	
	@Test
	public void testNOR() {
		assertEquals(norNetwork.inputs(), 2);
		assertEquals(norNetwork.outputs(), 1);
		assertTrue(norNetwork.feed(test00)[0] > 0.5);
		assertTrue(norNetwork.feed(test01)[0] < 0.5);
		assertTrue(norNetwork.feed(test11)[0] < 0.5);
		assertTrue(norNetwork.feed(test10)[0] < 0.5);
	}
	
	@Test
	public void testBINARYTODECIMAL() {
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				if(i == j){
					assertTrue(binaryToDecimalNetwork.feed(binary[i])[j] > 0.5);
				}
				else{
					assertTrue(binaryToDecimalNetwork.feed(binary[i])[j] < 0.5);
				}
			}
		}
	}
}