hotelReservationSystem
======================

Fall 2014 CS151 project

HOTEL RESERVATION SYSTEM
In this project, you will design and implement a reservation system for a small hotel. The 
application must be a GUI program without taking any input from the console. Let’s assume 
there are total 20 rooms in this hotel. Among them, 10 rooms are luxurious costing $200 and the 
rest are economic costing $80. We are going to consider two different types of users: Guest and
Manager. The system maintains user accounts as well as room information. A user account 
information includes user id, user name, and all reservations this user made excluding cancelled 
ones and expired ones from the past. (We assume any expired reservation is persisted in archive 
and this archiving function is not required in this project.) It is up to you (designer) what to save 
for the room information in the system. When this application starts, it loads reservation information from a file to the system if it exists. 
(Loading was a responsibility of Manager, but I took it away from the Manager and made it as a 
function to be done when the application starts.) 
Then, the reservation system asks to choose the type of user. 
If the user is a guest, the system asks for user id. If the guest is the first timer, the system asks 
for the information required to create an account and creates one. Then, the system displays an 
interface for the guest to make a reservation. The guest gets to choose one the two options: 
With “Make a Reservation” option, the guest should be able to select the check in date, check out 
date, and the type of room. The start or end date/time should not be prior to the current date.
The length of stay cannot be longer than 60 nights. Your system enforces these constraints and 
generates an error for any violation. 
Room type: 
With this information, the system presents the room availability view displaying all available 
rooms that satisfy the guest request. To simplify the project, a room is considered to be available 
only if it is available for the entire stay. For example, with the above input, the room availability 
list shows rooms which are available for 10/16/14 through 10/18/14
The guest can change his/her selection. If the guest changes the check-in date, check-out date, or 
the room type, the room availability view should be changed accordingly in real time. You are 
required to design this part of project based on the MVC pattern. (Hint: The check-in/out 
boxes and the selection button for the room type can serve as controllers. The room availability 
view is the view. Think of what to store in the model.) The reservation will be saved when the 
user clicks on the Confirm button. The system updates the user account and room information 
accordingly. 
The system asks if the guest is interested in making more reservations. If yes, the same “Make a 
Reservation” steps will be repeated until the guest indicates to finish this transaction. The 
transaction is done when the guest clicks on TransactionDone button. Once the transaction is 
done, the system displays a confirmation receipt for the guest to print. There should be two 
different formats of receipt for the guest can choose: Simple and Comprehensive. With a simple 
format, the receipt shows user id, name, and reserved rooms and corresponding total amount 
dues only made through this particular transaction. With a comprehensive formation, the receipt 
shows user id, name, and all valid reservations made by this user with corresponding total 
amount due. 
You are required to use the Strategy pattern to design two different format of the receipt. Make 
sure to identify the context program, strategy, and concrete strategies. 
With “View/Cancel a reservation” option, the system displays all reservations made by this guest.
(When the guest asks for the view/cancel, he/she already logged in and thus the system does not
have to ask the id.) The guest may just view the reservations or select and cancel reservations. 
If the user is the hotel manager, the system displays a Month view of current calendar where 
days are clickable. The manager is allowed to choose one day at a time to see the room 
availability of this particular day. When the manager selects a different day, the view will change 
accordingly in real time. The calendar can move back and forth as far as GregorianCalendar goes. 
It can be advanced by year and by month. The look of the calendar does have to be exactly like 
the example shown below. There are three functions associated with the Manager: View, and 
Save and Quit. The manager can view the room information by chosen date. If a room is 
reserved on that date, the system shows the room number, the guest who reserved this room, and 
the price. It also shows the list of available rooms on that day. 
With Save and Quit option, the user account and live room reservation information will be saved 
in files (name them as you want) and the program exits. The Load option will load existing files 
that contain user account and previously saved room reservation information to populate data 
structures. 
NOTE: Not all required GUI components are drawn in this description. You identify them all 
and make sure the application displays or pops up required components as needed. 
