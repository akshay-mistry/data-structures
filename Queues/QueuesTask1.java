import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QueuesTask1 {

    private Queue<Word> queue;
    private PriorityQueue<Word> pQueue;
    private PriorityQueue<Word> pQueueRev;

    public QueuesTask1() {
        File name = new File("/Users/akshaymistry/Desktop/Data Structures/Queues/Task1Text");

        try {
            queue = new LinkedList<>();
            pQueue = new PriorityQueue<>();
            pQueueRev = new PriorityQueue<>(Collections.reverseOrder());
            BufferedReader input = new BufferedReader(new FileReader(name));

            String text="";

            while ((text=input.readLine())!= null) {
                text = text.replace(",","").replace(".","");
                String[] arr = text.split(" ");
                for (int i = 0; i < arr.length; i++) {
                    //System.out.print(arr[i] + " ");
                    Word word = new Word(arr[i]);
                    queue.add(word);
                    pQueue.add(word);
                    pQueueRev.add(word);
                }
            }

            while(!queue.isEmpty() && !pQueue.isEmpty() && !pQueueRev.isEmpty()) {
                Word q = queue.poll();
                Word p = pQueue.poll();
                Word pr = pQueueRev.poll();
                String str = String.format("%-20s%-20s%s",q, p, pr);
                System.out.println(str);
            }
        }

        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

    public static void main (String[] args) {
        QueuesTask1 queuesTask1 = new QueuesTask1();
    }


    public class Word implements Comparable<Word> {

        private String wordVal;

        public Word(String wordVal) {
            this.wordVal = wordVal;
        }

        public String getWordVal() {
            return wordVal;
        }

        @Override
        public String toString() {
            return wordVal;
        }

        @Override
        public int compareTo(Word o) {
            return this.getWordVal().toLowerCase().compareTo(o.getWordVal().toLowerCase());
        }
    }
}
