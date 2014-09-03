package me.lorenc.samples.javaoctet;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.IntSummaryStatistics;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class _7MoreStreams {

    @Test
    public void concatenateStrings() {
        String string = Stream.of("a", "b", "c")
            .collect(Collectors.joining(", "));
        
        assertThat(string, equalTo("a, b, c"));
    }
    
    @Test
    public void becauseWeCan() {
        boolean found = IntStream.range(1, 100)
            .skip(10)
            .filter(value -> value % 3 == 0)
            .limit(20)
            .anyMatch(value -> value == 69);
        
        assertTrue(found);
    }
    
    @Test
    public void summaryStatistics() {
        Random random = new Random(System.currentTimeMillis());
        
        IntSummaryStatistics statistics = Stream.generate(() -> random.nextInt(100))
            .limit(1000)
            .mapToInt(value -> value)
            .summaryStatistics();
        
        statistics.getMin();
        statistics.getMax();
        statistics.getCount();
        statistics.getAverage();
        statistics.getSum();
    }
    
    @Test
    public void someCrazyFlatMap() {
        String string = Stream.of("cd", "ab", "ef", "abz")
            .flatMap(value -> value.chars().mapToObj(i -> (char) i))
            .map(String::valueOf)
            .distinct()
            .sorted()
            .collect(Collectors.joining());
        
        assertThat(string, equalTo("abcdefz"));
    }

}
