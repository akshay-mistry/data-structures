import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class SetProgram {

    public SetProgram() {

        //1. Fill an ArrayList with a random number (between 2 and 12) of HashSets that each hold exactly 10
        //random numbers between 1 and 30. All of the sets must haves sizes equal to 10.
        ArrayList<TreeSet<Integer>> list = new ArrayList<>();
        int rand = (int)(Math.random()*11) + 2;
        for (int i = 0; i < rand; i++) {
            TreeSet<Integer> set = new TreeSet<>();
            while (set.size() < 10){
                int rand1 = (int)(Math.random()*30) + 1;
                set.add(rand1);
            }
            list.add(set);
        }
        System.out.println(list);
        System.out.println();
        TreeSet<Integer> set1 = list.get(0);
        TreeSet<Integer> set2 = list.get(1);
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        System.out.println();

        //2. Make a method that will receive two sets and return a set of the intersection of the sets. The
        //intersection will consist only of the common values contained within all sets.
        TreeSet<Integer> intersectionSet = getIntersection(set1, set2);
        System.out.println("Intersection: " + intersectionSet);

        //3. Make a method that will receive two sets and return a set of the union of the sets. The union will
        //consist only of the unique values contained within all sets.
        TreeSet<Integer> unionSet = getUnion(set1, set2);
        System.out.println("Union: " + unionSet);

        //4. Make a method that will receive two sets and return a set of even intersection of the sets. The
        //intersection will consist only of the common even values contained within all sets.
        TreeSet<Integer> evenIntersectionSet = getEvenIntersection(set1, set2);
        System.out.println("Even Intersection: " + evenIntersectionSet);

        //5. Make a method that will receive two sets and return a set of the even union of the sets. The union
        //will consist only of the unique even values contained within all sets.
        TreeSet<Integer> evenUnionSet = getEvenUnion(set1, set2);
        System.out.println("Even Union: " + evenUnionSet);
    }

    public TreeSet<Integer> getIntersection (TreeSet<Integer> set1, TreeSet<Integer> set2) {
        TreeSet<Integer> intersection = new TreeSet<>();
        Iterator<Integer> it1 = set1.iterator();
        Iterator<Integer> it2 = set2.iterator();
        while (it1.hasNext()) {
            int next = it1.next();
            if (set2.contains(next))
                intersection.add(next);
        }
        return intersection;
    }


    public TreeSet<Integer> getUnion (TreeSet<Integer> set1, TreeSet<Integer> set2) {
        TreeSet<Integer> union = new TreeSet<>();
        Iterator<Integer> it1 = set1.iterator();
        Iterator<Integer> it2 = set2.iterator();
        while (it1.hasNext()) {
            union.add(it1.next());
        }
        while (it2.hasNext()) {
            union.add(it2.next());
        }
        return union;
    }

    public TreeSet<Integer> getEvenIntersection (TreeSet<Integer> set1, TreeSet<Integer> set2) {
        TreeSet<Integer> evenIntersection = new TreeSet<>();
        Iterator<Integer> it1 = set1.iterator();
        Iterator<Integer> it2 = set2.iterator();
        while (it1.hasNext()) {
            int next = it1.next();
            if (next % 2 == 0 && set2.contains(next))
                evenIntersection.add(next);
        }
        return evenIntersection;
    }

    public TreeSet<Integer> getEvenUnion (TreeSet<Integer> set1, TreeSet<Integer> set2) {
        TreeSet<Integer> evenUnion = new TreeSet<>();
        Iterator<Integer> it1 = set1.iterator();
        Iterator<Integer> it2 = set2.iterator();
        while (it1.hasNext()) {
            int next1 = it1.next();
            if (next1 % 2 == 0)
                evenUnion.add(next1);
        }
        while (it2.hasNext()) {
            int next2 = it2.next();
            if (next2 % 2 == 0)
                evenUnion.add(next2);
        }
        return evenUnion;
    }

    public static void main (String[] args) {
        SetProgram setProgram = new SetProgram();
    }



}
