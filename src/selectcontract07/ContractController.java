/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selectcontract07;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author Jianping
 */
class ContractController {

//    ContractController(ContractView theView, ContractModel theModel) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    private ContractView theView;
    private ContractModel theModel;

    class PrevButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (theModel.getCurrentContractNum() == 0) {
                return;     
            }

            try {               
                theModel.prevContract();
            } catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error:There is a problem setting a previous contract.");
            }

            setUpDisplay();
        }
    }

    class NextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (theModel.getCurrentContractNum() < theModel.getContractCount() - 1) {
                try {
                    theModel.nextContract();
                    setUpDisplay();
                } catch (Exception ex) {
                    System.out.println(ex);
                    theView.displayErrorMessage(
                            "Error:There is a problem setting a previous contract.");
                }
            }

        }
    }

    class BidButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ConfirmBid cb;
                cb = new ConfirmBid(theView, true, theModel.getTheContract());
                    cb.setLocationRelativeTo(null);
                    cb.setVisible(true);
                }
             catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage(
                        "Error: The numbers entered must be integers");
            }

            setUpDisplay();
        }
    }
    
    class ComboListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            System.out.println(e.getItem().toString());
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedCity = e.getItem().toString();
                System.out.println(selectedCity);
                theModel.updateContractList(selectedCity);
                setUpDisplay();
            }
        }
    }

    public ContractController(ContractView theView, ContractModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addPrevListener(new PrevButtonListener());
        this.theView.addBidListener(new BidButtonListener());
        this.theView.addNextListener(new NextButtonListener());
        this.theView.addcomboBoxListener(new ComboListener());
        this.theView.setOriginCityList(theModel.getOriginCityList());
        setUpDisplay();
    }

    private void setUpDisplay() {
        try {
            
            if (theModel.getCurrentContractNum() == 0) {
                theView.setPrevButtonEnabled(false);
            }
            else 
                theView.setPrevButtonEnabled(true);
            
            if (theModel.getCurrentContractNum() == theModel.getContractCount() - 1) {
                theView.setNextButtonEnabled(false);
            }
            else 
                theView.setNextButtonEnabled(true);
            
            
            if (theModel.foundContracts()) {
                Contract c = theModel.getTheContract();
                theView.setContractID(c.getContractID());
                theView.setDestCity(c.getDestCity());
                theView.setOriginCity(c.getOriginCity());
                theView.setOrderItem(c.getOrderItem());
                theView.updateContractViewPanel(theModel.getCurrentContractNum(), theModel.getContractCount());
            } else {
                theView.setContractID("???");
                theView.setDestCity("???");
                theView.setOriginCity("???");
                theView.setOrderItem("???");
            }

        } catch (Error ex) {
            System.out.println(ex);
            theView.displayErrorMessage("Error: There was a problem setting the contract \n" + "    Contract number: " + theModel.getCurrentContractNum());
        }
    }

}
