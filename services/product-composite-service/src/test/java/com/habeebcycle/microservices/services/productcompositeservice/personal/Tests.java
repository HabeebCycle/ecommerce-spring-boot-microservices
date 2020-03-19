package com.habeebcycle.microservices.services.productcompositeservice.personal;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    public static void main(String args[]){
        List<Integer> list = new ArrayList<>();
        Flux.just(1 ,2, 3, 4)
                .filter(x -> x % 2 == 0)
                .map(x -> x * 2)
                .log()
                .subscribe(list::add);
        System.out.println(list);
    }
}
