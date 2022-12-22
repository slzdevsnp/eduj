package org.szi.l02Streams.downstream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class DownStreamCollectors {
    private static final Logger log = LoggerFactory.getLogger(DownStreamCollectors.class);

    //inner class
    public static class City {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population) {
            this.name = name;
            this.state = state;
            this.population = population;
        }
        public String getName() { return name; }
        public String getState() { return state; }
        public int getPopulation() { return population; }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", state='" + state + '\'' +
                    ", population=" + population +
                    '}';
        }
    }

    public static Stream<Locale> locales(){
        return Stream.of(Locale.getAvailableLocales());
    }

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Paths.get(filename))
                .map(l -> l.split(", "))
                .map(w -> new City(w[0], w[1], Integer.parseInt(w[2])));
    }
    public static void main(String[] args) throws IOException {
        log.info("### locales");
        Map<String,String> country_locales =
                locales().collect(Collectors.toMap(Locale::toString,
                Locale::getDisplayLanguage));
        log.info("locales: {}",country_locales);

        log.info("#### grouping by country");
        Map<String, List<Locale>> countryToLocales = locales()
                    .collect(groupingBy(Locale::getCountry));
        log.info("locale countries: {}",countryToLocales);
        log.info("local countries in .CH:{} , in CA:{}",
                countryToLocales.get("CH"),
                countryToLocales.get("CA"));

        Map<String, Set<Locale>> countrytoLocaleSet = locales()
                    .collect(groupingBy(Locale::getCountry, toSet()));
        log.info("countryToLocaleSet:{}",countrytoLocaleSet);

        log.info("#### cities stats by state");
        Stream<City> cities = readCities("src/main/resources/cities.txt");
        Map<String, Integer> stateToCityPopulation = cities
                .collect(groupingBy(City::getState,
                         summingInt(City::getPopulation)));
        log.info("state cities Population: {}", stateToCityPopulation);

        cities = readCities("src/main/resources/cities.txt");
        Map<String, IntSummaryStatistics> stateCitySumm = cities
                .collect(groupingBy(City::getState,
                        summarizingInt(City::getPopulation)));
        for (String k : stateCitySumm.keySet()){
            IntSummaryStatistics v = stateCitySumm.get(k);
            log.info("state: {}: city average population:{}",k, v.getAverage());
        }

        cities = readCities("src/main/resources/cities.txt");
        List<String> cityNames= cities
                    .map(City::getName)
                    .collect(Collectors.toList());
        log.info("cities names :{}", cityNames);

        log.info("#### cities longest city in state");
        cities = readCities("src/main/resources/cities.txt");
        Map<String, Optional<String>> stateLongestCityName = cities
                    .collect(groupingBy(City::getState,
                             mapping(City::getName,
                                maxBy(Comparator.comparing(String::length)))));
        log.info("states longest citie names: {}",stateLongestCityName);
    }

}
