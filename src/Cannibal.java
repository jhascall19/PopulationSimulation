import java.awt.*;
import java.util.Random;

/**
 * Created by jhascall19 on 3/1/18.
 */
public class Cannibal extends Animal {


    public int Animal4Life;




    public Cannibal(int size, int speed, int indexOfAnimal4Life){
        this.size = size;
        this.speed = speed;
this.Animal4Life = indexOfAnimal4Life;
// FIXME: 3/14/18 FIND AN ALGORITHM for color

        switch (size){
            case 1: c = (new Color(255,0,0));
                break;
            case 2: c = (new Color(0,0,216));
                break;
            case 3: c = (new Color(84,0,255));
                break;
            case 4: c = (new Color(0,161,255));
                break;
            case 5: c = (new Color(0,255,237));
                break;
            case 6: c = (new Color(0,255,110));
                break;
            case 7: c = (new Color(127,255,0));
                break;
            case 8: c = (new Color(255,250,0));
                break;
            case 9: c = (new Color(255,170,0));
                break;
            case 10: c = (new Color(200,0,100));
                break;
        }
        //this.position = (Point) position.clone();
        this.energyMaxReletive = (int)(ENERGY_MAX * (size *.05));
        energy = this.energyMaxReletive;

    }



//    public void setPosition(Point position) {
//        this.position = (Point)position.clone();
//    }





    //getters



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

    public int getAnimal4Life() {
        return Animal4Life;
    }

    public void setAnimal4Life(int animal4Life) {
        Animal4Life = animal4Life;
    }

}





