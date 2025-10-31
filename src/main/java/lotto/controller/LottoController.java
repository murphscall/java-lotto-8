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

    public LottoController() {
        this.lottoGenerator = new LottoGenerator();
    }

    public void run() {
        int purchaseAmount = InputView.inputPurchaseAmount();

        List<Lotto> lottos = lottoGenerator.generateMultipleLotto(purchaseAmount);

        OutputView.printLottos(lottos);

        List<Integer> winningNumbers = InputView.inputWinningNumbers();

        int bonusNumber = InputView.inputBonusNumber(winningNumbers);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator(lottos, winningLotto);

        OutputView.printResult(lottoResultCalculator, purchaseAmount);
    }

}
