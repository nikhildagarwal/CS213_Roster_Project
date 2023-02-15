CS213 Software Methodology Project 1

Authors: Nikhil Agarwal, Hyeon Oh

University Roster Project Description:

Your team will develop a simple software to help a university processes the student roster for tuition purpose. The
roster maintains a list of students who are currently registered. The initial phase is to provide a console-based
interactive user interface to manage the student roster. The software will read a sequence of line commands entered
by the user and write the results on the console.

A line command entered by the user will begin with an operation code followed by the data tokens needed to complete
the operations for managing the student roster. An operation code will be represented with a single character or two
characters in uppercase letters. The operation code and the data tokens will be delimited by one or more spaces in a
line command. The operation code is case-sensitive, which means operation codes in lowercase letters are invalid
and should be rejected by the software.

A - add a student

R - remove secified student

P - display roster sorted by last name, first name, DoB

PS - display roster by standing

PC - display roster by school,major

L - list students in specified school by last name, first name, DoB

C - change students major to specified major

Q - Stop program execution


Project1TestCases.txt contains a list of example inputs to this software. The expected output txt file is provided as well

The java class files can be found in the src folder, while the java API for this project is contained within a javaDoc.
Open index.html (in the Java_Doc folder) in a web browser to see API.


