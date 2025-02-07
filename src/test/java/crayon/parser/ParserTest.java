package crayon.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import crayon.commands.Command;
import crayon.commands.FindCommand;
import crayon.commands.ListCommand;

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
