package rsb.bootstrap.basicdi;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import rsb.bootstrap.DataSourceUtils;
import rsb.bootstrap.Demo;

public class Application {

    public static void main(String[] args) {
        /*
        datasource initialization is factored out into a Application runnable class
        It is no longer inside DataSourceCustomerService
         */

        var datasource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
        var initializedDataSource = DataSourceUtils.initializeDdl(datasource);

        var cs = new DataSourceCustomerService(initializedDataSource);
        Demo.workWithCustomerService(Application.class, cs);
    }

}
