package assignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC001 {

    @Test
    public void testCase001(){
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
        int bookingid = response.then().extract().path("bookingid");

        System.out.println("Booking id: " + bookingid);

    }
}
