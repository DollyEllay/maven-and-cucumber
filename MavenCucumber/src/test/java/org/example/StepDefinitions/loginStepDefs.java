package org.example.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class loginStepDefs {

    @Given("User open login page")
    public void login_p(){

        hooks.driver.findElement(By.cssSelector("a[href=\"/login\"]")).click();

    }

    @When("User enter Valid username and password")
    public void user_info(){
        hooks.driver.findElement(By.id("username")).sendKeys("tomsmith");
        hooks.driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("SuperSecretPassword!");
    }



    @And("User clicks login button")
    public void user_clicks_login_button() {

        hooks.driver.findElement(By.xpath("//button[@class=\"radius\"]")).click();

    }
    @Then("User login successfully")
    public void user_login_successfully() {
        SoftAssert soft = new SoftAssert();

        String actualUrl = hooks.driver.getCurrentUrl();
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        soft.assertEquals(actualUrl, expectedUrl, "First assertion is failed");

        //2- <h2> element contains text "Secure Area"
        String actualText = hooks.driver.findElement(By.cssSelector("div[class=\"example\"] h2")).getText();
        String  expectedText = "Secure Area";
        soft.assertEquals(actualText, expectedText, "Second assertion is failed");

        soft.assertAll();
    }


}
