package main.domain;

/**
 * 用于查询的实体类
 * appName 应用名称
 * appVersion 应用版本
 * appenv 应用运行环境
 * @author Administrator
 *
 */
public class SearchEntity {
	private String appName;
	private String appVersion;
	private String appenv;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppenv() {
		return appenv;
	}

	public void setAppenv(String appenv) {
		this.appenv = appenv;
	}

	@Override
	public String toString() {
		return "SearchEntity [appName=" + appName + ", appVersion=" + appVersion + ", appenv=" + appenv + "]";
	}

}
