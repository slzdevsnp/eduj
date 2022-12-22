package database.snowflake;

import org.checkerframework.common.util.report.qual.ReportOverride;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDatasetDAO implements  DatasetDAO{

    private static final String DRIVER = "net.snowflake.client.jdbc.SnowflakeDriver";
    //private static final String URL = "jdbc:mysql://localhost:3306/hr";
    private static final String URL = "jdbc:snowflake://alpiq.eu-central-1.snowflakecomputing.com:443?warehouse=PRV_DIP2_WH&db=PRV_DIP2&schema=dip2_poc_schema";
    private static final String USER = "USER_DIP2";     //szi put this in property file
    private static final String PASSWORD = "R4Uf8ZhsG86zduAe";


    public JdbcDatasetDAO(){
        try{
            Class.forName(DRIVER);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Dataset> findAll(){
        List<Dataset> dsets = new ArrayList<>();
        String sqlstr="select DATA_SET_ID, IDENTIFIER, NAME from DATA_SET";
        try(
                Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
                PreparedStatement pst = conn.prepareStatement(sqlstr)
                ){
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                dsets.add(new Dataset(rs.getInt(1),rs.getInt(2),rs.getString(3)));
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsets;
    }

    @Override
    public Dataset findById(Integer id){
        Dataset d = null;
        String sqlstr="select DATA_SET_ID, IDENTIFIER, NAME from DATA_SET WHERE DATA_SET_ID=?";
        try(
                Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
                PreparedStatement pst = conn.prepareStatement(sqlstr)
        ){
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                d = new Dataset(rs.getInt(1),rs.getInt(2),rs.getString(3));
            }
            rs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return d;
    }

}
