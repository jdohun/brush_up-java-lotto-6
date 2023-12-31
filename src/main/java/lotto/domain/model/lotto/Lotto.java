package lotto.domain.model.lotto;

import lotto.domain.model.lottoNumber.LottoNumber;
import lotto.dto.LottoDto;

import java.util.*;

public class Lotto {
    public static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(LottoNumber.from(numbers));
    }

    private static void validate(List<Integer> numbers) {
        validateSize(numbers);
        hasNoDuplicateNumbers(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LottoErrorMessage.ERROR_NOT_MATCH_THE_LOTTO_NUMBER_COUNT.getMessage());
        }
    }

    private static void hasNoDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> duplicateNumberChecker = new HashSet<>();
        for (int number : numbers) {
            if (!duplicateNumberChecker.add(number)) {
                throw new IllegalArgumentException(LottoErrorMessage.ERROR_HAS_DUPLICATE_NUMBERS.getMessage());
            }
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        Objects.requireNonNull(lottoNumber);
        return numbers.contains(lottoNumber);
    }

    public int countMatchingNumbers(Lotto lotto) {
        return (int) lotto.numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public LottoDto mapToDto() {
        List<Integer> numbersForDto = new ArrayList<>();
        for (LottoNumber number : numbers) {
            numbersForDto.add(number.toDto().number());
        }
        return new LottoDto(numbersForDto);
    }

    @Override
    public String toString() {
        return "Lotto{" + numbers + '}';
    }
}
