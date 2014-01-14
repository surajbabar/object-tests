import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

public class PersonTest {
    @Test
    public void testEqualsWithLiteralsAndStringConstructor() throws Exception {
        String str1 = new String("String");
        String str2 = new String("String");
        assertEquals(true, str1.equals(str2));
        assertEquals(true, str1.equals("String"));

    }

    @Test
    public void testEqualsWithWrongEqualsSameValues() throws Exception {
        Person p1 = new Person("Person", 20);
        Person p2 = new Person("Person", 20);

        assertEquals(false, p1.equals(p2));
        assertEquals(false, p2.equals(p1));
    }

    @Test
    public void testEqualsWithWrongEqualDifferentValues() throws Exception {

        Person p3 = new Person("Person3", 20);
        Person p4 = new Person("Person4", 20);

        assertEquals(false, p3.equals(p4));
        assertEquals(false, p4.equals(p3));
    }

    @Test
    public void testEqualsWithCorrectEqualSameValues() throws Exception {

        Person p3 = new Person("Person", 20);
        Person p4 = new Person("Person", 20) {
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Person)) return false;

                Person person = (Person) o;

                if (this.getAge() != person.getAge()) return false;
                if (this.getName() != null ? !this.getName().equals(person.getName()) : person.getName() != null)
                    return false;

                return true;
            }
        };

        assertEquals(true, p4.equals(p3));
    }

    @Test
    public void testEqualsWithCorrectEqualDifferentValues() throws Exception {

        Person p3 = new Person("Person3", 20);
        Person p4 = new Person("Person4", 20) {
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Person)) return false;
                Person person = (Person) o;
                if (this.getAge() != person.getAge()) return false;
                if (this.getName() != null ? !this.getName().equals(person.getName()) : person.getName() != null)
                    return false;

                return true;
            }
        };

        assertEquals(false, p3.equals(p4));
    }

    @Test
    public void testHashWithCorrectHashValue() throws Exception {
        Person p3 = new Person("Person3", 20);
        Person p4 = new Person("Person3", 20);
        int actualp3 = p3.hashCode();
        int actualp4 = p4.hashCode();
        assertEquals(actualp3, actualp4);
    }

    @Test
    public void testHashWithWrongHashValue() throws Exception {

        Person p3 = new Person("Person", 20) {
            public int hashCode() {
                return super.hashCode();
            }
        };
        Person p4 = new Person("Person", 20) {
            public int hashCode() {
                return super.hashCode();
            }
        };
        int actual = p3.hashCode();
        int expected = p4.hashCode();
        assertNotSame(actual, expected);

    }

    @Test
    public void testEqualPersonHashMap() throws Exception {
        Person suraj = new Person("suraj", 19);
        Person akshay = new Person("akshay", 19);
        List Persons = new ArrayList();
        Persons.add(suraj);
        Persons.add(akshay);
        Map<Integer, List<Person>> Names = new HashMap<Integer, List<Person>>();
        Names.put(1, Persons);
        for (Map.Entry<Integer, List<Person>> entry : Names.entrySet()) {
            List<Person> people = entry.getValue();
            assertEquals(false, people.get(0).equals(people.get(1)));
        }
    }

    @Test
    public void testHashPersonHashMap() throws Exception {
        Person suraj = new Person("suraj", 19);
        Person akshay = new Person("akshay", 19);
        List Persons = new ArrayList();
        Persons.add(suraj);
        Persons.add(akshay);
        Map<Integer, List<Person>> Names = new HashMap<Integer, List<Person>>();
        Names.put(1, Persons);
        for (Map.Entry<Integer, List<Person>> entry : Names.entrySet()) {
            List<Person> people = entry.getValue();
            assertEquals(false, people.get(0).hashCode() == people.get(1).hashCode());
        }
    }

    @Test
    public void testNameLengthComparator() throws Exception {
        Person swamiji = new Person("Swamiji", 3);
        Person kunal = new Person("Kunal", 50);
        Person digvijay = new Person("Digvijay", 35);
        List people = new ArrayList();
        people.add(swamiji);
        people.add(kunal);
        people.add(digvijay);
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().length() - p2.getName().length();
            }
        });
        List<Person> expected = new ArrayList();
        expected.add(kunal);
        expected.add(swamiji);
        expected.add(digvijay);

        assertEquals(people, expected);
    }

    @Test
    public void testNameComparator() throws Exception {
        Person swamiji = new Person("Swamiji", 3);
        Person kunal = new Person("Kunal", 50);
        Person digvijay = new Person("Digvijay", 35);
        List people = new ArrayList();
        people.add(swamiji);
        people.add(kunal);
        people.add(digvijay);
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        List<Person> expected = new ArrayList();
        expected.add(digvijay);
        expected.add(kunal);
        expected.add(swamiji);

        assertEquals(people, expected);
    }

    @Test
    public void testAscendingAgeComparator() throws Exception {
        Person swamiji = new Person("Swamiji", 3);
        Person kunal = new Person("Kunal", 50);
        Person digvijay = new Person("Digvijay", 35);
        List people = new ArrayList();
        people.add(swamiji);
        people.add(kunal);
        people.add(digvijay);
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getAge() - p2.getAge();
            }
        });
        List<Person> expected = new ArrayList();
        expected.add(swamiji);
        expected.add(digvijay);
        expected.add(kunal);
        assertEquals(people, expected);
    }

    @Test
    public void testDescendingAgeComparator() throws Exception {
        Person swamiji = new Person("Swamiji", 3);
        Person kunal = new Person("Kunal", 50);
        Person digvijay = new Person("Digvijay", 35);
        List people = new ArrayList();
        people.add(swamiji);
        people.add(kunal);
        people.add(digvijay);
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p2.getAge() - p1.getAge();
            }
        });
        List<Person> expected = new ArrayList();
        expected.add(kunal);
        expected.add(digvijay);
        expected.add(swamiji);

        assertEquals(people, expected);
    }
}