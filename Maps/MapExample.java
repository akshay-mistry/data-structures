import java.util.*;

public class MapExample {

    public MapExample() {

        String[] names = {"Suns", "Sixers", "Knicks"};

        TreeMap<String, ArrayList<Integer>> diceMap = new TreeMap<>();

        for (int x = 0; x < 3; x++) {
            diceMap.put(names[x], new ArrayList<Integer>());
        }
        for (int y = 0; y < 100; y++) {
            int name = (int)(Math.random()*names.length);
            int die2 = (int)(Math.random()*6)+1;
            diceMap.get(names[name]).add(die2);
        }
        System.out.println(diceMap);
        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.println(names[i] + ": " + diceMap.get(names[i]).size());
        }
        System.out.println();

        //keySet - index
        //values - values attached to each index
        //entrySet - snapShot of everything
        Iterator<String> it1 = diceMap.keySet().iterator();
        while (it1.hasNext()) {
            String teamName = it1.next();
            System.out.println(teamName);
        }
        System.out.println();

        //for-each loop method
        for (String name : diceMap.keySet()) {
            System.out.println(name);
        }
        System.out.println();

        Iterator<ArrayList<Integer>> it2 = diceMap.values().iterator();
        while (it2.hasNext()) {
           ArrayList<Integer> value = it2.next();
           System.out.println(value);
        }
        System.out.println();

        Iterator<Map.Entry<String, ArrayList<Integer>>> it3 = diceMap.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry<String, ArrayList<Integer>> value = it3.next();
            System.out.println(value);
        }
        System.out.println();

    }

    public static void main (String[] args) {
        MapExample map = new MapExample();
    }
}
