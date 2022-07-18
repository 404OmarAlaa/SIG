
package sig.model;

//handle data from invoice line csv file(numberLine,itemName,itemPrice,itemCount,itemTotal)
public class InvoiceItem {
    private String itemName;
    private double itemPrice;
    private int itemCount;
    private InvoiceHeader inv;


    public InvoiceItem(String itemName, double itemPrice, int itemCount, InvoiceHeader inv) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.inv = inv;
    }


    public int getItemCount() {
        return itemCount;
    }


    public String getItemName() {
        return itemName;
    }


    public double getItemPrice() {
        return itemPrice;
    }

    public double getItemTotal()
    {
        return itemPrice * itemCount;
    }
    @Override
    public String toString() {
        return "InvoiceItem{" + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCount=" + itemCount + ", inv=" + inv + '}';
    }

    public InvoiceHeader getInv() {
        return inv;
    }


    public String getInvoiceItemFile() // For Saving InvoiceLine File
    {
        return inv.getInvoiceNumber()+ "," + itemName + "," + itemPrice + "," + itemCount;
    }


}
