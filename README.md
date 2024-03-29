### Project writer: Jiuzhou Zhang

### Project tester: Yukai Wang

To easily run the application in windows, you can either click run.cmd or HW Airport.exe in the directory AirportSystem_jar.

The project was completed in 3 sprints. 

#### **Sprint 1:**

1. MVC design pattern was applied to arrange the layout of files.

2. Write a function to read the flight list for subsequent output.

3. Create a virtual time timer (looping from 06:00 to 12:00 in sequence).

4. Output the information to the Event Board panel in order of the start of ticket checking and departure of the flight.
    Display and update flight information on the Flight Detail panel based on the current flight being checked (using observer mode).

5. Created the log generation function of the first generation. (using a single mode, currently only recording flight conditions, passenger check-in and other information will be added later)

   

#### **Sprint 2:**

1. Improve the GUI of the waiting queue and complete the writing of data structures for passengers and waiting queues.

2. An automatic passenger generator (PassengerGenerator, the single mode adopted) is written, which can generate passengers indefinitely according to the flight currently checking in. The specific rule is that passengers will be given a number according to the generation sequence (here, the object lock of the waiting queue is used to avoid conflicts caused by multiple threads accessing the same piece of data). There are two threads generating passengers simultaneously, namely the instantiation thread of PassengerGenerator, which generates passengers per second based on the current flight being checked. There is also a timer that generates a large number of passengers (about 20) for a new flight at once when it starts checking.

3. Two sets of observer modes were adopted, namely: 1 Subject ->FlightDetailList, Observer ->FlightDetailsPanel 2 Subject ->FlightDetails, Observer ->FlightDetailsComponent (in the FlightDetailList class)

4. The act of adding passengers to the queue to the log (to improve the log function).

   

#### **Sprint 3:**

1. The counter function has been implemented, and the specific rules are: when a flight starts ticket checking, a new counter (ticket gate) will be opened, and you can also manually add a counter; When a flight takes off, a counter will be closed, and you can also manually close a counter. When each ticket checkpoint is idle, it will "take" a passenger at the top of the queue from the waiting queue, complete the ticket check after a period of time, and then repeat the operation.
    Tip 1: The important classes involved in this are Desk, DeskList, and DeskDetailsPanel. The Desk class inherits the runnable interface and can be used to start an independent new thread. By the way, the PassengerGenerator is also the same
    Tip 2: Simply put, the principle is that the instance thread of Desk removes the top ranked Passengers from the PassengerList instance and uses it to update the FlightDetailsList instance. FlightDetails and FlightDetailsPanel are in observer mode, so the content displayed in the FlightDetails in the upper left corner will also change accordingly. The DeskList and DeskDetailsPanel, as well as the DeskDetailsComponent (two groups), adopt observer mode, so the content displayed in the bottom right corner will change with the change of the DeskList.

2. Added a tools menu. There are four operations that can be performed as shown in the figure: clearing all passengers, generating a random passenger, adding a counter, and deleting a counter.

3. Dual queues have been enabled, and the specific rule is that every time a passenger is generated, there is a 20% chance to go to the business waiting queue and an 80% chance to go to the economy waiting queue. The ticket gate will give priority to checking tickets for business class passengers. When there is no one in the business waiting queue, tickets will be checked for economy class passengers.

   
