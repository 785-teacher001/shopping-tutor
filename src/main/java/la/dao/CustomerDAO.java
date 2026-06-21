package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.CustomerBean;

public class CustomerDAO {
	
    /**
     * フィールド
     */
	private static final String JDBC_DRIVER = "org.postgresql.Driver"; // JDBCドライバ名
    private static final String DB_URL = "jdbc:postgresql:sample";     // 接続先DBのURL
    private static final String DB_USER = "student";                   // 接続ユーザ名
    private static final String DB_PASSWORD = "himitu";                // 接続パスワード
    
    /**
     * 引数なしコンストラクタ
     * @throws DAOException 
     */
	public CustomerDAO() throws DAOException {
		try {
			Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	/**
	 * メールアドレスとパスワードに一致する顧客を取得する。
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return　一致する顧客があればその顧客インスタンス、しない場合はnull
	 * @throws DAOException 
	 */
	public CustomerBean findByEmailAndPassword(String email, String password) throws DAOException {
		// 1. 実行するSQｌを設定
		String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";
		// 2. データベース接続オブジェクトを取得
		// 3. SQL実行オブジェクトを取得
		try (
			// 2. データベース接続オブジェクトを取得
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// 3. SQL実行オブジェクトを取得
			PreparedStatement pstmt = con.prepareStatement(sql);
			) {
			// 4. パラメータバインディング
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			try (
			// 5. SQLの実行と結果セットの取得
			ResultSet rs = pstmt.executeQuery();
			) {
				// 6. 結果セットを顧客インスタンスに変換
				CustomerBean bean = null;
				if (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					bean = new CustomerBean(code, name, address, tel, email, password);
				}
				// 7. 顧客インスタンスを返却
				return bean;
			}
        } catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
        } 
	}
    
}
