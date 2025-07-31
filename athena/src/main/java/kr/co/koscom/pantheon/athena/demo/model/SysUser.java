package kr.co.koscom.pantheon.athena.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class SysUser {
    private String id;
    private String name;
    private int age;
    private String email;
}
