package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import java.util.List;

public class Gamer {
    private static final String NAME_LENGTH_ERROR_MESSAGE = "이름은 최소 1글자, 최대 6글자로 입력해야 합니다.";
    private static final String NAME_EMPTY_ERROR_MESSAGE = "빈 문자는 이름으로 입력할 수 없습니다.";
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 6;

    private final CardGroup cardGroup;
    private final String name;

    public Gamer(String name) {
        validateName(name);
        this.name = name;
        this.cardGroup = new CardGroup();
    }

    public Gamer(String name, List<Card> cards) {
        validateName(name);
        this.name = name;
        this.cardGroup = new CardGroup(cards);
    }

    private void validateName(String name) {
        validateEmpty(name);
        validateLength(name);
    }

    private void validateEmpty(String name) {
        if (isEmpty(name)) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR_MESSAGE);
        }
    }

    private boolean isEmpty(String name) {
        return name == null || name.isBlank();
    }

    private void validateLength(String name) {
        if (isWrongLength(name)) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR_MESSAGE);
        }
    }

    private boolean isWrongLength(String name) {
        return name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH;
    }

    public void addCard(Card card) {
        if (cardGroup.isBust()) {
            return;
        }
        cardGroup.addCard(card);
    }

    public boolean isBust() {
        return cardGroup.isBust();
    }

    public boolean isNotBust() {
        return cardGroup.isNotBust();
    }

    public boolean isInstantBlackJack() {
        return cardGroup.isInstantBlackJack();
    }

    public int getScore() {
        return cardGroup.calculateScore();
    }

    public List<Card> getCards() {
        return cardGroup.getCards();
    }

    public String getName() {
        return this.name;
    }
}
