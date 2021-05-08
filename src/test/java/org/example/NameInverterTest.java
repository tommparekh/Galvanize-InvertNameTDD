package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameInverterTest {
    private String name;
    private static NameInverter nameInverter;

    @BeforeAll
    public static void setup() {
        nameInverter = new NameInverter();
    }

    @AfterEach
    public void cleanUp() {
        name = "";
    }

//    -zero
//    Given I have an empty name
//    When I try to invert the name
//    Then a message should ask me to insert a name
    @Test
    public void testIfNameIsEmptyThenReturnInsertANameMessage() {
        // Arrange
        name = "";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Please insert a name", message );
    }

    @Test
    public void testIfNameIsEmptyObjectThenReturnInsertANameMessage() {
        // Arrange
        name = new String();

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Please insert a name", message );
    }

    @Test
    public void testIfNameIsBlankThenReturnInsertANameMessage() {
        // Arrange
        name = " ";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Please insert a name", message );
    }


//    -one
//    Given I have either a first name or the last name but not both
//    When I try to invert the name
//    Then it should return the name as it is written

    @Test
    public void testIfFirstOrLastNameIsGivenThenReturnSameName() {
        // Arrange
        name = "John";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("John", message  );
    }

    @Test
    public void testIfFirstOrLastNameWithLeadingSpaceIsGivenThenReturnSameName() {
        // Arrange
        name = " John";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("John", message  );
    }

    @Test
    public void testIfFirstOrLastNameWithTrailingSpaceIsGivenThenReturnSameName() {
        // Arrange
        name = " John";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("John", message  );
    }

//    -many
//    Given I have at least two names
//    When I try to invert the name
//    Then it should return “lastname, firstname”

    @Test
    public void testIfFirstAndLastNameIsGivenThenReturnInvertedName() {
        // Arrange
        name = "John Smith";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Smith, John", message );
    }

    @Test
    public void testIfFirstAndLastNameWithLeadingSpaceIsGivenThenReturnInvertedName() {
        // Arrange
        name = " John Smith";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Smith, John", message );
    }

    @Test
    public void testIfFirstAndLastNameWithTrailingSpaceIsGivenThenReturnInvertedName() {
        // Arrange
        name = "John Smith ";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Smith, John", message );
    }

    @Test
    public void testIfFirstAndLastNameWithExtraSpaceBetweenNamesIsGivenThenReturnInvertedName() {
        // Arrange
        name = "John   Smith ";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Smith, John", message );
    }

//    Given I have at least two names that contains a prefix such as Mr. Mrs. or Ms.
//    When I try to invert the name
//    Then it should keep the prefix in the beginning of the name

    @Test
    public void testIfFirstAndLastNameWithPrefixIsGivenThenReturnInvertedNameWithPrefixAtStart() {
        // Arrange
        name = "Mr. John Smith ";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Mr. Smith, John", message );
    }

    @Test
    public void testIfFirstOrLastNameWithPrefixIsGivenThenReturnInvertedNameWithPrefixAtStart() {
        // Arrange
        name = "Mrs. Smith";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Mrs. Smith", message );
    }

    @Test
    public void testIfFirstOrLastNameWithPrefixAtEndThenReturnInvalidNameMessage() {
        // Arrange
        name = "Smith Mrs. ";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Invalid name", message );
    }

    @Test
    public void testIfFirstAndLastNameWithPrefixAtEndThenReturnInvalidNameMessage() {
        // Arrange
        name = "John Smith Mrs. ";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Invalid name", message );
    }

    @Test
    public void testIfFirstAndLastNameWithPrefixInMiddleThenReturnInvalidNameMessage() {
        // Arrange
        name = "John Mrs. Smith";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Invalid name", message );
    }


//    Given I have at least two names that contains a suffix such as Sr. Jr or II, III
//    When I try to invert the name
//    Then it should keep the suffix at the end of the name

    @Test
    public void testIfFirstAndLastNameWithSuffixIsGivenThenReturnInvertedNameWithSuffixAtEnd() {
        // Arrange
        name = "John Smith Sr.";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Smith, John Sr.", message );
    }

    @Test
    public void testIfFirstOrLastNameWithSuffixIsGivenThenReturnInvertedNameWithSuffixAtEnd() {
        // Arrange
        name = "Smith III";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Smith III", message );
    }

    @Test
    public void testIfFirstOrLastNameWithSuffixAtStartThenReturnInvalidNameMessage() {
        // Arrange
        name = "Jr. Smith";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Invalid name", message );
    }

    @Test
    public void testIfFirstAndLastNameWithSuffixInMiddleThenReturnInvalidNameMessage() {
        // Arrange
        name = "John II Smith";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Invalid name", message );
    }

    @Test
    public void testIfFirstAndLastNameWithSuffixAndPrefixIsGivenThenReturnInvertedNameWithPrefixAtStartAndSuffixAtEnd() {
        // Arrange
        name = "Mr. John Smith Sr.";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Mr. Smith, John Sr.", message );
    }

    @Test
    public void testIfFirstOrLastNameWithSuffixAndPrefixIsGivenThenReturnInvertedNameWithPrefixAtStartAndSuffixAtEnd() {
        // Arrange
        name = "Mrs. Smith Jr.";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Mrs. Smith Jr.", message );
    }

    @Test
    public void testIfFirstAndLastNameWithSuffixAndPrefixIsGivenInMiddleThenReturnInvertedNameWithPrefixAtStartAndSuffixAtEnd() {
        // Arrange
        name = " John Mr. Sr. Smith ";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Invalid name", message );
    }


    // name with 5 words

    @Test
    public void testIfFirstLastAndExtraNameWithSuffixAndPrefixIsGivenThenReturnInvalidNameMessage() {
        // Arrange
        name = "Mr. John X Smith Jr. ";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Invalid name", message );
    }


    // name with 3 words without prefix or suffix
    @Test
    public void testIfFirstLastAndExtraNameIsGivenThenReturnInvalidNameMessage() {
        // Arrange
        name = "John X Smith";

        // Act
        String message = nameInverter.invertName(name);

        // Assert
        assertEquals("Invalid name", message );
    }

}
