package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        CoffeeMakerMachine coffeeMakerMachine = new CoffeeMakerMachine();
        while (coffeeMakerMachine.update(scanner.nextLine())) {
        }
    }
}

class CoffeeMakerMachine {
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;
    private State state;

    public CoffeeMakerMachine() {
        this.state = State.CHOOSING_AN_ACTION;
        System.out.println(state.getMessage());
    }

    public boolean update(String param) {
        boolean running = true;
        switch (state) {
            case CHOOSING_AN_ACTION:
                running = selectAction(param);
                break;
            case FILLING_WATER:
                water += Integer.parseInt(param);
                state = State.FILLING_MILK;
                break;
            case FILLING_MILK:
                milk += Integer.parseInt(param);
                state = State.FILLING_BEANS;
                break;
            case FILLING_BEANS:
                beans += Integer.parseInt(param);
                state = State.FILLING_CUPS;
                break;
            case FILLING_CUPS:
                cups += Integer.parseInt(param);
                state = State.CHOOSING_AN_ACTION;
                break;
            case CHOOSING_COFFEE:
                buy(param);
                break;
            default:
        }
        System.out.println(state.getMessage());
        return running;
    }

    private boolean selectAction(String param) {
        switch (param) {
            case "fill":
                state = State.FILLING_WATER;
                break;
            case "buy":
                state = State.CHOOSING_COFFEE;
                break;
            case "take":
                System.out.println("I gave you $" + money);
                money = 0;
                state = State.CHOOSING_AN_ACTION;
                break;
            case "remaining":
                printContents();
                break;
            case "exit":
                System.out.println("Have a nice day!");
                return false;
            default:
                System.out.println("Invalid action!");
        }
        return true;
    }

    private void buy(String param) {
        switch (param) {
            case "1":
                makeCoffee(Coffee.ESPRESSO);
                break;
            case "2":
                makeCoffee(Coffee.LATTE);
                break;
            case "3":
                makeCoffee(Coffee.CAPPUCCINO);
                break;
            case "back":
                break;
            default:
                System.out.println("Invalid option!");
        }
        state = State.CHOOSING_AN_ACTION;
    }

    private boolean isIngredientsEnough(Coffee coffee) {
        return cups >= 1 && coffee.getWater() <= water && coffee.getMilk() <= milk && coffee.getBeans() <= beans;
    }

    private void makeCoffee(Coffee coffee) {
        if (isIngredientsEnough(coffee)) {
            --cups;
            water -= coffee.getWater();
            milk -= coffee.getMilk();
            beans -= coffee.getBeans();
            money += coffee.getPrice();
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            System.out.println("Sorry, not enough ingredients!");
        }
    }

    private void printContents() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }
}


enum Coffee {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    private final int water;
    private final int milk;
    private final int beans;
    private final int price;

    Coffee(int water, int milk, int beans, int price) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.price = price;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getPrice() {
        return price;
    }
}

enum State {
    CHOOSING_AN_ACTION("Write action (buy, fill, take, remaining, exit):"),
    CHOOSING_COFFEE("What do you want to buy? 1 - espresso, 2 - latte," +
            " 3 - cappuccino, back - to main menu:"),
    FILLING_WATER("Write how many ml of water do you want to add: "),
    FILLING_MILK("Write how many ml of milk do you want to add:"),
    FILLING_BEANS("Write how many grams of coffee beans do you want to add:"),
    FILLING_CUPS("Write how many disposable cups of coffee do you want to add:");

    String message;

    State(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}