package database.snowflake;

import java.util.List;

public interface DatasetDAO {
    List<Dataset> findAll();
    Dataset findById(Integer id);
}
