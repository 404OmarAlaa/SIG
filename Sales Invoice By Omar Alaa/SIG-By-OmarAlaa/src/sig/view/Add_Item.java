package sig.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class Add_Item extends JDialog{
    private JTextField itemNameField;
    private JSpinner itemCountSpinner;
    private JTextField itemPriceField;
    private JLabel itemNameLbl;
    private JLabel itemCountLbl;
    private JLabel itemPriceLbl;
    private JButton okBtn;
    private JButton cancelBtn;

    public Add_Item(Main_Screen frame) {
        itemNameField = new JTextField(20);
        itemNameLbl = new JLabel("Item Name");

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 1000 , 1);
        itemCountSpinner = new JSpinner(spinnerModel);
        itemCountLbl = new JLabel("Item Count");

        itemPriceField = new JTextField(20);
        itemPriceLbl = new JLabel("Item Price");

        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");

        okBtn.setActionCommand("createLineOK");
        cancelBtn.setActionCommand("createLineCancel");

        okBtn.addActionListener(frame.getBcontroller());
        cancelBtn.addActionListener(frame.getBcontroller());
        setLayout(new GridLayout(4, 2));

        add(itemNameLbl);
        add(itemNameField);
        add(itemCountLbl);
        add(itemCountSpinner);
        add(itemPriceLbl);
        add(itemPriceField);
        add(okBtn);
        add(cancelBtn);

        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JSpinner getItemCountSpinner() {
        return itemCountSpinner;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
}
