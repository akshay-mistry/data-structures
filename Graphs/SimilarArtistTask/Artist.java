package SimilarArtistTask;

public class Artist {

    String name;
    int uniqueID;

    public Artist(String name) {
        this.name = name;
        uniqueID = name.hashCode();
    }

    public String getName() {
        return name;
    }

    public int hashCode() {
        return uniqueID;
    }

    public boolean equals(Object obj) {
        if (obj!=null && getClass() == obj.getClass()) {
            Artist otherArtist = (Artist) obj;
            return this.hashCode() == otherArtist.hashCode();
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
