package kr.co.koscom.olympus.pb.ab.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("pb")
@Data
public class PBProperties {

    private boolean multitenantWebEnable;

    private boolean multitenantSysEnable;

}
