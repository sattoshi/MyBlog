package model;

import java.sql.ResultSet;
import java.sql.SQLException;



public class LoginDB {

	public LoginUserBean getUserData(String userName, String password) {
		LoginUserBean bean = null;
		LoginDao dao = null;
		ResultSet rs = null;

		try {
			// DAOクラスをインスタンス化
			dao = new LoginDao();
			// 画面で入力されたIDとパスワードを基にDB検索を実行
			rs  = dao.selectUser(userName, password);

			while(rs.next()) {
				// 検索結果が存在する場合はbeanに値をセット（結果が1件しか返らないことを想定）
				bean = new LoginUserBean();
				//ユーザID
				bean.setId(rs.getInt("user_id"));
				// ユーザ名
				bean.setUserName(userName);
				// 名前
				bean.setName(rs.getString("name"));
				// プロフィール
				bean.setProfile(rs.getString("profile"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 処理終了時に各接続を解除
			dao.close();
		}

		return bean;
	}
}
