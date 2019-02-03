package j4kyu;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PokerHand
{
    public enum Result { TIE, WIN, LOSS }

    public enum Role {
        Highest(1),
        Pair(2),
        TwoPairs(3),
        ThreeOfKind(4),
        Straight(5),
        Flush(6),
        FullHouse(7),
        FourOfKind(8),
        StraightFlush(9),
        RoyalStraightFlush(10);

        private int order;
        Role(int order) {
            this.order = order;
        }
    }

    private Role role = Role.Highest;
    private List<Integer> checkInts;

    PokerHand(String hand)
    {
        String[] cards = hand.split(" ");

        setRole(cards);
    }

    private void setRole(String[] cards) {
        Map<String, Long> map = getMap(cards);
        if (isFlash(cards)) {
            role = isStraight(cards)
                    ? isRoyal(cards) ? Role.RoyalStraightFlush
                    : Role.StraightFlush : Role.Flush;

        } else if (isStraight(cards)) {
            role = Role.Straight;

        } else if (map.containsValue(4L)) {
            role = Role.FourOfKind;

        } else if (map.containsValue(3L)) {
            if (map.containsValue(2L)) {
                role = Role.FullHouse;
            } else {
                role = Role.ThreeOfKind;
            }

        } else if (map.containsValue(2L)) {
            role = map.values().stream().filter(v -> v == 2L).count() == 2 ? Role.TwoPairs : Role.Pair;

        } else {
            role = Role.Highest;
        }

        checkInts = map.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .thenComparing(e -> order(e.getKey())).reversed())
                .map(Map.Entry::getKey)
                .map(PokerHand::order)
                .collect(Collectors.toList());
    }

    public Result compareWith(PokerHand hand) {
        int myOrder = this.role.order;
        int hisOrder = hand.getRole().order;
        return myOrder > hisOrder ? Result.WIN
                : myOrder < hisOrder ? Result.LOSS
                : compareCheckInts(hand);
    }
    private Result compareCheckInts(PokerHand hand) {
        List<Integer> handList = hand.checkInts;
        for (int idx = 0; idx < this.checkInts.size(); idx++) {
            if (checkInts.get(idx) > handList.get(idx)) return Result.WIN;
            if (checkInts.get(idx) < handList.get(idx)) return Result.LOSS;
        }
        return Result.TIE;
    }

    public Role getRole() {
        return role;
    }

    private boolean isFlash(String[] cards) {
        return Arrays.stream(cards).map(c -> c.substring(1, 2)).distinct().count() == 1;
    }

    private boolean isStraight(String[] cards) {

        List<Integer> normal = Arrays.stream(cards).map(PokerHand::order).collect(Collectors.toList());

        // there is one or more pairs
        if (normal.stream().distinct().count() != cards.length) return false;

        // normal straight
        if (normal.stream().max(Comparator.comparing(Integer::valueOf)).get()
                - (normal.stream().min(Comparator.comparing(Integer::valueOf)).get()) == 4 )
            return true;

        // extra straight
        List<Integer> extra = normal.stream().map(n -> n < 10 ? n + 13 : n).collect(Collectors.toList());
        return extra.stream().max(Comparator.comparing(Integer::valueOf)).get()
                - (extra.stream().min(Comparator.comparing(Integer::valueOf)).get()) == 4;
    }

    private boolean isRoyal(String[] cards) {
        List<Integer> normal = Arrays.stream(cards).map(PokerHand::order).collect(Collectors.toList());
        int min = normal.stream().min(Comparator.comparing(Integer::valueOf)).get();
        int max = normal.stream().max(Comparator.comparing(Integer::valueOf)).get();
        return min == 10 && max == 14;
    }

    private Map<String, Long> getMap(String[] cards) {
        return Arrays.stream(cards).map(c -> c.substring(0, 1))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static int order(String card) {
        String no = card.substring(0, 1);
        switch (no) {
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "T": return 10;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
        }
        return 0;
    }
}
