package lotto.domain.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public final class LottoGenerator {
    private LottoGenerator() {
    }

    public static LottoGenerator getInstance() {
        return Holder.LOTTO_GENERATOR;
    }

    public Lotteries createLotteries(final int count) {
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottoList.add(this.generate());
        }

        return Lotteries.from(lottoList);
    }

    private Lotto generate() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(LottoNumber.START_OF_RANGE, LottoNumber.END_OF_RANGE, LottoNumber.LOTTO_NUMBER_COUNT));
    }

    private static final class Holder {
        private static final LottoGenerator LOTTO_GENERATOR = new LottoGenerator();
    }
}
