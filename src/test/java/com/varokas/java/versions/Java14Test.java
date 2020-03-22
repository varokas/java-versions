package com.varokas.java.versions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Java14Test {
    // https://openjdk.java.net/projects/jdk/14/

    // 358:	Helpful NullPointerExceptions
    // The feature can be toggled with the new boolean command-line option
    // -XX:+ShowCodeDetailsInExceptionMessages.
    class Address { String street; String zipCode; }
    class Profile { String name; Address address; }

    @Test void testHelpfulNPE() {
        Profile p = new Profile();
        try {
            String zip = p.address.zipCode;
        } catch (NullPointerException e) {
            assertEquals("Cannot read field \"zipCode\" because \"p.address\" is null",
                    e.getMessage());
            e.printStackTrace();;
            return;
        }

        fail("Did not throw expected NPE");
    }

    // 361: Switch Expressions (Standard)
    @Test void testSwitchExpression() {
        var number = 1;
        var text = switch (number) {
            case  1 -> "one";
            case  2 -> "two";
            default -> "many";
        };

        assertEquals("one", text);
    }

    // *** Preview ***
    // 368:	Text Blocks (Second Preview)
    @Test void testTextBlock() {
        // There must a a newline after """
        var text = """
                Multiline
                Text
                """;

        // Automatically trim leading whitespace
        assertEquals("Multiline\nText\n", text);
    }

    // 305:	Pattern Matching for instanceof (Preview)
    @Test void testPatternMatchingWithInstanceOf() {
        Object obj = 1;
        if (obj instanceof Integer i) {
            //Compiler recognize `i` as Integer. No need for casting
            var longVal = i.longValue();
            assertEquals(1L, longVal);
        }
    }

    // 359:	Records (Preview)
    record Person(String firstname, String lastname) {
        public String getName() {
            return firstname + " " + lastname;
        }
    }
    @Test void testRecord() {
        final var p = new Person("Hello", "World");

        assertEquals(new Person("Hello", "World"), p, "Equality implemented");
        assertEquals("Person[firstname=Hello, lastname=World]",
                p.toString(),
                "toString() implemented");

        assertEquals("Hello", p.firstname, "Member");
        assertEquals("Hello", p.firstname(), "Accessor");
        // p.firstname = "New"; Won't compile - Cannot assign a value to final variable 'firstname'

        assertEquals("Hello World", p.getName(), "Custom method");
    }

    // *** Other things ***
    // JEP 343: Packaging Tool (Incubator)
    // JEP 370: Foreign-Memory Access API (Incubator)
}
