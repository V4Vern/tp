# BYTE-CEPS User Guide

## Introduction
Welcome to BYTE-CEPS, your CLI-based all-in-one tool for setting and tracking fitness goals. Whether you're a tech-savvy fitness enthusiast or just starting your fitness journey, BYTE-CEPS offers the simplicity and efficiency of a CLI interface to help you maintain or improve your fitness through self-managed routines.

Please note that, as of the current version, BYTE-CEPS supports tracking both weight-related exercises and body weight exercises. However, cardio tracking is not yet supported. Stay tuned for future updates as we strive to expand BYTE-CEPS to encompass a broader range of fitness activities.

- [BYTE-CEPS User Guide](#byte-ceps-user-guide)
	- [Introduction](#introduction)
	- [Features](#features)
	- [Usage](#usage)
		- [Running ByteCeps](#running-byteceps)
	- [Exercise Management](#exercise-management)
		- [Add an exercise](#add-an-exercise)
		- [Delete an exercise](#delete-an-exercise)
		- [Edit an exercise](#edit-an-exercise)
		- [List all exercises](#list-all-exercises)
		- [Search exercises](#search-exercises)
	- [Workout Plan Management](#workout-plan-management)
		- [Add a workout plan](#add-a-workout-plan)
		- [Delete a workout plan](#delete-a-workout-plan)
		- [Edit Workout Plan](#edit-workout-plan)
		- [List workout plan](#list-workout-plan)
		- [Search workout plans](#search-workout-plans)
		- [Assign an exercise to a workout plan](#assign-an-exercise-to-a-workout-plan)
		- [Remove an exercise from a workout plan](#remove-an-exercise-from-a-workout-plan)
		- [List all exercises in a workout plan](#list-all-exercises-in-a-workout-plan)
	- [Program Management](#program-management)
		- [Choose a workout plan for a day](#choose-a-workout-plan-for-a-day)
		- [View Today's workout plan:](#view-todays-workout-plan)
		- [View Weekly workout plan](#view-weekly-workout-plan)
		- [Remove a workout plan for a day](#remove-a-workout-plan-for-a-day)
		- [Remove all workouts in weekly program](#remove-all-workouts-in-weekly-program)
	- [Logging Workouts](#logging-workouts)
		- [Adding an exercise log](#adding-an-exercise-log)
			- [To log a single set of an exercise](#to-log-a-single-set-of-an-exercise)
			- [Logging Multiple Sets with Varying Weights and Reps](#logging-multiple-sets-with-varying-weights-and-reps)
			- [Overwriting an Existing Log](#overwriting-an-existing-log)
		- [Adding an exercise log for a separate date](#adding-an-exercise-log-for-a-separate-date)
		- [Viewing logs](#viewing-logs)
		- [Viewing historic logs](#viewing-historic-logs)
	- [Help Menu](#help-menu)
		- [Accessing Help Menu](#accessing-help-menu)
		- [Displaying Help Menu Category: Exercise](#displaying-help-menu-category-exercise)
		- [Displaying Help Menu Category: Workout](#displaying-help-menu-category-workout)
		- [Displaying Help Menu Category: Program](#displaying-help-menu-category-program)
	- [Exiting program](#exiting-program)
	- [Saving the data](#saving-the-data)
	- [Editing the data](#editing-the-data)
	- [Command summary](#command-summary)


## Features
BYTE-CEPS can track & manage several types of tasks, such as:
1. Exercise
2. Workout
3. Program
   
## Usage
### Running ByteCeps
- You are required to install Java 11 onto your computer.
- Download the [latest release](https://github.com/AY2324S2-CS2113-F14-3/tp/releases) from the releases page.
- Run the program, in its own folder, from your preferred terminal using the command: java -jar byteceps.jar.

## Exercise Management
Using the `exercise` command, you may manage your exercises that have been stored in BYTE-CEPS. 
- Do take note that exercise names are `case insensitive`
  
### Add an exercise
You may add a new exercise using the `/add` flag.
```
exercise /add <EXERCISE_NAME [string]>
```

Example of usage: 
```
exercise /add pushups
```

Expected outcome:
```
[BYTE-CEPS]> Added Exercise: pushups
```
**Note:** Exercise names cannot contain special characters: { } [ ] / \\ : , # -

### Delete an exercise
You may also delete an existing exercise using the `/delete` flag.
```
exercise /delete <EXERCISE_NAME [string]>
```

Example of usage: 
```
exercise /delete pushups
```

Expected outcome:
```
[BYTE-CEPS]> Deleted Exercise: pushups
```

**Note:** Deleting an exercise also removes it from all workouts containing that exercise.
### Edit an exercise
If you ever need to edit an exercise name, you may do so using the `/edit` flag.
```
exercise /edit <OLD_EXERCISE_NAME [string]> /to <NEW_EXERCISE_NAME [string]>
```

Example of usage: 
```
exercise /edit pushups /to Decline pushups
```

Expected outcome:
```
[BYTE-CEPS]> Edited Exercise from pushups to Decline pushups
```

### List all exercises
You may list all existing exercises by using the `/list` flag.
```
exercise /list
```

Example of usage: 
```
exercise /list
```

Expected outcome:
```
[BYTE-CEPS]> Listing Exercises:
          1. Decline pushups
```
**Note**: The exercises may not be listed in the order you added them to ByteCeps.

### Search exercises
You may search exercises by using the `/search` flag.
```
exercise /search <EXERCISE_NAME [string]>
```

Example of usage: 
```
exercise /search pushup
```

Expected outcome:
```
[BYTE-CEPS]> Search Results:
          1. pushup
```

## Workout Plan Management
A workout plan is a curated list of exercises that you would like to do in a single session. You may use the `workout` command to manage your workout plans.
- Do take note that workout plan names are `case insensitive`
  
### Add a workout plan
In order to assign a exercise to a workout plan, you must first create a workout using the `/create` flag.
```
workout /create <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /create push day
```

Expected outcome:
```
[BYTE-CEPS]> Added Workout Plan: push day
```
**Note:** Workout Plan names cannot contain special characters: { } [ ] / \\ : , # -

### Delete a workout plan
To delete an existing workout plan, use the `/delete` flag.
```
workout /delete <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /delete push day
```

Expected outcome:
```
[BYTE-CEPS]> Deleted Workout: push day
```
**Note:** Deleting a workout assigned to a day in your training program will also cause it to be removed from the training program.

### Edit Workout Plan
If you ever need to edit a workout plan, you may do so using the `/edit` flag.
```
workout /edit <OLD_WORKOUT_PLAN_NAME [string]> /to <NEW_WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /edit push day /to pull day
```

Expected outcome:
```
[BYTE-CEPS]> Edited Workout Plan from push day to pull day
```

### List workout plan
You may list all your workout plans by using the `/list` flag.
```
workout /list 
```

Example of usage: 
```
workout /list
```

Expected outcome:
```
[BYTE-CEPS]> Listing Workouts:
            1. test
            2. push day
```
**Note**: The workouts may not be listed in the order you added them to ByteCeps.

### Search workout plans
To search existing workout plans, use the `/search` flag.
```
workout /search <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /search day
```

Expected outcome:
```
[BYTE-CEPS]> Search Results:
	     1. leg day
             2. push day
```

### Assign an exercise to a workout plan
You may assign an exercise to a specified workout plan using the `/assign` flag.
```
workout /assign <EXERCISE_NAME [string]> /to <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /assign pushups /to push day
```
Expected outcome:
```
[BYTE-CEPS]> Assigned Exercise 'pushups' to Workout Plan 'push day'
```

### Remove an exercise from a workout plan
You may remove an exercise from a specified workout plan using the `/unassign` flag.
```
workout /unassign <EXERCISE_NAME [string]> /from <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /unassign pushups /from push day
```

Expected outcome:
```
[BYTE-CEPS]> Unassigned Exercise 'pushups' from Workout Plan 'push day'
```

### List all exercises in a workout plan
You may see all the exercises in a given workout plan by using the `/info` flag.
```
workout /info <WORKOUT_PLAN_NAME [string]>
```

Example of usage: 
```
workout /info push day
```

Expected outcome:
```
[BYTE-CEPS]> Listing exercises in workout plan 'push day':
            1. pushups
```
**Note**: The exercises may not be listed in the order you added them to the workout.

## Program Management
The `program` command not only allows you to assign a workout to a given day, but it allows you to log your completed exercises.

### Choose a workout plan for a day
You may assign a workout plan to a specific day of the week using the `/assign` flag.
```
program /assign <WORKOUT_PLAN_NAME [string]> /to <DAY [string]>
```

The `<DAY [string]>` parameter must be one of the listed variants of a day of the week, and is case insensitive:
1. Monday / Mon
2. Tuesday / Tues / Tue
3. Wednesday / Wed
4. Thursday / Thurs / Thu
5. Friday / Fri
6. Saturday / Sat
7. Sunday / Sun

Example of usage: 
```
program /assign push day /to monday
```

Expected outcome:
```
[BYTE-CEPS]> Workout push day assigned to monday
```

**Note**: You can only assign ONE workout plan to any given day

### View Today's workout plan:
You may see the workout plan for today using the `/today` flag.
```
program /today
```

Example of usage: 
```
program /today
```

Expected outcome:
```
[BYTE-CEPS]> Listing Exercises on 2024-03-28:
			1. benchpress
			2. overhead press
			3. chest fly
```
OR 
```
[BYTE-CEPS]> There is no workout assigned today (MONDAY)
```

### View Weekly workout plan
You may see all workout plans assigned to each day of the week by using the `/list` flag.
```
program /list
```

Example of usage: 
```
program /list
```

Expected outcome:
```
[BYTE-CEPS]> Your workouts for the week:
	MONDAY: 	full body
		1. benchpress
		2. barbell squat
		3. deadlift

	TUESDAY: Rest day

	WEDNESDAY: 	legs
		1. leg extensions
		2. barbell squat

	THURSDAY: Rest day

	FRIDAY: push day
		1. benchpress
		2. overhead press
		3. chest fly

	SATURDAY: Rest day

	SUNDAY: Rest day
```

### Remove a workout plan for a day
You can also remove a workout plan from a given day of the week by using the `/clear` flag.
```
program /clear <DAY [string]>
```
As stated, the DAY parameter must follow the format above.

Example of usage: 
```
program /clear Tuesday
```

Expected outcome:
```
[BYTE-CEPS]> Your workout on Tuesday has been cleared
```

### Remove all workouts in weekly program
You can also remove all workout plans in a week by using the `/clear` flag without specifying the date.

Example of usage: 
```
program /clear
```

Expected outcome:
```
[BYTE-CEPS]> All your workouts have been cleared from the week
```

## Logging Workouts
You can log the amount of weight, sets, and repetitions you have completed for an exercise on a given day using the logging functionality. This allows for comprehensive tracking of your workout progress over time.

To log your exercises, you must first have a workout plan assigned for the day you are logging. It's possible to log an exercise not originally in your workout plan, providing flexibility in your programs. However, the exercise must be created beforehand.

### Adding an exercise log
You may create a workout log using the `/log` flag in the program command. Now, with enhanced functionality, you can log multiple weights and repetitions for each set, allowing for a more detailed recording of your workout.

#### To log a single set of an exercise
```
program /log <EXERCISE_NAME [string]> /weight <WEIGHT [integer]> /sets <NUMBER_OF_SETS [integer]> /reps <NUMBER_OF_REPS [integer]>
```

Example of usage: 
```
program /log benchpress /weight 125 /sets 1 /reps 5
```

Expected outcome:
```
[BYTE-CEPS]> Successfully logged benchpress with a weight of 125kg and 5 reps across 1 set on 2024-03-28
```

#### Logging Multiple Sets with Varying Weights and Reps
```
program /log <EXERCISE_NAME [string]> /weight <WEIGHT1 [integer] WEIGHT2 [integer] ...> /sets <NUMBER_OF_SETS> /reps <REPS1 [integer] REPS2 [integer] ...>
```

Example of usage: 
```
program /log benchpress /weight 100 110 120 /sets 3 /reps 5 4 3
```

Expected outcome:
```
[BYTE-CEPS]> Successfully logged benchpress with weights of 100kg, 110kg, 120kg and reps of 5, 4, 3 across 3 sets on 2024-03-28
```

#### Overwriting an Existing Log
You may overwrite an existing logged exercise by logging an exercise with the same name.

For example, you may have logged an exercise incorrectly.
```
program /log benchpress /weight 100 11 120 /sets 3 /reps 5 4 3
```

You may wish to overwrite it by sending the same command with the fixed values.
```
program /log benchpress /weight 100 110 120 /sets 3 /reps 5 4 3
```

Expected outcome:
```
[BYTE-CEPS]> It seems like benchpress already exists, overwriting...
[BYTE-CEPS]> Successfully logged benchpress with weights of 100kg, 110kg, 120kg and 5, 4, 3 reps across 3 sets on 2024-04-14
```


### Adding an exercise log for a separate date
You may also create a workout log for a specified date.
```
program /log <EXERCISE_NAME [string]> /weight <WEIGHT [integer]> /sets <NUMBER_OF_SETS [integer]> /reps <NUMBER_OF_REPS [integer]> /date <DATE [yyyy-mm-dd]>
```

Example of usage: 
```
program /log benchpress /weight 125 /sets 1 /reps 5 /date 2024-03-25
```

Expected outcome:
```
[BYTE-CEPS]> Successfully logged 125kg benchpress with 3 sets and 5 reps on 2024-03-25
```

### Viewing logs
You may see all the dates for which you have entered at least a single log entry by using the `/history` flag in the program command. 
```
program /history
```

Example of usage:
```
program /history
```

Expected outcome:
```
[BYTE-CEPS]> Listing Workout Logs:
			1. 2024-03-27
			2. 2024-03-06
			3. 2024-03-28
			4. 2024-03-25
```

### Viewing historic logs
You may view the logs that you have added on a given date by specifying a date in the `/history` flag.
```
program /history <DATE [yyyy-mm-dd]>
```

Example of usage:
```
program /history 2024-03-27
```

Expected outcome:
```
[BYTE-CEPS]> Listing Exercises on 2024-03-27:
			1. barbell squat (weight: 70, sets: 3, reps: 5)
			2. leg extensions (weight: 55, sets: 3, reps: 15)
```
## Help Menu
You are able to access an in-program help menu that provides you with command formats for all of ByteCeps's functionality.

### Accessing Help Menu
ByteCeps provides the command format to access the help menu upon initial program execution:
```
[BYTE-CEPS]> To access the help menu for command guidance, please type:
help /COMMAND_TYPE_FLAG
Available command types (type exactly as shown):
exercise
workout
program
To view this message again, enter 'help' alone
```

To view this message that provides guidance for how to access the help menu, a user only needs to type `help` with no additional flags or parameters.

Command formats are shown according to 3 separate categories: `exercise`, `workout` & `program`

### Displaying Help Menu Category: Exercise
You may access this portion of the help menu using the `/exercise` flag with the `help command`:
```
help /exercise
```

Outcome:
```
[BYTE-CEPS]> Please enter 'help /exercise LIST_NUMBER'. LIST_NUMBER corresponds to the exercise command format you want to see
			 1. add an exercise
			 2. delete an existing exercise
			 3. edit an existing exercise's name
			 4. list all existing exercises
```

To see a specific `exercise`-related command's format, enter `help /exercise <INDEX [integer]>`.

The `<INDEX [integer]>` parameter refers to the desired command's corresponding list number as displayed by the help menu.

Example of usage:
```
help /exercise 3
```

Expected outcome:
```
[BYTE-CEPS]> exercise /edit <OLD_EXERCISE_NAME [string]> /to <NEW_EXERCISE_NAME [string]>
```

### Displaying Help Menu Category: Workout
You may access this portion of the help menu using the `/workout` flag with the `help command`:
```
help /workout
```

Outcome:
```
[BYTE-CEPS]> Please enter 'help /workout LIST_NUMBER'. LIST_NUMBER corresponds to the workout command format you want to see
			 1. create a workout plan
			 2. delete an existing workout plan
			 3. list all existing workout plans
			 4. assign an exercise to a specified workout plan
			 5. remove an exercise from a specified workout plan
			 6. list all exercises in a given workout plan
```

To see a specific `workout`-related command's format, enter `help /workout <INDEX [integer]>`.

The `<INDEX [integer]>` parameter refers to the desired command's corresponding list number as displayed by the help menu.

Example of usage:
```
help /workout 4
```

Expected outcome:
```
[BYTE-CEPS]> workout /assign <EXERCISE_NAME [string]> /to <WORKOUT_PLAN_NAME [string]>
```

### Displaying Help Menu Category: Program
You may access this portion of the help menu using the `/program` flag with the `help command`:
```
help /program
```

Outcome:
```
[BYTE-CEPS]> Please enter 'help /program LIST_NUMBER'. LIST_NUMBER corresponds to the program command format you want to see
			 1. assign a workout plan to a specific day of the week
			 2. view today's workout plan
			 3. see all workout plans assigned to each day of the week
			 4. remove a workout plan from a given day of the week
			 5. create a log for the amount of weight, sets & reps completed for an exercise on a given day
			 which already has an assigned workout plan
			 6. create a log for a specified date
			 7. see all the dates that you have entered at least 1 log entry
			 8. view the logs that you have added on a specific date
```

To see a specific `program`-related command's format, enter `help /program <INDEX [integer]>`.

The `<INDEX [integer]>` parameter refers to the desired command's corresponding list number as displayed by the help menu.

Example of usage:
```
help /program 7
```

Expected outcome:
```
[BYTE-CEPS]> program /history
```

## Exiting program
You may exit the program using the `exit` or the `bye` command.
```
exit
```
Example of usage: 
```
exit
```
Expected outcome:
```
[BYTE-CEPS]> All your workouts and exercises have been saved.
-------------------------------------------------
-------------------------------------------------
GOODBYE FOR NOW. STAY HARD!
-------------------------------------------------
```

## Saving the data
BYTE-CEPS data are saved on the hard disk automatically after the `exit` command . There is no need to save manually.

## Editing the data
BYTE-CEPS data are saved automatically as a JSON file `data.json` in the same directory as the Jar File. Advanced users are welcome to update data directly by editing that data file.

> Caution: If your changes to the data file make its format invalid, BYTE-CEPS will save the current data file as `data.json.old_YYYY_MMDD_HHSS_MM` and start with an empty data file at the next run.
Furthermore, certain edits can cause BYTE-CEPS to behave in unexpected ways (e.g., if a date entered is of a different format). Therefore, only edit the data file if you are confident that you can do so correctly.

## Command summary

| Action 		 			                                 | Format 	 								                                                                                                                                               | Example 	 				                                                         |
|-----------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|	
| Add an exercise                               | `exercise /add <EXERCISE_NAME [string]>` 					                                                                                                                  | `exercise /add pushups`			                                             |
| Delete an exercise 	 			                      | `exercise /delete <EXERCISE_NAME [string]>`					                                                                                                                | `exercise /delete pushups ` 		                                         |
| Edit an exercise 	 			                        | `exercise /edit <OLD_EXERCISE_NAME [string]> /to <NEW_EXERCISE_NAME [string]> `                                                                                 | `exercise /edit pushups /to Decline pushups`                           |
| List all exercises 			                        | `exercise /list `								                                                                                                                                       | `exercise /list` 			                                                   |
| Search exercise	 			                          | `exercise /search <EXERCISE_NAME [string]>` 				                                                                                                                | `exercise /search pushups`		                                           |
| Add a workout plan 			                        | `workout /create <WORKOUT_PLAN_NAME [string]>` 				                                                                                                            | `workout /create push day ` 		                                         |
| Delete a workout plan	                        | `workout /delete <WORKOUT_PLAN_NAME [string]>` 				                                                                                                             | `workout /delete push day`		                                           |
| List all workout plans 	 	                    | `workout /list`								                                                                                                                                         | `workout /list` 			                                                    |
| Search workout plans 		                       | `workout /search <WORKOUT_PLAN_NAME [string]>` 				                                                                                                             | `workout /search push day ` 		                                         |
| Assign an exercise to a workout plan	         | `workout /assign <EXERCISE_NAME [string]> /to <WORKOUT_PLAN_NAME [string]>`                                                                                     | `workout /assign pushups /to push day`                                 |
| Remove an exercise from a workout plan 	      | `workout /unassign <EXERCISE_NAME [string]> /from <WORKOUT_PLAN_NAME [string]>`                                                                                 | `workout /unassign pushups /from push day`                             |
| List all exercises in a workout plan 	        | `workout /info <WORKOUT_PLAN_NAME [string]> `				                                                                                                               | `workout /info push day`		                                             |
| Choose a workout plan for a day 	 	           | `program /assign <WORKOUT_PLAN_NAME [string]> /to <DAY [string]> `	                                                                                             | `program /assign push day /to monday`                                  |
| View Today's workout plan	 		                 | `program /today `								                                                                                                                                       | `program /today`			                                                    |
| View Weekly workout plan 	 		                 | `program /list` 								                                                                                                                                        | `program /list`			                                                     |
| Remove a workout plan for a day 	 	           | `program /clear <DAY [string]>`						                                                                                                                           | `program /clear Tuesday`		                                             |
| Remove all workouts in weekly program 	 	     | `program /clear`						                                                                                                                                          | `program /clear`		                                                     |
| Adding an exercise log			                     | `program /log <EXERCISE_NAME [string]> /weight <WEIGHT1 [integer] WEIGHT2 [integer] ...> /sets <NUMBER_OF_SETS [integer]> /reps <REPS1 [integer] REPS2 [integer] ...> `                           | `program /log benchpress /weight 100 110 120 /sets 3 /reps 5 4 3`                 |
| Adding an exercise log for a separate date			 | `program /log <EXERCISE_NAME [string]> /weight <WEIGHT [integer]> /sets <NUMBER_OF_SETS [integer]> /reps <NUMBER_OF_REPS [integer]> /date <DATE [yyyy-mm-dd]> ` | `program /log benchpress /weight 125 /sets 3 /reps 5 /date 2024-03-25` |
| Viewing historic logs 	 		                    | `program /history`							                                                                                                                                       | `program /history`			                                                  |
| Viewing historic logs 	 		                    | `program /history <DATE [yyyy-mm-dd]>	`				                                                                                                                     | `program /history <DATE [yyyy-mm-dd]>` 	                               |
| Displaying Help Menu Guidance Message 	      | `help`				                                                                                                                        | `help` 	
| Displaying Help Menu Category: Exercise	      | `help /exercise`				                                                                                                                        | `help /exercise` 			                                                 |
| Displaying Help Menu Category: Workout	       | `help /workout`					                                                                                                                         | `help /workout` 			                                                  |
| Displaying Help Menu Category: Program	       | `help /program`				                                                                                                                           | `help /program `			                                                   |
| Displaying Command Format from Help Menu Category: Exercise	      | `help /exercise <INDEX [integer]> 	`				                                                                                                                        | `help /exercise 3` 			                                                 |
| Displaying Command Format from Help Menu Category: Workout	       | `help /workout<INDEX [integer]> 	`					                                                                                                                         | `help /workout 4` 			                                                  |
| Displaying Command Format from Help Menu Category: Program	       | `help /program<INDEX [integer]>	`				                                                                                                                           | `help /program 7`			                                                   |
| Exit the program	       | `bye `					                                                                                                                         | `bye ` 			                                                  |
| Exit the program	       | `bye `				                                                                                                                           | `bye `			                                                   |

