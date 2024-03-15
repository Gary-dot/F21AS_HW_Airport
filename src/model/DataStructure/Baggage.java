package model.DataStructure;


import model.DataStructure.Exceptions.*;

public class Baggage {
    int length;
    int width;
    int height;
    int volume;
    int weight;

    /**
     * The length, width, height, weight are measured in inches.
     * Please note that length > width > height.
     * The weight is measured in pounds.
     * The volume represents the product of the length, width, and height.
     *
     * @param length The length of the baggage.
     * @param width  The width of the baggage.
     * @param height The height of the baggage.
     * @param weight The weight of the baggage.
     */
    public Baggage(int length, int width, int height, int weight) throws WrongBaggageSizeFormatException {
        if (!(length >= width && width >= height))
            throw new WrongBaggageSizeFormatException();
        this.length = length;
        this.width = width;
        this.height = height;
        this.volume = this.length * this.width * this.height;
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getVolume() {
        return volume;
    }
    public String printBaggage() {
        return String.format("%-9s%d", String.format("%dx%dx%d", length, width, height), weight);
    }
    @Override
    public String toString() {
        return String.format("%dx%dx%d;%d", length, width, height, weight);
    }
}
