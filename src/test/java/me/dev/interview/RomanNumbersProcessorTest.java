package me.dev.interview;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class RomanNumbersProcessorTest {
    private RomanNumbersProcessor processor = new RomanNumbersProcessor();

    @ParameterizedTest(name = "Converting {0} to {1}")
    @MethodSource("provideValidNumbers")
    void testToRoman(String expectedRoman, int actualInt) {
        assertThat(processor.toRoman(actualInt)).isEqualTo(expectedRoman);
    }

    @ParameterizedTest(name = "Converting from {0} to {1}")
    @MethodSource("provideValidNumbers")
    void testToInteger(String actualRoman, int expectedInt) {
        assertThat(processor.toInteger(actualRoman)).isEqualTo(expectedInt);
    }

    @Test
    void testFullRangeConversion() {
        for (int i=1; i<=RomanNumbersProcessor.MAX; i++) {
            try {
                String roman = processor.toRoman(i);
                int integer = processor.toInteger(roman);
                assertThat(integer).isEqualTo(i);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
    }

    @ParameterizedTest(name = "Testing invalid range {0}")
    @ValueSource(ints = { -1, 0, RomanNumbersProcessor.MAX + 1 })
    void testToRoman_whenOutOfRange(Integer outOfRangeNumber) {
        assertThrows(IllegalArgumentException.class, () ->
                processor.toRoman(outOfRangeNumber));
    }

    @ParameterizedTest(name = "Testing invalid roman string: {0}")
    @NullSource
    @ValueSource(strings = {"", " "})
    void testToInteger(String invalidRoman) {
        assertThrows(IllegalArgumentException.class, () ->
                processor.toInteger(invalidRoman));
    }

    private static Stream<Arguments> provideValidNumbers() {
        return Stream.of(
                Arguments.of("XIV", 14),
                Arguments.of("IV", 4),
                Arguments.of("CM", 900),
                Arguments.of("CD", 400),
                Arguments.of("XC", 90),
                Arguments.of("XL", 40),
                Arguments.of("XL", 40),
                Arguments.of("IX", 9),
                Arguments.of("IV", 4),
                Arguments.of("MMXXIII", 2023)
        );
    }
}