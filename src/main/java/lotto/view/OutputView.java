package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoResultCalculator;
import lotto.domain.Rank;

public class OutputView {

    public static void printLottos(List<Lotto> lottos) {
        StringBuilder sb = new StringBuilder();
        sb.append(lottos.size() + "개를 구매했습니다.\n");
        for (Lotto lotto : lottos) {
            sb.append(lotto.getNumbers() + "\n");
        }
        System.out.println(sb);
    }

    public static void printResult(LottoResultCalculator lottoResultCalculator, int purchaseAmount) {
        Map<Rank, Integer> result = lottoResultCalculator.getResult();
        double profitRate = lottoResultCalculator.profitRate(purchaseAmount);
        StringBuilder sb = new StringBuilder();
        sb.append("당첨 통계\n");
        sb.append("---\n");

        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue;
            }
            int count = result.get(rank);
            sb.append(String.format("%s - %d개%n", rank.getDescription(), count));
        }
        sb.append(String.format("총 수익률은 %.1f%%입니다.\n", profitRate));
        System.out.println(sb);
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
