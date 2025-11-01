package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoGenerator;

public class Application {
    public static void main(String[] args) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoController lottoController = new LottoController(lottoGenerator);
        lottoController.run();
    }
}
