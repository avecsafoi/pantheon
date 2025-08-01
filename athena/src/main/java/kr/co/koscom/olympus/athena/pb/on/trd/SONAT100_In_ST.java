package kr.co.koscom.olympus.athena.pb.on.trd;

import kr.co.koscom.olympus.athena.base.io.data.annotations.XAText;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(fluent = true)
public class SONAT100_In_ST {

    @XAText(name = "계좌번호", size = 20)
    public String zAcntNo;

    @XAText(name = "계좌번호", size = 20)
    public String zInptPwd;

    @XAText(name = "계좌번호", size = 20)
    public String zIsuNo;

    @XAText(name = "계좌번호", size = 20)
    public long lOrdQty;

    @XAText(name = "계좌번호", size = 20)
    public BigDecimal dOrdPrc;

    @XAText(name = "계좌번호", size = 20)
    public String zBnsTp;

    @XAText(name = "계좌번호", size = 20)
    public String zOrdprcPtnCode;

    @XAText(name = "계좌번호", size = 20)
    public String zPrgmOrdprcPtnCode;

    @XAText(name = "계좌번호", size = 20)
    public String zStslOrdpcrTp;

    @XAText(name = "계좌번호", size = 20)
    public String zStslAbleYn;

    @XAText(name = "계좌번호", size = 20)
    public String zCommdaCode;
    public String zMgntrnCode;
    public String zLoanDt;
    public String zMbrNo;
    public String zOrdCndiTp;
    public String zStrtgCode;
    public String zGrpId;
    public long lOrdSeqNo;
    public long lPtflNO;
    public long lBskNo;
    public long lTrchNo;
    public long lItemNo;
    public String zOpDrtNo;
    public String zLpYn;
    public String zCvrgTp;
}

