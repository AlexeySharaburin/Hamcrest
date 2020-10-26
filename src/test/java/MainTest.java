import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class MainTest {

    // Old test with hamcrest

    @Test
    void testUnder18_success() {

        Collection<Person> testPersons = new ArrayList<>();

        testPersons.add(new Person("Jack", "Evans", 29, Sex.MAN, Education.HIGHER));
        testPersons.add(new Person("Connor", "Young", 15, Sex.WOMAN, Education.FURTHER));

        long expected = 1;

        long result = Main.under18(testPersons);

        assertThat(expected, equalTo(result));
    }

    @Test
    void testUnder18_unsuccess() {

        Collection<Person> testPersons = new ArrayList<>();
        testPersons.add(new Person("Jack", "Evans", 29, Sex.MAN, Education.HIGHER));
        testPersons.add(new Person("Connor", "Young", 15, Sex.WOMAN, Education.FURTHER));

        long expected = 2;

        long result = Main.under18(testPersons);

        assertThat(expected, equalTo(result));
    }

    @Test
    void testRecruits_sucess() {

        Collection<Person> testPersons = new ArrayList<>();

        testPersons.add(new Person("Jack", "Evans", 26, Sex.MAN, Education.HIGHER));
        testPersons.add(new Person("Connor", "Young", 15, Sex.WOMAN, Education.FURTHER));

        Person expectedPerson = new Person("Jack", "Evans", 26, Sex.MAN, Education.HIGHER);

        List<String> expectedPersons = new ArrayList<>();

        expectedPersons.add(expectedPerson.toString());

        List<String> result = Main.recruits(testPersons);

        assertThat(expectedPersons, equalTo(result));

    }

    @Test
    void testRecruits_unsucess() {

        Collection<Person> testPersons = new ArrayList<>();
        testPersons.add(new Person("Jack", "Evans", 28, Sex.MAN, Education.HIGHER));
        testPersons.add(new Person("Connor", "Young", 15, Sex.WOMAN, Education.FURTHER));


        Person expectedPerson = new Person("Jack", "Evans", 25, Sex.MAN, Education.ELEMENTARY);

        List<String> expectedPersons = new ArrayList<>();

        expectedPersons.add(expectedPerson.toString());

        List<String> result = Main.recruits(testPersons);

        assertThat(expectedPersons, equalTo(result));


    }

    @Test
    void testEmployables_success() {

        Collection<Person> testPersons = new ArrayList<>();

        testPersons.add(new Person("Jack", "Evans", 28, Sex.MAN, Education.HIGHER));
        testPersons.add(new Person("Connor", "Young", 15, Sex.WOMAN, Education.FURTHER));

        Person expectedPerson = new Person("Jack", "Evans", 28, Sex.MAN, Education.HIGHER);

        List<String> expectedPersons = new ArrayList<>();

        expectedPersons.add(expectedPerson.toString());

        List<String> result = Main.employables(testPersons);

        assertThat(expectedPersons, equalTo(result));
    }

    @Test
    void testEmployables_unsuccess() {

        Collection<Person> testPersons = new ArrayList<>();

        testPersons.add(new Person("Jack", "Evans", 77, Sex.MAN, Education.HIGHER));
        testPersons.add(new Person("Connor", "Young", 15, Sex.WOMAN, Education.FURTHER));

        Person expectedPerson = new Person("Jack", "Evans", 28, Sex.MAN, Education.HIGHER);

        List<String> expectedPersons = new ArrayList<>();

        expectedPersons.add(expectedPerson.toString());

        List<String> result = Main.employables(testPersons);

        assertThat(expectedPersons, equalTo(result));
    }

    // New tests with Hamcrest

    @Test
    void hamcrest_testUnder18_success() {

        Collection<Person> testPersons = new ArrayList<>();

        testPersons.add(new Person("Jack", "Evans", 17, Sex.MAN, Education.HIGHER));
        testPersons.add(new Person("Connor", "Young", 15, Sex.WOMAN, Education.FURTHER));
        testPersons.add(new Person("Harry", "Harris", 11, Sex.MAN, Education.ELEMENTARY));

        long expected1 = 2;
        long expected2 = 3;
        long expected3 = 4;

        long result = Main.under18(testPersons);

        assertThat(result, is(allOf(greaterThan(expected1), lessThanOrEqualTo(expected2), not(equalTo(expected3)))));
    }

    @Test
    void hamcrest_testRecruits_isNotEmpty() {

        Collection<Person> testPersons = new ArrayList<>();

        testPersons.add(new Person("Jack", "Evans", 26, Sex.MAN, Education.HIGHER));
        testPersons.add(new Person("Connor", "Young", 15, Sex.WOMAN, Education.FURTHER));
        testPersons.add(new Person("Harry", "Harris", 40, Sex.MAN, Education.ELEMENTARY));
        testPersons.add(new Person("Samuel", "Brown", 19, Sex.MAN, Education.SECONDARY));

        List<String> result = Main.recruits(testPersons);

        assertThat(result, is((not(empty()))));
    }

    @Test
    void hamcrest_testEmployables() {

        Collection<Person> testPersons = new ArrayList<>();

        testPersons.add(new Person("Jack", "Evans", 26, Sex.MAN, Education.HIGHER));
        testPersons.add(new Person("Connor", "Young", 22, Sex.WOMAN, Education.FURTHER));
        testPersons.add(new Person("Harry", "Harris", 40, Sex.MAN, Education.ELEMENTARY));
        testPersons.add(new Person("Samuel", "Brown", 19, Sex.MAN, Education.SECONDARY));
        testPersons.add(new Person("Jack", "Connor", 77, Sex.MAN, Education.HIGHER));


        Person expectedPerson1 = new Person("Jack", "Evans", 26, Sex.MAN, Education.HIGHER);

        List<String> result = Main.employables(testPersons);

        assertThat(result, contains(expectedPerson1.toString()));

    }

}