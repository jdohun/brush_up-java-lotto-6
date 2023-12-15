package lotto.domain.model.lotto;

import lotto.domain.model.lottoNumber.LottoNumber;
import lotto.dto.LottoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("생성 테스트")
    @Nested
    class createTest {
        @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
        @Test
        void createLottoByOverSize() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
        @Test
        void createLottoByDuplicatedNumber() {
            // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("구현 메소드 테스트")
    @Nested
    class MethodTest {
        private static List<List<Integer>> lottoSourceProvider() {
            return Arrays.asList(
                    Arrays.asList(1, 2, 3, 4, 5, 6),
                    Arrays.asList(1, 2, 3, 4, 5, 7),
                    Arrays.asList(1, 2, 3, 4, 7, 8),
                    Arrays.asList(1, 2, 3, 7, 8, 9),
                    Arrays.asList(1, 2, 7, 8, 9, 10),
                    Arrays.asList(1, 7, 8, 9, 10, 11),
                    Arrays.asList(7, 8, 9, 10, 11, 12)
            );
        }

        private static Stream<Arguments> matchingNumbersProvider() {
            List<Lotto> lotteries = lottoSourceProvider().stream()
                    .map(Lotto::new)
                    .collect(Collectors.toList());

            return IntStream.range(0, lotteries.size())
                    .mapToObj(i -> Arguments.of(lotteries.get(i), lotteries.size() - i - 1));
        }

        @DisplayName("전달받은 LottoNumber 의 보유 여부를 반환한다.")
        @Test
        void containsTest() {
            // arrange
            Lotto sample = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
            LottoNumber source1 = new LottoNumber(1);
            LottoNumber source2 = new LottoNumber(7);

            // act & assert
            assertThat(sample.contains(source1)).isTrue();
            assertThat(sample.contains(source2)).isFalse();
        }

        @DisplayName("전달받은 Lotto 와 일치하는 LottoNumber 의 개수를 반환한다.")
        @ParameterizedTest
        @MethodSource("matchingNumbersProvider")
        void countMatchingNumbersTest(Lotto sample, int expected) {
            // arrange
            Lotto standard = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

            // act & assert
            assertThat(standard.countMatchingNumbers(sample))
                    .isEqualTo(expected);
        }

        @DisplayName("저장한 값을 Dto 를 통해 반환한다.")
        @ParameterizedTest
        @MethodSource("lottoSourceProvider")
        void toDtoTest(List<Integer> numbers) {
            // act
            LottoDto sample = new Lotto(numbers).mapToDto();

            // assert
            assertThat(sample.numbers()).containsExactlyInAnyOrderElementsOf(numbers);
        }
    }
}