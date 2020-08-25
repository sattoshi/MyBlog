package model;

import java.io.Serializable;

public class AccountBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id; 			//　ユーザID
	private String userName;	// ユーザ名
	private String name;		// 名前
	private String profile;		// プロフィール


	//コンストラクタ(値を初期化)
	public AccountBean() {
		this.id = 0;
		this.userName   = "";
		this.name = "";
		this.profile = "";
	}

	//UserNameのゲッター
		public int getId() {
			return id;
		}

		//UserNameのセッター
		public void setId(int id) {
			this.id = id;
		}

	//UserNameのゲッター
	public String getUserName() {
		return userName;
	}

	//UserNameのセッター
	public void setUserName(String userName) {
		this.userName = userName;
	}

	//nameのゲッター
	public String getName() {
		return name;
	}

	//nameのセッター
	public void setName(String name) {
		this.name = name;
	}

	//profileのゲッター
	public String getProfile() {
		return profile;
	}

	//profileのセッター
	public void setProfile(String profile) {
		this.profile = profile;
	}

}
