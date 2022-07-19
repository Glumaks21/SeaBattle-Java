package logic.location;

import logic.ships.Ship;
import java.util.*;

public final class PlayerField extends SkeletalField {
    private final List<Ship> shipList;

    public PlayerField() {
        shipList = new ArrayList<>();
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                openAt(new Cords(x, y));
            }
        }
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

    private void markShip(Ship ship) {
        for (Cords shipCords : ship.getLocation()) {
            setStateAt(shipCords, Cell.State.SHIP);
        }
    }

    protected void markShoot(Cords cords) {
        if (getStateAt(cords) == Cell.State.FREE) {
            setStateAt(cords, Cell.State.MISS);
        } else if (getStateAt(cords) == Cell.State.SHIP) {
            Ship ship = getShipAt(cords);
            ship.hit(cords);
            if (!ship.isAlive()) {
                markDestroyedShip(ship);
                shipList.remove(ship);
            }
            setStateAt(cords, Cell.State.DESTROYED);
        }
    }

    private Ship getShipAt(Cords cords) {
        for (Ship ship : shipList) {
            for (Cords shipCords : ship.getLocation()) {
                if (shipCords.equals(cords)) {
                    return ship;
                }
            }
        }
        return null;
    }

    private void markDestroyedShip(Ship ship) {
        Cords[] location = ship.getLocation();
        int startX = location[0].getX() - 1;
        int startY = location[0].getY() - 1;
        int endX = location[location.length - 1].getX() + 1;
        int endY = location[location.length - 1].getY() + 1;

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                Cords cords = new Cords(x, y);
                if (isCordsBelong(cords)) {
                    setStateAt(cords, Cell.State.MISS);
                }
            }
        }

        for (Cords shipCords : location) {
            setStateAt(shipCords, Cell.State.DESTROYED);
        }
    }

    public boolean areShipsAlive() {
        for (Ship ship : shipList) {
            if (ship.isAlive()) {
                return true;
            }
        }
        return false;
    }
}