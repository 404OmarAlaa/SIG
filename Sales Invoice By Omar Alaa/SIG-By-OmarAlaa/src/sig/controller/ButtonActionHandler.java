package sig.controller;

import sig.model.InvoiceHeader;
import sig.model.InvoiceItem;
import sig.model.ItemTableModel;
import sig.view.Add_Invoice;
import sig.view.Main_Screen;
import sig.view.Add_Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ButtonActionHandler implements ActionListener{ //This Class To Make Action On Every Button In The Project

    private Main_Screen main_Screen;
    private Add_Invoice add_Invoice;
    private Add_Item add_Item;


    public ButtonActionHandler(Main_Screen main_Screen) {
        this.main_Screen = main_Screen;
    }



    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if(actionCommand == "Create New Invoice")
        {
            createNewInvoice();
            System.out.println("Action Done " + actionCommand);
        }
        else if(actionCommand == "Delete Invoice")
        {
            deleteInvoice();
            System.out.println("Action Done" + actionCommand);
        }

        else if(actionCommand == "Add Item")
        {
            createNewItem();
            System.out.println("Action Done" + actionCommand);
        }
        else if(actionCommand == "Delete Item")
        {
            deleteItem();
            System.out.println("Action Done" + actionCommand);
        }
        else if(actionCommand == "createInvoiceOK")
        {
            createInvoiceOK();
            System.out.println("Action Done" + actionCommand);
        }
        else if(actionCommand == "createInvoiceCancel")
        {
            createInvoiceCancel();
            System.out.println("Action Done" + actionCommand);
        }
        else if(actionCommand == "createLineOK")
        {
            createLineOK();
            System.out.println("Action Done" + actionCommand);
        }
        else if(actionCommand == "createLineCancel")
        {
            createLineCancel();
            System.out.println("Action Done" + actionCommand);
        }
        else {
            System.out.println("WRONG ACTION!!!!");

            /*
            - And This is Switch Case Code Which Make The Same Actions
            switch (actionCommand) {
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Create New Item":
                createNewItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            case "createInvoiceCancel":
                createInvoiceCancel();
                break;
            case "createInvoiceOK":
                createInvoiceOK();
                break;
            case "createLineOK":
                createLineOK();
                break;
            case "createLineCancel":
                createLineCancel();
                break;
        }
             */
        }

    }

    private void deleteInvoice() {
        int SelectedRow = main_Screen.getHeaderTable().getSelectedRow();
        if(SelectedRow != -1){
            JOptionPane.showMessageDialog(main_Screen, "Successfully Deleted");
            main_Screen.getInvoices().remove(SelectedRow);
            main_Screen.getHeaderTableModel().fireTableDataChanged();
        }else{
            JOptionPane.showMessageDialog(main_Screen, "Please Select A Single Row", "Attention", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteItem() {
        int selectedInvoice = main_Screen.getHeaderTable().getSelectedRow();
        int selectedRow = main_Screen.getLineTable().getSelectedRow();

        if(selectedInvoice != -1 && selectedRow != -1){
            JOptionPane.showMessageDialog(main_Screen, "Successfully Deleted");
            ItemTableModel lineTableModel = (ItemTableModel) main_Screen.getLineTable().getModel();
            lineTableModel.getItems().remove(selectedRow);
            lineTableModel.fireTableDataChanged();

        }


    }

    private void createInvoiceOK() {
        JOptionPane.showMessageDialog(main_Screen, "You Successfully Create New Invoice ");
        String createdDate = add_Invoice.getInvDateField().getText();
        String createdCustomer = add_Invoice.getCustNameField().getText();
        int createdNumber = main_Screen.getNextInvoiceNum();

        InvoiceHeader header = new InvoiceHeader(createdNumber, createdDate, createdCustomer);
        main_Screen.getInvoices().add(header);
        main_Screen.getHeaderTableModel().fireTableDataChanged();
        add_Invoice.setVisible(false);
        add_Invoice.dispose();
        add_Invoice = null;
    }

    private void createInvoiceCancel() {
        JOptionPane.showMessageDialog(main_Screen, "You Cancel Creating New Invoice ");
        add_Invoice.setVisible(false);
        add_Invoice.dispose();
        add_Invoice = null;

    }

    private void createLineOK() {
        JOptionPane.showMessageDialog(main_Screen, "New Item Successfully Added ");
        String itemName = add_Item.getItemNameField().getText();
        String countStr = add_Item.getItemCountSpinner().getValue().toString();
        String priceStr = add_Item.getItemPriceField().getText();
        int itemCount = Integer.parseInt(countStr);
        double itemPrice = Double.parseDouble(priceStr);
        int selectedInvoice = main_Screen.getHeaderTable().getSelectedRow();
        if(selectedInvoice != -1)
        {
            InvoiceHeader header = main_Screen.getInvoices().get(selectedInvoice);
            InvoiceItem item = new InvoiceItem(itemName, itemPrice, itemCount, header);
            header.getItems().add(item);
            ItemTableModel ItemsTableModel = (ItemTableModel) main_Screen.getLineTable().getModel();
            ItemsTableModel.fireTableDataChanged();
            main_Screen.getHeaderTableModel().fireTableDataChanged();

        }

        add_Item.setVisible(false);
        add_Item.dispose();
        add_Item = null;
    }

    private void createLineCancel() {
        JOptionPane.showMessageDialog(main_Screen, "You Cancel Adding New Item ");
        add_Item.setVisible(false);
        add_Item.dispose();
        add_Item = null;

    }

    private void createNewInvoice() {
        add_Invoice = new Add_Invoice(main_Screen);
        add_Invoice.setVisible(true);
    }

    private void createNewItem() {
        add_Item = new Add_Item(main_Screen);
        add_Item.setVisible(true);
    }

}
