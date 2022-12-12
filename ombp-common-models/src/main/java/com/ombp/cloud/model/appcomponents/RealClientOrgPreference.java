/**
 * 
 */
package com.ombp.cloud.model.appcomponents;

import com.ombp.cloud.model.BaseEntity;

/**
 * @author PranayKathade
 *
 */
public class RealClientOrgPreference extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2551086177609906899L;

	private String identifier;

	private String orgNameDBLabel;

	private String orgNameKeyLabel;

	private String details;

	private String hoverTitle;

	private String logoPackPath;

	private String url;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getOrgNameDBLabel() {
		return orgNameDBLabel;
	}

	public void setOrgNameDBLabel(String orgNameDBLabel) {
		this.orgNameDBLabel = orgNameDBLabel;
	}

	public String getOrgNameKeyLabel() {
		return orgNameKeyLabel;
	}

	public void setOrgNameKeyLabel(String orgNameKeyLabel) {
		this.orgNameKeyLabel = orgNameKeyLabel;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getHoverTitle() {
		return hoverTitle;
	}

	public void setHoverTitle(String hoverTitle) {
		this.hoverTitle = hoverTitle;
	}

	public String getLogoPackPath() {
		return logoPackPath;
	}

	public void setLogoPackPath(String logoPackPath) {
		this.logoPackPath = logoPackPath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
