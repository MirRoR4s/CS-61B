public class Palindrome {

    /**
     * 将字符串转换为双端队列
     *
     * @param word 待转换字符串
     * @return  一个由字符串中的字符按序组成的双端队列
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /**
     * 判断 word 是否为回文串
     * 此处的回文串指正着读和倒着读都一样的字符串
     * @param word 待判断的字符串
     * @return 如果 word 是回文串则返回 true，否则返回 false。
     */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = this.wordToDeque(word);
        return isPalindrome(deque);
    }
    private boolean isPalindrome(Deque<Character> a) {
        if (a.size() == 0 || a.size() == 1) {
            return true;
        }
        if (a.removeFirst() != a.removeLast()) {
            return false;
        }
        return isPalindrome(a);
    }
    /**
     * 判断 word 是否为回文串，但采用 cc 中指定的字符比较规则
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = this.wordToDeque(word);
        return isPalindrome(deque, cc);
    }

    private boolean isPalindrome(Deque<Character> a, CharacterComparator cc) {
        if (a.size() == 0 || a.size() == 1) {
            return true;
        }
        if (!cc.equalChars(a.removeFirst(), a.removeLast())) {
            return false;
        }
        return isPalindrome(a, cc);
    }

}
