package j5kyu;
import java.util.*;

public class BattleShips {

    public static Map<String,Double> damagedOrSunk(final int[][] board, final int[][] attacks) {
        System.out.println(Arrays.deepToString(board) + "\r\n" + Arrays.deepToString(attacks));

        Map<Integer,Boat> boats = new HashMap<>();
        boats.put(1, new Boat(getLength(board, 1)));
        boats.put(2, new Boat(getLength(board, 2)));
        boats.put(3, new Boat(getLength(board, 3)));

        for (int[] a : attacks) {
            int value = getCellValue(board, a);
            if (value != 0) {
                Boat boat = boats.get(value);
                boat.hit();
            }
        };

        System.out.println("Boat 1, " + boats.get(1).toString());
        System.out.println("Boat 2, " + boats.get(2).toString());
        System.out.println("Boat 3, " + boats.get(3).toString());
        Map<String,Double> map = new HashMap<>();
        map.put("sunk", boats.values().stream().mapToDouble(Boat::getSunk).sum());
        map.put("damaged", boats.values().stream().mapToDouble(Boat::getDamaged).sum());
        map.put("notTouched", boats.values().stream().mapToDouble(Boat::getNotTouched).sum());
        map.put("points", boats.values().stream().mapToDouble(Boat::getPoints).sum());

        return map;
    }
    private static long getLength(int[][] board, int boat) {
        return Arrays.stream(board).mapToLong(c -> Arrays.stream(c).filter(x -> x == boat).count()).sum();
    }
    private static int getCellValue(int[][] board, int[] attack) {
        int x = attack[0] - 1;
        int y = board.length - attack[1];
        return board[y][x];
    }
    private static void AddPoint(Map<String,Double> map, String key, Double point) {
        Double current = map.get(key);
        Double newValue = current + point;
        map.put(key, newValue);
    }
    private static class Boat {
        long length;
        int damage;

        public Boat(long len) {
            length = len;
            damage = 0;
        }
        private boolean notTouched() {
            return damage == 0;
        }
        public void hit() {
            damage++;
        }
        private boolean isSunk() {
            return length == damage;
        }
        public double getPoints() {
            if (length == 0) return 0.0;
            return notTouched() ? -1.0 : isSunk() ? 1.0 : 0.5;
        }
        public double getNotTouched() {
            if (length == 0) return 0.0;
            return notTouched() ? 1.0 : 0.0;
        }
        public double getSunk() {
            if (length == 0) return 0.0;
            return isSunk() ? 1.0 : 0.0;
        }
        public double getDamaged() {
            if (length == 0) return 0.0;
            return isSunk() || damage == 0 ? 0.0 : 1.0;
        }
        @Override
        public String toString() {
            return "length:" + length + ",damage:" + damage;
        }
    }
}