# Cash Dispenser App

Command line application that can initialise an ATM, withdraw notes of 50$ and 20$, and reload balance.

This project can serve as a skeleton code for any project which requires the need to serially communicate with an ATM or similar cash dispenser device.

#### What does this package do?

- Report back on available notes
- Supply notes of $50 and $20
- Validation of amount to withdraw
- Automatically topup ATM's notes
- Reduce total balance on withdrawal

### How to run

The application has been tested for edge cases. To compile and run the tests:

```
mvn clean install
```

To initialise custome values and withdraw amount on the command line:

```
mvn package
```

```
java -jar target/CashDispenserCodingChallenge-1.0-SNAPSHOT.jar
```

### Technologies ###

- Java - 11
- Maven - 4.0.0
- JUnit - 5.2.0

# Thought process

The cash withdrawal algorithm is a  Recursive Greedy Algorithm that finds the correct combination of 50 and 20 dollar notes against a given value. The algorithm is greedy with 50 notes.

To develop the main algorithm, I broke down the tasks into smaller parts. 

First I wrote an algorithm with unlimited notes available, trying to find a combination with 50 notes first, then moving on to 20 notes. 

As it wouldn't give the correct result (for example in the case of withdrawal value of 110) a recursive function seemed the best option, in order to be able to backtrack the 50s and add additional 20s until a correct combination is found.

Counting the sum of these notes, when the sum equals the requested amount to withdraw, the function can return a solution. Otherwise, if the difference between total amount requested and the sum of the current combination of notes, the recursive function is called again to try a combination with fewer 50 notes.

Once I planned this logic, I added statements to check maxing out notes in the ATM and error conditions when there is no possible combination.

Once logic of the application was passing all tests, I refactored the source code, based on clean code principles and added Observable pattern to get notifications about available notes.

# Testing

I used **TDD** as my software development approach. Based on TDD software development principles I wrote acceptance tests using JUnit.

Tested edge cases provided in the specification, along with the topup function and error conditions.

# Next steps/Roadmap

If there were no time constraint, additional features I would have implemented:

- Multi-currency support using Enums on types of notes 
- Supporting other denominations by extending the withdrawal logic in the ATM class to include 10, 5 notes
