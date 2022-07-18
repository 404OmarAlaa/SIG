
package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ItemTableModel extends AbstractTableModel{
    private ArrayList<InvoiceItem> items;
    private String[] itemColumns = {"No.", "Item Name", "Item Price", "Count", "Total"};

    public ItemTableModel(ArrayList<InvoiceItem> items) {
        this.items = items;
    }

    public ArrayList<InvoiceItem> getItems() {
        return items;
    }


    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return itemColumns.length;
    }

    @Override
    public String getColumnName(int column) {
        return itemColumns[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItem item = items.get(rowIndex);

        switch(columnIndex){
            case 0:
                return item.getInv().getInvoiceNumber();
            case 1:
                return item.getItemName();
            case 2:
                return item.getItemPrice();
            case 3:
                return item.getItemCount();
            case 4:
                return item.getItemTotal();
            default :
                return "";

        }
    }

}
