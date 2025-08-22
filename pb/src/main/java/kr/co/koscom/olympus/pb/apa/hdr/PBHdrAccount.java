package kr.co.koscom.olympus.pb.apa.hdr;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Description("계정계헤더")
public class PBHdrAccount extends PBObject {

    @PBA(name = "서비스아이디", scale = 8)
    public String svcId;

    @PBA(name = "이용사번호", scale = 3)
    public String firmNo;

    @PBA(name = "사용자ID", scale = 16)
    public String userId;

    @PBA(name = "접속구분", scale = 2)
    public String connClssCode;

    @PBA(name = "접속채널번호", scale = 3)
    public String connChnNo;

    @PBA(name = "트랜잭션순차번호", scale = 9)
    public String trxSeqNo;

    @PBA(name = "접속클라이언트번호", scale = 4)
    public String connClntNo;

    @PBA(name = "호스트클라이언트번호", scale = 6)
    public String hostClntNo;

    @PBA(name = "Filler", scale = 6)
    public String filler;

    @PBA(name = "공인IP", scale = 12)
    public String pubIP;

    @PBA(name = "사설IP", scale = 12)
    public String prvtIP;

    @PBA(name = "처리지점번호", scale = 3)
    public String trxBrnNo;

    @PBA(name = "지점번호", scale = 3)
    public String brnNo;

    @PBA(name = "단말분류코드", scale = 5)
    public String termClssCode;

    @PBA(name = "단말번호", scale = 3)
    public String termNo;

    @PBA(name = "언어구분", scale = 1)
    public String lngTp;

    @PBA(name = "AP처리시각", scale = 9)
    public String apTrxTime;

    @PBA(name = "메시지코드", scale = 4)
    public String msgCode;

    @PBA(name = "출력메시지구분", scale = 1)
    public String outMsgTp;

    @PBA(name = "압축요청구분", scale = 1)
    public String cmprReqstTp;

    @PBA(name = "기능키목록", scale = 4)
    public String funcKeyList;

    @PBA(name = "요청 레코드 개수", scale = 4)
    public int reqstRecQty;

    @PBA(name = "예외처리(트랜잭션중복바지)", scale = 2)
    public String excpTRx;

    @PBA(name = "Y'경우 SMS(단문메시지) 발송", scale = 1)
    public String smsSndYn;

    @PBA(name = "Filler", scale = 3)
    public String filler1;

    @PBA(name = "연속 여부", scale = 1)
    public String contYn;

    @PBA(name = "연속 Key", scale = 18)
    public String contKey;

    @PBA(name = "가변시스템정보길이", scale = 2)
    public int varSysLen;

    @PBA(name = "가변헤더정보길이", scale = 2)
    public int varHederLen;

    @PBA(name = "가변메시지길이", scale = 2)
    public int varMsgLen;

    @PBA(name = "계정계메세지", scale = 1)
    public String varMsg;

}
