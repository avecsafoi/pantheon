package kr.co.koscom.olympus.athena.pb.common.bcm;

import kr.co.koscom.olympus.athena.base.io.data.annotations.XAText;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

import java.math.BigDecimal;

@Description("환율정보 등록/조회/정정")
public class OffFcstckXchrat_CBSMM {

    @Data
    @Accessors(fluent = true)
    public static class OFF_FCSTCK_XCHRAT_ST {

        @XAText(name="기능코드", size=2)
        public String zFuncCode;

        @XAText(name="기능코드", size=2)
        public String zBaseDt;

        @XAText(name="기능코드", size=2)
        public String zCrcyCode;

        @XAText(name="기능코드", size=2)
        public String zFirmNo;

        @XAText(name="기능코드", size=2)
        public BigDecimal dPchsXchrat;

        @XAText(name="기능코드", size=2)
        public BigDecimal dSellXchrat;

        @XAText(name="기능코드", size=2)
        public BigDecimal dBaseXchrat;

        @XAText(name="기능코드", size=2)
        public BigDecimal dCrosXchrat;

        @XAText(name="기능코드", size=2)
        public long lMxchgBaseAmt;

        @XAText(name="기능코드", size=2)
        public BigDecimal dRegBaseXchrat;

        @XAText(name="기능코드", size=2)
        public BigDecimal dRegCrosXchrat;

        @XAText(name="기능코드", size=2)
        public long lMdfyCnt;

        @XAText(name="기능코드", size=2)
        public int nErrCode;
    }


    public OffFcstckXchrat_CBSMM(OFF_FCSTCK_XCHRAT_ST st) {

    }
}
