package drawing;

import Util.Utils;
import network.Network;

import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Panel extends JPanel implements MouseMotionListener{
    public static final int CELL_SIZE = 15;
    double[] cells = new double[28 * 28];
    Network network;
    String networkPath = "res/network1.txt";
    String test = "[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.140625, 0.375, 0.6796875, 0.4453125, 0.140625, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.078125, 0.8359375, 0.98046875, 0.98046875, 0.98046875, 0.75390625, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.125, 0.80078125, 0.98828125, 0.98046875, 0.98046875, 0.98046875, 0.3671875, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.03125, 0.55859375, 0.89453125, 0.98046875, 0.89453125, 0.921875, 0.98046875, 0.98046875, 0.3671875, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.51171875, 0.98046875, 0.98046875, 0.98046875, 0.23046875, 0.35546875, 0.98046875, 0.98046875, 0.75390625, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.375, 0.8359375, 0.98828125, 0.98828125, 0.75390625, 0.0, 0.0, 0.5, 0.98828125, 0.98828125, 0.234375, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.98828125, 0.98046875, 0.98046875, 0.94921875, 0.3203125, 0.0, 0.0, 0.49609375, 0.98046875, 0.98046875, 0.5390625, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.98828125, 0.98046875, 0.98046875, 0.734375, 0.0, 0.0, 0.0, 0.49609375, 0.98046875, 0.98046875, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.54296875, 0.98828125, 0.98046875, 0.98046875, 0.1953125, 0.0, 0.0, 0.0, 0.49609375, 0.98046875, 0.82421875, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.6171875, 0.98828125, 0.98046875, 0.671875, 0.046875, 0.0, 0.0, 0.078125, 0.80078125, 0.98046875, 0.3671875, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.98828125, 0.99609375, 0.86328125, 0.0, 0.0, 0.0, 0.0, 0.125, 0.98828125, 0.89453125, 0.23046875, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.98046875, 0.98828125, 0.859375, 0.0, 0.0, 0.0, 0.046875, 0.6171875, 0.98046875, 0.734375, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.98046875, 0.98828125, 0.859375, 0.0, 0.0, 0.0, 0.375, 0.98046875, 0.98046875, 0.734375, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.98046875, 0.98828125, 0.859375, 0.0, 0.0, 0.0, 0.375, 0.98046875, 0.98046875, 0.734375, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.25, 0.98046875, 0.98828125, 0.9375, 0.30859375, 0.0, 0.0, 0.7578125, 0.98046875, 0.98046875, 0.34765625, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.390625, 0.99609375, 0.98828125, 0.98828125, 0.83203125, 0.7578125, 0.99609375, 0.98828125, 0.4921875, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.98828125, 0.98046875, 0.98046875, 0.98046875, 0.98046875, 0.98828125, 0.96484375, 0.4296875, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.98828125, 0.98046875, 0.98046875, 0.98046875, 0.98046875, 0.98828125, 0.859375, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.12109375, 0.76953125, 0.98046875, 0.98046875, 0.98046875, 0.89453125, 0.2578125, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.27734375, 0.3671875, 0.75390625, 0.3671875, 0.23046875, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]";
    double[] ar = Utils.doubleArrayFromString(test);

    public Panel() {
        cells = ar;
        setBounds(0, 80, CELL_SIZE * 28, CELL_SIZE * 28);
        setBackground(Color.black);
        try {
            network = Network.loadNetwork(networkPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {

    }

    public void render() {
        repaint();
    }

    public int check() {
        return Utils.indexOfHighestValue(network.feetForward(cells));
    }

    public void clear() {
        cells = new double[28 * 28];
    }

    public void learn(int num) {
        double[] target = new double[10];
        target[num] = 1;
        network.feetForward(cells);
        network.backProp(target);
        network.updateWeightsAndBiases(0.3);
        try {
            network.saveNetwork(networkPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == 0) continue;
            int x = i % 28;
            int y = i / 28;
            g.setColor(new Color(255, 255, 255, (int) (cells[i] * 100)));
            g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = (e.getX() - 10) / CELL_SIZE;
        int y = (e.getY() - 100) / CELL_SIZE;
        if (x >= 0 && y >= 0 && x < 28 && y < 28) {
            int pos = y * 28 + x;
            cells[y * 28 + x] = 0.98;
            cells[pos - 1] = Math.min(cells[pos - 1] + 0.1, 1);
            cells[pos + 1] = Math.min(cells[pos + 1] + 0.1, 1);
           if (pos + 28 < 784) cells[pos + 28] = Math.min(cells[pos + 28] + 0.1, 1);
           if (pos - 28 > 0) cells[pos - 28] =  Math.min(cells[pos - 28] + 0.1, 1);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
