package com.studentapp.junit.studentsinfo;

import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import com.studentapp.utils.reusableSpecifications;
import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class studentsCRUDTest extends TestBase {

    static String firstName = "SMOKEUSER" + TestUtils.getRandomValue();
    static String lastName = "SMOKEUSER";
    static String programme = "ComputerScience";
    static String email = TestUtils.getRandomValue() + "xyz@email";
    static int studentId;

    @Steps
    StudentSerenitySteps steps;

    @Title("This test will create a new student")
    @Test
    public void test001(){

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("JAVA");
        courses.add("C++");

        steps.createStudent(firstName, lastName, email, programme, courses)
        .statusCode(201)
        .spec(reusableSpecifications.getGenericResponseSpec());

    }
    @Title("Verify if the student was added to the application")
    @Test
    public void test002(){

        HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);

        System.out.println("The value is: " + value);
        assertThat(value,hasValue(firstName));

        studentId = (int) value.get("id");
    }

    @Title("Update the user information and verify updated information")
    @Test
    public void test003(){


        ArrayList<String> courses = new ArrayList<String>();
        courses.add("JAVA");
        courses.add("C++");

        firstName = firstName + "_Updated";

        steps.updateStudent(studentId,firstName,lastName,email,programme,courses);

        HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);

        assertThat(value,hasValue(firstName));

    }

    @Title("Delete a student and verify if the student is deleted")
    @Test
    public void test004(){

        steps.deleteStudent(studentId)
                .statusCode(204);

        steps.getStudentById(studentId)
                .statusCode(404);


    }

}
