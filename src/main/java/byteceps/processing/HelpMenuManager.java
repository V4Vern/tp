package byteceps.processing;

import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.strings.HelpStrings;
import byteceps.ui.strings.CommandStrings;
import byteceps.validators.HelpValidator;

import static byteceps.ui.strings.ManagerStrings.NO_ACTION_EXCEPTION;

/**
 * Displays the correct command formatting for all BYTE-CEPS functionalities. Command formats are divided into 3
 * categories, each one corresponding to one of the 3 main commands (exercise, workout & program).
 */
//@@author LWachtel1
public class HelpMenuManager {

    public HelpMenuManager() {
    }
    /**
     * Returns String that explains to user how to access each of the 3 "help menus" for the
     * 3 main commands (exercise, workout & program). This can be accessed by user using 'help'.
     *
     * @return String informing user how to access each "help menu" for the main commands (exercise, workout & program).
     */
    public String getHelpGuidanceString() {
        return HelpStrings.HELP_GUIDANCE_MESSAGE;
    }
    /**
     * Displays either (1) a command help menu (if no valid numerical parameter is provided) or (2) the specific
     * command format for a specific BYTE-CEPS functionality, which corresponds to the help menu entry specified by
     * the provided valid numerical parameter or (3) the message containing guidance for accessing help menus (if
     * 'help' is entered alone).
     *
     * @param parser Parser containing required user input.
     * @return String of a command help menu, a specific command formatting or guidance for accessing help menus.
     */
    public String execute(Parser parser) throws Exceptions.InvalidInput {

        try {
            HelpValidator.validateCommand(parser);
        } catch (Exceptions.InvalidInput e) {
            if (e.getMessage().equals(NO_ACTION_EXCEPTION)) {
                return getHelpGuidanceString();
            } else {
                throw e;
            }
        }

        String commandToShow = parser.getAction().toLowerCase();
        boolean showAllActions = parser.getActionParameter().isEmpty();

        if (showAllActions) {
            return generateAllActions(commandToShow);
        }
        String parameter = parser.getActionParameter();
        return getParamFormat(parameter, commandToShow);

    }
    /**
     * Builds a String containing a command's entire help menu (either exercise, workout  or program) i.e., a command's
     * entire list of associated functionalities
     *
     * @param command Command for which user wants to view help menu.
     * @return String of a command's help menu as an indented list
     */
    private String generateAllActions(String command) throws Exceptions.InvalidInput {
        String[] flagFunctions;
        StringBuilder result = new StringBuilder();

        switch (command) {
        case CommandStrings.COMMAND_EXERCISE:
            flagFunctions = HelpStrings.EXERCISE_FLAG_FUNCTIONS;
            result.append(String.format(HelpStrings.HELP_LIST_ITEM, HelpStrings.EXERCISE_MESSAGE,
                    System.lineSeparator()));
            break;
        case CommandStrings.COMMAND_WORKOUT:
            flagFunctions = HelpStrings.WORKOUT_FLAG_FUNCTIONS;
            result.append(String.format(HelpStrings.HELP_LIST_ITEM, HelpStrings.WORKOUT_MESSAGE,
                    System.lineSeparator()));
            break;
        case CommandStrings.COMMAND_PROGRAM:
            flagFunctions = HelpStrings.PROGRAM_FLAG_FUNCTIONS;
            result.append(String.format(HelpStrings.HELP_LIST_ITEM, HelpStrings.PROGRAM_MESSAGE,
                    System.lineSeparator()));
            break;
        default:
            throw new Exceptions.InvalidInput(HelpStrings.INVALID_COMMAND_TYPE);
        }

        for (String flagFunction : flagFunctions) {
            result.append(String.format(HelpStrings.HELP_LIST_ITEM, flagFunction, System.lineSeparator()));
        }
        result.delete(0, 4);
        return result.toString();
    }
    /**
     * Returns a String containing the specific command format for a specific BYTE-CEPS functionality, corresponding
     * to the help menu entry specified by the user-provided numerical parameter.
     *
     * @param userParam String corresponding to numerical position of help menu entry of user-desired command format.
     * @param commandType String specifying which command help menu to find command formatting from.
     * @return String containing the specific command format corresponding to provided parameter.
     */
    private String getParamFormat(String userParam, String commandType) throws Exceptions.InvalidInput {
        try {
            int paramChoice = Integer.parseInt(userParam);
            int paramIndex = paramChoice - 1;

            switch (commandType) {
            case CommandStrings.COMMAND_EXERCISE:
                return getExerciseParamFormats(paramIndex);
            case CommandStrings.COMMAND_WORKOUT:
                return getWorkoutParamFormats(paramIndex);
            case CommandStrings.COMMAND_PROGRAM:
                return getProgramParamFormats(paramIndex);
            default:
                throw new Exceptions.InvalidInput(HelpStrings.INVALID_COMMAND_TYPE);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new Exceptions.InvalidInput(HelpStrings.INVALID_COMMAND);
        }
    }
    private String getExerciseParamFormats(int index) {
        return HelpStrings.EXERCISE_PARAM_FORMAT[index];
    }
    private String getWorkoutParamFormats(int index) {
        return HelpStrings.WORKOUT_PARAM_FORMAT[index];
    }
    private String getProgramParamFormats(int index) {
        return HelpStrings.PROGRAM_PARAM_FORMAT[index];
    }

}
