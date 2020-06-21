package Lesson_03;

import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.util.HashMap;
import java.util.HashSet;

public class Phonebook {
    private HashMap<String, HashSet<String>> phonebook = new HashMap<>();

    public void add(String surname, String number){
        HashSet<String> numbers = phonebook.get(surname);
        if (numbers == null) numbers = new HashSet<>();
        numbers.add(number);
        phonebook.put(surname, numbers);
    }

    public void get(String surname){
        System.out.println(surname+":");
        HashSet<String> numbers = phonebook.get(surname);
        System.out.println(numbers == null ? "В справочнике не найден абонент с такой файмилией" : numbers + "\n");
    }
}
