package mnist;

import network.Network;
import trainset.TrainSet;

import java.io.File;
import java.io.IOException;

import static Util.Utils.indexOfHighestValue;

/**
 * This class loads the data from Mnist data set to train the network
 * Created by Luecx on 10.08.2017.
 */
public class Mnist {

    public static void main(String[] args) throws IOException {
        Network network = new Network(784, 75, 45, 10);
        TrainSet set = createTrainSet(0,4999);
        trainData(network, set, 100,  100);
        network.saveNetwork("res/network1.txt");

          //Network network = Network.loadNetwork("res/network1.txt");
        TrainSet testSet = createTrainSet(5000,9999);
        testTrainSet(network, testSet, 10);
    }

    public static TrainSet createTrainSet(int start, int end) {

        TrainSet set = new TrainSet(28 * 28, 10);

        try {

            String path = new File("").getAbsolutePath();

            MnistImageFile m = new MnistImageFile(path + "/res/trainImage.idx3-ubyte", "rw");
            MnistLabelFile l = new MnistLabelFile(path + "/res/trainLabel.idx1-ubyte", "rw");

            for(int i = start; i <= end; i++) {
                if(i % 100 ==  0){
                    System.out.println("prepared: " + i);
                }

                double[] input = new double[28 * 28];
                double[] output = new double[10];

                output[l.readLabel()] = 1d;
                for(int j = 0; j < 28*28; j++){
                    input[j] = (double)m.read() / (double)256;
                }

                set.addData(input, output);
                m.next();
                l.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

         return set;
    }

    public static void trainData(Network net,TrainSet set, int epochs,  int batch_size) {
        net.SGD(set,epochs,batch_size,0.3);
    }

    public static void testTrainSet(Network net, TrainSet set, int printSteps) {
        int count = 0;
        for (int i = 0; i < set.size(); i++) {
            double[] output = net.feetForward(set.getInput(i));
            int highest = indexOfHighestValue(output);
            int expectedHighest =  indexOfHighestValue(set.getOutput(i));

            if (highest == expectedHighest) {
                count++;
            }
            if (i % printSteps == 0) {
                System.out.println(i+ ": " + count / (double) (i + 1));
            }
        }

        System.out.println("Testing finished, RESULT: " + count + " / " + set.size()+ "  -> " + (double)count * 100/ (double)set.size() +" %");
    }
}
