package me.lorenc.rest.beer;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BeerStorage {

    private final AtomicInteger idGenerator = new AtomicInteger();
    
    private final ConcurrentMap<Integer, Beer> beers = new ConcurrentHashMap<>();

    public BeerStorage() {
        preLoad();
    }
    
    private void preLoad() {
        addBeer(nextId(), "Rebellion Smuggler");
        addBeer(nextId(), "London Pride"); 
        addBeer(nextId(), "Coopers Sparkling Ale");
    }

    private void addBeer(Integer id, String name) {
        beers.put(id, new Beer(id, name)); 
    }
    
    private Integer nextId() {
        return idGenerator.incrementAndGet();
    }

    public Collection<Beer> findAll() {
        return beers.values();
    }

    public Beer findById(Integer id) {
        return beers.get(id);
    }
    
    public void store(Beer beer) {
        Integer id = nextId();
        beer.setId(id);
        beers.put(id, beer); 
    }

    public void update(Beer beer) {
        beers.put(beer.getId(), beer);
    }

    public void remove(Integer id) {
        beers.remove(id);
    }
    
}
