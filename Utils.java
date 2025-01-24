package assignment;

import com.github.javafaker.Faker;

public class Utils {

    public static String firstName(){
        Faker faker = new Faker();
       return faker.name().firstName();
    }

    public static String lastname(){
        Faker faker = new Faker();
        return faker.name().lastName();
    }


}
