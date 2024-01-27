public class OffByOne implements CharacterComparator {
    /**
     * 判断 a b 两个字符是否相等
     * 如果 a 和 b 的 ascii 值差一则认为它们相等
     * @param a 字符 a
     * @param b 字符 b
     * @return 如果 a 和 b 相等则返回 true，否则返回 false
     */
    @Override
    public boolean equalChars(char a, char b) {
        return Math.abs(a - b) == 1;
    }
}
