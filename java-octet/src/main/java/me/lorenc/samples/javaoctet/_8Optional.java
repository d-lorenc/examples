package me.lorenc.samples.javaoctet;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;

@SuppressWarnings("unused")
public class _8Optional {

    private List<String> list = Arrays.asList("ab", "cc", "aa", "ba");
    
    @Test
    public void tiredOfNullPointerExceptions() {
        Optional<String> optional = list.stream().max(String.CASE_INSENSITIVE_ORDER);
        
        assertThat(optional.get(), equalTo("cc"));
        assertThat(optional.orElse("UNKNOWN"), equalTo("cc"));
        assertThat(optional.orElseThrow(NullPointerException::new), equalTo("cc"));
        
        optional.ifPresent(System.out::println);
    }
    
    @Test
    public void constructOptional() {
        assertThat(Optional.of("nonNullValue").get(), equalTo("nonNullValue"));
        assertThat(Optional.ofNullable("nonNullValue").get(), equalTo("nonNullValue"));

        Optional.empty().get();
    }

}
