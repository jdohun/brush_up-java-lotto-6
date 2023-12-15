package lotto.domain.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.model.lotteries.Lotteries;
import lotto.domain.model.lotto.Lotto;
import lotto.domain.model.lottoNumber.LottoNumber;

import java.util.ArrayList;
import java.util.List;

public final class AutoLottoGenerator {
    public static final int LOTTO_PRICE = 1_000;

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

        return new Lotteries(lottoList);
    }

    private Lotto generate() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(LottoNumber.START_OF_RANGE, LottoNumber.END_OF_RANGE, Lotto.LOTTO_NUMBER_COUNT));
    }

    private int calculateCountOfLotteries(int money) {
        return money / LOTTO_PRICE;
    }

    private static final class Holder {
        private static final AutoLottoGenerator LOTTO_GENERATOR = new AutoLottoGenerator();
    }
}
