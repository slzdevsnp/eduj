package baeldung.dtoSrc;

public class Person {
    private String name;
    private String nickname;
    private int age;

    public Person(String name, String nickname, int age) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
    }
    //need this construct to exclude fields in a mapper
    // @Test givenSrcAndDest_whenCanExcludeField_thenCorrect
    public Person(String nickname, int age){
        this.nickname = nickname;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }
}
