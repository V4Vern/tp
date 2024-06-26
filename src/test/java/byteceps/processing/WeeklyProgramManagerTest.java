package byteceps.processing;

import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import byteceps.ui.UserInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.TestInstantiationException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeeklyProgramManagerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final UserInterface ui = new UserInterface();
    private Parser parser;
    private ExerciseManager exerciseManager;
    private WorkoutManager workoutManager;
    private WeeklyProgramManager weeklyProgramManager;

    @BeforeEach
    void setUp() {
        parser = new Parser();
        exerciseManager = new ExerciseManager();
        workoutManager = new WorkoutManager(exerciseManager);
        WorkoutLogsManager workoutLogsManager = new WorkoutLogsManager();
        weeklyProgramManager = new WeeklyProgramManager(exerciseManager, workoutManager, workoutLogsManager);

        try {
            // create dummy exercises and workouts
            String[] exerciseInput = {"exercise /add benchpress", "exercise /add deadlift",
                "exercise /add barbell squat"};
            for (String input : exerciseInput) {
                parser.parseInput(input);
                assertDoesNotThrow(() -> exerciseManager.execute(parser));
            }

            String[] workoutInput = {"workout /create leg day", "workout /create full day"};
            for (String input : workoutInput) {
                parser.parseInput(input);
                assertDoesNotThrow(() -> workoutManager.execute(parser));
            }
        } catch (Exceptions.InvalidInput e) {
            throw new TestInstantiationException("Could not instantiate tests");
        }
    }

    @AfterEach
    void tearDown() {
    }

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void execute_assignValidWorkout_success() {
        String assignWorkoutInput = "program /assign leg day /to thurs";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_assignDuplicateWorkout_throwsActivityExistsException() {
        String assignWorkoutInput = "program /assign leg day /to thurs";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));
        assertThrows(Exceptions.ActivityExistsException.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_assignInvalidWorkout_throwsActivityDoesNotExist() {
        String assignWorkoutInput = "program /assign laze day /to thurs";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertThrows(Exceptions.ActivityDoesNotExist.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_assignBlankWorkout_throwsActivityDoesNotExist() {
        String assignWorkoutInput = "program /assign/to thurs";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_assignInvalidDate_throwsInvalidInput()  {
        String assignWrongWorkoutInput = "program /assign leg day /to wrong day";
        assertDoesNotThrow(() -> parser.parseInput(assignWrongWorkoutInput));
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));

        String assignWorkoutInput = "program /assign leg day /to 2024-03-11";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_incompleteAssignCommand_throwsInvalidInput() {
        String assignWorkoutInput = "program /assign";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_clearWorkout_success() {
        setUpStreams();
        String assignWorkoutInput = "program /assign leg day /to thurs";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        outContent.reset();
        ui.printMessage(weeklyProgramManager.getListString());

        String expectedAssignedOutput = "[BYTE-CEPS]> Your workouts for the week:\n" +
                "\tMONDAY: Rest day\n" +
                "\n" +
                "\tTUESDAY: Rest day\n" +
                "\n" +
                "\tWEDNESDAY: Rest day\n" +
                "\n" +
                "\tTHURSDAY: leg day\n" +
                "\n" +
                "\tFRIDAY: Rest day\n" +
                "\n" +
                "\tSATURDAY: Rest day\n" +
                "\n" +
                "\tSUNDAY: Rest day\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------";

        assertEquals(expectedAssignedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

        String clearWorkoutInput = "program /clear thurs";
        assertDoesNotThrow(() -> parser.parseInput(clearWorkoutInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        outContent.reset();


        ui.printMessage(weeklyProgramManager.getListString());
        String expectedClearOutput = "[BYTE-CEPS]> Your workouts for the week:\n" +
                "\tMONDAY: Rest day\n" +
                "\n" +
                "\tTUESDAY: Rest day\n" +
                "\n" +
                "\tWEDNESDAY: Rest day\n" +
                "\n" +
                "\tTHURSDAY: Rest day\n" +
                "\n" +
                "\tFRIDAY: Rest day\n" +
                "\n" +
                "\tSATURDAY: Rest day\n" +
                "\n" +
                "\tSUNDAY: Rest day\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------";
        assertEquals(expectedClearOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
        restoreStreams();
    }

    @Test
    void execute_clearInvalidDay_throwsInvalidInput() {
        String clearWorkoutInput = "program /clear noday";
        assertDoesNotThrow(() -> parser.parseInput(clearWorkoutInput));
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
    }


    @Test
    void executeHistoryAction_validDate_returnsFormattedWorkout() {
        setUpStreams();
        String dateString = LocalDate.now().toString();
        String todayString = LocalDate.now().getDayOfWeek().toString();
        String assignWorkoutInput = String.format("program /assign full day /to %s", todayString);

        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        String logInput = "program /log benchpress /weight 50 /sets 1 /reps 5";
        assertDoesNotThrow(() -> parser.parseInput(logInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        outContent.reset();

        String todayInput = "program /today";
        assertDoesNotThrow(() -> parser.parseInput(todayInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        String expectedOutput = String.format("[BYTE-CEPS]> Listing Exercises on %s:\n" +
                "1. benchpress\n" +
                "   Set 1: 50kg, 5 reps\n" +
                "-------------------------------------------------", dateString);

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
        restoreStreams();
    }

    @Test
    void execute_clearAll_success() {
        setUpStreams();
        String assignWorkoutInput = "program /assign leg day /to thurs";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        String assignWorkoutInput2 = "program /assign full day /to mon";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput2));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        outContent.reset();
        ui.printMessage(weeklyProgramManager.getListString());

        String expectedAssignedOutput = "[BYTE-CEPS]> Your workouts for the week:\n" +
                "\tMONDAY: full day\n" +
                "\n" +
                "\tTUESDAY: Rest day\n" +
                "\n" +
                "\tWEDNESDAY: Rest day\n" +
                "\n" +
                "\tTHURSDAY: leg day\n" +
                "\n" +
                "\tFRIDAY: Rest day\n" +
                "\n" +
                "\tSATURDAY: Rest day\n" +
                "\n" +
                "\tSUNDAY: Rest day\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------";

        assertEquals(expectedAssignedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

        String clearWorkoutInput = "program /clear";
        assertDoesNotThrow(() -> parser.parseInput(clearWorkoutInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        outContent.reset();

        ui.printMessage(weeklyProgramManager.getListString());
        String expectedClearOutput = "[BYTE-CEPS]> Your workouts for the week:\n" +
                "\tMONDAY: Rest day\n" +
                "\n" +
                "\tTUESDAY: Rest day\n" +
                "\n" +
                "\tWEDNESDAY: Rest day\n" +
                "\n" +
                "\tTHURSDAY: Rest day\n" +
                "\n" +
                "\tFRIDAY: Rest day\n" +
                "\n" +
                "\tSATURDAY: Rest day\n" +
                "\n" +
                "\tSUNDAY: Rest day\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------";
        assertEquals(expectedClearOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
        restoreStreams();
    }

    @Test
    void log_validLog_success() {
        setUpStreams();
        String dateString = LocalDate.now().toString();
        String todayString = LocalDate.now().getDayOfWeek().toString();
        String assignWorkoutInput = String.format("program /assign full day /to %s", todayString);

        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        String logInput = "program /log benchpress /weight 60 70 80 /sets 3 /reps 5 8 10";
        assertDoesNotThrow(() -> parser.parseInput(logInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        String expectedOutput = String.format("[BYTE-CEPS]> Workout full day assigned to %s\n" +
                "-------------------------------------------------" +
                "[BYTE-CEPS]> Successfully logged benchpress with weights of 60kg,70kg,80kg and 5,8,10 reps across 3 " +
                "sets on %s\n" +
                "-------------------------------------------------\n", todayString, dateString);

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

        outContent.reset();

        String todayInput = "program /today";
        assertDoesNotThrow(() -> parser.parseInput(todayInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        expectedOutput = String.format("[BYTE-CEPS]> Listing Exercises on %s:\n" +
                "1. benchpress\n" +
                "   Set 1: 60kg, 5 reps\n" +
                "   Set 2: 70kg, 8 reps\n" +
                "   Set 3: 80kg, 10 reps\n" +
                "-------------------------------------------------", dateString);

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
        restoreStreams();
    }

    @Test
    void log_incompleteLog_throwsInvalidInput() {
        String todayString = LocalDate.now().getDayOfWeek().toString();
        String assignWorkoutInput = String.format("program /assign full day /to %s", todayString);

        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        String[] invalidInputs = {"program /log benchpress /weight 500 /sets 5", "program /log benchpress " +
                "/weight 500 /reps 5", "program /log benchpress /sets 5 /reps 5", "program /log /weight 500 /sets 5 " +
                "/reps 5", "program /log benchpress /weight /sets 5 /reps 5", "program /log benchpress /weight /sets 5"
                + " /reps 5", "program /log benchpress /weight 2 /sets /reps 5", "program /log benchpress /weight 2 " +
                "/sets 5 /reps ", "program /log benchpress /weight /sets /reps ", "program /log benchpress /weight " +
                "/sets /reps abc", "program /log benchpress /weight /sets test /reps 4", "program /log benchpress " +
                "/weight abc /sets 3 /reps 4"};

        for (String input : invalidInputs) {
            assertDoesNotThrow(() -> parser.parseInput(input));
            assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
        }
    }


    @Test
    void log_invalidExerciseLog_throwsActivityDoesNotExist() {
        String todayString = LocalDate.now().getDayOfWeek().toString();
        String assignWorkoutInput = String.format("program /assign full day /to %s", todayString);

        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        String logInput = "program /log snooze /weight 500 /sets 1 /reps 5";
        assertDoesNotThrow(() -> parser.parseInput(logInput));
        assertThrows(Exceptions.ActivityDoesNotExist.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void log_history_success() {
        setUpStreams();
        String dateString = LocalDate.now().toString();
        String todayString = LocalDate.now().getDayOfWeek().toString();
        String assignWorkoutInput = String.format("program /assign full day /to %s", todayString);
        String finalAssignWorkoutInput = assignWorkoutInput;
        assertDoesNotThrow(() -> parser.parseInput(finalAssignWorkoutInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        String logInput = "program /log benchpress /weight 50 /sets 1 /reps 5";
        assertDoesNotThrow(() -> parser.parseInput(logInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        outContent.reset();

        String historyInput = "program /history";
        assertDoesNotThrow(() -> parser.parseInput(historyInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        String expectedHistory = String.format("[BYTE-CEPS]> Listing Workout Logs: 1. %s\n" +
                "-------------------------------------------------", dateString);

        assertEquals(expectedHistory.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));


        if (!todayString.equalsIgnoreCase("monday")) {
            assignWorkoutInput = "program /assign full day /to monday";
            String finalAssignWorkoutInput1 = assignWorkoutInput;
            assertDoesNotThrow(() -> parser.parseInput(finalAssignWorkoutInput1));
            assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));
        }


        String logHistoryInput = "program /log benchpress /weight 50 /sets 1 /reps 5 /date 2024-03-25";
        assertDoesNotThrow(() -> parser.parseInput(logHistoryInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        outContent.reset();
        assertDoesNotThrow(() -> parser.parseInput(historyInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        boolean checkContains = outContent.toString().contains(dateString)
                && outContent.toString().contains("2024-03-25");
        assertTrue(checkContains);
        restoreStreams();
    }

    @Test
    void log_historyInvalidDate_throwsInvalidInput() {
        String assignWorkoutInput = "program /assign full day /to monday";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        String logHistoryInput = "program /log benchpress /weight 500 /sets 5 /reps 5 /date 2024-2323-23";
        assertDoesNotThrow(() -> parser.parseInput(logHistoryInput));
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_list_success() {
        setUpStreams();
        String assignWorkoutInput = "program /assign full day /to monday";
        assertDoesNotThrow(() -> parser.parseInput(assignWorkoutInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        String listInput = "program /list";
        assertDoesNotThrow(() -> parser.parseInput(listInput));
        assertDoesNotThrow(() -> ui.printMessage(weeklyProgramManager.execute(parser)));

        String expectedOutput = "[BYTE-CEPS]>Workoutfulldayassignedtomonday\n" +
                "-------------------------------------------------";

        expectedOutput += "[BYTE-CEPS]> Your workouts for the week:\n" +
                "\tMONDAY: full day\n" +
                "\tTUESDAY: Rest day\n" +
                "\tWEDNESDAY: Rest day\n" +
                "\tTHURSDAY: Rest day\n" +
                "\tFRIDAY: Rest day\n" +
                "\tSATURDAY: Rest day\n" +
                "\tSUNDAY: Rest day\n" +
                "-------------------------------------------------";

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
        restoreStreams();
    }

}
