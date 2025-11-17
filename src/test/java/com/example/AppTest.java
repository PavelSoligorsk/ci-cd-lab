package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Комплексные тесты для демонстрационного приложения
 */
@DisplayName("App Class Tests")
class AppTest {

    @Test
    @DisplayName("Тест основного приветствия")
    void testMainGreeting() {
        assertEquals("Hello, World!", App.greet("World"));
    }

    @Test
    @DisplayName("Тест приветствия с именем")
    void testGreetWithName() {
        assertEquals("Hello, Alice!", App.greet("Alice"));
        assertEquals("Hello, Bob!", App.greet("Bob"));
    }

    @Test
    @DisplayName("Тест приветствия с пустым именем")
    void testGreetWithEmptyName() {
        assertEquals("Hello, Anonymous!", App.greet(""));
        assertEquals("Hello, Anonymous!", App.greet("   "));
    }

    @Test
    @DisplayName("Тест приветствия с null")
    void testGreetWithNull() {
        assertEquals("Hello, Anonymous!", App.greet(null));
    }

    @Test
    @DisplayName("Тест операции сложения")
    void testAddOperation() {
        assertEquals(5, App.add(2, 3));
        assertEquals(0, App.add(-2, 2));
        assertEquals(10, App.add(5, 5));
        assertEquals(-5, App.add(-2, -3));
    }

    @Test
    @DisplayName("Тест проверки четности")
    void testEvenNumber() {
        assertTrue(App.isEven(2));
        assertTrue(App.isEven(0));
        assertTrue(App.isEven(-4));
        assertFalse(App.isEven(1));
        assertFalse(App.isEven(-3));
    }

    @Test
    @DisplayName("Интеграционный тест: приветствие + сложение")
    void testIntegration() {
        String greeting = App.greet("Test");
        int result = App.add(10, 20);

        assertEquals("Hello, Test!", greeting);
        assertEquals(30, result);
        assertTrue(greeting.contains("Test"));
    }
}