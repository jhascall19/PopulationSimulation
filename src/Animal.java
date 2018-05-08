/**
 *Josh Hascall
 * 9/18/17
 * Creates and sets up animals. Their size, energy (hunger/food) amount, color, and speed.
 */
import java.awt.*;
import java.util.Random;


public class Animal {
    //default parameters
    protected static final int SPEED_MAX = 6;
    public static final int SIZE_MAX = 10;
    protected static final int ENERGY_MAX = 5000;
    protected static final int SPEED_MIN = 1;
    public static final int SIZE_MIN = 1;
    protected static final int ENERGY_MIN = 1;
    protected Color c;



    protected int size;
    protected int speed;
    protected int energy;
    protected int energyMaxReletive;
    protected Point position;

    public void setPosition(Point position) {
        this.position = (Point) position.clone();
    }

    //every animal has food, size, and speed
    public Animal(int size, int speed) {
        this.size = size;
        this.speed = speed;


        switch (size) {
            case 1:
                c = (new Color(255, 0, 0));
                break;
            case 2:
                c = (new Color(0, 0, 216));
                break;
            case 3:
                c = (new Color(84, 0, 255));
                break;
            case 4:
                c = (new Color(0, 161, 255));
                break;
            case 5:
                c = (new Color(0, 255, 237));
                break;
            case 6:
                c = (new Color(0, 255, 110));
                break;
            case 7:
                c = (new Color(127, 255, 0));
                break;
            case 8:
                c = (new Color(255, 250, 0));
                break;
            case 9:
                c = (new Color(255, 170, 0));
                break;
            case 10:
                c = (new Color(200, 0, 100));
                break;
        }
        this.energyMaxReletive = (int) (ENERGY_MAX * (size * .05));
        energy = this.energyMaxReletive;

    }

    public Animal() { //all parameters are randomized... size corresponds to the color of the animal. just to look nice.
        Random random = new Random();
        this.size = random.nextInt(SIZE_MAX - SIZE_MIN + 1) + SIZE_MIN;
        this.speed = random.nextInt(SPEED_MAX - SPEED_MIN + 1) + SPEED_MIN;
        this.energyMaxReletive = (int) (ENERGY_MAX * (size * .05));
        energy = this.energyMaxReletive;
        switch (size) {
            case 1:
                c = (new Color(255, 0, 0));
                break;
            case 2:
                c = (new Color(0, 0, 216));
                break;
            case 3:
                c = (new Color(84, 0, 255));
                break;
            case 4:
                c = (new Color(0, 161, 255));
                break;
            case 5:
                c = (new Color(0, 255, 237));
                break;
            case 6:
                c = (new Color(0, 255, 110));
                break;
            case 7:
                c = (new Color(127, 255, 0));
                break;
            case 8:
                c = (new Color(255, 250, 0));
                break;
            case 9:
                c = (new Color(255, 170, 0));
                break;
            case 10:
                c = (new Color(200, 0, 100));
                break;
        }
    }


    //setters
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void useEnergy() { //will  suck away more energy based on size and speed. Like in real life
        double energyloss = 0;
        energyloss += (speed * 1.5);
        energyloss += ((double) size / 3);

        while (energyloss > 0) {
            energy--;
            energyloss--;
        }

    }

    public int getEnergyMaxReletive() {
        return energyMaxReletive;
    }

//getters

    public int getSize(){
        return this.size;
    }

    public int getEnergy(){
        return this.energy;
    }


    public static int getSpeedMax() {
        return SPEED_MAX;
    }

    public static int getSizeMax() {
        return SIZE_MAX;
    }

    public static int getSpeedMin() {
        return SPEED_MIN;
    }

    public static int getEnergyMax() {
        return ENERGY_MAX;
    }

    public static int getSizeMin() {
        return SIZE_MIN;
    }

    public static int getEnergyMin() {
        return ENERGY_MIN;
    }

    public int getSpeed() {
        return speed;
    }

    public Point getPosition() {
        return position;
    }

    public Color getC() {
        return c;
    }
}

