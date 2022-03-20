package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardGroupTest {

    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("invalidParameters")
    @DisplayName("카드의 합을 테스트한다.")
    void getSum(Card card, int result, String testName) {
        CardGroup cardGroup = new CardGroup();
        cardGroup.addCard(Card.of(CardShape.HEART, CardNumber.TWO));
        cardGroup.addCard(Card.of(CardShape.HEART, CardNumber.QUEEN));
        cardGroup.addCard(card);
        assertThat(cardGroup.calculateScore()).isEqualTo(result);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of(Card.of(CardShape.HEART, CardNumber.THREE), 15, "카드 숫자 합 12에서 3 추가시에 15"),
                Arguments.of(Card.of(CardShape.HEART, CardNumber.KING), 22, "카드 숫자 합 12에서 10 추가시에 22")
        );
    }
}
