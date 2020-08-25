package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDB {

	public AccountBean getUserData(String userName, String password) {
		AccountBean bean = null;
		AccountDao dao = null;
		ResultSet rs = null;

		try {
			// DAOクラスをインスタンス化
			dao = new AccountDao();
			// 画面で入力されたIDとパスワードを基にDB検索を実行
			rs  = dao.selectUser(userName, password);

			while(rs.next()) {
				// 検索結果が存在する場合はbeanに値をセット（結果が1件しか返らないことを想定）
				bean = new AccountBean();
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

	//指定のuserNameが既にデータベースにある場合はtrue・ない場合はfalseを返すメソッド
	public boolean findUserData(String userName) {
		AccountDao dao = null;
		ArrayList<String> list = new ArrayList<String>();
		ResultSet rs = null;
		boolean result = false;

		try {
			// DAOクラスをインスタンス化
			dao = new AccountDao();
			// 画面で入力されたIDとパスワードを基にDB検索を実行
			rs = dao.selectUser(userName);

			while(rs.next()) {
				// ユーザ名
				list.add(rs.getString("user_id"));
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
