/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selectcontract08;

/**
 *
 * @author C0532972
 */
public class Bid {
    private String contractID = "";
    private Integer bidAmount = 0;
    private String time = "";
    private String name = "";
    
    public Bid(String contractID, Integer bidAmount, String time,String name ){
        this.contractID = contractID;
        this.bidAmount = bidAmount;
        this.time = time;
        this.name = name;
    }
    
    public String getContractID() {
        return contractID;
    }

    // Getter for bidAmount
    public Integer getBidAmount() {
        return bidAmount;
    }

    // Getter for time
    public String getTime() {
        return time;
    }

    // Getter for name
    public String getName() {
        return name;
    }
}
    

