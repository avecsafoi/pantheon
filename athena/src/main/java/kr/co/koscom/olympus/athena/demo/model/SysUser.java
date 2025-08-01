package kr.co.koscom.olympus.athena.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class SysUser {
    public String id;
    public String name;
    public int age;
    public String email;
}
