package kr.co.koscom.olympus.pb.ab.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("pb")
public class PBProperties {

	private boolean multiTenantWebEnable;

	private boolean multiTenantSysEnable;

	public boolean isMultiTenantWebEnable() {
		return multiTenantWebEnable;
	}

	public void setMultiTenantWebEnable(boolean multiTenantWebEnable) {
		this.multiTenantWebEnable = multiTenantWebEnable;
	}

	public boolean isMultiTenantSysEnable() {
		return multiTenantSysEnable;
	}

	public void setMultiTenantSysEnable(boolean multiTenantSysEnable) {
		this.multiTenantSysEnable = multiTenantSysEnable;
	}
}
