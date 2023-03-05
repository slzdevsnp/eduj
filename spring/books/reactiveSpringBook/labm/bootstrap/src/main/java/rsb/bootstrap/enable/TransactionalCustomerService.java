package rsb.bootstrap.enable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rsb.bootstrap.BaseCustomerService;
import rsb.bootstrap.Customer;

import javax.sql.DataSource;
import java.util.Collection;

@Service
@Transactional // all public methods in this class will support transactions
public class TransactionalCustomerService extends BaseCustomerService {

	public TransactionalCustomerService(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public Collection<Customer> save(String... names) {
		return super.save(names);
	}

	@Override
	public Customer findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Collection<Customer> findAll() {
		return super.findAll();
	}

}
