public class RotatingSLList<Item> extends SLList<Item> {

    public RotatingSLList(Item x){
        super(x);
    }

    public void rotateRight() {
        // get the last item
       Item x = removeLast();
       addFirst(x); 
    }
    
}
