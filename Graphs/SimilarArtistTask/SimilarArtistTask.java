package SimilarArtistTask;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class SimilarArtistTask {

    HashMap<Artist, HashSet<Edge>> artistMap;
    Artist start, end;
    Graph graph;
    Stack<Artist> currentPath;
    HashSet<Artist> visited;

    public SimilarArtistTask() {
        artistMap = new HashMap<>();
        graph = new Graph();
        File file = new File("/Users/akshaymistry/Desktop/Data Structures/Graphs/SimilarArtistTask/SimilarArtists.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String text;
            while ((text = input.readLine()) != null) {
                System.out.println(text);
                String info[] = text.split(", ");
                Artist a1 = new Artist(info[0]);
                Artist a2 = new Artist(info[1]);
                graph.addArtist(a1);
                graph.addArtist(a2);
                graph.addEdge(a1, a2);
                graph.addEdge(a2, a1);
                if (!artistMap.containsKey(a1)) {
                    artistMap.put(a1, new HashSet<Edge>());
                }
                if (!artistMap.containsKey(a2)) {
                    artistMap.put(a2, new HashSet<Edge>());
                }
                artistMap.get(a1).add(new Edge(a1, a2));
                artistMap.get(a2).add(new Edge(a2, a1));
            }
        }
        catch (IOException e) {}

        System.out.println("Edges – Connecting artists with similar");
        for(Edge e : graph.getEdges()) {
            System.out.println("\t"+e);
        }

        for (Artist startingArtist : graph.getArtists()) {
            System.out.println(startingArtist);
            for (Artist endingArtist : graph.getArtists()) {
                if (!startingArtist.equals(endingArtist)) {
                    currentPath = new Stack<Artist>();
                    visited = new HashSet<Artist>();
                    dft(startingArtist, endingArtist);
                }
            }
        }
    }

    public void dft(Artist startingArtist, Artist endingArtist) {
        currentPath.push(startingArtist);
        visited.add(startingArtist);
        if (startingArtist.equals(endingArtist)) {
            printCurrentPath();
        }
        else {
            for (Edge e : graph.getEdges()) {
                Artist artist = e.getArtist();
                Artist similar = e.getSimilar();
                if (visited.contains(artist) && !visited.contains(similar)) {
                    dft(similar, endingArtist);
                }
                if (visited.contains(similar) && !visited.contains(artist)) {
                    dft(artist, endingArtist);
                }
            }
        }
    }

    public void printCurrentPath() {
        String output = "";
        while (!currentPath.isEmpty()) {
            output = currentPath.pop() + output;
            if (!currentPath.isEmpty()) {
                output = " → " + output;
            }
        }
        System.out.println("\t" + output);
    }

    public static void main (String[] args) {
        SimilarArtistTask similarArtistTask = new SimilarArtistTask();
    }
}
