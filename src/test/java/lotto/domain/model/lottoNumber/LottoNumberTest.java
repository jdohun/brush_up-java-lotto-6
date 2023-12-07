package lotto.domain.model.lottoNumber;

import lotto.dto.LottoNumberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static lotto.util.ErrorMessagePrefix.ERROR_MESSAGE_PREFIX;
import static org.assertj.core.api.Assertions.*;

class LottoNumberTest {
    @DisplayName("생성 테스트")
    @Nested
    class createTest {
        private static List<List<Integer>> unsortedNumbersProvider() {
            return Arrays.asList(
                    Arrays.asList(5, 3, 1, 2, 4, 6),
                    Arrays.asList(45, 12, 33, 7, 21, 1),
                    Arrays.asList(10, 15, 25, 30, 20, 35)
            );
        }

        @DisplayName("1 ~ 45 숫자 범위를 벗어나면 예외를 발생시킨다.")
        @ParameterizedTest
        @ValueSource(ints = {0, 46})
        void createLottoNumberOutOfRange(int number) {
            assertThatThrownBy(() -> new LottoNumber(number))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_MESSAGE_PREFIX + "로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }

        @DisplayName("1 ~ 45 숫자 범위를 벗어나지 않으면 예외가 발생하지 않는다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45})
        void createLottoNumberInRange(int number) {
            assertThatCode(() -> new LottoNumber(number))
                    .doesNotThrowAnyException();
        }

        @DisplayName("정수 List 를 통해 생성할 때는 정렬하여 생성한다.")
        @ParameterizedTest
        @MethodSource("unsortedNumbersProvider")
        void fromTest(List<Integer> unsortedNumbers) {
            // act
            List<LottoNumber> createdByUnsortedNumbers = LottoNumber.from(unsortedNumbers);
            Collections.sort(unsortedNumbers);
            List<LottoNumber> createdBySortedNumbers = LottoNumber.from(unsortedNumbers);

            // assert
            assertThat(createdByUnsortedNumbers).containsExactlyElementsOf(createdBySortedNumbers);
        }
    }

    @DisplayName("구현 메소드 테스트")
    @Nested
    class MethodTest {
        @DisplayName("저장한 값을 Dto 를 통해 반환한다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45})
        void toDtoTest(int number) {
            // act
            LottoNumberDto tester = new LottoNumber(number).toDto();

            // assert
            assertThat(tester.number()).isEqualTo(number);
        }
    }

    @DisplayName("스탠다드 메소드 테스트")
    @Nested
    class StandardMethodTest {
        @DisplayName("equals 메소드")
        @Nested
        class EqualsMethodTest {

            @DisplayName("동일한 값을 통해 생성된 객체는 동일한 객체다.")
            @Test
            void testEqualsWithSameNumber() {
                LottoNumber lottoNumber1 = new LottoNumber(5);
                LottoNumber lottoNumber2 = new LottoNumber(5);

                assertThat(lottoNumber1.equals(lottoNumber2)).isTrue();
            }

            @DisplayName("다른 값을 통해 생성된 객체는 다른 객체다.")
            @Test
            void testEqualsWithDifferentNumber() {
                LottoNumber lottoNumber1 = new LottoNumber(10);
                LottoNumber lottoNumber2 = new LottoNumber(20);

                assertThat(lottoNumber1.equals(lottoNumber2)).isFalse();
            }

            @DisplayName("동일한 값이라도 다른 타입의 객체와는 같지 않다.")
            @Test
            void testEqualsWithDifferentType() {
                LottoNumber lottoNumber = new LottoNumber(15);
                String otherObject = "15";

                assertThat(lottoNumber.equals(otherObject)).isFalse();
            }
        }

        @DisplayName("hashCode 메소드")
        @Nested
        class HashCodeMethodTest {

            @DisplayName("동일한 숫자의 경우 hashCode 값이 같다.")
            @Test
            void testHashCodeWithSameNumber() {
                LottoNumber lottoNumber1 = new LottoNumber(8);
                LottoNumber lottoNumber2 = new LottoNumber(8);

                assertThat(lottoNumber1.hashCode()).isEqualTo(lottoNumber2.hashCode());
            }

            @DisplayName("다른 숫자의 경우 hashCode 값이 다르다.")
            @Test
            void testHashCodeWithDifferentNumber() {
                LottoNumber lottoNumber1 = new LottoNumber(30);
                LottoNumber lottoNumber2 = new LottoNumber(40);

                assertThat(lottoNumber1.hashCode()).isNotEqualTo(lottoNumber2.hashCode());
            }
        }
    }
}