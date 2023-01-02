import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = getPeople();

        // Imperative approach

/*        List<Person> females = new ArrayList<>();
        for (Person person: people){
            if (person.getGender().equals(Gender.FEMALE)){
                females.add(person);
            }
        }
        females.forEach(System.out::println);*/

        // Declarative approach

        // Filter

        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE)).toList();
        females.forEach(System.out::println);

        // Sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender)).toList();
        sorted.forEach(System.out::println);

        // All match
        boolean allMatch =  people.stream()
                .allMatch(person -> person.getAge() > 2);
        System.out.println(allMatch);

        // Any match
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 12 );
        System.out.println(anyMatch);

        // None match
        boolean noneMatch = people.stream()
                        .noneMatch(person -> person.getName().equals("Junior"));
        System.out.println(noneMatch);

        // Max
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // Min
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // Group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });


        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);

        oldestFemaleAge.ifPresent(System.out::println);
    }


    private static List<Person> getPeople() {
        return List.of(
                new Person("Junior", 26, Gender.MALE),
                new Person("Sam Lifouala", 22, Gender.MALE),
                new Person("James Itoua", 27, Gender.MALE),
                new Person("Sarah Mbon", 24, Gender.FEMALE),
                new Person("Deborah Itoua", 25, Gender.FEMALE),
                new Person("Julia Akouala", 21, Gender.FEMALE)
        );
    }
}
