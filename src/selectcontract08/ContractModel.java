/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selectcontract08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

    JSONParser parser = new JSONParser();
    private ArrayList<String> bidContractIDs = new ArrayList<>();
    private ArrayList<Integer> bidAmount = new ArrayList<>();
    private ArrayList<String> bidTime = new ArrayList<>();
    private ArrayList<String> bidName = new ArrayList<>();
    private ArrayList<Bid> theBids = new ArrayList<>();
    private int bidCounter = 0;
    private ArrayList<Bid> theAllBids = new ArrayList<>();

    public ContractModel() {
        this.theContracts = new ArrayList<>();
        this.contractCounter = 0;
        this.originCityList = new TreeSet<>();
//        The elements in a TreeSet are stored in a sorted (ascending) order, and each element must be unique
        this.theContractsAll = new ArrayList<>(theContracts);

        try {
            File inputFile = new File("M:\\Term2\\ICS125\\NetBeansProjects\\SelectContract08\\src\\selectcontract08\\contract.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("contract");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String contractID = eElement.getElementsByTagName("contractID").item(0).getTextContent();
                    String originCity = eElement.getElementsByTagName("originCity").item(0).getTextContent();
                    String destCity = eElement.getElementsByTagName("destCity").item(0).getTextContent();
                    String orderItem = eElement.getElementsByTagName("orderItem").item(0).getTextContent();

                    Contract dataContract = new Contract(contractID, originCity, destCity, orderItem);
                    theContracts.add(dataContract);
                    theContractsAll.add(dataContract);
                    originCityList.add(originCity);
                }
                originCityList.add("All");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONArray bidsArray = (JSONArray) parser.parse(new FileReader("M:\\Term2\\ICS125\\NetBeansProjects\\SelectContract08\\src\\selectcontract08\\bid.json"));

            for (Object o : bidsArray) {
                JSONObject bidObject = (JSONObject) o;

                String contractID = (String) bidObject.get("contractID");
                long bidAmountLong = (long) bidObject.get("bidAmount");
                Integer bidAmount = (int) bidAmountLong; // Note: This might cause loss of precision for large numbers
                String time = (String) bidObject.get("time");
                String name = (String) bidObject.get("Name");

                Bid bid = new Bid(contractID, bidAmount, time, name);
                theBids.add(bid);
                theAllBids.add(bid);
            }

        } catch (Exception e) {
            System.out.println(e + "this is a mistake");
        }
    }

    boolean foundBids() {
        if (theBids.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Bid getTheBid() {
        return theBids.get(bidCounter);
    }

    public Integer getBidCount() {
        return theAllBids.size();
    }

    public int getCurrentBidNum() {
        return bidCounter;
    }

    public void nextBid() {
        bidCounter++;
    }

    public void prevBid() {
        bidCounter--;
    }

//    void updateBidList(String city) {
//        theBids = new ArrayList<>(theAllBids);
//        if (city != "All") {
//            theBids.removeIf(s -> !s.contains(city));
//        }
//        contractCounter = 0;
//        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
    boolean foundContracts() {
        if (theContracts.size() > 0) {
            return true;
        } else {
            return false;
        }
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
        theContracts = new ArrayList<>(theContractsAll);
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

    public String[] getNewOriginCityList() {
        String[] newCityList = {"Victoria", "Vancouver", "Seattle", "Nanaimo", "Prince George"};
        return newCityList;
    }
    //

    public String[] getNewDestinationList() {
        String[] newCityList = {"Victoria", "Vancouver", "Seattle", "Nanaimo", "Prince George"};
        return newCityList;
    }
    //this method create an ArrayList to store the newCityList, the newCityList will be 
    //shown on the dialog.

}
