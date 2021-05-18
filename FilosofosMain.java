import java.util.Scanner;
import java.util.Timer;

public class FilosofosMain {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantos filósofos você quer? ");
        int numberOfPhilosophers = scanner.nextInt();
        System.out.println("Voce quer: " + numberOfPhilosophers);
        
        System.out.print("Por quantos segundos você quer que o programa seja executado? ");
        int executionTime = scanner.nextInt();
        System.out.println("O programa irá ser executado por: " + executionTime + " segundos");

        Fork[] forks = new Fork[numberOfPhilosophers];
        Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork();
        }
    
        for (int i = 0; i < philosophers.length; i++) {
    
            if (i != philosophers.length - 1) {
                philosophers[i] = new Philosopher(forks[i], forks[i+1], i + 1);
                philosophers[i].start();
            } else {
                philosophers[i] = new Philosopher(forks[0], forks[i], i + 1);
                philosophers[i].start();
            }
        }

        Timer timer = new Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println("");
                System.out.println("");

                for (Philosopher p : philosophers) {
                    System.out.println("Philosopher #" + p.getID() + " ate " + p.getEatingStateCount() + " times");
                    System.out.println("Philosopher #" + p.getID() + " thought " + p.getThinkingStateCount() + " times");
                    System.out.println("Philosopher #" + p.getID() + " tried to eat " + p.getTryingToEatStateCount() + " times");
                    System.out.println("------");
                    System.out.println("------");
                }

                System.exit(1);
                timer.cancel();
            }
        }, executionTime * 1000);
    }
}