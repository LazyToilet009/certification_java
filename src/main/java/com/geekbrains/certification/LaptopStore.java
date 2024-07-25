package com.geekbrains.certification;

import java.util.*;

public class LaptopStore {

    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 8, 256, "Windows 10", "Black"));
        laptops.add(new Laptop("HP", 16, 512, "Windows 10", "Silver"));
        laptops.add(new Laptop("Apple", 8, 512, "macOS", "Gray"));
        laptops.add(new Laptop("Asus", 16, 256, "Windows 10", "Blue"));
        laptops.add(new Laptop("Lenovo", 4, 128, "Windows 10", "Black"));

        Scanner scanner = new Scanner(System.in);
        Map<String, String> filters = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        while (true) {
            System.out.print("Введите номер критерия (или 0 для завершения): ");
            int criterion = scanner.nextInt();
            scanner.nextLine();

            if (criterion == 0) {
                break;
            }

            switch (criterion) {
                case 1:
                    System.out.print("Введите минимальный объем ОЗУ (в ГБ): ");
                    filters.put("ram", scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Введите минимальный объем ЖД (в ГБ): ");
                    filters.put("storage", scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Введите операционную систему: ");
                    filters.put("os", scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Введите цвет: ");
                    filters.put("color", scanner.nextLine());
                    break;
                default:
                    System.out.println("Некорректный критерий.");
            }
        }

        Set<Laptop> filteredLaptops = filterLaptops(laptops, filters);

        if (filteredLaptops.isEmpty()) {
            System.out.println("Нет ноутбуков, соответствующих заданным критериям.");
        } else {
            System.out.println("Ноутбуки, соответствующие критериям:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop);
            }
        }
    }

    public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, String> filters) {
        Set<Laptop> filteredLaptops = new HashSet<>(laptops);

        for (Map.Entry<String, String> filter : filters.entrySet()) {
            String key = filter.getKey();
            String value = filter.getValue();

            switch (key) {
                case "ram":
                    int minRam = Integer.parseInt(value);
                    filteredLaptops.removeIf(laptop -> laptop.getRam() < minRam);
                    break;
                case "storage":
                    int minStorage = Integer.parseInt(value);
                    filteredLaptops.removeIf(laptop -> laptop.getStorage() < minStorage);
                    break;
                case "os":
                    filteredLaptops.removeIf(laptop -> !laptop.getOs().equalsIgnoreCase(value));
                    break;
                case "color":
                    filteredLaptops.removeIf(laptop -> !laptop.getColor().equalsIgnoreCase(value));
                    break;
            }
        }

        return filteredLaptops;
    }
}
