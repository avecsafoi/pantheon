package kr.co.koscom.olympus.pb.include;

import kr.co.koscom.olympus.pb.ab.data.PBData;

public interface PBService<T extends PBST<?,?>> {

    int process(T st);
}
