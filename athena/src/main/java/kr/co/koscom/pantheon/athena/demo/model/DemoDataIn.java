package kr.co.koscom.pantheon.athena.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import kr.co.koscom.pantheon.athena.base.io.data.TextXData;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAText;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DemoDataIn implements TextXData {

    @XAText(name = "아이디", size = 10)
    private String id;

    @XAText(name = "이름", size = 20)
    private String name;

    @XAText(name = "예수금", size = 10)
    private long deposit;
}
