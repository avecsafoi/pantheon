package kr.co.koscom.olympus.pb.ab.data.annotation;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import lombok.Getter;

@Getter
public class PBGeneralValidator implements PBValidator {

	Object target;
	PBValidException exception;

	List<Field> errorFields = new LinkedList<>();

	public PBGeneralValidator(Object target) {
		this.target = target;
	}

	@Override
	public boolean valid() {
		if (target == null) {
			this.exception = new PBValidException("입력값이 NULL 입니다.");
			return false;
		}
		List<Field> ls = FieldUtils.getAllFieldsList(target.getClass());
		for (Field f : ls) {
			try {
				PBA a = f.getAnnotation(PBA.class);
				if (a == null)
					continue;

				Object o = f.get(target);
				String cfn = f.getDeclaringClass().getSimpleName() + "." + f.getName();

				if (o == null)
					throw new PBValidException("%s(%s)값이 NULL 입니다.".formatted(cfn, a.name()));

				String s = o.toString();

				for (PBCheck c1 : a.check()) {

					switch (c1) {
						case ISU_NO -> {
							if (!s.matches("\\d[12]"))
								throw new PBValidException("%s(%s)는 12자리 숫자이어야 합니다.".formatted(cfn, a.name()));
						}
						case ACNT_NO -> {

						}
						case DATE -> {

						}
						default -> {

						}
					}
				}
			} catch (PBValidException e) {
				this.errorFields.add(f);
				this.exception = e;
				return false;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return true;
	}

	@Override
	public String msg() {
		// TODO Auto-generated method stub
		return null;
	}

}
