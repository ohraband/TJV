package cz.cvut.fit.tjv.ohraband.farming_monitor.api.exception;

public class NoEntityFoundException extends RuntimeException {

    public NoEntityFoundException() {
        super("No entity found");
    }
}
