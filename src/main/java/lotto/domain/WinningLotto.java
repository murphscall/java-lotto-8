package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto lotto) {
        long matchCount = lotto.getNumbers().stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
        boolean hasBonus = lotto.getNumbers().contains(bonusNumber);

        return Rank.valueOf((int) matchCount, hasBonus);
    }

    private void validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }


}
