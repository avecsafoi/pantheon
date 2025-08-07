package kr.co.koscom.olympus.pb.include;

public interface PB_Service<T extends PB_ST<?, ?>> {

    int call(T st);
}
