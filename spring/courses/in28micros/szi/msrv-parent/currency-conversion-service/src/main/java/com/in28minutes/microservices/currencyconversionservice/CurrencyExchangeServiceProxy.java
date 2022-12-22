package com.in28minutes.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//scenario 1
//@FeignClient(name="currency-exchange-service", url="localhost:8000")  // url hardcoded

//scenario 2
// //requires ribbon.listOfServers property in application.properties
// // currency-exchange-service.ribbon.listOfServers=http://localhost:8000,http://localhost:8001
//@FeignClient(name="currency-exchange-service")
//@RibbonClient(name="currency-exchange-service")

//scenario 3  call through zuul api gateway
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")

public interface CurrencyExchangeServiceProxy {
    //points to a method in Controller in a proxied Service (currency-exchange-service)
    //scenario 2
    //@GetMapping("/currency-exchange/from/{from}/to/{to}")
    //scenario 3
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue
    (@PathVariable("from") String from, @PathVariable("to") String to);
}
