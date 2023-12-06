package lotto.domain.model.lottoNumber;

import lotto.dto.LottoNumberDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoNumber {
    public static final int START_OF_RANGE = 1;
    public static final int END_OF_RANGE = 45;

    private final int lottoNumber;

    public LottoNumber(int number) {
        validate(number);
        this.lottoNumber = number;
    }

    public static List<LottoNumber> from(List<Integer> numbers) {
        List<Integer> copyNumbers = new ArrayList<>(numbers);
        Collections.sort(copyNumbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (int number : copyNumbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return lottoNumbers;
    }

    private void validate(int number) {
        validateLottoNumberRange(number);
    }

    private void validateLottoNumberRange(int number) {
        if (START_OF_RANGE > number || END_OF_RANGE < number) {
            throw new IllegalArgumentException(LottoNumberErrorMessage.ERROR_OUT_OF_RANGE.getMessage());
        }
    }

    public LottoNumberDto toDto() {
        return new LottoNumberDto(lottoNumber);
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
