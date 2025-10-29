package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

    private LottoGenerator lottoGenerator;

    @BeforeEach
    void setUp() {
        lottoGenerator = new LottoGenerator();
    }

    @Test
    void 생성된로또는_중복되지않는_숫자를_가진다() {
        Lotto lotto = lottoGenerator.generate();
        assertThat(lotto.getNumbers()).doesNotHaveDuplicates();
    }

    @Test
    void 생성된로또는_1부터_45사이의_숫자로_구성된다() {
        Lotto lotto = lottoGenerator.generate();
        assertThat(lotto.getNumbers()).allMatch(number -> number >= 1 && number <= 45);
    }

    @Test
    void 구입금액에_맞게_여러개의_로또를_생성한다() {
        int purchaseAmount = 14000;

        List<Lotto> lottos = lottoGenerator.generateMultipleLotto(purchaseAmount);

        assertThat(lottos).hasSize(14);
    }

    @Test
    void 생성된_모든로또는_유효한_번호를_가진다() {
        int purchaseAmout = 14000;
        List<Lotto> lottos = lottoGenerator.generateMultipleLotto(purchaseAmout);

        assertThat(lottos).allMatch(lotto ->
                lotto.getNumbers().size() == 6 && lotto.getNumbers().stream().allMatch(n -> n >= 1 && n <= 45)
                        && lotto.getNumbers().stream().distinct().count() == 6
        );
    }
}