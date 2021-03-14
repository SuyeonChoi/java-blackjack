package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int GET_ONE_MORE_CARD_NORM = 16;
    private final List<GameResult> gameResults;

    public Dealer() {
        super(DEALER_NAME);
        gameResults = new ArrayList<>();
    }

    public boolean canDraw() {
        return score() <= GET_ONE_MORE_CARD_NORM;
    }

    public int resultCount(GameResult gameResult) {
        return (int) gameResults.stream()
            .filter(it -> it == gameResult)
            .count();
    }

    public void addGameResult(GameResult gameResult) {
        gameResults.add(gameResult);
    }

    public Money profit(Players players) {
        Money dealerProfit = new Money();
        for (Player player : players.getPlayers()) {
            Money playerProfitMoney = player.profit(this);
            dealerProfit = dealerProfit.sum(playerProfitMoney.negative());
        }
        return dealerProfit;
    }
}