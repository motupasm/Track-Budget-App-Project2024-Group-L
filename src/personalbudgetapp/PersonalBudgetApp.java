
import java.util.Scanner; // Importing the Scanner class for user input

// Abstract class representing a general budget
abstract class Budget {

    // Attributes to store various budget details
    protected double monthlyIncome; // User's monthly income
    protected double estimatedMonthlyTax; // Estimated monthly tax
    protected double estimatedMonthlyGrocery; // Estimated monthly grocery expenses
    protected double estimatedMonthlyWaterAndLights; // Estimated monthly water and electricity expenses
    protected double estimatedMonthlyCallphoneAndTelephone; // Estimated monthly phone bills
    protected double estimatedMonthlyTravels; // Estimated monthly travel expenses
    protected double otherExpenses; // Other miscellaneous expenses
    protected double totalExpenses;

    // Method to input budget details from the user
    public void inputBudgetDetails() {
        Scanner input = new Scanner(System.in); // Creating a Scanner object for user input

        System.out.print("Enter Monthly Income: R");
        this.monthlyIncome = getPositiveDoubleInput(input); // Inputting income with validation

        System.out.print("Estimated Monthly Tax: R");
        this.estimatedMonthlyTax = getPositiveDoubleInput(input); // Inputting tax with validation

        System.out.print("Estimated Monthly Grocery: R");
        this.estimatedMonthlyGrocery = getPositiveDoubleInput(input); // Inputting grocery expenses with validation

        System.out.print("Estimated Monthly Water and Lights: R");
        this.estimatedMonthlyWaterAndLights = getPositiveDoubleInput(input); // Inputting water and lights expense with validation

        System.out.print("Estimated Monthly Call Phone and Telephone: R");
        this.estimatedMonthlyCallphoneAndTelephone = getPositiveDoubleInput(input); // Input for telephone expenses

        System.out.print("Estimated Monthly Travels: R");
        this.estimatedMonthlyTravels = getPositiveDoubleInput(input); // Input for travel expenses

        System.out.print("Other Expenses: R");
        this.otherExpenses = getPositiveDoubleInput(input); // Input for other expenses

    }

    // Method to get positive double input from the user
    protected double getPositiveDoubleInput(Scanner input) {
        double value;
        do {
            value = input.nextDouble(); // Read user input
            if (value < 0) {
                System.out.print("Value cannot be negative. Please try again: R"); // Prompt for valid input if negative
            }
        } while (value < 0);
        return value; // Return validated positive value
    }

}

class Renting extends Budget {

    protected double rentAmount; // Amount for rent

    // Method to input details specific to renting
    public void inputRentDetails() {
        Scanner input = new Scanner(System.in);
        System.out.print("Rent amount: R");
        rentAmount = getPositiveDoubleInput(input); // Input rent amount with validation

        calculateTotalMonthlyCost(); // Calculate and display total monthly cost
    }

    // Method to calculate total monthly cost including rent and other expenses
    protected void calculateTotalMonthlyCost() {
        // Calculate total expenses
        this.totalExpenses = estimatedMonthlyTax + estimatedMonthlyGrocery + estimatedMonthlyWaterAndLights
                + estimatedMonthlyCallphoneAndTelephone + estimatedMonthlyTravels + rentAmount;
        double remaining = monthlyIncome - totalExpenses; // Calculate remaining balance
        System.out.println("Remaining balance: R" + remaining); // Display the remaining balance
    }
}

class Buying extends Budget {

    protected double propertyPrice; // Price of the property
    protected double deposit; // Amount of deposit
    protected double interestRate; // Interest rate for the loan
    protected double period; // Period for the loan in months
    protected double homeLoanRepayment; // Monthly home loan repayment

    // Method to input details specific to buying a property
    public void inputBuyingDetails() {
        Scanner input = new Scanner(System.in);

        System.out.print("Property Price: R");
        this.propertyPrice = input.nextDouble(); // Input property price

        System.out.print("Total Deposit: R");
        this.deposit = input.nextDouble(); // Input deposit amount

        System.out.print("Interest Rate (percentage): ");
        this.interestRate = input.nextDouble() / 100; // Input interest rate (converted to decimal)

        // Validate interest rate to ensure it's reasonable (0 to 1)
        while (interestRate > 1) {
            System.out.print("Interest Rate: ");
            this.interestRate = input.nextDouble() / 100; // Prompt for correct format
        }

        System.out.print("Period (In Months Between 240 & 360): ");
        this.period = getValidPeriod(input); // Input period with validation

        // Calculate the monthly home loan repayment based on the entered details
        homeLoanRepayment = (propertyPrice * interestRate) / (1 - Math.pow(1 + interestRate, -period));

        System.out.println("Your Monthly Home Loan Repayment is R" + Math.round(homeLoanRepayment));
        calculateTotalMonthlyCost(); // Calculate and show total monthly cost including repayment

        if (homeLoanRepayment + homeLoanRepayment + homeLoanRepayment > monthlyIncome) {
            System.out.println("Home Loan declined");
        } else {
            System.out.println("Home loan Approved");
        }
    }

    // Method to get a valid loan period from the user
    private double getValidPeriod(Scanner input) {
        double period;
        do {
            period = input.nextDouble(); // Read user input
            if (period < 240 || period > 360) {
                System.out.print("Enter Value Between 240 & 360: "); // Prompt if out of range
            }
        } while (period < 240 || period > 360); // Repeat until valid
        return period; // Return valid period
    }

    // Method to calculate total monthly cost including home loan repayment
    private void calculateTotalMonthlyCost() {
        // Calculate total expenses including home loan repayment
        this.totalExpenses = estimatedMonthlyTax + estimatedMonthlyGrocery + estimatedMonthlyWaterAndLights
                + estimatedMonthlyCallphoneAndTelephone + estimatedMonthlyTravels + homeLoanRepayment;
        double remaining = monthlyIncome - totalExpenses; // Calculate remaining balance
        System.out.println("Remaining balance: R" + Math.round(remaining)); // Display the remaining balance
    }
}

class Vehicle extends Budget {

    protected String modelAndMake; // Model and make of the vehicle
    protected double price; // Purchase price of the vehicle
    protected double totalDeposit; // Deposit amount for the vehicle
    protected double interestRate; // Interest rate for the vehicle loan
    protected double insurance; // Estimated insurance premium

    // Method to input details regarding vehicle purchase
    public void inputVehicleDetails() {
        Scanner input = new Scanner(System.in);

        System.out.print("Do you want to buy a vehicle (type in Y or N): ");
        String carOrNoCar = getYorN(input); // Capture user choice for vehicle purchase

        // If the user decides to buy a vehicle, gather relevant details
        if (carOrNoCar.equalsIgnoreCase("y")) {
            System.out.print("Model and Make: ");
            this.modelAndMake = input.next(); // Input vehicle model and make
            System.out.print("Purchase Price: R");
            this.price = input.nextDouble(); // Input vehicle price
            System.out.print("Total Deposit: R");
            this.totalDeposit = input.nextDouble(); // Input deposit amount
            System.out.print("Interest Rate (percentage): ");
            this.interestRate = input.nextDouble() / 100; // Input interest rate

            System.out.print("Estimated Insurance Premium: R");
            this.insurance = input.nextDouble(); // Input estimated insurance costs

            // Calculate the total monthly cost for the vehicle based on the inputs
            double totalMonthlyCost = (price - totalDeposit) * (interestRate / 12) + insurance;
            System.out.println("Total monthly cost: R" + Math.round(totalMonthlyCost)); // Display total monthly vehicle cost
        } else if (carOrNoCar.equalsIgnoreCase("n")) {
            // If the user chooses not to buy a vehicle
            System.out.println("You did not choose to buy a vehicle.");
        } else {
            // Handle invalid input
            System.out.println("Invalid input please try again!");
        }

    }

    // Method to validate user's input for buying a vehicle (Y or N)
    public String getYorN(Scanner option) {
        String yOrN;
        do {
            yOrN = option.next(); // Read user input
            // Repeat until valid input
            if (!yOrN.equalsIgnoreCase("y") && !yOrN.equalsIgnoreCase("n")) {
                System.out.println("Invalid input please try again!");
                System.out.print("Do you want to buy a vehicle (type in Y or N): ");
            }
        } while (!yOrN.equalsIgnoreCase("y") && !yOrN.equalsIgnoreCase("n")); // Validate input loop
        return yOrN; // Return the validated answer
    }
}

// Main class to run the Personal Budget Application
public class PersonalBudgetApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("---------------BudgetWise Application---------------");

        Renting budget = new Renting(); // Creating instance of Renting
        budget.inputBudgetDetails(); // Inputting user budget details

        String rentOrBuy;
        do {
            System.out.print("Are you renting accommodation or buying property (Type Rent or Buy): ");
            rentOrBuy = input.nextLine(); // Capture user's choice for rent or buy
            // Handle renting option
            if (rentOrBuy.equalsIgnoreCase("rent")) {
                Renting renting = new Renting(); // Create a Renting instance
                // Transfer budget details to the Renting instance
                renting.monthlyIncome = budget.monthlyIncome;
                renting.estimatedMonthlyTax = budget.estimatedMonthlyTax;
                renting.estimatedMonthlyGrocery = budget.estimatedMonthlyGrocery;
                renting.estimatedMonthlyWaterAndLights = budget.estimatedMonthlyWaterAndLights;
                renting.estimatedMonthlyCallphoneAndTelephone = budget.estimatedMonthlyCallphoneAndTelephone;
                renting.estimatedMonthlyTravels = budget.estimatedMonthlyTravels;
                renting.inputRentDetails(); // Input rent details
            } else if (rentOrBuy.equalsIgnoreCase("buy")) {
                Buying buying = new Buying(); // Create a Buying instance
                // Transfer budget details to the Buying instance
                buying.monthlyIncome = budget.monthlyIncome;
                buying.estimatedMonthlyTax = budget.estimatedMonthlyTax;
                buying.estimatedMonthlyGrocery = budget.estimatedMonthlyGrocery;
                buying.estimatedMonthlyWaterAndLights = budget.estimatedMonthlyWaterAndLights;
                buying.estimatedMonthlyCallphoneAndTelephone = budget.estimatedMonthlyCallphoneAndTelephone;
                buying.estimatedMonthlyTravels = budget.estimatedMonthlyTravels;
                buying.inputBuyingDetails(); // Input buying details
            } else {
                // Handle invalid input
                System.out.println("Invalid input, Please enter 'Rent' or 'Buy'");
            }
        } while (!rentOrBuy.equalsIgnoreCase("rent") && !rentOrBuy.equalsIgnoreCase("buy")); // Loop until valid input

        Vehicle vehicle = new Vehicle(); // Create a Vehicle instance
        vehicle.inputVehicleDetails(); // Input vehicle details
        
        // Calculate 75% of income
        double threshold = 0.75 * budget.monthlyIncome;

        // Check if total expenses exceed 75% of income
        if (budget.totalExpenses > threshold) {
            System.out.println("Alert: Your total expenses (" + budget.totalExpenses
                    + ") exceed 75% of your income (" + budget.monthlyIncome + ").");
        } else {
            System.out.println("Your total expenses are within the limit.");
        }
    }
}
