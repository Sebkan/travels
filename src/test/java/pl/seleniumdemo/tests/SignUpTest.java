package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        String lastName = "Kanecki";
        int randomNumber = (int) (Math.random()*1000);
        String email = "test"+randomNumber+"@tester.pl";
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        hotelSearchPage.signUpForm();
        signUpPage.setNameLastPhone("Sebastian",lastName,"666666666");
        signUpPage.setEmail(email);
        signUpPage.setPassword("test123","test123");
        signUpPage.submit();
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertTrue(loggedUserPage.headingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.headingText(), "Hi, Sebastian Kanecki");
    }

        @Test
        public void notFullData() {
            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
            hotelSearchPage.signUpForm();
            SignUpPage signUpPage = new SignUpPage(driver);
            signUpPage.submit();
            Assert.assertEquals(signUpPage.getErrorNames().get(0),"The Email field is required.");
            Assert.assertEquals(signUpPage.getErrorNames().get(1),"The Password field is required.");
            Assert.assertEquals(signUpPage.getErrorNames().get(2),"The Password field is required.");
            Assert.assertEquals(signUpPage.getErrorNames().get(3),"The First name field is required.");
            Assert.assertEquals(signUpPage.getErrorNames().get(4),"The Last Name field is required.");

        }
        @Test
        public void wrongEmail(){
            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
            SignUpPage signUpPage = new SignUpPage(driver);
            hotelSearchPage.signUpForm();
            signUpPage.setNameLastPhone("Sebastian","Kanecki","666666666");
            signUpPage.setEmail("tester");
            signUpPage.setPassword("test123","test123");
            signUpPage.submit();
            Assert.assertEquals(signUpPage.getEmailError(),"The Email field must contain a valid email address.");
        }
    }

