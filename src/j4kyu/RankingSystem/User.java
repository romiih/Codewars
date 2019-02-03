package j4kyu.RankingSystem;

import java.util.Arrays;
import java.util.List;

public class User {
    public int rank;
    public int progress = 0;

    private Rank _rank = new Rank();

    public User() {
        rank = _rank.getRank();
    }

    public void incProgress(int rank) {
        if (_rank.isMaxRank()) return;

        int prog = _rank.getProgress(rank);
        prog += progress;

        while(prog >= 100) {
            _rank.incRank();
            prog -= 100;

            if (_rank.isMaxRank()) {
                prog = 0;
                break;
            }
        }
        progress = prog;
        this.rank = _rank.getRank();
    }

    public class Rank {
        private int rank = -8;
        private int value = 0;
        private List<Integer> ranks;

        Rank() {
            this(-8);
        }
        Rank(int rank) {
            ranks = Arrays.asList(-8,-7,-6,-5,-4,-3,-2,-1,1,2,3,4,5,6,7,8);
            if (ranks.indexOf(rank) == -1) throw new IllegalArgumentException();
            this.rank = rank;
            this.value = ranks.indexOf(rank);
        }

        int getRank() {
            return rank;
        }

        int getValue() {
            return value;
        }

        int getProgress(int rank) {
            Rank d = new Rank(rank);
            if (this.rank == rank) {
                return 3;
            }

            int diff = d.getValue() - getValue();
            if (diff < 0) {
                return diff == -1 ? 1 : 0;
            }
            return 10 * diff * diff;
        }

        void incRank() {
            if (isMaxRank()) {
                return;
            }
            rank = ranks.get(++value);
        }

        boolean isMaxRank() {
            return value == ranks.size() - 1;
        }
    }
}
