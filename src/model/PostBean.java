package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;


public class PostBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id; 				//　投稿ID
	private String title;			// タイトル
	private String description;		// ディスクリプション
	private Date post_date;			// 投稿日
	private String eyecatchPath;	// アイキャッチ画像のPATH
	private String postContent;		// 投稿内容
	private int user_id;			//ユーザID(投稿者ID)
	private String name;			//ユーザ氏名
	private String category;		//カテゴリー名
	private String tag;				//タグ名
	private ArrayList<String> categoryList = null;
	private ArrayList<String> tagList = null;


	//コンストラクタ(値を初期化)
	public PostBean() {
		this.id = 0;
		this.title   = "";
		this.description = "";
		this.post_date = null;
		this.eyecatchPath = "";
		this.postContent = "";
		this.user_id = 0;
		this.name = "";
		this.category = "";
		this.tag = "";
		this.categoryList = new ArrayList<String>();
		this.tagList = new ArrayList<String>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	public String getEyecatchPath() {
		return eyecatchPath;
	}

	public void setEyecatchPath(String eyecatchPath) {
		this.eyecatchPath = eyecatchPath;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public ArrayList<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<String> categoryList) {
		this.categoryList = categoryList;
	}

	public ArrayList<String> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<String> tagList) {
		this.tagList = tagList;
	}
}
