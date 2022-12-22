package baeldung;

import baeldung.dtoDest.Personne3;
import baeldung.dtoSrc.Person3;
import baeldung.mapping.PersonCustomMapper;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomMappingTest {
    MapperFactory mapperFactory;
    CustomMapper<Personne3, Person3> customMapper;
    // constant to help us cover time zone differences
    private final long GMT_DIFFERENCE = 46800000;

    private static final Logger log = LoggerFactory.getLogger(CustomMappingTest.class);

    @BeforeEach
    public void before() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        customMapper = new PersonCustomMapper(); // NB!
    }

    @Test
    public void givenSrcAndDest_whenCustomMapperWorks_thenCorrect() {
        mapperFactory.classMap(Personne3.class, Person3.class)
                .customize(customMapper).register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        long timestamp = Long.valueOf("1182882159000");
        Personne3 personne3 = new Personne3("Leornardo", timestamp);
        Person3 person3 = mapper.map(personne3, Person3.class);

        String timestampTest = person3.getDtob();
        // since different timezones will resolve the timestamp to a different
        // datetime string, it suffices to check only for format rather than
        // specific date
        assertTrue(timestampTest.charAt(10) == 'T' && timestampTest.charAt(19) == 'Z');
    }

    @Test
    public void givenSrcAndDest_whenCustomMapperWorksBidirectionally_thenCorrect() {
        mapperFactory.classMap(Personne3.class, Person3.class).customize(customMapper).register();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        String dateTime = "2007-06-26T21:22:39Z";
        long timestamp = new Long("1182882159000");
        Person3 person3 = new Person3("Leornardo", dateTime);
        Personne3 personne3 = mapper.map(person3, Personne3.class);
        long timestampToTest = personne3.getDtob();
        /*
         * since different timezones will resolve the datetime to a different
         * unix timestamp, we must provide a range of tolerance
         */
        assertTrue(timestampToTest == timestamp || timestampToTest >= timestamp - GMT_DIFFERENCE || timestampToTest <= timestamp + GMT_DIFFERENCE);

    }

}
