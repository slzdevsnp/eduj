package baeldung;

import baeldung.dtoDest.PersonNameParts;
import baeldung.dtoSrc.*;
import baeldung.dtoDest.Dest;
import baeldung.dtoDest.Personne;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MappingTest {
    private  MapperFactory mapperFactory;

    private static final Logger log = LoggerFactory.getLogger(MappingTest.class);

    @BeforeEach
    public void initializeMapperFactory(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    @Test
    public void givenSrcAndDest_whenMaps_thenCorrect() {
        mapperFactory.classMap(Source.class, Dest.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Source src = new Source("Baeldung", 10);
        Dest dest = mapper.map(src, Dest.class);

        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), src.getName());
    }

    @Test
    public void givenSrcAndDest_whenMapsReverse_thenCorrect() {
        mapperFactory.classMap(Source.class, Dest.class).byDefault();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Dest src = new Dest("Baeldung", 10);
        Source dest = mapper.map(src, Source.class);

        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), src.getName());
    }

    @Test
    public void givenSrcAndDest_whenMapsUsingBoundMapper_thenCorrect() {
        BoundMapperFacade<Source, Dest>
                boundMapper = mapperFactory.getMapperFacade(Source.class, Dest.class);
        Source src = new Source("baeldung", 10);
        Dest dest = boundMapper.map(src);

        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), src.getName());
    }

    @Test
    public void givenSrcAndDest_whenMapsUsingBoundMapperInReverse_thenCorrect() {
        BoundMapperFacade<Source, Dest>
                boundMapper = mapperFactory.getMapperFacade(Source.class, Dest.class);
        Dest src = new Dest("baeldung", 10);
        Source dest = boundMapper.mapReverse(src); //mapReverse()

        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), src.getName());
    }

    @Test
    public void givenSrcAndDestWithDifferentFieldNames_whenMaps_thenCorrect(){

        // we must explicitly register all field mappings
        mapperFactory.classMap(Personne.class, Person.class)
                .field("nom", "name")
                .field("surnom", "nickname")
                .field("age", "age")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Personne frenchPerson = new Personne("Jack", "jackie", 25);
        Person englishPerson = mapper.map(frenchPerson, Person.class);

        assertEquals(englishPerson.getName(), frenchPerson.getNom());
        assertEquals(englishPerson.getNickname(), frenchPerson.getSurnom());
        assertEquals(englishPerson.getAge(), frenchPerson.getAge());
    }


    @Test
    public void givenSrcAndDest_whenCanExcludeField_thenCorrect() {
        mapperFactory.classMap(Personne.class, Person.class)
                .exclude("nom")
                .field("surnom", "nickname")
                .field("age", "age").register();

        MapperFacade mapper = mapperFactory.getMapperFacade();
        Personne frenchPerson = new Personne("Claire", "cla", 25);
        Person englishPerson = mapper.map(frenchPerson, Person.class);

        assertEquals(null, englishPerson.getName());
        assertEquals(englishPerson.getNickname(), frenchPerson.getSurnom());
        assertEquals(englishPerson.getAge(), frenchPerson.getAge());

    }


    //fails to execute  on line MapperFacade mapper = mapperFactory.getMapperFacade();
    @Test
    public void givenSrcWithListAndDestWithPrimitiveAttributes_whenMaps_thenCorrect() {
        mapperFactory.classMap(PersonNameList.class, PersonNameParts.class)
                .field("nameList[0]", "firstName")
                .field("nameList[1]", "lastName").register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        List<String> nameList = Arrays.asList(new String[] { "Sylvester", "Stallone" });
        PersonNameList src = new PersonNameList(nameList);
        PersonNameParts dest = mapper.map(src, PersonNameParts.class);
        assertEquals(dest.getFirstName(), "Sylvester");
        assertEquals(dest.getLastName(), "Stallone");
    }


    @Test
    public void givenSrcWithMapAndDestWithPrimitiveAttributes_whenMaps_thenCorrect() {
        mapperFactory.classMap(PersonNameMap.class, PersonNameParts.class)
                .field("nameMap['first']", "firstName")
                .field("nameMap[\"last\"]", "lastName")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("first", "Leornado");
        nameMap.put("last", "DiCaprio");
        PersonNameMap src = new PersonNameMap(nameMap);
        PersonNameParts dest = mapper.map(src, PersonNameParts.class);

        assertEquals(dest.getFirstName(), "Leornado");
        assertEquals(dest.getLastName(), "DiCaprio");
    }

    //nested DTO for mapping
    @Test
    public void givenSrcWithNestedFields_whenMaps_thenCorrect() {
        mapperFactory.classMap(PersonContainer.class, PersonNameParts.class)
                .field("name.firstName", "firstName")
                .field("name.lastName", "lastName").register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        PersonContainer src = new PersonContainer(new Name("Nick", "Canon"));
        PersonNameParts dest = mapper.map(src, PersonNameParts.class);

        assertEquals(dest.getFirstName(), "Nick");
        assertEquals(dest.getLastName(), "Canon");
    }

    //by default orika maps null values
    @Test
    public void givenSrcWithNullField_whenMapsThenCorrect() {
        mapperFactory.classMap(Source.class, Dest.class).byDefault();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Source src = new Source(null, 10);
        Dest dest = mapper.map(src, Dest.class);

        System.out.println("dest name:" + dest.getName());

        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), src.getName());

    }

    @Test
    public void givenSrcWithNullAndGlobalConfigForNoNull_whenFailsToMap_ThenCorrect() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
        mapperFactory.classMap(Source.class, Dest.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Source src = new Source(null, 10);
        Dest dest = new Dest("Clinton", 55);
        mapper.map(src, dest);
        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), "Clinton");
    }

    @Test
    public void givenSrcWithNullAndLocalConfigForNoNull_whenFailsToMap_ThenCorrect() {
        mapperFactory.classMap(Source.class, Dest.class)
                .field("age", "age").mapNulls(false)
                .field("name", "name").byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Source src = new Source(null, 10);
        Dest dest = new Dest("Clinton", 55);
        mapper.map(src, dest);
        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), "Clinton");
    }

    //bedirectional mapping
    @Test
    public void givenDestWithNullReverseMappedToSource_whenMapsByDefault_thenCorrect() {
        mapperFactory.classMap(Source.class, Dest.class).byDefault();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Dest src = new Dest(null, 10);
        Source dest = new Source("Vin", 44);
        mapper.map(src, dest);

        assertEquals(dest.getAge(), src.getAge());  //baeldung original
        assertEquals(dest.getName(), src.getName());
    }

    @Test
    public void givenDestWithNullReverseMappedToSourceAndLocalConfigForNoNull_whenFailsToMap_thenCorrect() {
        mapperFactory.classMap(Source.class, Dest.class)
                .field("age", "age").mapNullsInReverse(false)
                .field("name", "name").byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Dest src = new Dest(null, 10);
        Source dest = new Source("Vin", 44);
        mapper.map(src, dest);
        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), "Vin");
    }

    //config on field level
    @Test
    public void givenSrcWithNullAndFieldLevelConfigForNoNull_whenFailsToMap_ThenCorrect() {
        mapperFactory.classMap(Source.class, Dest.class).field("age", "age").fieldMap("name", "name").mapNulls(false).add().byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        Source src = new Source(null, 10);
        Dest dest = new Dest("Clinton", 55);
        mapper.map(src, dest);
        assertEquals(dest.getAge(), src.getAge());
        assertEquals(dest.getName(), "Clinton");
    }

}
