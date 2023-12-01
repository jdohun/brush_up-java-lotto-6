package lotto.domain.model.AutoLottoGenerator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.model.Lotteries;
import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lottoNumber.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public final class AutoLottoGenerator {
    static final int LOTTO_PRICE = 1_000;

    private AutoLottoGenerator() {
    }

    public static AutoLottoGenerator getInstance() {
        return Holder.LOTTO_GENERATOR;
    }

    public Lotteries createLotteries(final int money) {
        final int count = calculateCountOfLotteries(money);
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottoList.add(this.generate());
        }

        return Lotteries.from(lottoList);
    }

    private Lotto generate() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(LottoNumber.START_OF_RANGE, LottoNumber.END_OF_RANGE, LottoNumber.LOTTO_NUMBER_COUNT));
    }

    private int calculateCountOfLotteries (int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(AutoLottoGeneratorErrorMessage.ERROR_NOT_DIVISIBLE_BY_LOTTO_PRICE.getMessage());
        }
        return money / LOTTO_PRICE;
    }

    private static final class Holder {
        private static final AutoLottoGenerator LOTTO_GENERATOR = new AutoLottoGenerator();
    }
}