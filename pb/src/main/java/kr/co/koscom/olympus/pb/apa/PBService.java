package kr.co.koscom.olympus.pb.apa;

public interface PBService<T extends PBST<?, ?>> {

    void process(T st);
}
