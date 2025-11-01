package lotto.controller;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoResultCalculator;
import lotto.domain.WinningLotto;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final LottoGenerator lottoGenerator;

    public LottoController() {
        this.lottoGenerator = new LottoGenerator();
    }

    public void run() {
        int purchaseAmount = getValidPurchaseAmount();
        List<Lotto> lottos = lottoGenerator.generateMultipleLotto(purchaseAmount);
        OutputView.printLottos(lottos);

        List<Integer> winningNumbers = getValidWinningNumbers();
        int bonusNumber = getValidBonusNumber(winningNumbers);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator(lottos, winningLotto);
        OutputView.printResult(lottoResultCalculator, purchaseAmount);
    }

    private int getValidPurchaseAmount() {
        while (true) {
            try {
                OutputView.printPurchaseAmountPrompt();
                String input = InputView.readUserInput();
                InputValidator.validatePurchaseAmount(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private List<Integer> getValidWinningNumbers() {
        while (true) {
            try {
                OutputView.printWWinningNumbersPrompt();
                String input = InputView.readUserInput();
                InputValidator.validateWinningNumbers(input);

                return Arrays.stream(input.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .toList();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }

    private int getValidBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            try {
                OutputView.printBonusNumberPrompt();
                String input = InputView.readUserInput();
                InputValidator.validateBonusNumbers(winningNumbers, input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e);
            }
        }
    }
}
