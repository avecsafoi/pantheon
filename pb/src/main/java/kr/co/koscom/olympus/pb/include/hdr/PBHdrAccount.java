package kr.co.koscom.olympus.pb.include.hdr;

import kr.co.koscom.olympus.pb.include.PBA;
import kr.co.koscom.olympus.pb.include.data.PBData;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

@Accessors(chain = true)
@Data
@Description("계정계헤더")
public class PBHdrAccount implements PBData {

    @PBA(name = "서비스아이디", scale = 8)
    public String aSvcId;

    @PBA(name = "이용사번호", scale = 3)
    public String aFirmNo;

    @PBA(name = "사용자ID", scale = 16)
    public String aUserId;

    @PBA(name = "접속구분", scale = 2)
    public String aConnClssCode;

    @PBA(name = "접속채널번호", scale = 3)
    public String aConnChnNo;

    @PBA(name = "트랜잭션순차번호", scale = 9)
    public String aTrxSeqNo;

    @PBA(name = "접속클라이언트번호", scale = 4)
    public String aConnClntNo;

    @PBA(name = "호스트클라이언트번호", scale = 6)
    public String aHostClntNo;

    @PBA(name = "Filler", scale = 6)
    public String aFiller;

    @PBA(name = "공인IP", scale = 12)
    public String aPubIP;

    @PBA(name = "사설IP", scale = 12)
    public String aPrvtIP;

    @PBA(name = "처리지점번호", scale = 3)
    public String aTrxBrnNo;

    @PBA(name = "지점번호", scale = 3)
    public String aBrnNo;

    @PBA(name = "단말분류코드", scale = 5)
    public String aTermClssCode;

    @PBA(name = "단말번호", scale = 3)
    public String aTermNo;

    @PBA(name = "언어구분", scale = 1)
    public String aLngTp;

    @PBA(name = "AP처리시각", scale = 9)
    public String aAPTrxTime;

    @PBA(name = "메시지코드", scale = 4)
    public String aMsgCode;

    @PBA(name = "출력메시지구분", scale = 1)
    public String aOutMsgTp;

    @PBA(name = "압축요청구분", scale = 1)
    public String aCmprReqstTp;

    @PBA(name = "기능키목록", scale = 4)
    public String aFuncKeyList;

    @PBA(name = "요청 레코드 개수", scale = 4)
    public int aReqstRecQty;

    @PBA(name = "예외처리(트랜잭션중복바지)", scale = 2)
    public String aExcpTRx;

    @PBA(name = "Y'경우 SMS(단문메시지) 발송", scale = 1)
    public String aSmsSndYn;

    @PBA(name = "Filler", scale = 3)
    public String aFiller1;

    @PBA(name = "연속 여부", scale = 1)
    public String aContYn;

    @PBA(name = "연속 Key", scale = 18)
    public String aContKey;

    @PBA(name = "가변시스템정보길이", scale = 2)
    public int aVarSysLen;

    @PBA(name = "가변헤더정보길이", scale = 2)
    public int aVarHederLen;

    @PBA(name = "가변메시지길이", scale = 2)
    public int aVarMsgLen;

    @PBA(name = "계정계메세지", scale = 1)
    public String aVarMsg;

}
