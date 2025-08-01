package kr.co.koscom.pantheon.athena.demo.model;

import kr.co.koscom.pantheon.athena.base.io.data.TextXData;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAText;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class DemoDataOut implements TextXData {

    @XAText(name = "아이디", size = 10)
    public String id;

    @XAText(name = "이름", size = 20)
    public String name;

    @XAText(name = "예수금", size = 10)
    public long deposit;
}
