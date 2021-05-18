import java.util.concurrent.Semaphore;

public class Fork {
    public Semaphore fork = new Semaphore(1);

    public boolean take() {
        return fork.tryAcquire();
    }

    public void putDown() {
        fork.release();
    }
}