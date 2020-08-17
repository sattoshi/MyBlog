package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	private Connection con = null;
	private ResultSet  rs  = null;
	private PreparedStatement ps = null;


	// データベースから指定されたユーザ名とパスワードを使ってユーザ情報を検索
	public ResultSet selectUser(String userName, String password) throws SQLException {

		try {

			// JDBCドライバのロード
			// 「com.mysql.jdbc.Driver」クラス名
			Class.forName("com.mysql.jdbc.Driver");

			// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BLOG",
											  "root",
											  "admin000");
			// SQL文を生成
			ps = con.prepareStatement("select USER_ID, NAME, PROFILE from USER where USER_NAME = ? and PASSWORD = ?");

			// 生成したSQL文の「？」の部分にIDとパスワードをセット
			ps.setString(1, userName);
			ps.setString(2, password);

			// SQLを実行
			rs = ps.executeQuery();

		} catch(ClassNotFoundException ce) {

			// JDBCドライバが見つからなかった場合
			ce.printStackTrace();
		}

		return rs;
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
