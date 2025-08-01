package kr.co.koscom.olympus.athena.pb.on.trd;

import kr.co.koscom.olympus.athena.pb.ab.lang.meta.PB_FMeta;
import kr.co.koscom.olympus.athena.pb.ab.lang.st.PB_IN_ST;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(fluent = true)
public class SONAT100_In_ST extends PB_IN_ST {

    @PB_FMeta(name = "계좌번호", size = 20)
    public String zAcntNo;

    @PB_FMeta(name = "입력비밀번호", size = 32, mask = PB_FMeta.Mask.PWD)
    public String zInptPwd;

    @PB_FMeta(name = "종목번호", size = 12)
    public String zIsuNo;

    @PB_FMeta(name = "주문수량")
    public long lOrdQty;

    @PB_FMeta(name = "주문가")
    public BigDecimal dOrdPrc;

    @PB_FMeta(name = "매매구분", size = 1)
    public String zBnsTp;

    @PB_FMeta(name = "호가유형코드", size = 2)
    public String zOrdprcPtnCode;

    @PB_FMeta(name = "프로그램호가유형코드", size = 2)
    public String zPrgmOrdprcPtnCode;

    @PB_FMeta(name = "공매도가능여부", size = 1)
    public String zStslOrdpcrTp;

    @PB_FMeta(name = "공매도호가구분", size = 1)
    public String zStslAbleYn;

    @PB_FMeta(name = "통신매체코드", size = 2)
    public String zCommdaCode;

    @PB_FMeta(name = "신용거래코드", size = 3)
    public String zMgntrnCode;

    @PB_FMeta(name = "대출일", size = 8)
    public String zLoanDt;

    @PB_FMeta(name = "회원번호", size = 3)
    public String zMbrNo;

    @PB_FMeta(name = "주문조건구분", size = 1)
    public String zOrdCndiTp;

    @PB_FMeta(name = "전략코드", size = 6)
    public String zStrtgCode;

    @PB_FMeta(name = "그룹ID", size = 20)
    public String zGrpId;

    @PB_FMeta(name = "주문회차")
    public long lOrdSeqNo;

    @PB_FMeta(name = "포트폴리오번호")
    public long lPtflNO;

    @PB_FMeta(name = "바스켓번호")
    public long lBskNo;

    @PB_FMeta(name = "트렌치번호")
    public long lTrchNo;

    @PB_FMeta(name = "아이템번호")
    public long lItemNo;

    @PB_FMeta(name = "운용지시번호", size = 12)
    public String zOpDrtNo;

    @PB_FMeta(name = "유동성공급자여부", size = 1)
    public String zLpYn;

    @PB_FMeta(name = "반대매매구분", size = 1)
    public String zCvrgTp;
}

