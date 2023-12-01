package lotto.domain.model.lotto;

import lotto.domain.model.lottoNumber.LottoNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.domain.model.lottoNumber.LottoNumber.LOTTO_NUMBER_COUNT;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        hasNoDuplicateNumbers(numbers);
        this.numbers = LottoNumber.from(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        hasNoDuplicateNumbers(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LottoErrorMessage.ERROR_NOT_MATCH_THE_LOTTO_NUMBER_COUNT.getMessage());
        }
    }

    private void hasNoDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> duplicateNumberChecker = new HashSet<>();
        for (int number : numbers) {
            if (!duplicateNumberChecker.add(number)) {
                throw new IllegalArgumentException(LottoErrorMessage.ERROR_HAS_DUPLICATE_NUMBERS.getMessage());
            }
        }
    }

    public int countMatchingNumbers(Lotto lotto) {
        return (int) lotto.numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public boolean hasBonusNumber(LottoNumber bonusNumber) {
        if (!bonusNumber.isBonus()) {
            return false;
        }
        return numbers.stream().anyMatch(number -> number.equals(bonusNumber));
    }
}
