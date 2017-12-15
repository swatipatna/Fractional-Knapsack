import java.util.Arrays;

public class FractionalKnapsack {
    public static void main(String[] args) {        
        int maxWeight = 5;
        Item item0 = new Item(1, 2);
        Item item1 = new Item(3, 4);
        Item item2 = new Item(2, 6);
        Item item3 = new Item(3, 3);
        Item[] items = {item0, item1, item2, item3};

        printKnapsack(maxWeight, items);
    }

    public static void printKnapsack(int maxWeight, Item[] items) {
        // sort the items array by value to weight ratio, from largest ratio to the smallest
        // Big-O: O(N Log N)
        Arrays.sort(items); 
        
        int knapsackWeight = 0;  // Current weight in knapsack
        double knapsackValue = 0.0; // current value in knapsack

        // loop through the sorted items array to add items into the knapsack
        // Big-O: O(N)
        for (Item item : items) { 
            int newWeight = knapsackWeight + item.getWeight();

            // if we can add a whole item into the knapsack
            if (newWeight <= maxWeight) {
                knapsackWeight = newWeight;
                knapsackValue += item.getValue();
                System.out.println(item.getWeight() + " " + item.getValue());
            }
            // if we can't add a whole item into the knapsack
            // let's just add a fraction of the item into the knapsack 
            else {
                int remainingWeight = maxWeight - knapsackWeight;                
                double fractionalValue = item.getValue() * ((double) remainingWeight / item.getWeight());
                knapsackValue += fractionalValue;
                knapsackWeight = maxWeight;

                System.out.println("fraction: " + item.getWeight() + " " + item.getValue());
                break; // breaking out of the loop because we can't add more weight after this
            }
        }

        System.out.println("Total Knapsack weight: " + knapsackWeight);
        System.out.println("Total Knapsack value: " + knapsackValue);
    }
}


class Item implements Comparable<Item> {
    private int value;
    private int weight;
 
    // Constructor
    public Item(int weight, int value) {
        this.value = value;
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getValue() {
        return this.value;
    }

    public int compareTo(Item otherItem) {
        double myValueToWeight = (double)this.value / this.weight;
        double otherValueToWeight = (double)otherItem.value / otherItem.weight;
        
        if (otherValueToWeight > myValueToWeight) {
            return 1;
        }
        else if (otherValueToWeight == myValueToWeight) {
            return 0;
        }
        else {
            return -1;
        }
    }

    public String toString() {
        return "weight: " + this.weight + " value: " + this.value;
    }
}