package logic.location;

public class EnemyField extends SkeletalField {
    private final PlayerField playerField;

    public EnemyField(PlayerField playerField) {
        this.playerField = playerField;
        update();
    }

    public void update() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cords cords = new Cords(x, y);
                Cell.State state = playerField.getStateAt(cords);
                if (state == Cell.State.SHIP) {
                    state = Cell.State.FREE;
                }
                setStateAt(cords, state);
            }
        }
    }
}