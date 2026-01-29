package Dave.commands;

/**
 * A unique identifier used to tell the chatbot how to handle a command.
 */
public enum CommandType {
    INVALID,
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE
}
