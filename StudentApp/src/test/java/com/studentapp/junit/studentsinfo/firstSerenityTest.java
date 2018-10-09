package com.studentapp.junit.studentsinfo;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(SerenityRunner.class)
public class firstSerenityTest {

    @Before
    public void init(){
        RestAssured.baseURI = "http://localhost:8080/student";
    }

    @Test
    public void getAllStudents(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200);

    }

    @Test
    public void thisIAFailingTest() {
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(500);
    }

    @Pending
    @Test
    public void thisIsAPendingTest(){

    }

    @Ignore
    @Test
    public void thisIsASkippedTest(){

    }

    @Test
    public void thisIsATestWithError(){
        System.out.println("This is an error" + (5/0));

    }

    @Test
    public void fileDoesNotExist() throws FileNotFoundException {
        File file = new File("E://path.txt");
        FileReader fr = new FileReader(file);

    }

    @Manual
    @Test
    public void thisIsAManualTest() {

    }

    @Title("This test will get the information of all the students n the student App")
    @Test
    public void test01(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200);

    }

}

