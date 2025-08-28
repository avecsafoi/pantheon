package kr.co.koscom.olympus.pb5.aph;

import lombok.Data;

@Data
public class Pb5Response<R> {

	private Pb5ResponseHeader header;

	private R data;
}
