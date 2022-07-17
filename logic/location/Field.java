package logic.location;

public interface Field {
    Cell.State getStateAt(Cords cords);
    void setStateAt(Cords cords, Cell.State state);
    boolean isCordsBelong(Cords cords);
}
