package com.baeldung.java_8_features.groupingby;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@Slf4j
public class StreamTimeSeries {  //TODO: create a a dummy TimeseriesData class with data validity check methods

    @Data
    @AllArgsConstructor
    @ToString
    class Datum {
        ZonedDateTime ts;
        Double value;
    }

    @Data
    @AllArgsConstructor
    @ToString
    class DiffDatum {
        ZonedDateTime ts;
        Long tsDiff;
        Double valueDiff;
    }

    private List<Datum> constTserie;
    private List<Datum> incrementalTSerie;
    private List<Datum> irregularTserie;
    private int counter;

    @Before
    public void init() {
        constTserie = new ArrayList<>();
        incrementalTSerie = new ArrayList<>();
        irregularTserie = new ArrayList<>();
        counter = 10;
        Double v0 = 10.0d;
        ZonedDateTime dtStrt = ZonedDateTime.parse("2021-03-01T00:00:00Z");
        for (int i = 0; i < counter; i++) {
            constTserie.add(new Datum(dtStrt.plusMinutes(i), v0));
            incrementalTSerie.add(new Datum(dtStrt.plusMinutes(i), v0 + 1.0 * i));
        }
        irregularTserie = Arrays.asList(
                new Datum(dtStrt.plusMinutes(0), 10.0),
                new Datum(dtStrt.plusMinutes(1), 10.0),
                new Datum(dtStrt.plusMinutes(2), 10.0),
                new Datum(dtStrt.plusMinutes(5), 11.0),
                new Datum(dtStrt.plusMinutes(6), 10.0),
                new Datum(dtStrt.plusMinutes(8), 12.0),
                new Datum(dtStrt.plusMinutes(9), 10.0)
        );
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Test
    public void chkConstValues() {
        List<Datum> consDatumDistinct = constTserie.stream()
                .filter(distinctByKey(p -> p.getValue()))
                .collect(Collectors.toList());

        List<Datum> incrementalDistinct = incrementalTSerie.stream()
                .filter(distinctByKey(p -> p.getValue()))
                .collect(Collectors.toList());

        List<Datum> irregularDatumDistinct = irregularTserie.stream()
                .filter(distinctByKey(p -> p.getValue()))
                .collect(Collectors.toList());

        log.debug("constFilterd: {}", consDatumDistinct);
        //the filterd shows iregular Datum elements
        log.debug("irregularFilterd: {}", irregularDatumDistinct);

        assertThat(consDatumDistinct.size(), is(1));
        assertThat(incrementalDistinct.size(), is(10));
        assertThat(irregularDatumDistinct.size(), is(3));
    }

    @Test
    public void chkConstValuesAlt() {

        assertThat(constTserie.stream()
                        .map(x -> x.getValue())
                        .distinct()
                        .collect(Collectors.toList()).size(),
                is(1));
        assertThat(incrementalTSerie.stream()
                        .map(x -> x.getValue())
                        .distinct()
                        .collect(Collectors.toList()).size(),
                is(counter));
        assertThat(irregularTserie.stream()
                        .map(x -> x.getValue())
                        .distinct()
                        .collect(Collectors.toList()).size(),
                is(3));


    }

    @Test
    public void checkTimestampRegularity() {
        List<Long> tdiffs = new ArrayList<>();
        IntStream.range(0, constTserie.size() - 1)
                .forEach(i -> {
                            tdiffs.add(constTserie.get(i + 1).getTs().toInstant().toEpochMilli() -
                                    constTserie.get(i).getTs().toInstant().toEpochMilli());
                        }
                );
        List<Long> uniqdiffs = tdiffs.stream().distinct().collect(Collectors.toList());

        //on irregular ts
        List<DiffDatum> tdiffs3 = new ArrayList<>();
        IntStream.range(0, irregularTserie.size() - 1)
                .forEach(i -> {
                            tdiffs3.add(new DiffDatum(irregularTserie.get(i).getTs(),
                                    irregularTserie.get(i + 1).getTs().toInstant().toEpochMilli() -
                                            irregularTserie.get(i).getTs().toInstant().toEpochMilli(),
                                    irregularTserie.get(i + 1).getValue() -
                                            irregularTserie.get(i).getValue()));
                        }
                );

        List<DiffDatum> uniqdiffs3 = tdiffs3.stream()
                .filter(distinctByKey(p -> p.getTsDiff()))
                .collect(Collectors.toList());

        log.debug("tdiffs: {}", tdiffs);
        log.debug("tdiffs3: {}", tdiffs3);
        log.debug("uniqdiffs3:{}", uniqdiffs3);
        assertThat(uniqdiffs.size(), is(1));
        assertThat(uniqdiffs3.size(), is(3));
        assertThat(uniqdiffs3.get(1).getTsDiff(), is(1800_00L));

    }


}
