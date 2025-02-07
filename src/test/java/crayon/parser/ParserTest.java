package crayon.parser;

import crayon.commands.Command;
import crayon.commands.FindCommand;
import crayon.commands.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void parseCommand_list_returnsListCommand() {
        Command command = Parser.parseCommand("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parseCommand_find_returnsFindCommand() {
        Command command = Parser.parseCommand("find keyword");
        assertInstanceOf(FindCommand.class, command);
    }
}
