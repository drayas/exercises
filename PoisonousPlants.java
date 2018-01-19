import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class PoisonousPlants {
  // Complete this function
  static int poisonousPlants(int[] p) {
    ArrayList<Stack<Integer>> plants = new ArrayList<Stack<Integer>>();
    plants = createPlantStacks(p);
    
    int daysUntilNoPlantsDie = 0;
    while(plants.size() > 1){
      daysUntilNoPlantsDie++;
      plants = popAndMergeStacks(plants);
    }
    return daysUntilNoPlantsDie;
  }

  public static ArrayList<Stack<Integer>> createPlantStacks(int[] p){
    ArrayList<Stack<Integer>> plants = new ArrayList<Stack<Integer>>();
    Stack<Integer> plantStack = new Stack<Integer>();
    for(int i = p.length-1; i >= 0; i--){
      if(plantStack.isEmpty() || p[i] >= plantStack.peek()) plantStack.push(p[i]);
      else {
        plants.add(0, plantStack);
        plantStack = new Stack<Integer>();
        plantStack.push(p[i]);
      }
    }
    plants.add(0, plantStack);
    
    return plants;
  }
  
  public static ArrayList<Stack<Integer>> popAndMergeStacks(ArrayList<Stack<Integer>> plants){
    Iterator<Stack<Integer>> itr = plants.iterator();
    Stack<Integer> prev = itr.next();
    while(itr.hasNext()){
      Stack<Integer> current = itr.next();
      current.pop();
      if(current.isEmpty()) itr.remove();
      else if(current.peek() <= prev.firstElement()){
        prev.addAll(0, current);
        itr.remove();
      }
      else prev = current;
    }
    return plants;
  }

  //TESTS
  public static void runTests(){
    System.out.println("testCreatePlantStacks: " + testCreatePlantStacks());
    System.out.println("testPopAndMergeStacks: " + testPopAndMergeStacks());
    System.out.println("testPoisonousPlants: " + testPoisonousPlants());
  }
  
  public static boolean testCreatePlantStacks(){
    int[] p = new int[]{6, 5, 8, 4, 7, 10, 9};
    ArrayList<Stack<Integer>> plants = new ArrayList<Stack<Integer>>();
    Stack<Integer> plantStack = new Stack<Integer>();
    plantStack.push(9);
    plantStack.push(10);
    plants.add(0, plantStack);
    plantStack = new Stack<Integer>();
    plantStack.push(7);
    plants.add(0, plantStack);
    plantStack = new Stack<Integer>();
    plantStack.push(4);
    plantStack.push(8);    
    plants.add(0, plantStack);
    plantStack = new Stack<Integer>();
    plantStack.push(5);
    plantStack.push(6);
    plants.add(0, plantStack);
    
    return plants.equals(createPlantStacks(p));
  }
  
  public static boolean testPopAndMergeStacks(){
    ArrayList<Stack<Integer>> plantsBefore = new ArrayList<Stack<Integer>>();
    Stack<Integer> plantStack = new Stack<Integer>();
    plantStack.push(9);
    plantStack.push(10);
    plantsBefore.add(0, plantStack);
    plantStack = new Stack<Integer>();
    plantStack.push(7);
    plantsBefore.add(0, plantStack);
    plantStack = new Stack<Integer>();
    plantStack.push(4);
    plantStack.push(8);    
    plantsBefore.add(0, plantStack);
    plantStack = new Stack<Integer>();
    plantStack.push(5);
    plantStack.push(6);
    plantsBefore.add(0, plantStack);
    
    ArrayList<Stack<Integer>> plantsAfter = new ArrayList<Stack<Integer>>();
    plantStack = new Stack<Integer>();
    plantStack.push(9);
    plantsAfter.add(0, plantStack);
    plantStack = new Stack<Integer>();
    plantStack.push(4);
    plantStack.push(5);
    plantStack.push(6);
    plantsAfter.add(0, plantStack);
    
    return plantsAfter.equals(popAndMergeStacks(plantsBefore));   
  }
  
  public static boolean testPoisonousPlants(){
    int[] p = new int[]{6, 5, 8, 4, 7, 10, 9};
    
    return poisonousPlants(p) == 2;
  }
  //END TESTS

  //Main code preprovided
  public static void main(String[] args) {
    //runTests();
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] p = new int[n];
    for(int p_i = 0; p_i < n; p_i++){
      p[p_i] = in.nextInt();
    }
    int result = poisonousPlants(p);
    System.out.println(result);
    in.close();
  }
}
