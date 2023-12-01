package lotto.domain.model;

import lotto.domain.model.lotto.Lotto;

import java.util.List;

public class Lotteries {

    private final List<Lotto> lotteries;

    private Lotteries(List<Lotto> lotteries) {
        this.lotteries = lotteries;
    }

    public static Lotteries from(List<Lotto> lottoList) {
        return new Lotteries(lottoList);
    }
}
