package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class PostDB {
	public ArrayList<PostBean> getPostData() {
		PostBean bean = null;
		ArrayList<PostBean> beanList = new ArrayList<PostBean>();

		ArrayList<PostBean> postBeanList = new ArrayList<PostBean>();


		PostDao dao = null;
		ResultSet rs = null;

		try {
			// DAOクラスをインスタンス化
			dao = new PostDao();
			// 画面で入力されたIDとパスワードを基にDB検索を実行
			rs  = dao.selectPost();

			//データベースの行ごとに全てのデータをbeanに値をセット
			while(rs.next()) {
				bean = new PostBean();
				//投稿ID
				bean.setId(rs.getInt("post_id"));
				// タイトル
				bean.setTitle(rs.getString("title"));
				//ディスクリプション
				bean.setDescription(rs.getString("description"));
				//投稿日時
				bean.setPost_date(rs.getDate("post_date"));
				//アイキャッチ画像
				bean.setEyecatchPath(rs.getString("eyecatch_path"));
				//投稿内容
				bean.setPostContent(rs.getString("post_content"));
				//投稿者ID
				bean.setUser_id(rs.getInt("user_id"));
				// 名前
				bean.setName(rs.getString("name"));
				// カテゴリ名
				bean.setCategory(rs.getString("category_name"));
				// タグ名
				bean.setTag(rs.getString("tag_name"));

				beanList.add(bean);
			}

			//beanListから同一投稿のカテゴリ、タグの重複をなくしリスト化する。

			for(int i = 0 ; i < beanList.size(); i++ ){
				bean = new PostBean();
				ArrayList<String> categoryList = new ArrayList<String>();
				ArrayList<String> tagList = new ArrayList<String>();

				//投稿ID
				bean.setId(beanList.get(i).getId());
				//タイトル
				bean.setTitle(beanList.get(i).getTitle());
				//アイキャッチ
				if(beanList.get(i).getEyecatchPath() == null) {
		        	bean.setEyecatchPath("/MyBlog/images/no-image.png");
		        }else {
		        	bean.setEyecatchPath(beanList.get(i).getEyecatchPath());
		        }
				//ディスクリプション
		        bean.setDescription(beanList.get(i).getDescription());

		        //投稿内容
				bean.setPostContent(beanList.get(i).getPostContent());
				//投稿者ID
				bean.setUser_id(beanList.get(i).getUser_id());
				// 名前
				bean.setName(beanList.get(i).getName());
				//投稿日時
				bean.setPost_date(beanList.get(i).getPost_date());



		        categoryList.add(beanList.get(i).getCategory());
		        tagList.add(beanList.get(i).getTag());


	        	for(int j = i + 1; j < beanList.size(); j++){

		    		if(beanList.get(i).getId() == beanList.get(j).getId()){
		    			categoryList.add(beanList.get(j).getCategory());
		    			tagList.add(beanList.get(j).getTag());
		    		 }else {
		    			 i = j-1;
		    			 break;
		    		 }

		    		//jが最後まで行ったとき
		    		if(j == beanList.size()-1) {
		    			i = j;
		    			break;
		    		}

	        	 }


		         //カテゴリの重複を削除
	        	ArrayList<String> noDuplicateCategoryList = new ArrayList<String>(new LinkedHashSet<>(categoryList));
	        	bean.setCategoryList(noDuplicateCategoryList);

        	 System.out.println("カテゴリ：：：");//TODO デバック
	        	for(String c:noDuplicateCategoryList) {
	        		System.out.println(c);
	        	}

		         //タグの重複を削除
	        	ArrayList<String> noDuplicateTagList = new ArrayList<String>(new LinkedHashSet<>(tagList));
	        	bean.setTagList(noDuplicateTagList);

	        	System.out.println("タグ：：：");//TODO デバック
	        	for(String t:noDuplicateTagList) {
	        		System.out.println(t);
	        	}


	        	postBeanList.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}

		return postBeanList;
	}

	//指定された投稿IDの情報を返す。
	public PostBean getPostDetail(String postId) {
		PostBean bean = null;
		ArrayList<PostBean> beanList = new ArrayList<PostBean>();
		PostBean postBean = null;

		PostDao dao = null;
		ResultSet rs = null;

		try {
			// DAOクラスをインスタンス化
			dao = new PostDao();
			// 投稿IDをもとにDB検索を実行
			rs  = dao.selectPost(postId);

			//データベースの行ごとに全てのデータをbeanに値をセット
			while(rs.next()) {
				bean = new PostBean();
				//投稿ID
				bean.setId(rs.getInt("post_id"));
				// タイトル
				bean.setTitle(rs.getString("title"));
				//ディスクリプション
				bean.setDescription(rs.getString("description"));
				//投稿日時
				bean.setPost_date(rs.getDate("post_date"));
				//アイキャッチ画像
				bean.setEyecatchPath(rs.getString("eyecatch_path"));
				//投稿内容
				bean.setPostContent(rs.getString("post_content"));
				//投稿者ID
				bean.setUser_id(rs.getInt("user_id"));
				// 名前
				bean.setName(rs.getString("name"));
				// カテゴリ名
				bean.setCategory(rs.getString("category_name"));
				// タグ名
				bean.setTag(rs.getString("tag_name"));

				beanList.add(bean);
			}


			postBean = new PostBean();
			ArrayList<String> categoryList = new ArrayList<String>();
			ArrayList<String> tagList = new ArrayList<String>();

			//投稿ID
			postBean.setId(bean.getId());
			//タイトル
			postBean.setTitle(bean.getTitle());
			//アイキャッチ
			postBean.setEyecatchPath(bean.getEyecatchPath());
			//ディスクリプション
			postBean.setDescription(bean.getDescription());
	        //投稿内容
			postBean.setPostContent(bean.getPostContent());
			//投稿者ID
			postBean.setUser_id(bean.getUser_id());
			// 名前
			postBean.setName(bean.getName());
			//投稿日時
			postBean.setPost_date(bean.getPost_date());

			//beanListから同一投稿のカテゴリ、タグの重複をなくしリスト化する。
			for(int i = 0 ; i < beanList.size(); i++ ){
		        categoryList.add(beanList.get(i).getCategory());
		        tagList.add(beanList.get(i).getTag());
			}

	         //カテゴリの重複を削除
        	ArrayList<String> noDuplicateCategoryList = new ArrayList<String>(new LinkedHashSet<>(categoryList));
        	postBean.setCategoryList(noDuplicateCategoryList);

	         //タグの重複を削除
        	ArrayList<String> noDuplicateTagList = new ArrayList<String>(new LinkedHashSet<>(tagList));
        	postBean.setTagList(noDuplicateTagList);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}

		return postBean;
	}

	//指定の投稿の投稿IDを返すメソッド
	public int getPostId(String title, int user_id, String date ) {
		PostDao dao = null;
		ResultSet rs = null;
		int post_id = 0;

		try {
			// DAOクラスをインスタンス化
			dao = new PostDao();
			// 画面で入力されたIDとパスワードを基にDB検索を実行
			rs = dao.selectPost(title,user_id,date);

			while(rs.next()) {
				// ユーザ名
				post_id = Integer.parseInt(rs.getString("post_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}

		return post_id;
	}

	//指定のカテゴリのカテゴリIDを返すメソッド
	public int getCategoryId(String category_name ) {
		PostDao dao = null;
		ResultSet rs = null;
		int category_id = 0;

		try {
			// DAOクラスをインスタンス化
			dao = new PostDao();
			// 画面で入力されたカテゴリ名を基にDB検索を実行
			rs = dao.selectCategory(category_name);

			while(rs.next()) {
				// カテゴリID
				category_id = Integer.parseInt(rs.getString("category_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
		return category_id;
	}

	//指定のタグのタグIDを返すメソッド
	public int getTagId(String tag_name ) {
		PostDao dao = null;
		ResultSet rs = null;
		int tag_id = 0;

		try {
			// DAOクラスをインスタンス化
			dao = new PostDao();
			// 画面で入力されたタグ名を基にDB検索を実行
			rs = dao.selectTag(tag_name);

			while(rs.next()) {
				// カテゴリID
				tag_id = Integer.parseInt(rs.getString("tag_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}
		return tag_id;
	}


	//指定のcategory_nameが既にデータベースにある場合はtrue・ない場合はfalseを返すメソッド
	public boolean findCategoryData(String category_name) {
		PostDao dao = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		boolean result = false;

		try {
			// DAOクラスをインスタンス化
			dao = new PostDao();
			// 画面で入力されたIDとパスワードを基にDB検索を実行
			rs = dao.selectCategory(category_name);

			while(rs.next()) {
				// ユーザ名
				list.add(rs.getString("category_id"));
			}

			if(list.isEmpty()) {
				result = false;
			}else {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}

		return result;
	}

	//指定のtag_nameが既にデータベースにある場合はtrue・ない場合はfalseを返すメソッド
	public boolean findTagData(String tag_name) {
		PostDao dao = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		boolean result = false;

		try {
			// DAOクラスをインスタンス化
			dao = new PostDao();
			// 画面で入力されたIDとパスワードを基にDB検索を実行
			rs = dao.selectTag(tag_name);

			while(rs.next()) {
				// ユーザ名
				list.add(rs.getString("tag_id"));
			}

			if(list.isEmpty()) {
				result = false;
			}else {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}

		return result;
	}

}
