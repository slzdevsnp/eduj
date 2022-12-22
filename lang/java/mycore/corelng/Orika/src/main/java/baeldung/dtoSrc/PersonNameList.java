package baeldung.dtoSrc;

import java.util.List;

public class PersonNameList {
    private List<String> nameList;

    public PersonNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public List<String> getNameList() {
        return nameList;
    }
}
