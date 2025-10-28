package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {

    public static int inputPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");
        String input = Console.readLine();

        return Integer.parseInt(input);
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    public static int inputBonusNumber(List<Integer> WinningNumbers) {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();

        return Integer.parseInt(input);
    }
}
