package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonDetailsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tag/name contain any of "
            + "the specified keywords (case-insensitive) or if they owed you money\n"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_NAME + " KEYWORD " + PREFIX_TAG + " KEYWORD " + PREFIX_MONEY + "]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MONEY + " " + PREFIX_NAME + " Alex\n"
            + "(Finds an Alex that owes you money.)";

    private final PersonDetailsPredicate predicate;

    public FindCommand(PersonDetailsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
