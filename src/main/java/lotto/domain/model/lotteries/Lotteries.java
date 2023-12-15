package lotto.domain.model.lotteries;

import lotto.domain.model.lotto.Lotto;
import lotto.dto.LottoDto;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.model.lotteries.LotteriesErrorMessage.*;

public class Lotteries {

    private final List<Lotto> lotteries;

    public Lotteries(List<Lotto> lotteries) {
        validate(lotteries);
        this.lotteries = List.copyOf(lotteries);
    }

    private void validate(List<Lotto> lotteries) {
        validateNotNull(lotteries);
        validateNotEmpty(lotteries);
        validateNotNullElements(lotteries);
    }

    private void validateNotNull(List<Lotto> lotteries) {
        if (null == lotteries) {
            throw new IllegalArgumentException(ERROR_NULL.getMessage());
        }
    }

    private void validateNotEmpty(List<Lotto> lotteries) {
        if (lotteries.isEmpty()) {
            throw new IllegalArgumentException(ERROR_EMPTY_LIST.getMessage());
        }
    }

    private void validateNotNullElements(List<Lotto> lotteries) {
        for (Lotto lotto : lotteries) {
            if (null == lotto) {
                throw new IllegalArgumentException(ERROR_CONTAIN_NULL_ELEMENT.getMessage());
            }
        }
    }

    public int getSize() {
        return lotteries.size();
    }

    public List<LottoDto> toDtoList() {
        return lotteries.stream()
                .map(Lotto::mapToDto)
                .collect(Collectors.toList());
    }

    public List<Lotto> getImmutableLotteries() {
        return lotteries;
    }
}
