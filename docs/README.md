# User Guide

```
.----------------.  .----------------.  .----------------.  .----------------.  .----------------. 
| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
| | ____    ____ | || |      __      | || |  _______     | || |     _____    | || |     ____     | |
| ||_   \  /   _|| || |     /  \     | || | |_   __ \    | || |    |_   _|   | || |   .'    `.   | |
| |  |   \/   |  | || |    / /\ \    | || |   | |__) |   | || |      | |     | || |  /  .--.  \  | |
| |  | |\  /| |  | || |   / ____ \   | || |   |  __ /    | || |      | |     | || |  | |    | |  | |
| | _| |_\/_| |_ | || | _/ /    \ \_ | || |  _| |  \ \_  | || |     _| |_    | || |  \  `--'  /  | |
| ||_____||_____|| || ||____|  |____|| || | |____| |___| | || |    |_____|   | || |   `.____.'   | |
| |              | || |              | || |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
 '----------------'  '----------------'  '----------------'  '----------------'  '----------------' 
```
## Features 
### It's-a me, Mario!~
Talks like Mario, acts like Mario. This program will get rid of your boredom by making it feels like you are really talking to the actual character!

### Different Type of Tasks
Add tasks that suits your needs. Types of task includes:
- **Todo**: Specifies tasks that needs to be done.
- **Deadline**: Specifies tasks that have deadlines.
- **Event**: Specifies tasks that happen on a certain date.

### Mark Tasks as Done
Tasks can be marked as done so that it will be easier to differentiate between tasks that are done and not done.

### Delete Irrelevant Tasks
Tasks that are no longer relevant can be deleted easily with a single command.

### Search with Ease
Search tasks using keyword easily with a single command.

### Persistent Data
Changes made to the task list are always saved to the disk and loaded when the program starts.

### Multi-Platform
This program is supported across multiple platforms including but not limited to **Windows**, **Linux** and **macOS**.

## Usage
### `list` - List the tasks available

This command will output the list of tasks available. 
In general, this command will output:
- Type of the task
- Done status
- Name

For deadline and event tasks, it will also include the date to be completed by and the date of the task respectively.

Example of usage: 
`list`

Expected outcome:
```
Here we go! These are the tasks you have:
  1. [T][✓] borrow book
  2. [D][✘] return book (by: Jan 23 2020 19:25)
  3. [E][✘] project meeting (at: Mar 05 2020 15:00)
You have 3 tasks and you completed 1 of them
```
*- Populated list.*
```
Here we go! These are the tasks you have:
You have 0 tasks and you completed 0 of them
```
*- Empty list.*


### `find <keyword>` - Find tasks that contains specific keyword

This command will find tasks that contains a specific keyword provided by the user. It will then output in the format similar to `list` with the exception that it will only show the matching tasks.

Required Option:
- `keyword` - Keyword to search for.

Example of usage: 
`find book`

Expected outcome:
```
Here are the matching tasks in your list:
  1. [T][✓] borrow book
  2. [D][✓] return book (by: Jan 23 2020 19:25)
```
*- Results matching the keyword "book".*
```
Oh, no! There's no task that matches the keyword: grocery
```
*- No matching tasks.*
```
Oh, no! You have to specify what to find!
```
*- Keyword is not specified.*


### `todo <name>` - Add a new Todo task to the task list

This command will create and add a new Todo task.

Required Option:
- `name` - Specifies the name of the task.

Example of usage: 
`todo borrow book`

Expected outcome:
```
Okey Dokey! Added: [T][✘] borrow book 
```
*- Todo task added successfully.*
```
Oh, no! Task cannot be created without a name!
```
*- Name of task not specified.*
```
Oh, no! More arguments is needed!
```
*- Not enough arguments are supplied.*


### `deadline <name> /by <date>` - Add a new Event task to the task list

This command will create and add a new Deadline task.

Required Options:
- `name` - Specifies the name of the task.
- `date` - Specifies the deadline of the task. Format of date must be in: `yyyy-MM-dd HHmm`.

Example of usage: 
`deadline return book /by 2020-01-23 1925`

Expected outcome:
```
Okey Dokey! Added: [D][✘] return book (by: Jan 23 2020 19:25)
```
*- Deadline task added successfully.*
```
Oh, no! Task cannot be created without a name!
```
*- Name of task not specified.*
```
Oh, no! Deadline's /by cannot be empty!
```
*- Deadline of task not specified.*
```
Oh, no! Please follow the follow date format: yyyy-MM-dd HHmm
```
*- Date specified is not in the required format.*
```
Oh, no! More arguments is needed!
```
*- Not enough arguments are supplied.*


### `event <name> /at <date>` - Add a new Event task to the task list

This command will create and add a new Event task.

Required Options:
- `name` - Specifies the name of the task.
- `date` - Specifies the date of occurrence of the task. Format of date must be in: `yyyy-MM-dd HHmm`.

Example of usage: 
`event project meeting /at 2020-03-05 1500`

Expected outcome:
```
Okey Dokey! Added: [E][✘] project meeting (at: Mar 05 2020 15:00)
```
*- Event task added successfully.*
```
Oh, no! Task cannot be created without a name!
```
*- Name of task not specified.*
```
Oh, no! Event's /at cannot be empty!
```
*- Event of task not specified.*
```
Oh, no! Please follow the follow date format: yyyy-MM-dd HHmm
```
*- Date specified is not in the required format.*
```
Oh, no! More arguments is needed!
```
*- Not enough arguments are supplied.*


### `done <order>` - Mark a specific task as done.

This command will attempt to mark the task specified by order as done.

Required Option:
- `order` - Order of the task to be marked as done.

Example of usage: 
`done 2`

Expected outcome:
```
We did it! Good job little guy.
  [D][✓] return book (by: Jan 23 2020 19:25)
```
*- Task is successfully marked as done.*
```
Mama Mia! You have already done this task~
  [D][✓] return book (by: Jan 23 2020 19:25)
```
*- Task is already done.*
```
Oh, no! You didn't specify which task you are done with!
```
*- Order is not specified.*
```
Oh, no! This task does not exist!
```
*- Specified task does not exist.*


### `delete <order>` - Delete a task from the task list

This command will attempt to delete a task specified by order from the task list.

Required Option:
- `order` - Order of the task to be deleted.

Example of usage: 
`delete 3`

Expected outcome:
```
Alrighty! The following task is gone!
	[E][✘] project meeting (at: Mar 05 2020 15:00)
```
*- Task is successfully deleted.*
```
Oh, no! You didn't specify which task you want to delete!
```
*- Order is not specified.*
```
Oh, no! I can't delete a task that does not exist!
```
*- Specified task does not exist.*


### `exit` - Exit the program

This command will cause the program to save the current list of tasks and exit.

Example of usage: 
`exit`

Expected Outcome:
```
Bye bye! See you in my gameses~
```

### Unknown Command
When an unknown command is entered, the program outputs the following message and returns to wait for input.

Example:
`someunknowncommand`
```
Oh, no! I don't get what you are trying to say!
```
