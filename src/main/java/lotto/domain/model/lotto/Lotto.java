package lotto.domain.model.lotto;

import lotto.domain.model.lottoNumber.LottoNumber;
import lotto.dto.LottoDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        hasNoDuplicateNumbers(numbers);
        this.numbers = List.copyOf(LottoNumber.from(numbers));
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

    public LottoDto mapToDto() {
        List<Integer> numbersForDto = new ArrayList<>();
        for (LottoNumber number : numbers) {
            numbersForDto.add(number.toDto().number());
        }
        return new LottoDto(numbersForDto);
    }
}
