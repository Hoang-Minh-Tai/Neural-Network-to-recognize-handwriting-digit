package trainset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TrainSet {
    public int INPUT_SIZE;
    public int OUTPUT_SIZE;

    private ArrayList<double[][]> data = new ArrayList<>();

    public TrainSet(int INPUT_SIZE, int OUTPUT_SIZE) {
        this.INPUT_SIZE = INPUT_SIZE;
        this.OUTPUT_SIZE = OUTPUT_SIZE;
    }

    public void addData(double[] input, double[] expected) {
        data.add(new double[][]{input,expected});
    }

    public List<TrainSet> extractBatch(int batchSize) {
        List<TrainSet> list = new ArrayList<>();
        Collections .shuffle(data);
        TrainSet set = new TrainSet(INPUT_SIZE,OUTPUT_SIZE);

        for (int i = 0; i < size(); i++) {
            if (i != 0 && i % batchSize == 0) {
                list.add(set);
                set = new TrainSet(INPUT_SIZE,OUTPUT_SIZE);
            }
            set.addData(getInput(i),getOutput(i));
        }
        list.add(set);
        return list;

    }

    public int size() {
        return data.size();
    }

    public double[] getInput(int index) {
        return data.get(index)[0];
    }

    public double[] getOutput(int index) {
        return data.get(index)[1];
    }

    public ArrayList<double[][]> getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (double[][] datum : data) {
            builder.append(Arrays.toString(datum[0]));
            builder.append("----><><><>>----");
            builder.append(Arrays.toString(datum[1]));
            builder.append('\n');
        }
        return builder.toString();
    }
}
