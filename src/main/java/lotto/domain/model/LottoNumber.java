package lotto.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;

public class LottoNumber {
    public static final int START_OF_RANGE = 1;
    public static final int END_OF_RANGE = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final int lottoNumber;

    public LottoNumber(int number) {
        validate(number);
        this.lottoNumber = number;
    }

    public static List<LottoNumber> from(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return lottoNumbers;
    }

    private void validate(int number) {
        validateLottoNumberRange(number);
    }

    private void validateLottoNumberRange(int number) {
        if (START_OF_RANGE > number || END_OF_RANGE < number) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + "로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
