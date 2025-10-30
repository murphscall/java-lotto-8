package lotto.domain;

public enum Rank {
    THREE(3, 5000, "3개 일치 (5,000원)"),
    FOUR(4, 50000, "4개 일치 (50,000원)"),
    FIVE(5, 1500000, "5개 일치 (1,500,000원)"),
    FIVE_BONUS(5, 30000000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    SIX(6, 2000000000, "6개 일치 (2,000,000,000원)"),
    NONE(0, 0, "");

    private final int matchCount;
    private final int prizeMoney;
    private final String description;

    Rank(int matchCount, int prizeMoney, String description) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.description = description;
    }

    public static Rank valueOf(int matchCount, boolean hasBonus) {
        if (matchCount == 6) {
            return SIX;
        }
        if (matchCount == 5 && hasBonus) {
            return FIVE_BONUS;
        }
        if (matchCount == 5) {
            return FIVE;
        }
        if (matchCount == 4) {
            return FOUR;
        }
        if (matchCount == 3) {
            return THREE;
        }
        return NONE;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public String getDescription() {
        return description;
    }
}
