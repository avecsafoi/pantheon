package kr.co.koscom.olympus.athena.pb.ab.lang.st;

public interface PB_APS<T extends PB_ST<?, ?>> {

    int process(T psMsg);

}
