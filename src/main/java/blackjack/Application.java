package blackjack;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    private static final String AGREE = "y";
    private static final String DECLINE = "n";

    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        List<Player> players = InputView.inputPlayerNames()
                                        .stream()
                                        .map(Player::new)
                                        .collect(Collectors.toList());
        Participants participants = Participants.of(dealer, players);

        participants.receiveDefaultCards(cardDeck);
        OutputView.printDefaultCardMessage(dealer, players);
        players.forEach(player -> drawMoreCard(player, cardDeck));
        receiveDealerCard(dealer, cardDeck);
        OutputView.printFinalCardsAndScore(participants);
        StatisticResult statisticResult = createStatisticResult(dealer, players);
        OutputView.printFinalResult(statisticResult);
    }

    private static void drawMoreCard(Player player, CardDeck cardDeck) {
        while (player.isAbleToReceiveCard()) {
            String answer = InputView.inputAnswerToAdditionalCardQuestion(player);
            if (AGREE.equals(answer)) {
                player.receiveCard(cardDeck.draw());
            }
            OutputView.printEachPlayerCards(player);
            if (DECLINE.equals(answer)) {
                return;
            }
        }
    }

    private static void receiveDealerCard(Dealer dealer, CardDeck cardDeck) {
        if (dealer.isAbleToReceiveCard()) {
            dealer.receiveCard(cardDeck.draw());
            OutputView.printDealerDrawingMessage(dealer);
        }
    }

    private static StatisticResult createStatisticResult(Dealer dealer, List<Player> players) {
        Map<String, Result> playerNameAndResult = new HashMap<>();
        players.forEach(player -> {
            playerNameAndResult.put(player.getName(), player.judgeResult(dealer));
        });
        return new StatisticResult(playerNameAndResult);
    }
}