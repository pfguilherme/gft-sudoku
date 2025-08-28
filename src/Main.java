import io.github.pfguilherme.sudoku.service.BoardService;
import io.github.pfguilherme.sudoku.service.NotifierService;
import io.github.pfguilherme.sudoku.ui.screen.MainScreen;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
        final var config = Stream.of(args)
            .collect(Collectors.toMap(
                key -> key.split(";")[0],
                value -> value.split(";")[1]
            ));

        BoardService boardService = new BoardService(config);
        NotifierService notifierService = new NotifierService();

        MainScreen mainScreen = new MainScreen(boardService, notifierService);
        mainScreen.build();
    }
}
