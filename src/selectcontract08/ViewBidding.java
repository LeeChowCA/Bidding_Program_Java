/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package selectcontract08;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author C0532972
 */
public class ViewBidding extends javax.swing.JDialog {

    /**
     * Creates new form ViewBidding
     */
    private ContractController controller;
    private ContractModel contractModel;
    //create a instance of controller, so that we can linked into controller, and update the UI
    
    class PrevButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (contractModel.getCurrentBidNum() == 0) {
                return;
            }

            try {
                contractModel.prevBid();
            } catch (Exception ex) {
                System.out.println(ex);
                (ViewBidding.this).displayErrorMessage(
                        "Error:There is a problem setting a previous contract.");
            }

            setUpDisplay();
        }
    }

    class NextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (contractModel.getCurrentBidNum() < contractModel.getBidCount() - 1) {
                try {
                    contractModel.nextBid();
                    setUpDisplay();
                } catch (Exception ex) {
                    System.out.println(ex);
                    (ViewBidding.this).displayErrorMessage(
                            "Error:There is a problem setting a previous contract.");
                }
            }

        }
    }

    public ViewBidding(java.awt.Frame parent, boolean modal, ContractModel model, ContractController contractController) {
        super(parent, modal);
        initComponents();
        this.controller = contractController; // Store the controller reference   
        this.contractModel = model;
        this.addPrevListener(new PrevButtonListener());
        this.addNextListener(new NextButtonListener());
        
        setUpDisplay();

    }

    void setContractID(String contractID) {
        jLabelContractID.setText(contractID);
    }

    void setBidAmount(Integer bidAmount) {
        jLabelBidAmount.setText(bidAmount.toString());
    }

    void setBidderName(String name) {
        jLabelName.setText(name);
    }

    void setBidTime(String time) {
        jLabelTime.setText(time);
    }

    void setBidDisplay(String bidCount) {
        jLabelDisplay.setText(bidCount);
    }

    void updateBidViewPanel(int currentBidNum, int bidCount) {
        setBidDisplay((1 + currentBidNum) + " of "
                + bidCount + " contracts");
    }

    public void setPrevButtonEnabled(boolean enable) {
        jButtonPre.setEnabled(enable);
    }

    public void setNextButtonEnabled(boolean enable) {
        jButtonNext.setEnabled(enable);
    }
    
    void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    
    void addPrevListener(ActionListener listenForPrevButton) {
        jButtonPre.addActionListener(listenForPrevButton);
    }
    
    void addNextListener(ActionListener listenForNextButton) {
        jButtonNext.addActionListener(listenForNextButton);
    }

    private void setUpDisplay() {
        try {

            if (contractModel.getCurrentBidNum() == 0) {
                (this).setPrevButtonEnabled(false);
            } else {
                (this).setPrevButtonEnabled(true);
            }

            if (contractModel.getCurrentBidNum() == contractModel.getBidCount() - 1) {
                (this).setNextButtonEnabled(false);
            } else {
                (this).setNextButtonEnabled(true);
            }

            if (contractModel.foundBids()) {

                /*Bid b = model.getTheBid();
                (this).setContractID(b.getContractID());
                (this).setBidAmount(b.getBidAmount());
                (this).setBidderName(b.getName());
                (this).setBidTime(b.getTime());
                (this).setBidDisplay(model.getBidCount());*/

                Bid c = contractModel.getTheBid();
                (this).setContractID(c.getContractID());
                (this).setBidAmount(c.getBidAmount());
                (this).setBidderName(c.getName());
                (this).setBidTime(c.getTime());
                (this).updateBidViewPanel(contractModel.getCurrentBidNum(), contractModel.getBidCount());
            } else {
                (this).setContractID("???");
                (this).setBidAmount(000);
                (this).setBidderName("???");
                (this).setBidTime("???");
            }

        } catch (Error ex) {
            System.out.println(ex);
            (this).displayErrorMessage("Error: There was a problem setting the contract \n" + "    Contract number: " + contractModel.getCurrentBidNum());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel333 = new javax.swing.JLabel();
        jLabel555 = new javax.swing.JLabel();
        jLabelContractID = new javax.swing.JLabel();
        jLabelBidAmount = new javax.swing.JLabel();
        jLabelTime = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jButtonNext = new javax.swing.JButton();
        jButtonPre = new javax.swing.JButton();
        jLabelDisplay = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Bidding");

        jLabel1.setText("Contract ID");

        jLabel333.setText("Time");

        jLabel555.setText("Name");

        jLabelContractID.setText("jLabel5");

        jLabelBidAmount.setText("jLabel6");

        jLabelTime.setText("jLabel7");

        jLabelName.setText("jLabel8");

        jButtonNext.setText("Next");
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        jButtonPre.setText("Previous");
        jButtonPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreActionPerformed(evt);
            }
        });

        jLabelDisplay.setText("x/n");

        jLabel9.setText("bidAmount");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonNext))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabelContractID))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabelBidAmount))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTime, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel333, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonPre)
                                .addGap(93, 93, 93)))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel555)
                            .addComponent(jLabelName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel333)
                    .addComponent(jLabel555)
                    .addComponent(jLabel9))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContractID)
                    .addComponent(jLabelBidAmount)
                    .addComponent(jLabelTime)
                    .addComponent(jLabelName))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNext)
                    .addComponent(jButtonPre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(jLabelDisplay)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPreActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
    // TODO add your handling code here:
    }//GEN-LAST:event_jButtonNextActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel333;
    private javax.swing.JLabel jLabel555;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBidAmount;
    private javax.swing.JLabel jLabelContractID;
    private javax.swing.JLabel jLabelDisplay;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelTime;
    // End of variables declaration//GEN-END:variables
}
