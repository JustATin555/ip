package commands;

/**
 * Represents a parsed command produced by the parser.
 *
 * @param identifier An enumerable field that represents the command.
 * @param args       The command's arguments.
 */
public record ParsedCommand(
        CommandType identifier,
        Object args
) {
}
