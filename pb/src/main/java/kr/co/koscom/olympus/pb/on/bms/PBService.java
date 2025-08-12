package kr.co.koscom.olympus.pb.on.bms;

public interface PBService<T extends PB_ST<?, ?>> {

    int call(T st);
}
