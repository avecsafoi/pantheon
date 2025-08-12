package kr.co.koscom.olympus.pb.include;

public interface PBService<T extends PBST<?, ?>> {

    int call(T st);
}
