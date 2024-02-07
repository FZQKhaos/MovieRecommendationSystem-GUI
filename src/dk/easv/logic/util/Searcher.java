package dk.easv.logic.util;

import dk.easv.entities.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Searcher {

    public List<Movie> search(Map<Integer, Movie> movies, String query) {
        List<Movie> searchResults = new ArrayList<>();

        for (Movie m : movies.values()) {
            if (matchesQuery(m, query)) {
                searchResults.add(m);
            }
        }
        return searchResults;
    }

    private boolean matchesQuery(Movie m, String query) {
        return m.getTitle().toLowerCase().contains(query.toLowerCase());
    }

}
