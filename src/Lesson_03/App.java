package Lesson_03;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        System.out.println("1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). \n" +
                "Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем). " +
                "Посчитать сколько раз встречается каждое слово.");
        String[] words = {
                "яблоко", "груша", "слива", "персик", "груша", "абрикос", "черешня", "огурец", "слива",
                "вишня", "абрикос", "слива", "огурец", "морковь", "персик", "тыква"};

        countWords(words);
        System.out.println();

        System.out.println("2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров. \n" +
                "В этот телефонный справочник с помощью метода add() можно добавлять записи. " +
                "С помощью метода get() искать номер телефона по фамилии. \n" +
                "Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), " +
                "тогда при запросе такой фамилии должны выводиться все телефоны.");
        Phonebook phoneBook = new Phonebook();

        phoneBook.add("Иванов", "+7 987 654 32 10");
        phoneBook.add("Петров", "+7 987 654 32 11");
        phoneBook.add("Сидоров", "+7 987 654 32 12");
        phoneBook.add("Иванов", "+7 987 654 32 13");

        phoneBook.get("Петров");
        phoneBook.get("Иванов");
        phoneBook.get("Васечкин");
    }

    public static void countWords(String[] arr){
        HashMap<String, Integer> counter = new HashMap<String, Integer>();
        for (String item : arr){
            Integer value = counter.get(item);
            counter.put(item, value == null ? 1 : value + 1);
        }

        System.out.println("Список уникальных слов:");
        for (String item : counter.keySet()){
            if (counter.get(item) == 1){
                System.out.println(item);
            }
        }

        System.out.println("\nСколько раз встречается каждое слово:");
        System.out.println(counter);
    }
}
