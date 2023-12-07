package lotto.domain.model.lotteries;

import lotto.domain.generator.AutoLottoGenerator;
import lotto.domain.model.lotto.Lotto;
import lotto.dto.LottoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LotteriesTest {

    @DisplayName("생성 테스트")
    @Nested
    class CreateTest {
        @DisplayName("생성 시 null 을 전달하면 예외가 발생한다.")
        @Test
        void createByNull() {
            assertThatThrownBy(() -> new Lotteries(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "null 을 전달받을 수 없습니다.");
        }

        @DisplayName("생성 시 빈 리스트를 전달하면 예외가 발생한다.")
        @Test
        void createByEmptyList() {
            // arrange
            List<Lotto> emptyLotteries = new ArrayList<>();

            // act & assert
            assertThatThrownBy(() -> new Lotteries(emptyLotteries))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "전달받은 리스트가 비어있습니다.");
        }

        @DisplayName("생성 시 전달받은 리스트에 null 이 포함되있으면 예외가 발생한다.")
        @Test
        void createByContainsNullList() {
            // arrange
            List<Lotto> containsNullLotteries = new ArrayList<>();
            containsNullLotteries.add(null);

            // act & assert
            assertThatThrownBy(() -> new Lotteries(containsNullLotteries))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "전달받은 값에 null 이 포함되어 있습니다.");
        }
    }

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

        @DisplayName("저장한 Lotto List 를 Dto List 를 통해 반환한다.")
        @Test
        void toDtoListTest() {
            // arrange
            List<List<Integer>> lottoSourceProvider = lottoSourceProvider();
            List<Lotto> lotteries = lottoSourceProvider.stream()
                    .map(Lotto::new)
                    .collect(Collectors.toList());

            // act
            List<LottoDto> result = new Lotteries(lotteries).toDtoList();

            // assert
            assertThat(result).hasSize(lotteries.size());

            for (int i = 0; i < result.size(); i++) {
                LottoDto lottoDto = result.get(i);
                List<Integer> numbers = lottoSourceProvider.get(i);

                assertThat(lottoDto.numbers()).containsExactlyInAnyOrderElementsOf(numbers);
            }
        }

        @DisplayName("내부 정보를 불변 객체로 반환한다.")
        @Test
        void getImmutableLotteriesTest() {
            // arrange
            Lotteries lotteries = AutoLottoGenerator.getInstance().createLotteries(2000);

            // act
            List<Lotto> immutableLotteries = lotteries.getImmutableLotteries();

            // assert
            assertThatThrownBy(() -> immutableLotteries.add(new Lotto(Arrays.asList(1,2,3,4,5,6))))
                    .isInstanceOf(UnsupportedOperationException.class);

            assertThatThrownBy(() -> immutableLotteries.remove(1))
                    .isInstanceOf(UnsupportedOperationException.class);
        }
    }
}