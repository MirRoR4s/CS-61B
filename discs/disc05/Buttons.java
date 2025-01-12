import java.util.Stack;

/**
 * @Author mirror4s
 * @Date 2024/2/4
 * @Time 9:19
 */
public class Buttons {
    private String currentPage;
    private Stack<String> forward;
    private Stack<String> back;

    public Buttons() {
        forward = new Stack<>();
        back = new Stack<>();
    }

    /**
     * 移动当前网页
     * @param action 动作，0 代表前进，1 代表后退
     */
    public void move(int action) {

        // 前进
        if (action == 0) {
            back.push(currentPage);
            currentPage = forward.pop();
        }
        // 后退
        else {
            forward.push(currentPage);
            currentPage = back.pop();
        }
    }
    /** 无跳转访问一个新页面 */
    public void visitPageNoDirect(String page) {
        back.push(currentPage);
        currentPage = page;
    }

    public void visitNewPage(String newPage) {
        forward.removeAllElements();
    }

    public static void main(String[] args) {

    }
}
