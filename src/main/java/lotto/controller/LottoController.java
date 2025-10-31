package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoResultCalculator;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final LottoGenerator lottoGenerator;

    public LottoController(LottoGenerator lottoGenerator) {
        this.lottoGenerator = new LottoGenerator();
    }

    public void run() {
        int purchaseAmount = readPurchaseAmountWithRetry();

        List<Lotto> lottos = lottoGenerator.generateMultipleLotto(purchaseAmount);

        OutputView.printLottos(lottos);

        WinningLotto winningLotto = readWinningLottoWithRetry();

        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator(lottos, winningLotto);

        OutputView.printResult(lottoResultCalculator, purchaseAmount);
    }

    private int readPurchaseAmountWithRetry() {
        while (true) {
            try {
                return InputView.inputPurchaseAmount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningLotto readWinningLottoWithRetry() {
        while (true) {
            try {
                List<Integer> winningNumbers = InputView.inputWinningNumbers();
                int bonusNumber = InputView.inputBonusNumber(winningNumbers);
                return new WinningLotto(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
