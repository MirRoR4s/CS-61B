import java.util.Stack;

/**
 * @Author Jian Tao Huang
 * @Date 2/4/24 1:43 PM
 * @Version 1.0
 */
public class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        Stack<Integer> stack2 = new Stack<>();
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int result = stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
