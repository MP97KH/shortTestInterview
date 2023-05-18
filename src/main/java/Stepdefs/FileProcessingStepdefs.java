package Stepdefs;

import Helpers.FileReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static Helpers.FilePresenceAndAccessCheckHelper.*;
import static Helpers.FileReader.*;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileProcessingStepdefs {

    public static FileReader fileReader;
    @Given("the file with the name {string} and {string} extension exists")
    public void the_file_with_name_exists(String fileName, String fileExtension) {
        assertTrue("No file with such name or extension was found", isFileExisting(fileName,fileExtension));
    }

    @And("the file with the name {string} and {string} extension is readable")
    public void the_file_with_the_name_and_extension_is_readable(String fileName, String fileExtension) {
        assertTrue("File is not readable", isFileReadable(fileName, fileExtension));
    }

    @And("the file {string} verification can be started")
    public void the_file_verification_can_be_started(String filePath){
        initializeInputStream(filePath);
    }

    @And("the file is closed")
    public void the_file_is_closed(){
       closeFile();
    }

    @Then("the count of columns in the file equals {int}")
    public void the_count_of_columns_in_the_file_equals(int expectedCountOfColumns){
        int actualColumnsCount = getColumnsCount();
        assertThat( "Actual columns count " + actualColumnsCount + " doesn`t equal expected one" + expectedCountOfColumns,
                getColumnsCount() == expectedCountOfColumns);
    }

    @When("the columns are named: {string}, {string}, {string}, {string}")
    public void the_file_has_headers(String expectedColumn1, String expectedColumn2, String expectedColumn3, String expectedColumn4) {
        ArrayList actualColumnsNames = getFileHeader();
        assertThat("The actual name of column #1 doesn`t match the expected one", actualColumnsNames.get(0).equals(expectedColumn1));
        assertThat("The actual name of column #2 doesn`t match the expected one", actualColumnsNames.get(1).equals(expectedColumn2));
        assertThat("The actual name of column #3 doesn`t match the expected one", actualColumnsNames.get(2).equals(expectedColumn3));
        assertThat("The actual name of column #4 doesn`t match the expected one", actualColumnsNames.get(3).equals(expectedColumn4));
    }

    @Then("there is more than {int} lines in the file")
    public void there_is_more_than_specified_count_lines_in_the_file(int expectedLinesCount) {
        int actualCountOfLines = getRowsCount();
        assertThat("Actual count of rows " + actualCountOfLines + " is less than " + expectedLinesCount,
                actualCountOfLines > expectedLinesCount);
    }
}
