package kr.co.koscom.olympus.athena.pb.on.trd;

import kr.co.koscom.olympus.athena.pb.include.base.meta.PB_FMeta;
import kr.co.koscom.olympus.athena.pb.include.base.st.PB_OUT_ST;

public class SONAT100_Out_ST extends PB_OUT_ST {

    @PB_FMeta(name = "주문번호")
    public long lOrdNo;

    @PB_FMeta(name = "주문시각", size = 9)
    public String zOrdTime;

    @PB_FMeta(name = "주문시장코드", size = 2)
    public String zOrdMktCode;

    @PB_FMeta(name = "주문유형코드", size = 2)
    public String zOrdPtnCode;

    @PB_FMeta(name = "단축종목번호", size = 9)
    public String zShtnIsuNo;

    @PB_FMeta(name = "관리사원번호", size = 9)
    public String zMgempNo;

    @PB_FMeta(name = "주문금액")
    public long lOrdAmt;

    @PB_FMeta(name = "예비주문번호")
    public long lSpareOrdNo;

    @PB_FMeta(name = "반대매매일련번호")
    public long lCvrgSeqno;

    @PB_FMeta(name = "예약주문번호")
    public long lRsvOrdNo;

    @PB_FMeta(name = "실물주문수량")
    public long lSpotOrdQty;

    @PB_FMeta(name = "재사용주문수량")
    public long lRuseOrdQty;

    @PB_FMeta(name = "현금주문금액")
    public long lMnyOrdAmt;

    @PB_FMeta(name = "대용주문금액")
    public long lSubstOrdAmt;

    @PB_FMeta(name = "재사용주문금액")
    public long lRuseOrdAMt;

    @PB_FMeta(name = "계좌명", size = 40)
    public String zAcntNm;

    @PB_FMeta(name = "종목명", size = 40)
    public String zIsNm;
}

