package lotto.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class InputValidatorTest {

    @Test
    void 구입금액이_음수라면_예외가_발생한다() {
        String input = "-1000";

        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 양수여야 합니다.");
    }

    @Test
    void 구입금액이_숫자가아니라면_예외가_발생한다롱() {
        String input = "예외발생";
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 숫자를 입력하세요.");
    }

    @Test
    void 구입금액이_천원_단위가아니면_예외가_발생한다() {
        String input = "14500";
        assertThatThrownBy(() -> InputValidator.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
    }

    @Test
    void 당첨번호에_중복된_숫자가있으면_예외가_발생한다() {
        String input = "1,2,3,3,5,6";
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호에 중복된 숫자가 있습니다.");
    }

    @Test
    void 당첨번호의_범위가_크거나작다면_예외가발생한다() {
        String input = "0,1,2,3,5,6";
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 입력값이_NULL이거나_빈문자열이라면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 입력 값이 비어 있습니다.");
    }
}