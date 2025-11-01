package lotto.validator;

import static lotto.constant.LottoConstant.LOTTO_NUMBER_COUNT;
import static lotto.constant.LottoConstant.LOTTO_PRICE;
import static lotto.constant.LottoConstant.MAX_LOTTO_NUMBER;
import static lotto.constant.LottoConstant.MIN_LOTTO_NUMBER;
import static lotto.constant.MessageConstant.ERROR_BONUS_NUMBER_DUPLICATED;
import static lotto.constant.MessageConstant.ERROR_INPUT_IS_EMPTY;
import static lotto.constant.MessageConstant.ERROR_INPUT_NOT_NUMBER;
import static lotto.constant.MessageConstant.ERROR_LOTTO_NUMBER_RANGE;
import static lotto.constant.MessageConstant.ERROR_PURCHASE_AMOUNT_MUST_BE_POSITIVE;
import static lotto.constant.MessageConstant.ERROR_PURCHASE_AMOUNT_UNIT;
import static lotto.constant.MessageConstant.ERROR_WINNING_NUMBER_COUNT;
import static lotto.constant.MessageConstant.ERROR_WINNING_NUMBER_DUPLICATED;

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
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_MUST_BE_POSITIVE);
        }

        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_PURCHASE_AMOUNT_UNIT);
        }
    }

    public static void validateWinningNumbers(String input) {

        validateNotNullAndNotEmpty(input);

        String[] numbers = input.split(",");
        validateWinningNumbersCount(numbers);

        List<Integer> parsedNumbers = parseAndValidateNumbers(numbers);
        validateDuplication(parsedNumbers);
    }

    public static void validateBonusNumbers(List<Integer> winningNumbers, String input) {
        int bonusNumber = validateNumeric(input);
        validateNumberRange(bonusNumber);
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_DUPLICATED);
        }
    }

    private static List<Integer> parseAndValidateNumbers(String[] numbers) {
        List<Integer> parseNumbers = new ArrayList<>();
        for (String number : numbers) {
            parseNumbers.add(parseAndValidateNumber(number));
        }
        return parseNumbers;
    }

    private static int parseAndValidateNumber(String number) {
        int parseNumber = validateNumeric(number);
        validateNumberRange(parseNumber);
        return parseNumber;
    }

    private static void validateWinningNumbersCount(String[] numbers) {
        if (numbers.length != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_WINNING_NUMBER_COUNT);
        }
    }

    private static void validateDuplication(List<Integer> numbers) {
        Set<Integer> duplicatedNumbers = new HashSet<>(numbers);
        if (duplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_WINNING_NUMBER_DUPLICATED);
        }
    }

    private static int validateNumeric(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INPUT_NOT_NUMBER);
        }
    }

    private static void validateNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_RANGE);
        }
    }

    private static void validateNotNullAndNotEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_INPUT_IS_EMPTY);
        }
    }
}
