package SimilarArtistTask;

public class Edge {

    Artist artist;
    Artist similar;
    int uniqueID;

    public Edge(Artist artist, Artist similar) {
        this.artist = artist;
        this.similar = similar;
        uniqueID = artist.hashCode() + similar.hashCode();
    }

    public Artist getArtist() {
        return artist;
    }
    public Artist getSimilar() {
        return similar;
    }
    public int hashCode() {
        return uniqueID;
    }

    public boolean equals(Object obj) {
        if (obj!=null && getClass() == obj.getClass()) {
            Edge otherEdge = (Edge)obj;
            return this.hashCode() == otherEdge.hashCode();
        }
        return false;
    }

    @Override
    public String toString() {
        return artist.getName() + " is similar to " + similar.getName();
    }


}
