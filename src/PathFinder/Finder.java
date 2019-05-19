package PathFinder;

import java.util.*;
import java.util.stream.Collectors;

public class Finder {

    static int pathFinder(String maze) {
        return new PathMap(maze).getStepToGoal();
    }
    private static class PathMap {
        private Cell[][] map;

        PathMap(String maze) {
            String[] lines = maze.split("\n");
            map = new Cell[lines.length][lines.length];

            for (int i = 0; i < lines.length; i++) {
                char[] cells = lines[i].toCharArray();
                for (int j = 0; j < cells.length; j++) {
                    Cell cell = new Cell(i, j, cells[j]);
                    map[i][j] = cell;
                }
            }
        }

        private List<Cell> getNextSpaces(Cell cell) {
            int x = cell.point.x;
            int y = cell.point.y;
            List<Point> points = new ArrayList<>();
            // North
            if (x > 0) points.add(new Point(x-1, y));
            // West
            if (y > 0) points.add(new Point(x, y-1));
            // South
            if (x < map.length - 1) points.add(new Point(x+1, y));
            // East
            if (y < map.length - 1) points.add(new Point(x, y+1));

            return points.stream()
                    .map(p -> map[p.x][p.y])
                    .filter(c -> c.value == '.')
                    .collect(Collectors.toList());
        }

        int getStepToGoal() {
            Set<Cell> visited = new HashSet<>();
            Queue<Probe> queue = new LinkedList<>();
            Cell goal = map[map.length-1][map.length-1];
            queue.add(new Probe(map[0][0], 0));
            visited.add(map[0][0]);

            Probe probe;
            while(true) {
                probe = queue.poll();
                if (probe == null) break;
                Cell cell = probe.cell;
                final int step = probe.step;
                if (cell.equals(goal)) {
                    break;
                }

                getNextSpaces(cell).stream()
                        .filter(c -> !visited.contains(c))
                        .forEach(c -> {
                            queue.offer(new Probe(c, step + 1));
                            visited.add(c);
                        });
            }

            return probe == null ? -1 : probe.step;
        }
    }

    private static class Probe {
        private Cell cell;
        private int step;
        Probe(Cell cell, int step) {
            this.cell = cell;
            this.step = step;
        }
    }

    private static class Cell {
        private Point point;
        private char value;

        Cell(int x, int y, char val) {
            this.point = new Point(x, y);
            this.value = val;
        }

        @Override
        public String toString() {
            return point.toString();
        }
    }

    private static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object point) {
            if (point instanceof Point) {
                Point p = (Point)point;
                return x == p.x && y == p.y;
            } else {
                return super.equals(point);
            }
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }

}
