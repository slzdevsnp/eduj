package database.snowflake;

import java.util.Objects;

public class Dataset {
    public static final String DEFAULT_NAME="UNKNOWN";

    private Integer data_set_id; //PK
    private Integer identifier;
    private String  name;

    //constructors
    public Dataset() { this(DEFAULT_NAME); }
    public Dataset(String name){ this.name=name;}
    public Dataset(Integer id, String name){
        this.data_set_id = id;
        this.name = name;
    }

    public Dataset(Integer id, Integer identifier,String name){
        this.data_set_id = id;
        this.identifier=identifier;
        this.name = name;
    }

    public Integer getData_set_id() {
        return data_set_id;
    }

    public void setData_set_id(Integer data_set_id) {
        this.data_set_id = data_set_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "data_set_id=" + data_set_id +
                ", identifier=" + identifier +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dataset dataset = (Dataset) o;
        return Objects.equals(data_set_id, dataset.data_set_id) &&
                Objects.equals(identifier, dataset.identifier) &&
                Objects.equals(name, dataset.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data_set_id, identifier, name);
    }
}
