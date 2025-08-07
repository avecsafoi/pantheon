package kr.co.koscom.olympus.pb.include.hdr;

import kr.co.koscom.olympus.pb.include.PB_A;
import kr.co.koscom.olympus.pb.include.PB_Object;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Description("계정계헤더")
public class PB_HdrAccount extends PB_Object {

    @PB_A(name = "서비스아이디", scale = 8)
    public String aSvcId;

    @PB_A(name = "이용사번호", scale = 3)
    public String aFirmNo;

    @PB_A(name = "사용자ID", scale = 16)
    public String aUserId;

    @PB_A(name = "접속구분", scale = 2)
    public String aConnClssCode;

    @PB_A(name = "접속채널번호", scale = 3)
    public String aConnChnNo;

    @PB_A(name = "트랜잭션순차번호", scale = 9)
    public String aTrxSeqNo;

    @PB_A(name = "접속클라이언트번호", scale = 4)
    public String aConnClntNo;

    @PB_A(name = "호스트클라이언트번호", scale = 6)
    public String aHostClntNo;

    @PB_A(name = "Filler", scale = 6)
    public String aFiller;

    @PB_A(name = "공인IP", scale = 12)
    public String aPubIP;

    @PB_A(name = "사설IP", scale = 12)
    public String aPrvtIP;

    @PB_A(name = "처리지점번호", scale = 3)
    public String aTrxBrnNo;

    @PB_A(name = "지점번호", scale = 3)
    public String aBrnNo;

    @PB_A(name = "단말분류코드", scale = 5)
    public String aTermClssCode;

    @PB_A(name = "단말번호", scale = 3)
    public String aTermNo;

    @PB_A(name = "언어구분", scale = 1)
    public String aLngTp;

    @PB_A(name = "AP처리시각", scale = 9)
    public String aAPTrxTime;

    @PB_A(name = "메시지코드", scale = 4)
    public String aMsgCode;

    @PB_A(name = "출력메시지구분", scale = 1)
    public String aOutMsgTp;

    @PB_A(name = "압축요청구분", scale = 1)
    public String aCmprReqstTp;

    @PB_A(name = "기능키목록", scale = 4)
    public String aFuncKeyList;

    @PB_A(name = "요청 레코드 개수", scale = 4)
    public int aReqstRecQty;

    @PB_A(name = "예외처리(트랜잭션중복바지)", scale = 2)
    public String aExcpTRx;

    @PB_A(name = "Y'경우 SMS(단문메시지) 발송", scale = 1)
    public String aSmsSndYn;

    @PB_A(name = "Filler", scale = 3)
    public String aFiller1;

    @PB_A(name = "연속 여부", scale = 1)
    public String aContYn;

    @PB_A(name = "연속 Key", scale = 18)
    public String aContKey;

    @PB_A(name = "가변시스템정보길이", scale = 2)
    public int aVarSysLen;

    @PB_A(name = "가변헤더정보길이", scale = 2)
    public int aVarHederLen;

    @PB_A(name = "가변메시지길이", scale = 2)
    public int aVarMsgLen;

    @PB_A(name = "계정계메세지", scale = 1)
    public String aVarMsg;

}
