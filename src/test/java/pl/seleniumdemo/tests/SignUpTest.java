package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.time.Duration;

public class SignUpTest extends DriverFactory {

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
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.ignoring(NoSuchElementException.class);
        wait.withTimeout(Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(loggedUserPage.heading));
        Assert.assertTrue(loggedUserPage.headingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.headingText(), "Hi, Sebastian Kanecki");
    }
    @Test
    public void signUpTest2() {
        int randomNumber = (int) (Math.random()*1000);
        String email = "test"+randomNumber+"@tester.pl";
        User user = new User();
        user.setFirstName("Sebastian");
        user.setLastName("Kanecki");
        user.setPhone("666666666");
        user.setEmail(email);
        user.setPassword("test123");
        user.setConfirmPassword("test123");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        hotelSearchPage.signUpForm();

        signUpPage.fillSignUpForm(user);
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.ignoring(NoSuchElementException.class);
        wait.withTimeout(Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(loggedUserPage.heading));
        Assert.assertTrue(loggedUserPage.headingText().contains(user.getLastName()));
        Assert.assertEquals(loggedUserPage.headingText(), "Hi, Sebastian Kanecki");
    }
        @Test
        public void notFullData() {
            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
            hotelSearchPage.signUpForm();
            SignUpPage signUpPage = new SignUpPage(driver);
            signUpPage.submit();

            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.ignoring(java.util.NoSuchElementException.class);
            wait.withTimeout(Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']//p")));

            Assert.assertEquals(signUpPage.getErrorNames().get(0),"The Email field is required.");
            Assert.assertEquals(signUpPage.getErrorNames().get(1),"The Password field is required.");
            Assert.assertEquals(signUpPage.getErrorNames().get(2),"The Password field is required.");
            Assert.assertEquals(signUpPage.getErrorNames().get(3),"The First name field is required.");
            Assert.assertEquals(signUpPage.getErrorNames().get(4),"The Last Name field is required.");

            /*SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(alertList.contains("The Email field is required."));
            softAssert.assertTrue(alertList.contains("The Password field is required."));
            softAssert.assertTrue(alertList.contains("The Password field is required."));
            softAssert.assertTrue(alertList.contains("The First name field is required."));
            softAssert.assertTrue(alertList.contains("The Last Name field is required."));
            softAssert.assertAll();*/
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
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.ignoring(java.util.NoSuchElementException.class);
            wait.withTimeout(Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']//p")));
            Assert.assertEquals(signUpPage.getEmailError(),"The Email field must contain a valid email address.");
        }
    }

