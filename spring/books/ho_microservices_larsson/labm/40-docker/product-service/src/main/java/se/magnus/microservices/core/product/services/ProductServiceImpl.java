package se.magnus.microservices.core.product.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import se.magnus.api.core.product.Product;
import se.magnus.api.core.product.ProductService;
import se.magnus.util.exceptions.InvalidInputException;
import se.magnus.util.exceptions.NotFoundException;
import se.magnus.util.http.ServiceUtil;

@RestController
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ServiceUtil serviceUtil;

    @Autowired
    public ProductServiceImpl(ServiceUtil serviceUtil){
        this.serviceUtil = serviceUtil;
    }

    /*
    currently no use of db so return a hadrdcoded response based on
    productId along with service address from ServiceUtil
    */
    public Product getProduct(int productId){
        LOG.debug("/product return the found product for productid={}",
                productId);

        //case to test invalid product ids
        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: "
                    + productId);
        }

        //special case to test a missing product
        if (productId == 13) {
            throw new NotFoundException("No product found for productId: "
                    + productId);
        }

        return new Product(productId,
                "name-" + productId,
                123,
                serviceUtil.getServiceAddress());
    }
}
