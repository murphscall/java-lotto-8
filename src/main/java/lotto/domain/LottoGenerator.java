package lotto.domain;

import static lotto.domain.LottoConstant.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoConstant.LOTTO_PRICE;
import static lotto.domain.LottoConstant.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoConstant.MIN_LOTTO_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    public Lotto generate() {
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER,
                LOTTO_NUMBER_COUNT));

        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public List<Lotto> generateMultipleLotto(int purchaseAmount) {
        int count = purchaseAmount / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(generate());
        }
        return lottos;
    }
}
