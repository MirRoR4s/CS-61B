public class VengefulSLList<Item> extends SLList<Item>{

   SLList<Item> deletedItems; // 存储已移除的 item

   public VengefulSLList() {
    
    deletedItems = new SLList<Item>();
   }

   public void printLostItems() {
        deletedItems.print();
   }

   @Override
   public Item removeLast() {
        Item deletedItem = super.removeLast();
        deletedItems.addFirst(deletedItem);
        return deletedItem;
   }

}
