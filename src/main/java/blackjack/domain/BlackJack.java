package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.GamerGroup;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerGroup;
import blackjack.domain.result.GameResult;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJack {
    private final GamerGroup gamerGroup;
    private final CardPack cardPack;

    public BlackJack(PlayerGroup playerGroup) {
        this.gamerGroup = new GamerGroup(new Dealer(), playerGroup);
        this.cardPack = new CardPack();
    }

    public void divideCards() {
        gamerGroup.addInitialCards(cardPack);
    }

    public void addCardTo(Player player) {
        player.addCard(cardPack.pickOne());
    }

    public Player findPlayerByName(String name) {
        return gamerGroup.findPlayerByName(name);
    }

    public int addCardToDealer() {
        return gamerGroup.addCardToDealer(cardPack);
    }

    public void openDealerCards() {
        gamerGroup.openDealerCards();
    }

    public GameResult getGameResult() {
        return gamerGroup.getGameResult();
    }

    public List<Gamer> getGamers() {
        return gamerGroup.getGamers();
    }

    public Dealer getDealer() {
        return gamerGroup.getDealer();
    }

    public List<String> getPlayerNames() {
        return gamerGroup.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
