# Sudoku Helper

## Hi!


This is my second major project, the first one written in Java. The main purpose of this app is to help people solve sudoku, but not just classic 9x9. I want to enable this on non-standard boards (for example 6x6). Of course, Sudoku Helper offers the possibility of playing a regular game as well. Below I advertise **instruction for users**, **the documentation for programmers** and **application extension prospects**!

## Table of contents
* [How to run an app](#How-to-run-an-app)
* [Instruction for users](#Instruction-for-users)
* [Documantation for programers](#Documantation-for-programers)
* [Application extension prospects](#Application-extension-prospects)

## How to run an app
1. Open Eclipse IDE
2. Click *File* -> *Import* -> *Git* -> *Projects from Git* -> *Clone URI* **URI**: https://github.com/PiotrStoklosa/Sudoku_Helper.git
3. Run main in class Main.

## Instruction for users
In this section I will image you what does Sudoku Helper do, why this application is very helpful and how to use it!


Sudoku Helper is the application that helps you in solving, as the name suggests, sudoku. It can show you a lot of useful things! For instance, you can see the next move, solution, methods of solving (if you are somewhat advanced, you know what's going on) and so on! Let's have a look on how to use Sudoku Helper.


First of all, you should run the application (I am going to add really simple method of build project (Maven), but now it has to be sufficient). After this you will see the interface of the application. It should be similiar to the photo below.


![](https://i.imgur.com/XP7beD4.png)


Application supports two kinds of sudoku's boards (9x9 and 6x6, in the future I really want to add new types of boards, e.g. irregular).


If you don't need any help, you can play sudoku! Click ***Play*** button, choose difficulty level and enjoy yourself!


If you need any kind of help with sudoku, click ***Create*** button. Next, choose size of the board and fill out spaces with your numbers. Obviously, if you choose a wrong number (in this case if there would be the same numbers in the column/row/block) apllication will inform you (image below).


![](https://i.imgur.com/WC37bsc.png)


When you finish inserting numbers, click ***Finish*** button and start playing or request for help.


You have few options to get help
- ***Hint*** Sudoku Helper will inform you about next move- method and where you should be looking for the next number. (e. g. in a row 3).
- ***Show next*** fills the field with a number (the same number that ***Hint*** would suggest you using)
- ***Solve*** solve the sudoku with methods implemented in the application.
- ***Show solution*** shows the solution from the file.


![](https://i.imgur.com/glOSr1b.png)


Details:
1. At the moment, the application has 2 implemented methods: hidden single and naked single, so it is not able to find your next move / show next move / solve sudoku, if this requires more advanced methods. This 2 methods are sufficient to solve easy and medium sudoku (sometimes difficult sudoku too).
2. ***Show solution*** works when the solution in the file exists.


There are some other functions too:
- **Timer** shows you how long you are solving this sudoku (60 min is a limit!),
- **Mistakes** you can only do 3 mistakes!
- **Candidates** if you want to add some candidates to the fildes, select this button.


There is also button ***Options***
- ***Save*** save sudoku,
- ***Load*** load sudoku,
- ***Restart*** restart your game,
- ***Main menu*** back to menu


In Sudoku Helper you can fill spaces in 2 different ways! When you click on the blank field you can click numbers from another window or you can also type this number on the keyboard!


If you have any questions, contact me!


## Documantation for programers


**Technologies:**
- Java
- Swing
- Awt (events)


Short info about some more important classes:
- **Board** abstract class which is the base for specific sudoku boards (9x9 and 6x6).
- **Classic_Sudoku** / **Edited_Classic_Sudoku** (and others similiar) inherits from **Borad**. This class create particaular board.
- **Hidden_Single** / **Naked_Single** represents methods of solving sudoku.
- **Number_selector** is responsible for inserting numbers into boards.


Implemeted software designed patterns:
- Observer
- Singleton
- I want to add MVC


## Application extension prospects


At the moment the application is finished, but there are many things that could be added to make Sudoku Helper better! If you are intrested in developing my app look at some of ideas below!


#### For users:
##### New methods:
1. Hidden pair
2. Naked pair
3. Locked Candidate

##### New boards:
1. Directional sudoku
2. Irregular sudoku
3. Antiknight sudoku

#### for programmers:
1. Implement MVC
2. Break the classes into packages
