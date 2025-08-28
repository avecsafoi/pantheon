package kr.co.koscom.olympus.pb5.aph;

import lombok.Data;

@Data
public class Pb5Request<Q> {

	private Pb5RequestHeader header;

	private Q data;
}
