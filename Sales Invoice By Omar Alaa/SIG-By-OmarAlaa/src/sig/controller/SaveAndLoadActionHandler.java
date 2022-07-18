
package sig.controller;

import sig.model.HeaderTableModel;
import sig.model.InvoiceHeader;
import sig.model.InvoiceItem;
import sig.view.Main_Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFileChooser;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class SaveAndLoadActionHandler implements ActionListener{ //This Class To Make Action On File Menu Items (Save and Load) In The Project

    private Main_Screen main_Screen;



    public SaveAndLoadActionHandler (Main_Screen main_Screen) {
        this.main_Screen = main_Screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Action Done" );

        if(actionCommand == "Load File")
        {
            loadFile();
            System.out.println("You Take Action To " + actionCommand);
        }
        else if(actionCommand == "Save File")
        {
            saveFile();
            System.out.println("You Take Action To " + actionCommand);
        }
        else {
            System.out.println("WRONG ACTION!!!!");
        }
        /*
        - And This is Switch Case Code Which Make The Same Actions
        switch (actionCommand) {
        case "Load File":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
                }
         */

    }



    private void loadFile() {
        JOptionPane.showMessageDialog(main_Screen, "Please Select Invoice Table File", "Attention", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        try {
            //Code To Load First File (HeaderFile)
            int openDialog = fileChooser.showOpenDialog(main_Screen);
            if(openDialog == JFileChooser.APPROVE_OPTION)
            {
                File invHeader = fileChooser.getSelectedFile();
                Path headerPath = Paths.get(invHeader.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                System.out.println("Invoice Table File Have Successfully Added");

                ArrayList<InvoiceHeader> headersArray = new ArrayList<>();
                for(String invoiceLine:headerLines)
                {
                    String [] invoiceParts = invoiceLine.split(",");
                    int invNum = Integer.parseInt(invoiceParts[0]); // 1st part is Invoice Number
                    String invDate = invoiceParts[1];               // 2nd part is Invoice Date
                    String customerName = invoiceParts[2];          // 3rd part is Customer Name

                    InvoiceHeader header = new InvoiceHeader(invNum,invDate,customerName);
                    headersArray.add(header);
                }
                //Code To Load (LineFile) Which Contain Items In Invoice
                JOptionPane.showMessageDialog(main_Screen, "Please Select Item Table File", "Attention", JOptionPane.INFORMATION_MESSAGE);
                openDialog = fileChooser.showOpenDialog(main_Screen);
                if(openDialog == JFileChooser.APPROVE_OPTION) {

                    File invItem = fileChooser.getSelectedFile();
                    Path itemsPath = Paths.get(invItem.getAbsolutePath());
                    List<String> itemLines = Files.readAllLines(itemsPath);
                    System.out.println("Item Table File Have Successfully Added");

                    List<InvoiceItem> AllItems = new ArrayList<>();

                    for(String itemLine:itemLines)
                    {
                        String [] itemParts = itemLine.split(",");
                        int itemNum = Integer.parseInt(itemParts[0]);           // 1st part is Invoice Number
                        String itemName = itemParts[1];                         //2nd part is Item Name
                        double itemPrice = Double.parseDouble(itemParts[2]);    //3rd part is Item Price
                        int itemCount = Integer.parseInt(itemParts[3]);         //4th part is Item Count part

                        InvoiceHeader invoiceHeader = null;
                        for(InvoiceHeader invoice : headersArray){
                            if(invoice.getInvoiceNumber() == itemNum){
                                invoiceHeader = invoice;
                                break;
                            }

                        }
                        InvoiceItem items = new InvoiceItem(itemName,itemPrice,itemCount,invoiceHeader);
                        invoiceHeader.getItems().add(items);
                        AllItems.add(items);
                    }
                        //This Code To See the Loaded Files In The Command Screen
                    int LastNumberLine = 0;
                    for(InvoiceItem OneItem : AllItems){
                        if(LastNumberLine != OneItem.getInv().getInvoiceNumber()){
                            System.out.println("         - Invoice Number " + OneItem.getInv().getInvoiceNumber());
                            System.out.println("*************************************");
                            System.out.println(OneItem.getInv().getInvoiceDate() + "," + OneItem.getInv().getCustomerName());

                            // Print all items per this invoice
                            for(InvoiceItem SubItem : AllItems){
                                if(SubItem.getInv().getInvoiceNumber() == OneItem.getInv().getInvoiceNumber()){
                                    System.out.println("- Item Name : "+SubItem.getItemName()+  "   |   Item Price : " + SubItem.getItemPrice() +  "   |   ItemCount : " + SubItem.getItemCount());
                                }
                            }

                            System.out.println("*************************************");
                            LastNumberLine = OneItem.getInv().getInvoiceNumber();
                        }
                    }


                }

                main_Screen.setInvoices(headersArray);
                HeaderTableModel headerTableModel = new HeaderTableModel(headersArray);
                main_Screen.setHeaderTableModel(headerTableModel);
                main_Screen.getHeaderTable().setModel(headerTableModel);
                main_Screen.getHeaderTableModel().fireTableDataChanged();
            }
        } catch(IOException ex){
            ex.printStackTrace();
        }

    }

    private void saveFile() {
        ArrayList<InvoiceHeader> invoices = main_Screen.getInvoices();
        String invoiceHeaders = "";
        String itemLines ="";
        for(InvoiceHeader invoice : invoices)
        {
            String invCSV = invoice.getInvoiceHeaderFile();
            invoiceHeaders += invCSV;
            invoiceHeaders += "\n";

            for(InvoiceItem line : invoice.getItems())
            {
                String lineCSV = line.getInvoiceItemFile();
                itemLines += lineCSV;
                itemLines += "\n";
            }
        }
        try {
            //Code To Save Invoices
            JFileChooser fc = new JFileChooser();
            int saveResult = fc.showSaveDialog(main_Screen);
            if(saveResult == JFileChooser.APPROVE_OPTION){
                File headerFile = fc.getSelectedFile();
                FileWriter headerFileWriter = new FileWriter(headerFile);
                headerFileWriter.write(invoiceHeaders);
                headerFileWriter.flush();
                headerFileWriter.close();
                saveResult = fc.showSaveDialog(main_Screen);
                if(saveResult == JFileChooser.APPROVE_OPTION){
                    File lineFile = fc.getSelectedFile();
                    FileWriter lineFileWriter = new FileWriter(lineFile);
                    lineFileWriter.write(itemLines);
                    lineFileWriter.flush();
                    lineFileWriter.close();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
