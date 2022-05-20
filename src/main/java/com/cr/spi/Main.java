package com.cr.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import static com.cr.common.Facility.print;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<UserService> sl = ServiceLoader.load(UserService.class);
        Iterator<UserService> iterator = sl.iterator();
        while(iterator.hasNext()){
            UserService service = iterator.next();
            print(service.getClass().getName() + " - " + service.getUserCount());
        }
    }
}
