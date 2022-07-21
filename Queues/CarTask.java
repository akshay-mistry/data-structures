import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Comparator;


public class CarTask {

    private Queue<Car> queue;
    private PriorityQueue<Car> pQueue;
    private Stack<Car> stack;

    public CarTask() {
        File name = new File("/Users/akshaymistry/Desktop/Data Structures/Queues/CarData.txt");

        try {
            queue = new LinkedList<>();
            pQueue = new PriorityQueue<>();
            stack = new Stack<>();
            BufferedReader input = new BufferedReader(new FileReader(name));

            String text=input.readLine();

            while ((text=input.readLine())!= null) {
                String[] arr = text.split("\t");
                Car car = new Car(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]), Integer.valueOf(arr[3]), Integer.valueOf(arr[4]), Integer.valueOf(arr[5]), Integer.valueOf(arr[6]), Integer.valueOf(arr[7]));
                queue.add(car);
            }
            System.out.println("QUEUE:\n");
            while(!queue.isEmpty()) {
                Car q = queue.poll();
                stack.push(q);
                System.out.println(q);
            }
            System.out.println("\n\nSTACK:\n");
            while (!stack.isEmpty()) {
                Car s = stack.pop();
                pQueue.add(s);
                System.out.println(s);
            }
            System.out.println("\n\nPRIORITY QUEUE:\n");
            while (!pQueue.isEmpty()) {
                Car p = pQueue.poll();
                System.out.println(p);
            }
        }

        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

    public static void main (String[] args) {
        CarTask carTask = new CarTask();
    }

    public class Car implements Comparable<Car> {

        private int id;
        private int mpg;
        private int engSize;
        private int hp;
        private int weight;
        private int acceleration;
        private int country;
        private int cylinders;

        public Car(int id, int mpg, int engSize, int hp, int weight, int acceleration, int country, int cylinders) {
            this.id = id;
            this.mpg = mpg;
            this.engSize = engSize;
            this.hp = hp;
            this.weight = weight;
            this.acceleration = acceleration;
            this.country = country;
            this.cylinders = cylinders;
        }

        public int getAcceleration() {
            return acceleration;
        }
        public int getMpg() {
            return mpg;
        }
        public int getHp() {
            return hp;
        }
        public int getEngSize() {
            return engSize;
        }
        public int getWeight() {
            return weight;
        }
        public int getCylinders() {
            return cylinders;
        }
        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return String.format("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%s", id, mpg, engSize, hp, weight, acceleration, country, cylinders);
        }

        @Override
        public int compareTo(Car o) {
            return -Comparator.comparingInt(Car::getAcceleration).thenComparingInt(Car::getMpg).thenComparingInt(Car::getHp).thenComparingInt(Car::getEngSize).thenComparingInt(Car::getWeight).thenComparingInt(Car::getCylinders).thenComparingInt(Car::getId).compare(this, o);
        }
    }
}
