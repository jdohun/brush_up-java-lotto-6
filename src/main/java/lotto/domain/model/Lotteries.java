package lotto.domain.model;

import lotto.domain.model.lotto.Lotto;
import lotto.dto.LottoDto;

import java.util.ArrayList;
import java.util.List;

public class Lotteries {

    private final List<Lotto> lotteries;

    private Lotteries(List<Lotto> lotteries) {
        this.lotteries = lotteries;
    }

    public static Lotteries from(List<Lotto> lottoList) {
        return new Lotteries(lottoList);
    }

    public int getSize() {
        return lotteries.size();
    }

    public List<LottoDto> toDtos() {
        List<LottoDto> lottoDtos = new ArrayList<>();
        for (Lotto lotto : lotteries) {
            lottoDtos.add(lotto.mapToDto());
        }
        return lottoDtos;
    }
}
