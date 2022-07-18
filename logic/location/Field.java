package logic.location;

public interface Field {
    Cell cellAt(Cords cords);
    Cell.State getStateAt(Cords cords);
    boolean isOpenedAt(Cords cords);
    void openAt(Cords cords);
    void setStateAt(Cords cords, Cell.State state);
    void shoot(Cords cords);
    boolean isCordsBelong(Cords cords);
}
