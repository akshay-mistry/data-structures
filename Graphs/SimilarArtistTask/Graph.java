package SimilarArtistTask;

import java.util.HashSet;

public class Graph {

    HashSet<Artist> artists;
    HashSet<Edge> edges;

    public Graph() {
        artists = new HashSet<>();
        edges = new HashSet<>();
    }

    public HashSet<Artist> getArtists() {
        return artists;
    }
    public HashSet<Edge> getEdges() {
        return edges;
    }
    public void addArtist(Artist artist) {
        artists.add(artist);
    }
    public void addEdge(Artist source, Artist destination) {
        edges.add(new Edge(source, destination));
    }
}
