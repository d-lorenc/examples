package me.lorenc.samples.javaoctet;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

@SuppressWarnings("unused")
public class _6StreamsMapReduce {

    @Test
    public void simpleMapToAddGst() {
        List<Double> grossPrices = Stream.of(10.00, 20.00, 100.00)
            .map(netPrice -> 1.10 * netPrice)
            .collect(Collectors.toList());

        System.out.println(grossPrices);
    }
    
    @Test
    public void reduceToFindMaxPrice() {
        Double maxPrice = Stream.of(10.00, 20.00, 100.00)
            .reduce(Double.MIN_VALUE, Double::max);
        
        assertThat(maxPrice, closeTo(100.00, 0.0001));
    }

    @Test
    public void mapReduceToCalculateTotalGross() {
        Double totalGross = Stream.of(10.00, 20.00, 100.00)
            .map(netPrice -> netPrice * 1.10)
            .reduce((sum, price) -> sum + price).get();

        assertThat(totalGross, closeTo(143.00, 0.0001));
    }
    
    @Test
    public void parallelMapReduceToCalculateTotalGross() {
        Double totalGross = Stream.of(10.00, 20.00, 100.00)
            .parallel()
            .map(netPrice -> netPrice * 1.10)
            .reduce((sum, grossPrice) -> sum + grossPrice).get();
        
        assertThat(totalGross, closeTo(143.00, 0.0001));
    }

    @Test
    public void mapReduceToCalculateTotalGstUsingPriceObject() {
        Double totalGst = Stream.of(10.00, 20.00, 100.00)
            .map(Price::new)
            .mapToDouble(Price::getGst)
            .sum();

        assertThat(totalGst, closeTo(13.00, 0.0001));
    }
    
    private class Price {
        
        private static final double GST_RATE = 0.1;

        private final double net;
        private final double gst;
        private final double gross;
        
        public Price(double net) {
            this.net = net;
            this.gst = net * GST_RATE;
            this.gross = net + gst;
        }
        
        public double getNet() {
            return net;
        }
        
        private double getGst() {
            return gst;
        }
        
        private double getGross() {
            return gross;
        }
    }

}
