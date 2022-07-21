package CityConnectTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CityConnectTask {

    HashMap<City, HashSet<Edge>> cityMap;
    HashSet<City> cities;
    HashSet<Edge> edges;
    City start, end;

    public CityConnectTask() {
        cityMap = new HashMap<City, HashSet<Edge>>();
        cities = new HashSet<City>();
        edges = new HashSet<Edge>();
        ArrayList<String> cityList = new ArrayList<>();
        File file = new File("/Users/akshaymistry/Desktop/Data Structures/Graphs/CityConnectTask/City Distances.txt");

        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            while ((text = input.readLine()) != null) {
                String[] info = text.split(",");
                City c1 = new City(info[0]);
                City c2 = new City(info[1]);
                int distance = Integer.parseInt(info[2]);
                if (!cityList.contains(c1.getName())) {
                    cityList.add(c1.getName());
                }
                if (!cityList.contains(c2.getName())) {
                    cityList.add(c2.getName());
                }
                cities.add(c1);
                cities.add(c2);
                edges.add(new Edge(c1, c2, distance));
                edges.add(new Edge(c2, c1, distance));
                if (!cityMap.containsKey(c1)) {
                    cityMap.put(c1, new HashSet<Edge>());
                }
                if (!cityMap.containsKey(c2)) {
                    cityMap.put(c2, new HashSet<Edge>());
                }
                cityMap.get(c1).add(new Edge(c1, c2, distance));
                cityMap.get(c2).add(new Edge(c2, c1, distance));
            }

        } catch (IOException e) {
        }

        System.out.println("Vertices - Cities:");
        for (City city : cities) {
            System.out.println(city);
        }
        System.out.println("\nEdges - Connecting cities and distances between:");
        for (Edge edge : edges) {
            System.out.println(edge);
        }

        for (int i = 0; i < cityList.size() - 1; i++) {
            for (int j = i + 1; j < cityList.size(); j++) {
                for (City city : cities) {
                    if (city.getName().equals(cityList.get(i))) {
                        start = city;
                    }
                    if (city.getName().equals(cityList.get(j))) {
                        end = city;
                    }
                }
                Graph graph = new Graph(cities, edges);
                DijkstrasAlgorithm dijkstrasAlgorithm = new DijkstrasAlgorithm(graph);
                dijkstrasAlgorithm.createTravelsPaths(start);
                ArrayList<City> shortestPath = dijkstrasAlgorithm.getShortestPath(end);
                int distance = 0;
                System.out.println("\nShortest path between " + start.getName() + " to " + end.getName() + ".");
                for (int x = 0; x < shortestPath.size() - 1; x++) {
                    City c1 = shortestPath.get(x);
                    City c2 = shortestPath.get(x + 1);
                    System.out.println("\t" + c1 + " to " + c2);
                    for (Edge edge : cityMap.get(c1)) {
                        if (edge.getStart().equals(c1) && edge.getDestination().equals(c2)) {
                            distance += edge.getDistance();
                        }
                        else if (edge.getStart().equals(c2) && edge.getDestination().equals(c1)) {
                            distance += edge.getDistance();
                        }
                    }
                }
                System.out.println("Distance between: " + distance + " miles\n\n");
            }
        }
    }

    public static  void main(String[] args) {
        CityConnectTask cityConnectTask = new CityConnectTask();
    }
}
