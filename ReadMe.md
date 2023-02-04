CS213 Software Methodology Project 1

University Roster Project
#Your team will develop a simple software to help a university processes the student roster for tuition purpose. The
#roster maintains a list of students who are currently registered. The initial phase is to provide a console-based
#interactive user interface to manage the student roster. The software will read a sequence of line commands entered
#by the user and write the results on the console.
#A line command entered by the user will begin with an operation code followed by the data tokens needed to complete
#the operations for managing the student roster. An operation code will be represented with a single character or two
#characters in uppercase letters. The operation code and the data tokens will be delimited by one or more spaces in a
#line command. The operation code is case-sensitive, which means operation codes in lowercase letters are invalid
#and should be rejected by the software.
#The initial requirement for the software is to provide the following functionalities:
#(1) Add a student to the roster. A student is uniquely identified by the student’s profile, which includes first name,
#last name, and date of birth. The system also needs to keep track of a student’s major, and credits completed.
#For simplicity, let’s assume the university currently includes a short list of majors and schools as listed below.
#In addition, we assume any student under the age of 16 is not allowed to register.
#01:198, CS, SAS
##01:640, MATH, SAS
#14:332, EE, SOE
##04:547, ITI, SC&I
#33:136, BAIT, RBS
