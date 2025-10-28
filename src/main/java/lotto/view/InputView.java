package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import lotto.validator.InputValidator;

public class InputView {

    public static int inputPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");
        String input = Console.readLine();

        InputValidator.validatePurchaseAmount(input);

        return Integer.parseInt(input);
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();

        InputValidator.validateWinningNumbers(input);

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    public static int inputBonusNumber(List<Integer> winningNumbers) {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();

        InputValidator.validateBonusNumbers(winningNumbers, input);
        return Integer.parseInt(input);
    }
}
