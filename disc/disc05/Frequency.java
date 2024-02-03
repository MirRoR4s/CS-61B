/**
 * @Author Jian Tao Huang
 * @Date 2/3/24 8:41 PM
 * @Version 1.0
 */
import java.util.HashMap;
import java.util.Map;


public class Frequency {
    /**
     * 计算文章中出现的单词频率
     * @param words 单词列表
     * @return 以单词为key，其出现频率为value的Map
     */
    public static Map<String, Double> calcWordFrequency(String article) {
        String[] words = convert(article);
        // 计算每个单词出现的次数
        Map<String, Integer> map = new HashMap<>();
        for (String s: words) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            }
            else {
                map.put(s, 1);
            }
        }
        Map<String, Double> frequency = new HashMap<>();
        for (String s: map.keySet()) {
            frequency.put(s, (double)map.get(s) / words.length);
        }
        return frequency;
    }
    /** 将文章包含的单词转为单词数组 */
    private static String[] convert(String article) {
        // 去除文章中的标点符号
        article = article.replaceAll("[^a-zA-Z]", " ");
        // 将文章中的单词转为小写
        article = article.toLowerCase();
        // 将文章中的单词转为单词数组
        return article.split("\\s+");
    }

    public static void main(String[] args) {
    }
}
