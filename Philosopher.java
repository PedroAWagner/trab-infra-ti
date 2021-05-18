import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {

    private Fork forkLeft;
    private Fork forkRight;
    private int identifier;

    private int thinking = 0;
    private int eating = 0;
    private int tryingToEat = 0;

    Philosopher(Fork forkLeft, Fork forkRight, int identifier) {
        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
        this.identifier = identifier;
    }

    public void run() {
        while (true) {
            think();
            eat();
        }
    }

    public int getID() {
        return identifier;
    }

    public int getThinkingStateCount() {
        return thinking;
    }

    public int getEatingStateCount() {
        return eating;
    }

    public int getTryingToEatStateCount() {
        return tryingToEat;
    }


    private void think() {
        try {   
            System.out.println("Philosopher " + identifier + " is thinking");
            thinking++;
            sleep(5000);
        } catch (InterruptedException ex) {
        }
    }

    private void tryToEat() {
        try {   
            tryingToEat++;
            int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            System.out.println("Philosopher " + identifier + " is trying to eat, for: " + randomNum + " seconds");
            sleep(randomNum * 1000);
            eat();
        } catch (InterruptedException ex) {
        }
    }

    private void eat(){
        if(forkLeft.take()) {
            if(forkRight.take()) {
                try {
                    System.out.println("Philosopher " + identifier + " is eating");
                    eating++;
                    sleep(2000);
                } catch (InterruptedException ex) { }

                forkRight.putDown();
                forkLeft.putDown();  
            } else {
                forkLeft.putDown();
                tryToEat();
            }
        } else {
            tryToEat();
        }
    }
}