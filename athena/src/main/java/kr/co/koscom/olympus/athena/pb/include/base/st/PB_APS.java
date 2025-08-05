package kr.co.koscom.olympus.athena.pb.include.base.st;

public interface PB_APS<T extends PB_ST<?, ?>> {

    int process(T psMsg);

}
