import io.github.pfguilherme.sudoku.ui.screen.MainScreen;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    private static final int BOARD_SIZE = 9;

    public static void main(String[] args)
    {
        final var positions = Stream.of(args)
            .collect(Collectors.toMap(
                key -> key.split(";")[0],
                value -> value.split(";")[1]
            ));

        System.out.println(positions);

        MainScreen mainScreen = new MainScreen(BOARD_SIZE);
        mainScreen.build();
    }
}
