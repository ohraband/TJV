package cz.cvut.fit.tjv.ohraband.farming_monitor.business;

public class EntityStateException extends RuntimeException {
    public <E> EntityStateException(E entity) {
        super("Wrong state of entity " + entity);
    }
}

