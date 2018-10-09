package com.studentapp.cucumber.serenity;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.reusableSpecifications;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentSerenitySteps {

    @Step("Creating student with firstname: {0}, lastname: {1}, email: {2}, programme: {3}, courses: {4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, ArrayList<String> courses){

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.given()
                .spec(reusableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .post()
                .then();


    }

    @Step("Getting the Student Information With the FirstName: {0}")
    public HashMap<String, Object> getStudentInfoByFirstName(String firstName){

        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        return SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path(p1+firstName+p2);
    }

    @Step("Updaying student information with studenID: {0}, firstname: {1}, lastname: {2}, email: {3}, programme: {4}, courses: {5}")
    public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String email, String programme, ArrayList<String> courses){

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.given()
                    .spec(reusableSpecifications.getGenericRequestSpec())
                    .log()
                    .all()
                    .when()
                    .body(student)
                    .put("/" + studentId)
                    .then();


    }

    @Step("Deleting student information by using studentID: {0}")
    public ValidatableResponse deleteStudent (int studentId){

        return SerenityRest
                    .rest()
                    .given()
                    .when()
                    .delete("/" + studentId).then();
        }

    @Step("Getting student by using studentID: {0}")
    public ValidatableResponse getStudentById (int studentId){

        return SerenityRest
                    .rest()
                    .given()
                    .when()
                    .get("/" + studentId).then();


        }
}
