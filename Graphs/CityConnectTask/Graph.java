package CityConnectTask;

import java.util.HashSet;

public class Graph {

    HashSet<City> cities;
    HashSet<Edge> edges;

    public Graph (HashSet<City> cities, HashSet<Edge> edges) {
        this.cities = cities;
        this.edges = edges;
    }

    public HashSet<City> getCities() {
        return cities;
    }
    public HashSet<Edge> getEdges() {
        return edges;
    }
}
