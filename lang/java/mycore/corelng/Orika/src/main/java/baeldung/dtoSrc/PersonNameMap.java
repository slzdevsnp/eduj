package baeldung.dtoSrc;

import java.util.Map;

public class PersonNameMap {
    private Map<String, String> nameMap;

    public PersonNameMap(Map<String, String> nameMap) {
        this.nameMap = nameMap;
    }

    public Map<String, String> getNameMap() {
        return nameMap;
    }
}
