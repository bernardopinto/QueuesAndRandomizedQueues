import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k <= 0) {
            throw new IllegalArgumentException("Insert an integer greater than 0");
        }
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        System.out.println("Enter the word 'done' when finished writing input");

        String input = "";

        while (!input.equals("done")) {
            System.out.println("enter a string value: ");
            input = StdIn.readString();
            randomizedQueue.enqueue(input);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
