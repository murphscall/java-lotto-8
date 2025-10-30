package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResultCalculator {
    private Map<Rank, Integer> result = new EnumMap<>(Rank.class);

    public LottoResultCalculator(List<Lotto> lottos, WinningLotto winningLotto) {
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public int getTotalPrize() {
        int sum = 0;
        for (Rank rank : Rank.values()) {
            sum += rank.getPrizeMoney() * result.get(rank);
        }
        return sum;
    }

    public double profitRate(int purchaseAmount) {
        return (double) getTotalPrize() / purchaseAmount * 100;
    }
}
