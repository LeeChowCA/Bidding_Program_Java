/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selectcontract07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Jianping
 */
class ContractModel {
    
    private ArrayList<Contract> theContracts;
    private int contractCounter;
    private ArrayList<Contract> theContractsAll;
    private SortedSet<String> originCityList;
    
    public static final int NUMBER_OF_CONTRACT_ATTRIBUTES = 4;
    public static final int INDEX_OF_CONTRACT_ID = 0;
    public static final int INDEX_OF_ORIGIN_CITY = 1;
    public static final int INDEX_OF_DEST_CITY = 2;
    public static final int INDEX_OF_ORDER_ITEM = 3;
    
    public ContractModel () {
        this.theContracts = new ArrayList<>();
        this.contractCounter = 0;
        this.originCityList = new TreeSet<>();
//        The elements in a TreeSet are stored in a sorted (ascending) order, and each element must be unique
        this.theContractsAll = new ArrayList<> (theContracts);
        
        try {
            FileReader fileReader = new FileReader("M:\\Term2\\ICS125\\NetBeansProjects\\SelectContract07\\src\\selectcontract07\\contracts.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",", NUMBER_OF_CONTRACT_ATTRIBUTES);
                
                String contractID = tokens[INDEX_OF_CONTRACT_ID];
                String originCity = tokens[INDEX_OF_ORIGIN_CITY];
                String destCity = tokens[INDEX_OF_DEST_CITY];
                String orderItem = tokens[INDEX_OF_ORDER_ITEM];
                
                Contract dataContract = new Contract(contractID, originCity, destCity, orderItem);
                
                theContracts.add(dataContract);
                theContractsAll.add(dataContract);
                originCityList.add(originCity);
            }
            originCityList.add("All");
            
            fileReader.close();
        }
        
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    boolean foundContracts() {
        if (theContracts.size() > 0) {
            return true;
        } else 
            return false;
    }
    
    public Contract getTheContract() {
        return theContracts.get(contractCounter);
    }
    
    public int getContractCount() {
        return theContracts.size();
    }
    
    public int getCurrentContractNum() {
        return contractCounter;
    }
    
    public void nextContract() {
        contractCounter++;
    }
    //might have problems,refer to page 22 in the instruction
    
    public void prevContract() {
        contractCounter--;
    }
    //might have problems,refer to page 22 in the instruction

    void updateContractList(String city) {
        theContracts = new ArrayList<> (theContractsAll);
        if (city != "All") {
            theContracts.removeIf(s -> !s.contains(city));
        }
        contractCounter = 0;
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String[] getOriginCityList() {
        String[] a;
        a = originCityList.toArray(new String[originCityList.size()]);
        //create an array with the size of originCityList
        return a;
    }
    
    public String[] getNewOriginCityList(){
        String[] newCityList = {"Victoria", "Vancouver", "Seattle", "Nanaimo", "Prince George"};
        return newCityList;     
    }
    //
    
    public String[] getNewDestinationList(){
        String[] newCityList = {"Victoria", "Vancouver", "Seattle", "Nanaimo", "Prince George"};
        return newCityList;     
    }
    //this method create an ArrayList to store the newCityList, the newCityList will be 
    //shown on the dialog.
    
    
}
