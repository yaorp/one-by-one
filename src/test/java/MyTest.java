import com.alibaba.fastjson.JSONObject;
import com.springcloud.ms.controller.pattern.R;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MyTest {

    @Test
    public void PatternTest() {
        log.info("返回：{}", JSONObject.toJSONString(R.success("交易成功")));
        log.info("返回：{}", JSONObject.toJSONString(R.success(1)));
        log.info("返回：{}", JSONObject.toJSONString(R.success(null)));
    }
}
