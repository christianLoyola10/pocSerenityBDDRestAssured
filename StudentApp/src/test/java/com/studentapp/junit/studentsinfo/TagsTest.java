package com.studentapp.junit.studentsinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase {

    @WithTag("studentFeature:NEGATIVE")
    @Title("Provide 405 Status code when incorrect HTTP method is used to access a resource")
    @Test
    public void invalidMethod (){

        SerenityRest
            .rest()
            .given()
            .when()
                .post("/list")
                .then()
                .statusCode(405)
                .log()
                .all();
    }

    @WithTags(
            {
                    @WithTag("studentFeature:SMOKE"),
                    @WithTag("studentFeature:POSITIVE")
            }
    )
    @Title("This test will verify if a status code of 200 is returned for a GET request")
    @Test
    public void verifyIfTheStatusCodeIs200 (){

        SerenityRest
                .rest()
                .given()
                .when()
                .get("/list")
                .then()
                .statusCode(200);
    }

    @WithTags(
            {
                    @WithTag("studentFeature:SMOKE"),
                    @WithTag("studentFeature:NEGATIVE")
            }
            )
    @Title("This test will provide and error code of 400 when user trying to access an invalide resource")
    @Test
    public void incorrectResource (){

        SerenityRest
                .rest()
                .given()
                .when()
                .get("/list2")
                .then()
                .statusCode(400)
                .log()
                .all();
    }


}
