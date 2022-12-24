package network;

import trainset.TrainSet;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static Util.Utils.*;

/**
 * A class represents a neural network implementing stochastic gradient descent learning algorithm.
 */
public class Network {
    /**
     * Array NETWORK_SIZES stores the size of each layer in the network. For example, if the list
     * was [2, 3, 1] then it would be a three-layer network, with the first layer containing 2 neurons, the second layer 3 neurons,
     * and the third layer 1 neuron.
     * The weights and biases are created randomly from -0.3 to 0.3
     * Activations are the states of each neuron in each layer after feeding forward an input
     * Deltas are gradient descent of each biases after back propagating
     */
    public int NETWORK_NUM_LAYERS;
    public int[] NETWORK_SIZES;

    public double[][] biases;
    public double[][][] weights;
    public double[][] activations;
    public double[][] deltas;

    public Network(int... NETWORK_SIZES) {
        this.NETWORK_SIZES = NETWORK_SIZES;
        this.NETWORK_NUM_LAYERS = NETWORK_SIZES.length;

        this.biases = new double[NETWORK_NUM_LAYERS][];
        this.weights = new double[NETWORK_NUM_LAYERS][][];
        this.activations = new double[NETWORK_NUM_LAYERS][];
        this.deltas = new double[NETWORK_NUM_LAYERS][];

        for (int layer = 0; layer < NETWORK_NUM_LAYERS; layer++) {
            biases[layer] = createRandomArray(NETWORK_SIZES[layer], -0.3, 0.3);
            if (layer > 0)
                weights[layer] = createRandom2DArray(NETWORK_SIZES[layer], NETWORK_SIZES[layer - 1], -0.3, 0.3);
            activations[layer] = new double[NETWORK_SIZES[layer]];
            deltas[layer] = new double[NETWORK_SIZES[layer]];
        }
    }

    /**
     * First, the train set is random shuffled and divided into small batches of size bathSize
     * Then the network train with each minibatches.
     * The whole process is repeated epoch times.
     */
    public void SGD(TrainSet set, int epoch, int batchSize, double eta) {
        for (int i = 0; i < epoch; i++) {
            List<TrainSet> batches = set.extractBatch(batchSize);
            for (TrainSet batch : batches) updateMiniBatch(batch, eta);
            System.out.println("-------------Epoch " + i + " Completed--------------------");
        }
    }

    public void updateMiniBatch(TrainSet set, double eta) {
        for (int i = 0; i < set.size(); i++) {
            feetForward(set.getInput(i));
            backProp(set.getOutput(i));
            updateWeightsAndBiases(eta);
        }
    }


    public double[] feetForward(double[] input) {
        activations[0] = input;
        for (int layer = 1; layer < NETWORK_NUM_LAYERS; layer++) {
            for (int neuron = 0; neuron < activations[layer].length; neuron++) {
                double sum = biases[layer][neuron];
                for (int prevNeuron = 0; prevNeuron < activations[layer - 1].length; prevNeuron++) {
                    sum += activations[layer - 1][prevNeuron] * weights[layer][neuron][prevNeuron];
                }
                activations[layer][neuron] = sigmoid(sum);
            }
        }
        return activations[NETWORK_NUM_LAYERS - 1];
    }

    public void backProp(double[] target) {
        for (int neuron = 0; neuron < target.length; neuron++) {
            deltas[NETWORK_NUM_LAYERS - 1][neuron] = (activations[NETWORK_NUM_LAYERS - 1][neuron] - target[neuron])
                                                     * (activations[NETWORK_NUM_LAYERS - 1][neuron]) * (1 - activations[NETWORK_NUM_LAYERS - 1][neuron]);
        }

        for (int layer = NETWORK_NUM_LAYERS - 2; layer > 0; layer--) {
            for (int neuron = 0; neuron < deltas[layer].length; neuron++) {
                double sum = 0;
                for (int nextNeuron = 0; nextNeuron < deltas[layer + 1].length; nextNeuron++) {
                    sum += deltas[layer + 1][nextNeuron] * weights[layer + 1][nextNeuron][neuron];
                }
                deltas[layer][neuron] = sum * activations[layer][neuron] * (1 - activations[layer][neuron]);
            }
        }
    }

    public void updateWeightsAndBiases(double eta) {
        for (int layer = 1; layer < NETWORK_NUM_LAYERS; layer++) {
            for (int neuron = 0; neuron < deltas[layer].length; neuron++) {
                biases[layer][neuron] -= eta * deltas[layer][neuron];
                for (int prevNeuron = 0; prevNeuron < deltas[layer - 1].length; prevNeuron++) {
                    weights[layer][neuron][prevNeuron] -= eta * deltas[layer][neuron] * activations[layer - 1][prevNeuron];
                }
            }
        }
    }

    /**
     * Saving all weights and biases to file
     */
    public void saveNetwork(String file) throws IOException {
        FileWriter writer = new FileWriter(file);

        writer.write(Arrays.toString(NETWORK_SIZES));
        writer.write('\n');
        for (int layer = 1; layer < NETWORK_NUM_LAYERS; layer++) {
            writer.write(Arrays.toString(biases[layer]));
            writer.write('\n');
        }
        writer.write('\n');

        for (int layer = 1; layer < NETWORK_NUM_LAYERS; layer++) {
            for (int neuron = 0; neuron < NETWORK_SIZES[layer]; neuron++) {
                writer.write(Arrays.toString(weights[layer][neuron]));
                writer.write('\n');
            }
            writer.write('\n');
        }
        writer.close();
    }

    /**
     * Loading the network from file
     */
    public static Network loadNetwork(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File((file)));
        Network network = new Network(intArrayFromString(scanner.nextLine()));
        for (int layer = 1; layer < network.NETWORK_NUM_LAYERS; layer++) {
            network.biases[layer] = doubleArrayFromString(scanner.nextLine());
        }
        scanner.nextLine();
        for (int layer = 1; layer < network.NETWORK_NUM_LAYERS; layer++) {
            for (int neuron = 0; neuron < network.NETWORK_SIZES[layer]; neuron++) {
                network.weights[layer][neuron] = doubleArrayFromString(scanner.nextLine());
            }
            scanner.nextLine();
        }
        return network;
    }

}
