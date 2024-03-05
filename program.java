/**
Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
Например:
“Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет …
5 - Поиск
Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
Работу сдать как обычно ссылкой на гит репозиторий
Частые ошибки:
1. Заставляете пользователя вводить все существующие критерии фильтрации
2. Невозможно использовать более одного критерия фильтрации одновременно
3. При выборке выводятся ноутбуки, которые удовлетворяют только одному фильтру, а не всем введенным пользователем
4. Работа выполнена только для каких то конкретных ноутбуков, и если поменять характеристики ноутбуков или добавить еще ноутбук, то программа начинает работать некорректно
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class program {
    static void PrintLaptopParams(Laptop lap, Map<Integer, String> osList, Map<Integer, String> colorsList){
        System.out.println("Озу: " + lap.ram + ", Объем жд: " + lap.ssd + ", Операционная система: " + osList.get(lap.os) + ", Цвет: " + colorsList.get(lap.color));
    }

    static void getNumParametr(Map<String, Integer> paramList, String paramName, String text){
        System.out.println(text);
        Scanner input = new Scanner(System.in);
        boolean isNum = input.hasNextInt();
        if (isNum == true) {
            Integer num = input.nextInt();
            if (num > 0) {
                paramList.put(paramName,num);
            } else if (paramList.containsKey(paramName) == true) {
                paramList.remove(paramName);
            }
        }
        System.out.println("\n");
    }

    static void getTraitParametr(Map<String, Integer> paramList, Map<Integer, String> traitsList, String traitName, String text){
        System.out.println(text);
        for (Integer i = 0; i < traitsList.size(); i++) {
            System.out.println(i + 1 + " - " + traitsList.get(i));
        }
        Scanner input = new Scanner(System.in);
        boolean isNum = input.hasNextInt();
        if (isNum == true) {
            Integer num = input.nextInt();
            if (num > 0 && traitsList.containsKey(num-1) == true) {
                paramList.put(traitName,num-1);
            } else if (paramList.containsKey(traitName) == true) {
                paramList.remove(traitName);
            }
        }
        System.out.println("\n");
    }

    static void FindRequiredLaptop(Set<Laptop> list, Map<String, Integer> Criterias, Map<Integer, String> osList, Map<Integer, String> colorsList){
        Object[] arr = list.toArray();
        for (int i = 0; i < list.size(); i++) {
            if (((Laptop)arr[i]).compareWith(Criterias) == true) {
                PrintLaptopParams((Laptop)arr[i], osList, colorsList);
            }
        }
        System.out.println("\n");
    }

    public static void main(String[] args){
        Laptop a = new Laptop();
        a.ram = 8;
        a.ssd = 512;
        a.color = 0;
        a.os = 0;
        Laptop b = new Laptop();
        b.ram = 4;
        b.ssd = 128;
        b.color = 1;
        b.os = 0;
        Laptop c = new Laptop();
        c.ram = 4;
        c.ssd = 512;
        c.color = 0;
        c.os = 1;
        Laptop d = new Laptop();
        d.ram = 4;
        d.ssd = 256;
        d.color = 2;
        d.os = 2;

        Set<Laptop> сatalog = new HashSet<Laptop>(Arrays.asList(a,b,c,d));

        Map<Integer, String> colorsList = new HashMap<>();
        colorsList.put(0,"Черный");
        colorsList.put(1,"Белый");
        colorsList.put(2,"Серый");

        Map<Integer, String> osList = new HashMap<>();
        osList.put(0,"Windows");
        osList.put(1,"Linux");
        osList.put(2,"Mac");

        Map<String, Integer> criterias = new HashMap<>();

        byte i = 1;
        while (i > 0) {
            System.out.println("Введите цифру, соответствующую необходимому критерию: \n1 - ОЗУ \n2 - Объем ЖД \n" + //
                    "3 - Операционная система \n4 - Цвет … \n5 - Поиск \n0 - Выход\n");
            Scanner input = new Scanner(System.in);
            boolean isNum = input.hasNextInt();
            if (isNum == true) {
                i = input.nextByte();
                switch(i) {
                    case 1:
                        getNumParametr(criterias,"ram","Введите требование к Озу: \n (Чтобы выйти из выбора введите любой символ кроме чисел)");
                        continue;
                    case 2:
                        getNumParametr(criterias,"ssd","Введите требование к ЖД: \n (Чтобы выйти введите любой символ кроме чисел)");
                        continue;
                    case 3:
                        getTraitParametr(criterias,osList,"os","Требуемая операционная система: \n (Чтобы выйти из выбора введите любой символ кроме чисел)");
                        continue;
                    case 4:
                        getTraitParametr(criterias,colorsList,"color","Требуемый цвет: \n (Чтобы выйти из выбора введите любой символ кроме чисел)");
                        continue;
                    case 5:
                        FindRequiredLaptop(сatalog, criterias, osList, colorsList);
                        continue;
                    case 0:
                        i = -1;
                }
            }
            else {
                System.out.println("Неверный ввод, попробуйте сново\n");
            }
        }
    }
}