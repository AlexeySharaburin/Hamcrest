import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Collection<Person> persons = new ArrayList<>();

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        under18(persons);

        recruits(persons);

        employables(persons);

    }

    public static long under18(Collection<Person> persons) {
        Stream<Person> stream = persons.stream();
        long countUnder18 = stream
                .filter(person -> (person.getAge() < 18))
                .count();

        System.out.format("Число несовершеннолетних жителей: %s человек\n", countUnder18);
        System.out.println("-----------------------------------------------");
        return countUnder18;
    }

    public static List<String> recruits(Collection<Person> persons) {
        Stream<Person> stream = persons.stream();
        List<String> recruits = stream
                .filter(person -> (person.getSex() == Sex.MAN))
                .filter(person -> ((person.getAge() > 18) && (person.getAge() < 27)))
                .map(Person::toString)
                .collect(Collectors.toList());

        System.out.println("Данные жителей призывного возраста:\n " + recruits);
        System.out.println("-----------------------------------------------");
        return recruits;
    }

    public static List<String> employables(Collection<Person> persons) {
        Stream<Person> stream = persons.stream();
        List<String> employables = stream
                .filter(person -> (person.getEducation() == Education.HIGHER))
                .filter(person -> (((person.getSex() == Sex.MAN) && ((person.getAge() > 18) && (person.getAge() < 65))) || ((person.getSex() == Sex.WOMAN) && ((person.getAge() > 18) && (person.getAge() < 60)))))
                .sorted(Comparator.comparing(person -> person.getName()))
                .map(person -> person.toString())
                .collect(Collectors.toList());

        System.out.println("Данные работоспособных жителей с высшим образованием:\n " + employables);
        System.out.println("-----------------------------------------------");
        return employables;
    }
}
