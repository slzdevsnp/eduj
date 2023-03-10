package org.paumard.collections;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.paumard.collections.model.Actor;
import org.paumard.collections.model.Movie;
import org.paumard.collections.model.MovieReader;

public class ConcurrentHashMapParallelPatterrns {

    public static void main(String[] args) {

        //in how many movies an actor has played
        ConcurrentHashMap<Actor, Set<Movie>> map = new ConcurrentHashMap<>();

        MovieReader reader = new MovieReader();
        reader.addActorsToMap(map);

        System.out.println("# Actors = " + map.size());

        int maxMoviesForOneActor = map.reduce(10,
                (actor, movies) -> movies.size(), Integer::max);  //find the biggest element in a map
        System.out.println("Max movies for one actor = " + maxMoviesForOneActor);

		//now lets find this bastard
        Actor mostSeenActor =
                map.search(10,
						(actor, movies) -> movies.size() == maxMoviesForOneActor ? actor : null);
        System.out.println("Most seen actor = " + mostSeenActor);

        int numberOfMoviesReferences = map.reduce(10,
				(actor, movies) -> movies.size(),
				Integer::sum);

        System.out.println("Average movies per actor = " + numberOfMoviesReferences / map.size());

	}
}
