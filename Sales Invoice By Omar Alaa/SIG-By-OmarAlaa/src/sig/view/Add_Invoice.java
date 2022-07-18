
package sig.view;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Add_Invoice extends JDialog {
    private JTextField custNameField;
    private JTextField invDateField;
    private JLabel custNameLbl;
    private JLabel invDateLbl;
    private JButton okBtn;
    private JButton cancelBtn;

    public Add_Invoice(Main_Screen frame) {
        custNameLbl = new JLabel("Customer Name:");
        custNameField = new JTextField(20);

        invDateLbl = new JLabel("Invoice Date:");
        invDateField = new JTextField(20);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime currentDate = LocalDateTime.now();  // Set Current Date As Local Time Of Server
        try
        {
            invDateField = new JTextField(dateTimeFormatter.format(currentDate)); // Make Date Automatic
        }
        catch(Exception e){e.printStackTrace();}

        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");

        okBtn.setActionCommand("createInvoiceOK");
        cancelBtn.setActionCommand("createInvoiceCancel");

        okBtn.addActionListener(frame.getBcontroller());
        cancelBtn.addActionListener(frame.getBcontroller());
        setLayout(new GridLayout(3, 2));

        add(invDateLbl);
        add(invDateField);
        add(custNameLbl);
        add(custNameField);
        add(okBtn);
        add(cancelBtn);

        pack();

    }

    public JTextField getCustNameField() {
        return custNameField;
    }

    public JTextField getInvDateField() {
        return invDateField;
    }

}