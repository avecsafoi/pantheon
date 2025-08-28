package kr.co.koscom.olympus.pb5.aph;

public interface Pb5Service<I, O> {

	Pb5Response<O> process(Pb5Request<I> request);
}
