/**
 * Josh Hascall
 * 9/18/17
 * Creates and sets up animals. Their size, energy (hunger/food) amount, color, and speed.
 */
import java.awt.*;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import java.util.Scanner;

public class JDriver extends JPanel {
    private static final int INITAL_ANIMALS = 10;
    private static Enviorment enviorment;
     public BufferedWriter writer;

    public JDriver() {
        this.enviorment = new Enviorment(INITAL_ANIMALS);

    } //sets up the enviroment

    public static int getInitalAnimals() {
        return INITAL_ANIMALS;
    }

    public void paint(Graphics g) {
        Random r = new Random();
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        enviorment.makeBabies();
        //enviorment.allCannibalBabies();

        enviorment.moveAnimals(); //every time through, move and eat all the animals.
        enviorment.moveCannibals();

        enviorment.eat();
        //enviorment.eatCannibals();
        //enviorment.Cannibal();

        enviorment.die();



        for (int i = 0; i < enviorment.getFoodRects().size(); i++) { //loops through all squares and draws each one
            Rectangle curr = enviorment.getFoodRects().get(i);

            FoodSquare f = enviorment.getFoodSquares().get(i);

            g2d.draw(curr);
            g2d.setColor(f.getColor());
            g2d.fillRect((int) curr.getX(), (int) curr.getY(), curr.width, curr.height);
            //for (int p = 0; p < enviorment.getAnimalPoints().size(); p++) {
                //if (!curr.contains(enviorment.getAnimalPoints().get(p)))
                    f.regain(); //get more food per turn regains the foodsquares
            //}

        }



        for (int i = 0; i < enviorment.getAnimals().size() ; i++) { //loops through all the animals and if theyre not dead, draw them

            int x = (int) enviorment.getAnimalPoint(i).getX();
            int y = (int) enviorment.getAnimalPoint(i).getY();
            int xSize = enviorment.getAnimal(i).getSize();
            int ySize = enviorment.getAnimal(i).getSize();

            g2d.drawOval(x, y, xSize, ySize); //they are beautiful circles
            g2d.setColor(enviorment.getAnimals().get(i).getC());
            g2d.fillOval(x, y, xSize, ySize);

        }
        if (enviorment.getCannibals().size() > 0) {
            for (int q = 0; q < enviorment.getCannibals().size(); q++) {

                int x = (int) enviorment.getCannibalPoint(q).getX();
                int y = (int) enviorment.getCannibalPoint(q).getY();
                int xSize = enviorment.getCannibals().get(q).getSize();
                int ySize = enviorment.getCannibals().get(q).getSize();

                g2d.drawOval(x, y, xSize, ySize); //they are beautiful circles
                g2d.setColor(enviorment.getCannibals().get(q).getC());
                g2d.drawString("c", x+(xSize), y+(ySize));
                g2d.fillOval(x, y, xSize, ySize);

            }
        }
        try {
            writer = new BufferedWriter(new FileWriter("popsize.txt", true));
            writer.write(""+enviorment.getAnimals().size()+"\n");
            writer.close();
        } catch (IOException ex) {
            // report
        }
//for Cannibals
        try {
            writer = new BufferedWriter(new FileWriter("cansize.txt", true));
            writer.write(""+enviorment.getCannibals().size()+"\n");
            writer.close();
        } catch (IOException ex) {
            // report
        }

    }



    public static void Analytics(){
        int counterNum1 = 0;
        int counterNum2 = 0;
        int counterNum3 = 0;
        int counterNum4 = 0;
        int counterNum5 = 0;
        int counterNum6 = 0;
        int counterNum7 = 0;
        int counterNum8 = 0;
        int counterNum9 = 0;

        int counterNumSpeed1 = 0;
        int counterNumSpeed2 = 0;
        int counterNumSpeed3 = 0;
        int counterNumSpeed4 = 0;
        int counterNumSpeed5 = 0;
        int counterNumSpeed6 = 0;
        int counterNumSpeed7 = 0;
        int counterNumSpeed8 = 0;
        int counterNumSpeed9 = 0;





        for (int i = 0; i < enviorment.getAnimals().size() ; i++) {
            if (enviorment.getAnimals().get(i).getSize() == 1){
                counterNum1++;
            }
            if (enviorment.getAnimals().get(i).getSize() == 2){
                counterNum2++;
            }
            if (enviorment.getAnimals().get(i).getSize() == 3){
                counterNum3++;
            }
            if (enviorment.getAnimals().get(i).getSize() == 4){
                counterNum4++;
            }
            if (enviorment.getAnimals().get(i).getSize() == 5){
                counterNum5++;
            }
            if (enviorment.getAnimals().get(i).getSize() == 6){
                counterNum6++;
            }
            if (enviorment.getAnimals().get(i).getSize() == 7){
                counterNum7++;
            }
            if (enviorment.getAnimals().get(i).getSize() == 8){
                counterNum8++;
            }
            if (enviorment.getAnimals().get(i).getSize() == 9){
                counterNum9++;
            }


            //speed
            if (enviorment.getAnimals().get(i).getSpeed() == 1){
                counterNumSpeed1++;
            }
            if (enviorment.getAnimals().get(i).getSpeed() == 2){
                counterNumSpeed2++;
            }
            if (enviorment.getAnimals().get(i).getSpeed() == 3){
                counterNumSpeed3++;
            }
            if (enviorment.getAnimals().get(i).getSpeed() == 4){
                counterNumSpeed4++;
            }
            if (enviorment.getAnimals().get(i).getSpeed() == 5){
                counterNumSpeed5++;
            }
            if (enviorment.getAnimals().get(i).getSpeed() == 6){
                counterNumSpeed6++;
            }
            if (enviorment.getAnimals().get(i).getSpeed() == 7){
                counterNumSpeed7++;
            }
            if (enviorment.getAnimals().get(i).getSpeed() == 8){
                counterNumSpeed8++;
            }
            if (enviorment.getAnimals().get(i).getSpeed() == 9){
                counterNumSpeed9++;
            }

        }





    }

        //System.out.println("size: " + enviorment.getAnimals().size());


    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Animals");
        JDriver jDriver = new JDriver();
        frame.add(jDriver);



        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




int counter = 0;
        while (true) {
            jDriver.repaint();
            Thread.sleep(20); //waits a bit before re-running
counter++;
            if (counter%500 == 0){
                Analytics();
            }


        }

    }
}


