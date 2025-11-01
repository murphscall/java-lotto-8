package lotto.validator;

import static lotto.domain.LottoConstant.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoConstant.LOTTO_PRICE;
import static lotto.domain.LottoConstant.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoConstant.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputValidator {

    private InputValidator() {
    }

    public static void validatePurchaseAmount(String input) {
        validateNotNullAndNotEmpty(input);
        int amount = validateNumeric(input);
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양수여야 합니다.");
        }

        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    public static void validateWinningNumbers(String input) {

        validateNotNullAndNotEmpty(input);

        String[] numbers = input.split(",");
        validateWinningNumbersCount(numbers);

        List<Integer> numberList = parseAndValidateNumbers(numbers);
        validateDuplication(numberList);
    }

    public static void validateBonusNumbers(List<Integer> winningNumbers, String input) {
        int bonusNumber = validateNumeric(input);
        validateNumberRange(bonusNumber);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private static List<Integer> parseAndValidateNumbers(String[] numbers) {
        List<Integer> numberList = new ArrayList<>();
        for (String number : numbers) {
            numberList.add(parseAndValidateNumber(number));
        }
        return numberList;
    }

    private static int parseAndValidateNumber(String number) {
        int parseNumber = validateNumeric(number);
        validateNumberRange(parseNumber);
        return parseNumber;
    }

    private static void validateWinningNumbersCount(String[] numbers) {
        if (numbers.length != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
    }

    private static void validateDuplication(List<Integer> numberList) {
        Set<Integer> duplicatedNumbers = new HashSet<>(numberList);
        if (duplicatedNumbers.size() != numberList.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복된 숫자가 있습니다.");
        }
    }

    private static int validateNumeric(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력하세요.");
        }
    }

    private static void validateNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private static void validateNotNullAndNotEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력 값이 비어 있습니다.");
        }
    }
}
