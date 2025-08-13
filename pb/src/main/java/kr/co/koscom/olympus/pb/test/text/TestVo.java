package kr.co.koscom.olympus.pb.test.text;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class TestVo extends PBObject {

    @PBA(name = "서비스ID", scale = 12)
    private String svcId;

    @PBA(name = "비밀번호", scale = 12, mask = PBA.Mask.PASSWORD)
    private String pwd;

    @PBA(name = "현금", scale = 10)
    private long cash;

    @PBA(name = "증거금", scale = 10)
    private double price;

    @PBA(name = "예수금", scale = 20)
    private BigInteger margin;

    @PBA(name = "예수금", scale = 20)
    private BigDecimal deposit;

    @PBA(name = "배열", scale = 2, fix = false)
    private TestSubVo[] subArray;

    @PBA(name = "목록", scale = 3, fix = false)
    private List<TestSubVo> subList;

    @PBA(name = "기타", scale = 20)
    private String mark;
}
