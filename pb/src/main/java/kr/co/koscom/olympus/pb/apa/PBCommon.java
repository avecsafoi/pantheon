package kr.co.koscom.olympus.pb.apa;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PBCommon {

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;

    public static final int Z_SVC_ID = 8;
    public static final int Z_PWD = 20;
    public static final int Z_ACNT_NO = 20;
    public static final int Z_ISU_NO = 12;
    public static final int Z_BNS_TP = 2;
    public static final int Z_ORD_MKT_CODE = 2;
    public static final int Z_DATE = 8;
    public static final int Z_TIME = 9;

    public static final int Z_LONG = 20;
    public static final int Z_INT = 10;
    public static final int Z_DOUBLE = 20;
    public static final int Z_FLOAT = 10;
    public static final int Z_SHORT = 6;

    public static final int Z_REMARK = 40;

    public static final String ZS_CHARSET_DEFUALT = StandardCharsets.UTF_8.name();
    public static final String ZS_CHARSET_UTF8 = "UTF-8";
    public static final String ZS_CHARSET_CHINA = "GBK";
    public static final String ZS_CHARSET_VIETNAM = "VSCII";

    public static final Charset ZO_CHARSET_DEFAULT = StandardCharsets.UTF_8;
}
