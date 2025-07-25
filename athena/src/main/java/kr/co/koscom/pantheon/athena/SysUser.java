package kr.co.koscom.pantheon.athena;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysUser {
    private String id;
    private String name;
    private int age;
    private String email;
}
