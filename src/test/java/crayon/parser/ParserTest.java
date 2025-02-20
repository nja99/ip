package crayon.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import crayon.commands.*;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    void parseCommand_list_returnsListCommand() {
        Command c_1 = Parser.parseCommand("list");
        Command c_2 = Parser.parseCommand("list todo");
        Command c_3 = Parser.parseCommand("list deadline");
        Command c_4 = Parser.parseCommand("list event");
        assertInstanceOf(ListCommand.class, c_1);
        assertInstanceOf(ListCommand.class, c_2);
        assertInstanceOf(ListCommand.class, c_3);
        assertInstanceOf(ListCommand.class, c_4);
    }

    @Test
    void parseCommand_find_returnsFindCommand() {
        Command command = Parser.parseCommand("find keyword");
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    void parseCommand_add_returnsAddTasksCommand() {
        Command c_1 = Parser.parseCommand("todo test");
        Command c_2 = Parser.parseCommand("deadline test /by 12/12/2025 2359");
        Command c_3 = Parser.parseCommand("event test /from 1/1/2025 2359 /to 12/12/2025 2359");
        assertInstanceOf(AddToDoCommand.class, c_1);
        assertInstanceOf(AddDeadlineCommand.class, c_2);
        assertInstanceOf(AddEventCommand.class, c_3);
    }

    @Test
    void parseCommand_mark_returnsMarkCommand() {
        Command command = Parser.parseCommand("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void parseCommand_unmark_returnsUnmarkCommand() {
        Command command = Parser.parseCommand("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    void parseCommand_delete_returnsDeleteCommand() {
        Command command = Parser.parseCommand("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void parseCommand_bye_returnsByeCommand() {
        Command command = Parser.parseCommand("bye");
        assertInstanceOf(ByeCommand.class, command);
    }
}
