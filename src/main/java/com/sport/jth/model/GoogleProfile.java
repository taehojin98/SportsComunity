package com.sport.jth.model;

public class GoogleProfile {

	public String azp;
	public String aud;
	public String sub;
	public String scope;
	public String exp;
	public String expires_in;
	public String email;
	public String email_verified;
	public String access_type;
	
	public String getAzp() {
		return azp;
	}
	public void setAzp(String azp) {
		this.azp = azp;
	}
	public String getAud() {
		return aud;
	}
	public void setAud(String aud) {
		this.aud = aud;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpiresIn(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail_verified() {
		return email_verified;
	}
	public void setEmailVerified(String email_verified) {
		this.email_verified = email_verified;
	}
	public String getAccess_type() {
		return access_type;
	}
	public void setAccessType(String access_type) {
		this.access_type = access_type;
	}
	public GoogleProfile(String azp, String aud, String sub, String scope, String exp, String expires_in, String email,
			String email_verified, String access_type) {
		super();
		this.azp = azp;
		this.aud = aud;
		this.sub = sub;
		this.scope = scope;
		this.exp = exp;
		this.expires_in = expires_in;
		this.email = email;
		this.email_verified = email_verified;
		this.access_type = access_type;
	}
	@Override
	public String toString() {
		return "GoogleProfile [azp=" + azp + ", aud=" + aud + ", sub=" + sub + ", scope=" + scope + ", exp=" + exp
				+ ", expires_in=" + expires_in + ", email=" + email + ", email_verified=" + email_verified + ", access_type="
				+ access_type + "]";
	}

	public GoogleProfile() {
		// TODO Auto-generated constructor stub
	}
}