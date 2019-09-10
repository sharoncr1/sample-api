package com.mycom.thirdapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@SpringBootApplication
public class MainClass extends SpringBootServletInitializer {

    public static void main(String[] args){
//        SpringApplication.run(MainClass.class,args);

        String nodesString="HBEU,HBUS,HBSG,HBSP,HBAP";
        String currenciesString="USD, EUR, GBP, HKD, SGD, JPY, AUD, NZD, CAD, MXN, CNH, CZK, AED, TRY";

        List<String> nodes =  Arrays.asList(nodesString.split(",")).stream().distinct().collect(Collectors.toList());
        List<String> currencies = Arrays.asList(currenciesString.split(",")).stream().distinct().collect(Collectors.toList());

        nodes.replaceAll(String::trim);
        nodes.replaceAll(String::toUpperCase);

        currencies.replaceAll(String::trim);
        currencies.replaceAll(String::toUpperCase);

        List<String> insertedKeys = new ArrayList<>();
        String query;
        String sqlinsert = "INSERT IGNORE INTO `config` (`feature`, `key`, `value`, `readonly`, `description`) VALUES ";
        String sqlValue = "('autonet', 'node-pair_currency-pair', '{}', 0, '')";
        for(String node1 : nodes){
            for(String node2 : nodes){
                if(!node2.equalsIgnoreCase(node1)){
                    for(String currency1 : currencies){
                        for(String currency2 : currencies){
                            if(!currency1.equalsIgnoreCase(currency2)) {
                                if (!insertedKeys.contains(generateKey(node1, node2, currency1, currency2))) {
                                    insertedKeys.add(generateKey(node1, node2, currency1, currency2));
                                    query = sqlValue.replaceAll("node-pair_currency-pair",generateKey(node1, node2, currency1, currency2));
                                    if(insertedKeys.size()>1){
                                        sqlinsert = sqlinsert.concat(",");
                                    }
                                    sqlinsert = sqlinsert.concat("\n\t");
                                    sqlinsert = sqlinsert.concat(query);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        sqlinsert = sqlinsert.concat(";");

        System.out.println(sqlinsert);

        System.out.println("number of rows being inserted = " + insertedKeys.size());
    }

    private static String generateKey(String node1, String node2, String currency1, String currency2){
        return (getSorted(node1,node2)+"_"+getSorted(currency1,currency2));
    }

    private static String getSorted(String str1, String str2) {
        if (str1.compareTo(str2) < 0) {
            return str1.concat("-").concat(str2).toUpperCase();
        } else {
            return str2.concat("-").concat(str1).toUpperCase();
        }
    }
}
