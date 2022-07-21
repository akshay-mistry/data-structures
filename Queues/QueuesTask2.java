import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QueuesTask2 {

    private Queue<Passenger> queue;
    private PriorityQueue<Passenger> pQueue;

    public QueuesTask2() {
        File name = new File("/Users/akshaymistry/Desktop/Data Structures/Queues/PassengerInfo.txt");

        try {
            queue = new LinkedList<>();
            pQueue = new PriorityQueue<>();
            BufferedReader input = new BufferedReader(new FileReader(name));

            String text="";
            int num = 1;
            String firstLast = "";
            String city = "";
            String time = "";

            while ((text=input.readLine())!= null) {
                switch (num) {
                    case 1:
                        firstLast = text;
                        break;
                    case 2:
                        city = text;
                        break;
                    case 3:
                        time = text;
                        break;
                    default:
                        break;
                }
                if (num == 3) {
                    String[] fullName = firstLast.split(" ");
                    String first = fullName[0];
                    String last = fullName[1];
                    Passenger passenger = new Passenger(first, last, city, time);
                    queue.add(passenger);
                    pQueue.add(passenger);
                    num = 1;
                }
                else {
                    num++;
                }
            }
            System.out.println("ALL PASSENGERS:\n");
            while(!queue.isEmpty()) {
                Passenger p = queue.poll();
                System.out.println(p);
            }
            System.out.println("\n\nPASSENGERS THAT CAN SKIP THE LINE:\n");
            while(!pQueue.isEmpty()) {
                Passenger p = pQueue.poll();
                if (p.etdCalc().charAt(0) == '0')
                    System.out.println(p);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException io) {
            System.err.println("File does not exist");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        QueuesTask2 queuesTask2 = new QueuesTask2();
    }

    public class Passenger implements Comparable<Passenger>{

        private String last;
        private String first;
        private String city;
        private String time;

        public Passenger(String l, String f, String c, String t) {
            last = l;
            first = f;
            city = c;
            time = t;
        }

        public String getLastName() {
            return last;
        }
        public String getFirstName() {
            return first;
        }
        public String flightCity() {
            return city;
        }
        public String flightTime() {
            return time;
        }
        public String etdCalc() throws ParseException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm aa");
            Date date = simpleDateFormat.parse(time);
            Date current = simpleDateFormat.parse("9:03 AM");
            long difference = date.getTime() - current.getTime();
            long hours = (difference / (3600 * 1000)) % 24;
            long minutes = (difference / (60 * 1000)) % 60;
            return hours + " hours and " + minutes + " minutes";
        }

        @Override
        public String toString() {
            try {
                return last + ", " + first + " — " + city + " — " + time + " — " + etdCalc();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        public int compareTo(Passenger o) {
            try {
                int compare = this.etdCalc().compareTo(o.etdCalc());
                if (Integer.valueOf(this.etdCalc().charAt(0)) > 1) {
                    return compare;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
