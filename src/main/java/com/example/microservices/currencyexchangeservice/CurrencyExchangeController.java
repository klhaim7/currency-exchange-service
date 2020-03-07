package com.example.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    @Autowired
    Environment environment;

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retriveExchangeValue (@PathVariable String from,@PathVariable String to ){
        //ExchangeValue exchangeValue = new ExchangeValue(1000L,from,to, BigDecimal.valueOf(65));
        ExchangeValue exchangeValue = repository.findByFromAndTo(from,to);
        try{
            System.out.println("exchangeValue.toString():"+exchangeValue.toString());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error While trying to print exchangeValue.toString():"+e.getMessage());
        }
        try{
            exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error While trying to set port:"+e.getMessage());
        }
        try{
            System.out.println("exchangeValue.getPort():"+exchangeValue.getPort());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error While trying to get port:"+e.getMessage());
        }
        //exchangeValue.setPort(0);
        return exchangeValue;
    }
}
