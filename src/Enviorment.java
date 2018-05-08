import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Josh Hascall
 * 9/18/17
 * The place where the squares are created and assigned to a foodsquare. Where animals learn how to move and eat. Sets up and initializes animals and points. Also has move with purpose (MWP)
 */
import java.applet.*;
public class Enviorment extends Applet {
    public static final int WIDTH =  1000; // must be a square
    public static final int HEIGHT = 1000;
    private static final int NUM_FOODSQUARES_PER_ROW = 10; //because its a square, will be the square of this for total
    private static final double PROBABILITY = .8; //probility that the animal will MWP if hungry.

    private ArrayList<Animal> animals;
    private ArrayList<Cannibal> cannibals;
    private ArrayList<Point> cannibalPoints;
    private ArrayList<Point> animalPoints;
    private ArrayList<Color> colors;

    private ArrayList<FoodSquare> foodSquares;
    private ArrayList<Rectangle> foodRects;

    private int squareWidth;
    private int squareHeight;
// FIXME: 3/14/18 FIX THE SPACING OF EVERYTHING

    public ArrayList<Rectangle> getFoodRects() {
        return foodRects;
    }

    public Enviorment(int numAnimal){
        Random r = new Random();
        animals = new ArrayList<>();
        animalPoints = new ArrayList<>();
        colors = new ArrayList<>();
        foodRects = new ArrayList<>();
        foodSquares = new ArrayList<>();
        cannibals = new ArrayList<>();
        cannibalPoints = new ArrayList<>();



        this.squareHeight = getHEIGHT()/NUM_FOODSQUARES_PER_ROW;
        this.squareWidth = getWIDTH()/NUM_FOODSQUARES_PER_ROW;

        for (int i = 0; i < numAnimal; i++){

            animals.add(new Animal());
            animals.get(i).setPosition(new Point(r.nextInt(WIDTH)+1, r.nextInt(HEIGHT)+1 ));
            animalPoints.add(animals.get(i).getPosition());

        }
        setSquares();


    }

    public void moveAnimals() {
        Random r = new Random();


        for (int p = 0; p < animals.size(); p++) { //loops through every animal in the array, will update location then compare to EVERY SQUARE

            int desperation = animals.get(p).getEnergy();
            double probility = r.nextDouble();



                if (desperation < (animals.get(p).getEnergyMaxReletive() * .75)) {
                    probility = r.nextDouble();
                }
                if (probility > PROBABILITY) {

                    //System.out.println(animals.get(p).getEnergyMaxReletive() + " " + animals.get(p).getSize());
                    Point purpose = moveWithPurpose(animals.get(p), animalPoints.get(p));

                    double purposeX = purpose.getX();
                    double purposeY = purpose.getY();
                    if (purposeX > WIDTH || purposeX < 0) {
                        //loops around to the left
                        purposeX = 0;
                    }
                    if (purposeY > HEIGHT || purposeY < 0) {
                        purposeY = 0;
                    }
                    animalPoints.get(p).setLocation((int) purposeX, (int) purposeY); //sets location for the purpose

                    double energyloss = 0;
                    energyloss += (animals.get(p).getSpeed()*2);
                    energyloss += (animals.get(p).getSize()/2);

                    while (energyloss > 0){
                        animals.get(p).setEnergy(animals.get(p).getEnergy()-1);
                        energyloss--;
                    }

                }

                else {


                    int oldX = (int) animalPoints.get(p).getX();
                    int oldY = (int) animalPoints.get(p).getY();

                    int scalex = r.nextInt(2 + 1) - 1;
                    int scaley = r.nextInt(2 + 1) - 1;

                    int newX = oldX + scalex * (animals.get(p).getSpeed());
                    int newY = oldY + scaley * (animals.get(p).getSpeed());


                    if (newX >= WIDTH || newX < 0) {
                        //loops around to the left
                        newX = WIDTH;
                    }
                    if (newY >= HEIGHT || newY < 0) {
                        newY = HEIGHT;
                    }

                    double energyloss = 0;
                    energyloss += (animals.get(p).getSpeed()*2);
                    energyloss += (animals.get(p).getSize()/2);

                    while (energyloss > 0){
                        animals.get(p).setEnergy(animals.get(p).getEnergy()-1);
                        energyloss--;
                    }

                    animalPoints.get(p).setLocation((int) newX, (int) newY); //sets location not for purpose, random


                }
            }
        }







public void eat(){// gives food based on size
    for (int p = 0; p < animals.size(); p++){
            int i = locateFoodSquare((int)(animalPoints.get(p).getX()), (int)(animalPoints.get(p).getY()) );
// FIXME: 3/14/18 SAME AS EARLIER
                int eatBonus = 35;
                int size = animals.get(p).getSize();
                eatBonus += size;

        if (i < foodSquares.size()) {
            double newAmt = foodSquares.get(i).getFoodAmt() - (eatBonus); //makes them use less food

            if (newAmt >= foodSquares.get(i).getMinFood()) {

                if (eatBonus > foodSquares.get(i).getFoodAmt()) {

                    animals.get(p).setEnergy(animals.get(p).getEnergy()+ (int)foodSquares.get(i).getFoodAmt());
                    foodSquares.get(i).setFoodAmt(foodSquares.get(i).getMinFood());
                    foodSquares.get(i).setColor();
                } else {

                    animals.get(p).setEnergy(animals.get(p).getEnergy() + (eatBonus));
                    foodSquares.get(i).setFoodAmt(newAmt);
                    foodSquares.get(i).setColor();
                }
            }
        }

        double energyloss = 0;
        energyloss += (animals.get(p).getSpeed()*1.5);
        energyloss += (animals.get(p).getSize()/3);

        while (energyloss > 0){
            animals.get(p).setEnergy(animals.get(p).getEnergy()-1);
            energyloss--;
        }

            }

        }


private Point moveWithPurpose(Animal a, Point point) { // looks for all the squares and finds the one with the most food and goes to it.
    Point p = point;
    int location = locateFoodSquare((int) p.getX(), (int) p.getY());

    int indexOfFoodRight = locateFoodSquare((int) p.getX() + squareWidth, (int) p.getY());
    int indexOfFoodLeft = locateFoodSquare((int) p.getX() - squareWidth, (int) p.getY());
    int indexOfFoodUp = locateFoodSquare((int) p.getX(), (int) p.getY() + squareHeight);
    int indexOfFoodDown = locateFoodSquare((int) p.getX(), (int) p.getY() - squareHeight);
    int indexOfFoodUpLeft = locateFoodSquare((int) p.getX() - squareWidth, (int) p.getY() + squareHeight);
    int indexOfFoodUpRight = locateFoodSquare((int) p.getX() + squareWidth, (int) p.getY() + squareHeight);
    int indexOfFoodDownLeft = locateFoodSquare((int) p.getX() - squareWidth, (int) p.getY() - squareHeight);
    int indexOfFoodDownRight = locateFoodSquare((int) p.getX() + squareWidth, (int) p.getY() + squareHeight);

    ArrayList<Integer> storage = new ArrayList(); //holds all squares adjacent
    storage.add(location);
    storage.add(indexOfFoodRight);
    storage.add(indexOfFoodLeft);
    storage.add(indexOfFoodUp);
    storage.add(indexOfFoodUpLeft);
    storage.add(indexOfFoodUpRight);
    storage.add(indexOfFoodDown);
    storage.add(indexOfFoodDownLeft);
    storage.add(indexOfFoodDownRight);
    double max = FoodSquare.getMinFood(); //
    int bestSquare = 0;
    for (int j = 0; j < storage.size(); j++) { //finds best square
        if (storage.get(j) < foodSquares.size()) {



                if (getFoodSquares().get(storage.get(j)).getFoodAmt() > max) {
                    max = getFoodSquares().get(storage.get(j)).getFoodAmt();
                    bestSquare = storage.get(j);
                    if (bestSquare >= NUM_FOODSQUARES_PER_ROW*NUM_FOODSQUARES_PER_ROW){
                        bestSquare = storage.get(0); //if its too big, dont go
                    }
                }
        }
    }

        int moveX = (int) getFoodRects().get(bestSquare).getX();

        int moveY = (int) getFoodRects().get(bestSquare).getY();
        int diffX = moveX - (int) point.getX()+1; //fixed order of x
        int diffY = moveY - (int) point.getY()+1 ;



        if (diffX < 0 && Math.abs(diffX) > a.getSpeed()) {
            diffX =   -1 * a.getSpeed(); //should be -1 *
        } else if (diffX > 0 && Math.abs(diffX) > a.getSpeed()) {
            diffX =  a.getSpeed();
        }

        if (diffY < 0 && Math.abs(diffY) > a.getSpeed()) {
            diffY =   -1 * a.getSpeed(); //should be -1 *
        } else if (diffY > 0 && Math.abs(diffY) > a.getSpeed()) {
            diffY =  a.getSpeed(); //-1 should be up
        }

    double energyloss = 0;
    energyloss += (a.getSpeed()*2);
    energyloss += (a.getSize()/2);

    while (energyloss > 0){
        a.setEnergy(a.getEnergy()-1);
        energyloss--;
    }

//        a.useEnergy();

    if(foodSquares.get(locateFoodSquare((int) p.getX(), (int) p.getY())).getFoodAmt() >= FoodSquare.MAX_FOOD*.25){
        return p;
    }else {
        return new Point(diffX + (int) p.getX(), diffY + (int) p.getY()); //you need to add the animals x and y to diffx and diffy
    }

}



    private void setSquares(){

        //we build the array here with a correct double loop.
        for (int row = 0; row < getWIDTH()/squareWidth; row++ ){
            for (int cols = 0; cols < getHEIGHT()/this.squareHeight; cols++){
                foodSquares.add(new FoodSquare(FoodSquare.MAX_FOOD));
                Rectangle r = new Rectangle(cols*squareWidth, row*squareHeight, squareWidth, squareHeight);
                foodRects.add(r);
            }

        }


            }

            //to return the integer index that the animal is inside
            public int locateFoodSquare(int x, int y){


              int colPos =  (x-1)/squareWidth; //x = 50, y = 50, squareWidth = 10 -> col pos = 5 -1
                int rowPos = (y-1)/squareWidth;
                if(colPos < 0){
                    colPos = 0;
                }
                if (rowPos < 0){
                    rowPos = 0;
                }
                if (colPos+ (NUM_FOODSQUARES_PER_ROW*rowPos) >= NUM_FOODSQUARES_PER_ROW*NUM_FOODSQUARES_PER_ROW){
                    //System.out.println("AHHHHHH");
                    return 1;

                }

                return colPos + (NUM_FOODSQUARES_PER_ROW * rowPos);

            }


    public void makeBabies() { //makes babies but it doesn't work. theres good stuff here though. When it does, favorable traits will avg. out
              Animal potentialParent;
              for (int i = 0; i < animals.size() ; i++) {
                   potentialParent = animals.get(i);

              Random r = new Random();


              if (potentialParent.getEnergy() >= potentialParent.getEnergyMaxReletive() * .8) {
                  double rand = r.nextDouble();
                  if (rand > .97) {


                      int mutateSpeed;
                      int mutateSize;
                      double probSpeed = r.nextDouble();
                      if (probSpeed <= .33) {
                          if (potentialParent.getSpeed() == potentialParent.getSpeedMin()) {

                              mutateSpeed = potentialParent.getSpeedMin();

                          }else {
                              mutateSpeed = potentialParent.getSpeed() - 1;
                          }
                      } else if (probSpeed > .33 && probSpeed <= .66) {
                          mutateSpeed = potentialParent.getSpeed();
                      } else {
                          if (potentialParent.getSpeed() == potentialParent.getSpeedMax()){
                              mutateSpeed = potentialParent.getSpeedMax();
                          }else {
                              mutateSpeed = potentialParent.getSpeed() + 1;
                          }
                      }

                      double probSize = r.nextDouble();

                      if (probSize <= .33) {
                          if (potentialParent.getSize() == potentialParent.getSizeMin()){
                              mutateSize = potentialParent.getSizeMin();
                          }else {
                              mutateSize = potentialParent.getSize() - 1;
                          }
                      } else if (probSize > .33 && probSize <= .66) {
                          mutateSize = potentialParent.getSize();
                      } else {
                          if (potentialParent.getSize() == potentialParent.getSizeMax()){
                              mutateSize = potentialParent.getSizeMax();
                          }else {
                              mutateSize = potentialParent.getSize() + 1;
                          }
                      }
                      potentialParent.useEnergy();

                      double oddsOfCanni = r.nextDouble();
                      if (oddsOfCanni > .998){
                          if (cannibals.size() <= 10) {
                              Cannibal baby;
                              animals.get(i).useEnergy();
                              baby = new Cannibal(mutateSize, mutateSpeed, r.nextInt(animals.size()));
                              baby.setPosition(new Point((int) potentialParent.getPosition().getX(), (int) potentialParent.getPosition().getY()));
                              baby.setEnergy((int) (baby.getEnergyMaxReletive() * .65));
                              cannibals.add(baby);
                              cannibalPoints.add(baby.getPosition());
                          }
                      }else{

                          Animal baby;
                      baby = new Animal(mutateSize, mutateSpeed);
                      baby.setPosition(new Point((int)potentialParent.getPosition().getX(), (int)potentialParent.getPosition().getY()));
                      baby.setEnergy((int)(baby.getEnergyMaxReletive()*.65));


                          animals.add(baby);
                          animalPoints.add(baby.getPosition());
                      }





                  }
              }
              }
          }

//CANNIBAL STUFF


          public void moveCannibals(){
              Random r = new Random();
              for (int p = 0; p < cannibals.size(); p++) {
                  Point preyLocation;
                  if (cannibals.get(p).getAnimal4Life() >= animals.size()) {
                      System.out.println("CHANGED FROM: " + cannibals.get(p).getAnimal4Life() + " ");
                      int indexofAnimal4Life = choosePrey(cannibals.get(p));
                      cannibals.get(p).setAnimal4Life(indexofAnimal4Life);
                      preyLocation = animalPoints.get(cannibals.get(p).getAnimal4Life());
                      System.out.println("TO: " + cannibals.get(p).getAnimal4Life());
                  } else {
                      preyLocation = animalPoints.get(cannibals.get(p).getAnimal4Life());
                  }

                  if (cannibalPoints.get(p).distance(preyLocation) <= 15 ) {
                      //System.out.println(animalPoints.get(cannibals.get(p).getAnimal4Life()));
                      cannibalism(cannibals.get(p), animals.get(cannibals.get(p).getAnimal4Life()));
                  } else {

                      double purposeX = preyLocation.getX();
                      double purposeY = preyLocation.getY();


                      double diffX = purposeX - (int) cannibalPoints.get(p).getX() + 1; //fixed order of x
                      double diffY = purposeY - (int) cannibalPoints.get(p).getY() + 1;


                      //System.out.println(cannibals.get(p).getSpeed());


                      if (diffX < 0 && Math.abs(diffX) > cannibals.get(p).getSpeed()) {
                          diffX = -1 * cannibals.get(p).getSpeed(); //should be -1 *
                      } else if (diffX > 0 && Math.abs(diffX) > cannibals.get(p).getSpeed()) {
                          diffX = cannibals.get(p).getSpeed();
                      }

                      if (diffY < 0 && Math.abs(diffY) > cannibals.get(p).getSpeed()) {
                          diffY = -1 * cannibals.get(p).getSpeed(); //should be -1 *
                      } else if (diffY > 0 && Math.abs(diffY) > cannibals.get(p).getSpeed()) {
                          diffY = cannibals.get(p).getSpeed(); //-1 should be up

                      }
                      double moveamtX = (diffX + (int) cannibalPoints.get(p).getX());
                      double moveamtY = (diffY + (int) cannibalPoints.get(p).getY());

                      if (moveamtX > WIDTH || moveamtX < 0) {
                          //loops around to the left
                          moveamtX = 0;
                      }
                      if (moveamtY > HEIGHT || moveamtY < 0) {
                          moveamtY = 0;
                      }
                      cannibals.get(p).setPosition(new Point((int) moveamtX, (int) moveamtY));
                      cannibalPoints.get(p).setLocation((int) moveamtX, (int) moveamtY); //sets location for the purpose

                      double energyloss = 0;
                      energyloss += (cannibals.get(p).getSpeed() );
                      energyloss += (cannibals.get(p).getSize() ); //alter this to get better cannis

                      while (energyloss > 0) {
                          cannibals.get(p).setEnergy(cannibals.get(p).getEnergy() - 1);
                          energyloss--;
                      }

                  }
              }


              }





public void cannibalism(Cannibal cannibal, Animal animal){
    cannibal.setEnergy((int) (cannibal.getEnergy() + (animal.energy))); //big boys get more food
    animal.setEnergy(animal.getEnergyMin());
    animals.remove(animal);
    animalPoints.remove(cannibal.getAnimal4Life());

    System.out.println("REMOVED");
    //System.out.println(cannibal.getPosition() + "  This is the pos");
    cannibalBabies(cannibal);

    //added more for cannibal advantage
    cannibalBabies(cannibal);
    cannibalBabies(cannibal);
    cannibalBabies(cannibal);
}

public void cannibalBabies(Cannibal cannibal){
    Random r = new Random();
    double odds = r.nextDouble();
    Cannibal potentialParent = cannibal;

    int mutateSpeed;
    int mutateSize;
    double probSpeed = r.nextDouble();

    if (probSpeed <= .33) {
        if (potentialParent.getSpeed() == potentialParent.getSpeedMin()) {

            mutateSpeed = potentialParent.getSpeedMin();

        } else {
            mutateSpeed = potentialParent.getSpeed() - 1;
        }
    } else if (probSpeed > .33 && probSpeed <= .66) {
        mutateSpeed = potentialParent.getSpeed();
    } else {
        if (potentialParent.getSpeed() == potentialParent.getSpeedMax()) {
            mutateSpeed = potentialParent.getSpeedMax();
        } else {
            mutateSpeed = potentialParent.getSpeed() + 1;
        }
    }

    double probSize = r.nextDouble();

    if (probSize <= .33) {
        if (potentialParent.getSize() == potentialParent.getSizeMin()) {
            mutateSize = potentialParent.getSizeMin();
        } else {
            mutateSize = potentialParent.getSize() - 1;
        }
    } else if (probSize > .33 && probSize <= .66) {
        mutateSize = potentialParent.getSize();
    } else {
        if (potentialParent.getSize() == potentialParent.getSizeMax()) {
            mutateSize = potentialParent.getSizeMax();
        } else {
            mutateSize = potentialParent.getSize() + 1;
        }
    }

            Cannibal baby;
            baby = new Cannibal(mutateSize, mutateSpeed, r.nextInt(animals.size())); // FIXME: 3/14/18 do the mutations
            baby.setPosition(new Point((int) cannibal.getPosition().getX(), (int) cannibal.getPosition().getY()));
            cannibalPoints.add(baby.getPosition());
            cannibalPoints.add(baby.getPosition());
            int animal = choosePrey(baby);
    if (animal < animals.size()){
        baby.setAnimal4Life(r.nextInt(animals.size()));
    }else {
        baby.setAnimal4Life(animal);
    }

            baby.setEnergy((int) (baby.getEnergyMaxReletive() * .65));
            cannibals.add(baby);
    cannibals.add(baby);
            cannibal.setEnergy((int)(cannibal.getEnergy()*.65));

}




public int choosePrey(Cannibal cannibal){ //cannibals will get better the more you add to the first array... but will slow the program and be more intensive
    ArrayList<Integer> distances;
    distances = new ArrayList<>();
    Random r = new Random();
    if (animals.size() > 5) {


        for (int i = 0; i < animalPoints.size() / 2; i++) {

            distances.add(r.nextInt(animals.size()));
            //make a list of distances ints, then randomly pick 10 animals and find the closest one and set it...
        }
        int bestIndex = 1000;
        double dis;
        dis = 10000;
        for (int q = 0; q < distances.size(); q++) {
            try {

                dis = cannibalPoints.get(cannibalPoints.indexOf(cannibal.getPosition())).distance(new Point((int) animalPoints.get(distances.get(q)).getX(), (int) animalPoints.get(distances.get(q)).getY()));



            } catch (NullPointerException e) {
                System.out.println("error... Null");
            }
            if (dis < bestIndex) {
                bestIndex = distances.get(q);
            }
        }
        cannibal.setAnimal4Life(bestIndex);
        return bestIndex;
    }else{
        System.out.println("punk ass bitch");
        return 1;
    }

}

































    public void die() { //DEATH
        for (int i = 0; i < animals.size(); i++) {


            if (animals.get(i).getEnergy() <= animals.get(i).getEnergyMin()) {
                animals.remove(i); //if they are dead, they go away
                animalPoints.remove(i);
                i--;
            }
        }

        for (int q = 0; q < cannibals.size() ; q++) {
            if (cannibals.get(q).getEnergy() <= cannibals.get(q).getEnergyMin()) {
                cannibals.remove(q);
                cannibalPoints.remove(q);
               // System.out.println("Sorry Buddy... ");

        }

        }


    }











//getters!!!
    public Animal getAnimal(int index) {
            return animals.get(index);
    }

    public Point getAnimalPoint(int index){
        return animalPoints.get(index);
    }

    public Color getColors(int index) {
        return colors.get(index);
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public ArrayList<FoodSquare> getFoodSquares() {
        return foodSquares;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Point> getAnimalPoints() {
        return animalPoints;
    }


    public ArrayList<Cannibal> getCannibals() {
        return cannibals;
    }

    public ArrayList<Point> getCannibalPoints() {
        return cannibalPoints;
    }

    public Point getCannibalPoint(int index) {
        return cannibalPoints.get(index);


    }
}

