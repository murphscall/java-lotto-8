package lotto;

import java.util.List;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView.inputPurchaseAmount();
        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        InputView.inputBonusNumber(winningNumbers);
    }
}
