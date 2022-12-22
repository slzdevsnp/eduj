package database.snowflake;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JdbcDatasetDAOTest {

    private DatasetDAO dao = new JdbcDatasetDAO();

    @Test
    public void findAll() throws Exception {
        List<Dataset> ds = dao.findAll();
        System.out.println(ds);
        assertTrue(ds.size() > 0);
    }

    @Test
    public void findById() throws  Exception{
        Integer tst_id = Integer.valueOf(1001);
        Dataset ds = dao.findById(tst_id);
        System.out.println(ds);
        assertEquals(ds.getData_set_id(),tst_id);
        assertEquals(ds.getName(),"pro de wnd ec06 mwh/h cet min15 f ");
    }

}
