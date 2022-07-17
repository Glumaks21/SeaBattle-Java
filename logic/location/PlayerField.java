package logic.location;

import logic.ships.Ship;
import java.util.*;

public class PlayerField extends SkeletalField {
    private final List<Ship> shipList;

    public PlayerField() {
        shipList = new ArrayList<>();
    }

    public void setUpShips() {
        for (int size = 1; size <= 4; size++) {
            for (int count = size; count <= 4; count++) {
                Ship ship = new Ship(size);
                Cords[] location = findLocation(ship);
                ship.setLocation(location);
                markShip(ship);
                shipList.add(ship);
            }
        }
    }

    private Cords[] findLocation(Ship ship) {
        Cords[] location = new Cords[ship.getSize()];
        SEARCH:while(true) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            int direction = (int) (Math.random() * 2);

            for (int set = 0; set < ship.getSize(); set++) {
                Cords cords = new Cords(x, y);
                if (!isCordsBelong(cords) || !isFreeSquare(cords)) {
                    continue SEARCH;
                }
                location[set] = cords;
                x += direction;
                y += (direction + 1) % 2;
            }
            break;
        }
        return location;
    }

    private boolean isFreeSquare(Cords center) {
        for (int y = center.getY() - 1; y <= center.getY() + 1; y++) {
            for (int x = center.getX() - 1; x <= center.getX() + 1; x++) {
                Cords cords = new Cords(x, y);
                if (isCordsBelong(cords) && getStateAt(cords) != Cell.State.FREE) {
                    return false;
                }
            }
        }
        return true;
    }

    public void markShip(Ship ship) {
        for (Cords shipCords : ship.getLocation()) {
            setStateAt(shipCords, Cell.State.SHIP);
        }
    }
}

