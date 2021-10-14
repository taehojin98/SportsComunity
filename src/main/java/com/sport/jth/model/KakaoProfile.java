package com.sport.jth.model;

public class KakaoProfile {
	
	public Integer id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;
	
	public class Properties {

		public String nickname;

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public Properties(String nickname) {
			super();
			this.nickname = nickname;
		}
		
		public Properties() {
			// TODO Auto-generated constructor stub
		}

	}
	
	public class KakaoAccount {

		public Boolean profile_nickname_needs_agreement;
		public Boolean profile_image_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean is_email_valid;
		public Boolean is_email_verified;
		public String email;

		public class Profile {

			public String nickname;
			public String thumbnail_image_url;
			public String profile_image_url;
			public Boolean is_default_image;
			public String getNickname() {
				return nickname;
			}
			public void setNickname(String nickname) {
				this.nickname = nickname;
			}
			public String getThumbnail_image_url() {
				return thumbnail_image_url;
			}
			public void setThumbnail_image_url(String thumbnail_image_url) {
				this.thumbnail_image_url = thumbnail_image_url;
			}
			public String getProfile_image_url() {
				return profile_image_url;
			}
			public void setProfile_image_url(String profile_image_url) {
				this.profile_image_url = profile_image_url;
			}
			public Boolean getIs_default_image() {
				return is_default_image;
			}
			public void setIs_default_image(Boolean is_default_image) {
				this.is_default_image = is_default_image;
			}
			public Profile(String nickname, String thumbnail_image_url, String profile_image_url,
					Boolean is_default_image) {
				super();
				this.nickname = nickname;
				this.thumbnail_image_url = thumbnail_image_url;
				this.profile_image_url = profile_image_url;
				this.is_default_image = is_default_image;
			}
			
			public Profile() {
				// TODO Auto-generated constructor stub
			}

		}

		public Boolean getProfile_nickname_needs_agreement() {
			return profile_nickname_needs_agreement;
		}

		public void setProfile_nickname_needs_agreement(Boolean profile_nickname_needs_agreement) {
			this.profile_nickname_needs_agreement = profile_nickname_needs_agreement;
		}

		public Boolean getProfile_image_needs_agreement() {
			return profile_image_needs_agreement;
		}

		public void setProfile_image_needs_agreement(Boolean profile_image_needs_agreement) {
			this.profile_image_needs_agreement = profile_image_needs_agreement;
		}

		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile profile) {
			this.profile = profile;
		}

		public Boolean getHas_email() {
			return has_email;
		}

		public void setHas_email(Boolean has_email) {
			this.has_email = has_email;
		}

		public Boolean getEmail_needs_agreement() {
			return email_needs_agreement;
		}

		public void setEmail_needs_agreement(Boolean email_needs_agreement) {
			this.email_needs_agreement = email_needs_agreement;
		}

		public Boolean getIs_email_valid() {
			return is_email_valid;
		}

		public void setIs_email_valid(Boolean is_email_valid) {
			this.is_email_valid = is_email_valid;
		}

		public Boolean getIs_email_verified() {
			return is_email_verified;
		}

		public void setIs_email_verified(Boolean is_email_verified) {
			this.is_email_verified = is_email_verified;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public KakaoAccount(Boolean profile_nickname_needs_agreement, Boolean profile_image_needs_agreement,
				Profile profile, Boolean has_email, Boolean email_needs_agreement, Boolean is_email_valid,
				Boolean is_email_verified, String email) {
			super();
			this.profile_nickname_needs_agreement = profile_nickname_needs_agreement;
			this.profile_image_needs_agreement = profile_image_needs_agreement;
			this.profile = profile;
			this.has_email = has_email;
			this.email_needs_agreement = email_needs_agreement;
			this.is_email_valid = is_email_valid;
			this.is_email_verified = is_email_verified;
			this.email = email;
		}
		
		 public KakaoAccount() {
			// TODO Auto-generated constructor stub
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConnected_at() {
		return connected_at;
	}

	public void setConnected_at(String connected_at) {
		this.connected_at = connected_at;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public KakaoAccount getKakao_account() {
		return kakao_account;
	}

	public void setKakao_account(KakaoAccount kakao_account) {
		this.kakao_account = kakao_account;
	}

	public KakaoProfile(Integer id, String connected_at, Properties properties, KakaoAccount kakao_account) {
		super();
		this.id = id;
		this.connected_at = connected_at;
		this.properties = properties;
		this.kakao_account = kakao_account;
	}

	public KakaoProfile() {
		// TODO Auto-generated constructor stub
	}
}