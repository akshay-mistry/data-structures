package CityConnectTask;

public class City {

    String name;
    int uniqueID;

    public City(String name) {
        this.name = name;
        uniqueID = name.hashCode();
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        City other = (City) obj;
        return this.hashCode() == other.hashCode();
    }

    public int hashCode() {
        return uniqueID;
    }

    @Override
    public String toString() {
        return name;
    }
}
