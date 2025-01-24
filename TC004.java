
package assignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TC004 {

    int bookingid;

    @Test
    public void testCase006(){
        RestAssured.baseURI ="https://restful-booker.herokuapp.com";
        RestAssured.basePath ="/booking";

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2022-01-01");
        bookingDates.setCheckout("2025-01-11");

        Booking booking = new Booking();
        booking.setAdditionalneeds("testAdd");
        booking.setFirstname(Utils.firstName());
        booking.setLastname(Utils.lastname());
        booking.setTotalprice(10.11);
        booking.setDepositpaid(true);
        booking.setBookingdates(bookingDates);


        Response response = given().
                contentType(ContentType.JSON)
                .body(booking)
                .log().all().post();

        response.prettyPrint();
        bookingid = response.then().extract().path("bookingid");

        System.out.println("Booking id: " + bookingid);

    }

    @Test(dependsOnMethods ="testCase006")
    public void testCase007(){
        RestAssured.baseURI ="https://restful-booker.herokuapp.com";
        RestAssured.basePath ="/booking/";

        Response response = given()
                .log().all().get();
       List<String> bookingid = Collections.singletonList(response.then().extract().path("bookingid").toString());

        for (String id : bookingid){
            System.out.println(id);
        }
        response.prettyPrint();
        System.out.println( response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);


    }


}
