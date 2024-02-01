public class OffByOne implements CharacterComparator {
    /**
     * 判断 a b 两个字符是否相等，如果 a 和 b 的 ascii 值差一则认为它们相等。
     */
    @Override
    public boolean equalChars(char a, char b) {
        return Math.abs(a - b) == 1;
    }
}
