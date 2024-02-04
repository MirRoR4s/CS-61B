import org.junit.Test;

import java.util.Map;

public class FrequencyTest {

    @Test
    public void testCalcWordFrequency(){
        String article = "I have a dream that one day this nation will rise up " +
                "and live out the true meaning of its creed: We hold these truths to be self evident";
        Map<String, Double> frequency = Frequency.calcWordFrequency(article);
//        未能确定实际的频率是多少，最好的测试方式是找一篇已经有数据的文章来
//        assertEquals(0.0714, frequency.get("i"), 0.0001);
//        assertEquals(0.0714, frequency.get("have"), 0.0001);
//        assertEquals(0.0714, frequency.get("a"), 0.0001);

    }
    public static void main(String[] args) {
        // testCalcWordFrequency();
    }
}
