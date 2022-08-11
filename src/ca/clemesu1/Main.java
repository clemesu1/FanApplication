package ca.clemesu1;

import java.util.Scanner;

/**
 * Colin LeMesurier
 * Fan Application for Medavie Blue Cross Application
 * August 10th, 2022
 */


interface State {
    void pull(Cord wrapper);
}

class Cord {
    private State currentState;

    public Cord(State state) {
        currentState = state;
    }

    public void setState(State state) {
        currentState = state;
    }

    public void pull() {
        currentState.pull(this);
    }
}

class Off implements State {
    public void pull(Cord wrapper) {
        wrapper.setState(new Low());
        System.out.println("Fan speed set to LOW.");
    }
}

class Low implements State {
    public void pull(Cord wrapper) {
        wrapper.setState(new Medium());
        System.out.println("Fan speed set to MEDIUM.");
    }
}

class Medium implements State {
    public void pull(Cord wrapper) {
        wrapper.setState(new High());
        System.out.println("Fan speed set to HIGH.");
    }
}

class High implements State {
    public void pull(Cord wrapper) {
        wrapper.setState(new Off());
        System.out.println("Fan speed set to OFF.");
    }
}

class Clockwise implements State {
    public void pull(Cord wrapper) {
        wrapper.setState(new CounterClockwise());
        System.out.println("Fan direction set to Counter-Clockwise.");
    }
}

class CounterClockwise implements State {
    public void pull(Cord wrapper) {
        wrapper.setState(new Clockwise());
        System.out.println("Fan direction set to Clockwise.");
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Cord cordOne = new Cord(new Off());
        Cord cordTwo = new Cord(new CounterClockwise());

        System.out.println("Welcome to the fan application!");

        while (true) {
            try {
                System.out.print("Which cord will you pull? (1 or 2): ");
                String userInput = scan.nextLine();

                if (Integer.parseInt(userInput) == 1) {
                    cordOne.pull();
                } else if (Integer.parseInt(userInput) == 2) {
                    cordTwo.pull();
                } else {
                    System.out.print("Invalid input. Please try again.\n");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Closing application...");
                System.exit(0);
            }
        }


    }
}
