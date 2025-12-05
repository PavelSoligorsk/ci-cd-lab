package com.example;

/**
 * Демонстрационное приложение для CI/CD пайплайна
 * Создано через Maven Archetype
 */
public class App {

    /**
     * Точка входа приложения
     */
    public static void main(String[] args) {
        System.out.println("==========================0========");
        System.out.println("Java Maven CI/CD Demo Application");
        System.out.println("Built with Maven Archetype");
        System.out.println("==================================");

        if (args.length > 0) {
            System.out.println(greet(args[0]));
        } else {
            System.out.println(greet("World"));
        }

        // Демонстрация работы методов
        System.out.println("2 + 3 = " + add(2, 3));
        System.out.println("Application started successfully!");
    }

    /**
     * Бизнес-метод: формирует приветствие
     */
    public static String greet(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Hello, Anonymous!";
        }
        return "Hello, " + name + "!";
    }

    /**
     * Математическая операция сложения
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Проверка четности числа
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}