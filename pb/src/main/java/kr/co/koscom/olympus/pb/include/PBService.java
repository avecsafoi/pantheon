package kr.co.koscom.olympus.pb.include;

public interface PBService<T extends PBSTImpl<?, ?>> {

    int process(T st);
}
