package rsb.bootstrap.basicdi;

import rsb.bootstrap.BaseCustomerService;

import javax.sql.DataSource;

public class DataSourceCustomerService extends BaseCustomerService {

    // this CustomerServices only needs a pointer to a DataSource
    DataSourceCustomerService(DataSource ds) {
        super(ds);
    }

}



