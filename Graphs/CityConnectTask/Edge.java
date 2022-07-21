package CityConnectTask;

public class Edge {

    City start, destination;
    int distance;
    int uniqueID;

    public Edge(City start, City destination, int distance) {
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        uniqueID = start.hashCode() + destination.hashCode();
    }

    public City getStart() {
        return start;
    }
    public City getDestination() {
        return destination;
    }
    public int getDistance() {
        return distance;
    }
    public int hashCode() {
        return uniqueID;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        Edge other = (Edge) obj;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return start + " to " + destination + " : " + distance;
    }
}
