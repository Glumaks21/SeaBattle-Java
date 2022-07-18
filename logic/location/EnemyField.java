package logic.location;

public class EnemyField extends SkeletalField {
    private final PlayerField playerField;

    public EnemyField(PlayerField playerField) {
        this.playerField = playerField;
        updateAll();
    }

    @Override
    protected void markShoot(Cords cords) {
        playerField.shoot(cords);
        if (playerField.getStateAt(cords) == Cell.State.MISS) {
            openAt(cords);
            setStateAt(cords, Cell.State.MISS);
        } else {
            updateAll();
        }
    }

    private void updateAll() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Cords cords = new Cords(x, y);
                if (playerField.getStateAt(cords) == Cell.State.MISS ||
                    playerField.getStateAt(cords) == Cell.State.DESTROYED) {
                    openAt(cords);
                    setStateAt(cords, playerField.getStateAt(cords));
                }
            }
        }
    }
}