package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * ブログ投稿DAOクラス.
 */
public class PostDao {

	private Connection con = null;
	private ResultSet  rs  = null;
	private PreparedStatement ps = null;


	public ResultSet selectPost() throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement(
					"select p.post_id, p.title , p.description , p.post_date , p.eyecatch_path , p.post_content , p.user_id , u.name name , c.category_name , t.tag_name\n" +
					"from post p \n" +
					"left join user u on p.user_id = u.user_id \n" +
					"left join category_map cm on p.post_id = cm.post_id \n" +
					"left join category c on c.category_id = cm.category_id\n" +
					"left join tag_map tm on p.post_id = tm.post_id \n" +
					"left join tag t on t.tag_id = tm.tag_id;"
					);

			// SQLを実行
			rs = ps.executeQuery();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}

	public ResultSet selectPost(String postId) throws SQLException {
		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement(
					"select p.post_id, p.title , p.description , p.post_date , p.eyecatch_path , p.post_content , p.user_id , u.name name , c.category_name , t.tag_name\n" +
					"from post p \n" +
					"left join user u on p.user_id = u.user_id \n" +
					"left join category_map cm on p.post_id = cm.post_id \n" +
					"left join category c on c.category_id = cm.category_id\n" +
					"left join tag_map tm on p.post_id = tm.post_id \n" +
					"left join tag t on t.tag_id = tm.tag_id \n" +
					"where p.post_id = ?;"
					);

			ps.setInt(1, Integer.valueOf(postId));

			// SQLを実行
			rs = ps.executeQuery();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}

	public ResultSet selectPost(String title, int user_id , String date) throws SQLException {
		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement(
					"select post_id from post p where title = ? and user_id = ? and post_date = ?;"
					);
			ps.setString(1,title);
			ps.setInt(2, user_id);
			ps.setString(3,date);

			// SQLを実行
			rs = ps.executeQuery();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}


	// データベースから指定されたユーザ名を使ってユーザ情報を検索
	public ResultSet selectCategory(String category_name) throws SQLException {
		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement("select CATEGORY_ID from CATEGORY where CATEGORY_NAME = ?");

			// 生成したSQL文の「？」の部分にIDをセット
			ps.setString(1, category_name);

			// SQLを実行
			rs = ps.executeQuery();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}

	//データベースへカテゴリを登録
	public void insertCategory(String category_name) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement("insert into CATEGORY set CATEGORY_NAME = ?");

			// 生成したSQL文の「？」の部分にIDとパスワードをセット
			ps.setString(1, category_name);

			// SQLを実行
			ps.executeUpdate();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}

	//データベースへカテゴリマップを登録
	public void insertCategoryMap(int post_id , int category_id) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement("insert into CATEGORY_MAP set POST_ID = ? , CATEGORY_ID = ?");

			// 生成したSQL文の「？」の部分に投稿IDとカテゴリIDをセット
			ps.setInt(1, post_id);
			ps.setInt(2, category_id);

			// SQLを実行
			ps.executeUpdate();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}


	// データベースから指定されたタグ名を使ってタグ情報を検索
	public ResultSet selectTag(String tag_name) throws SQLException {
		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement("select TAG_ID from TAG where TAG_NAME = ?");

			// 生成したSQL文の「？」の部分にIDをセット
			ps.setString(1, tag_name);

			// SQLを実行
			rs = ps.executeQuery();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
	}


	//データベースへタグを登録
	public void insertTag(String tag_name) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement("insert into TAG set TAG_NAME = ?");

			// 生成したSQL文の「？」の部分にIDとパスワードをセット
			ps.setString(1, tag_name);

			// SQLを実行
			ps.executeUpdate();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}
	//データベースへカテゴリマップを登録
	public void insertTagMap(int post_id , int tag_id) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement("insert into TAG_MAP set POST_ID = ? , TAG_ID = ?");

			// 生成したSQL文の「？」の部分に投稿IDとタグIDをセット
			ps.setInt(1, post_id);
			ps.setInt(2, tag_id);

			// SQLを実行
			ps.executeUpdate();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}


	//データベースへ投稿を登録
	public void insertNewPost(String title, String description, String post_date , String eyecatch_path , String post_content , int user_id) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement("insert into POST set "
					+ "TITLE = ?,"
					+ "DESCRIPTION = ?,"
					+ "POST_DATE = ?,"
					+ "EYECATCH_PATH = ?,"
					+ "POST_CONTENT = ?,"
					+ "USER_ID = ?;");

			// 生成したSQL文の「？」の部分にIDとパスワードをセット
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setString(3, post_date);
			ps.setString(4, eyecatch_path);
			ps.setString(5, post_content);
			ps.setInt(6, user_id);

			// SQLを実行
			ps.executeUpdate();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}
	}



	/**
	 * コネクションをクローズします.
	 */
	public void close() {

		try {

			// データベースとの接続を解除する
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}

		} catch (SQLException se) {

			// データベースとの接続解除に失敗した場合
			se.printStackTrace();
		}
	}
}