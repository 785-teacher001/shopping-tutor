package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CategoryBean;
import la.bean.ItemBean;

public class ItemDAO {
    // URL、ユーザ名、パスワードの準備
    private String url = "jdbc:postgresql:sample";
    private String user = "student";
    private String pass = "himitu";

    public ItemDAO() throws DAOException {
        try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
        }
    }

    public List<CategoryBean> findAllCategory() throws DAOException {
        // SQL文の作成
        String sql = "SELECT * FROM category ORDER BY code";
		
        try (// データベースへの接続
             Connection con = DriverManager.getConnection(url, user, pass);
			 // PreparedStatementオブジェクトの取得
			 PreparedStatement st = con.prepareStatement(sql);
			 // SQLの実行
			 ResultSet rs = st.executeQuery();) {
            // 結果の取得および表示
			List<CategoryBean> list = new ArrayList<CategoryBean>();
			while (rs.next()) {
			    int code = rs.getInt("code");
			    String name = rs.getString("name");
			    CategoryBean bean = new CategoryBean(code, name);
			    list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
        } catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
        } 
    }

    public List<ItemBean> findByCategory(int categoryCode)
                                                throws DAOException {
        // SQL文の作成
        String sql =
            "SELECT * FROM item WHERE category_code = ? ORDER BY code";
		
        try (// データベースへの接続
             Connection con = DriverManager.getConnection(url, user, pass);
			 // PreparedStatementオブジェクトの取得
			 PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			st.setInt(1, categoryCode);
			
			try (// SQLの実行
			     ResultSet rs = st.executeQuery();) {
			    // 結果の取得および表示
			    List<ItemBean> list = new ArrayList<ItemBean>();
			    while (rs.next()) {
			        int code = rs.getInt("code");
			        String name = rs.getString("name");
			        int price = rs.getInt("price");
			        ItemBean bean = new ItemBean(code, name, price);
			        list.add(bean);
                }
                // 商品一覧をListとして返す
                return list;
			} catch (SQLException e) {
			    e.printStackTrace();
			    throw new DAOException("レコードの取得に失敗しました。");
            }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
        } 
    }

    public ItemBean findByPrimaryKey(int key) throws DAOException {
        // SQL文の作成
        String sql = "SELECT * FROM item WHERE code = ?";
		
        try (// データベースへの接続
             Connection con = DriverManager.getConnection(url, user, pass);
			 // PreparedStatementオブジェクトの取得
			 PreparedStatement st = con.prepareStatement(sql);) {
			// 商品番号の設定
			st.setInt(1, key);
			
			try (// SQLの実行
			     ResultSet rs = st.executeQuery();) {
			    // 結果の取得および表示
			    if (rs.next()) {
			        int code = rs.getInt("code");
			        String name = rs.getString("name");
			        int price = rs.getInt("price");
			        ItemBean bean = new ItemBean(code, name, price);
			        return bean; // 主キーに該当するレコードを返す
                } else {
			        return null; // 主キーに該当するレコードなし
                }
            } catch (SQLException e) {
			    e.printStackTrace();
			    throw new DAOException("レコードの取得に失敗しました。");
			}
        } catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
        }
    }
    
	public int countByCategory(int categoryCode) throws DAOException {
		// 1. 実行するSQLを設定
		String sql = "SELECT count(*) FROM item WHERE category_code = ?";
		try (
			// 2. データベース接続オブジェクトを取得
			Connection con = DriverManager.getConnection(url, user, pass);
			// 3. SQL実行オブジェクトを取得
			PreparedStatement pstmt = con.prepareStatement(sql);
			) {
			// 4. パラメータバインディング
			pstmt.setInt(1, categoryCode);
			// 5. SQLの実行と結果セットの取得
			try (ResultSet rs = pstmt.executeQuery();) {
				// 6. 結果セットから件数を取得
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1); // カラムインデックスで取得
				}
				// 7. 件数を返却
				return count;
			}
		} catch (SQLException e) {
			// スタックトレースに表示
			e.printStackTrace();
			// DAO例外をスロー
			throw new DAOException("件数の取得に失敗しました");
		}
	}

	public List<ItemBean> findByName(String keyword) throws DAOException {
		// 1. 実行するSQLを設定
		String sql = "SELECT * FROM item WHERE name LIKE ?";
		try (
			// 2. データベース接続オブジェクトを取得
			Connection con = DriverManager.getConnection(url, user, pass);
			// 3. SQL実行オブジェクトを取得
			PreparedStatement pstmt = con.prepareStatement(sql);
			) {
			// 4. パラメータバインディング：keywordをワイルドカードで囲む
			pstmt.setString(1, "%" + keyword + "%");
			// 5. SQLの実行と結果セットの取得
			try (ResultSet rs = pstmt.executeQuery();) {
				// 6. 結果セットを商品リストに変換
				List<ItemBean> list = new ArrayList<>();
				while (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					ItemBean bean = new ItemBean(code, name, price);
					list.add(bean);
				}
				// 7. 商品リストを返却
				return list;
			}
		} catch (SQLException e) {
			// スタックトレースに表示
			e.printStackTrace();
			// DAO例外をスロー
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public int countByName(String keyword) throws DAOException {
		// 1. 実行するSQLを設定
		String sql = "SELECT count(*) FROM item WHERE name LIKE ?";
		try (
			// 2. データベース接続オブジェクトを取得
			Connection con = DriverManager.getConnection(url, user, pass);
			// 3. SQL実行オブジェクトを取得
			PreparedStatement pstmt = con.prepareStatement(sql);
			) {
			// 4. パラメータバインディング
			pstmt.setString(1, "%" + keyword + "%");
			// 5. SQLの実行と結果セットの取得
			try (ResultSet rs = pstmt.executeQuery();) {
				// 6. 結果セットから件数を取得
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1); // カラムインデックスで取得
				}
				// 7. 件数を返却
				return count;
			}
		} catch (SQLException e) {
			// スタックトレースに表示
			e.printStackTrace();
			// DAO例外をスロー
			throw new DAOException("件数の取得に失敗しました。");
		}
	}

	/**
	 * カテゴリ検索結果をページ単位で取得する（模範解答コーディング例）
	 * @param  categoryCode   カテゴリコード
	 * @param  page           表示するページ番号（「1」から開始）
	 * @return List<ItemBean> 商品リスト
	 * 
	 * @throws DAOException
	 */
	public List<ItemBean> findByCategoryPaged(int categoryCode, int page) throws DAOException {
		// 1. 実行するSQLを設定
		String sql = "SELECT * FROM item WHERE category_code = ? ORDER BY code LIMIT 10 OFFSET ?";
		try (
			// 2. データベース接続オブジェクトを取得
			Connection con = DriverManager.getConnection(url, user, pass);
			// 3. SQL実行オブジェクトを取得
			PreparedStatement pstmt = con.prepareStatement(sql);
			) {
			// 4. オフセット値（スキップ数）を掲載
			int offset = (page - 1) * 10;
			// 5. パラメータバインディング
			pstmt.setInt(1, categoryCode);
			pstmt.setInt(2, offset);
			// 6. SQLの実行と結果セットの取得
			try (ResultSet rs = pstmt.executeQuery();) {
				// 7. 結果セットを商品リストに変換
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int itemCode = rs.getInt("code");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					ItemBean bean = new ItemBean(itemCode, name, price);
					list.add(bean);
				}
				// 8. 商品リストを返却
				return list;
			}
		} catch (SQLException e) {
			// スタックトレースに表示
			e.printStackTrace();
			// DAO例外をスロー
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * カテゴリ検索結果をページ単位で取得する（講師推奨例）
	 * @param  categoryCode   カテゴリコード
	 * @param  pageSize       ページあたり件数
	 * @param  page           表示するページ番号（「1」から開始）
	 * @return List<ItemBean> 商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> findByCategoryPaged(int categoryCode, int pageSize, int page) throws DAOException {
		// 1. 実行するSQLの設定
		String sql = "SELECT * FROM item WHERE category_code = ? ORDER BY code LIMIT ? OFFSET ?";
		try (
			// 2. データベース接続オブジェクトを取得
			Connection con = DriverManager.getConnection(url, user, pass);
			// 3. SQL実行オブジェクトを取得
			PreparedStatement pstmt = con.prepareStatement(sql);
			) {
			// 4. オフセット値（スキップ件数）の計算
			int offset = (page - 1) * pageSize;
			// 5. パラメータバインディング
			pstmt.setInt(1, categoryCode);
			pstmt.setInt(2, pageSize);
			pstmt.setInt(3, offset);
			try (
				// 6. SQLの実行と結果セットの取得
				ResultSet rs = pstmt.executeQuery();
				) {
				// 7. 結果セットを商品リストに変換
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					ItemBean bean = new ItemBean(code, name, price);
					list.add(bean);
				}
				// 8. 商品リストを返却
				return list;
			}
		} catch (SQLException e) {
			// スタックトレースに表示
			e.printStackTrace();
			// DAO例外をスロー
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	/**
	 * 商品名あいまい検索結果をページ単位で取得する（講師推奨例）
	 * @param  keyword        検索キーワード
	 * @param  pageSize       ページあたり件数
	 * @param  page           表示するページ番号（「1」から開始）
	 * @return List<ItemBean> 商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> findByNamePaged(String keyword, int pageSize, int page) throws DAOException {
		// 1. 実行するSQLの設定
		String sql = "SELECT * FROM item WHERE name LIKE ? ORDER BY code LIMIT ? OFFSET ?";
		try (
			// 2. データベース接続オブジェクトを取得
			Connection con = DriverManager.getConnection(url, user, pass);
			// 3. SQL実行オブジェクトを取得
			PreparedStatement pstmt = con.prepareStatement(sql);
			) {
			// 4. オフセット値（スキップ件数）の計算
			int offset = (page - 1) * pageSize;
			// 5. パラメータバインディング
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, pageSize);
			pstmt.setInt(3, offset);

			try ( // 6. SQLの実行と結果セットの取得
				  ResultSet rs = pstmt.executeQuery();
				) {
				// 7. 結果セットを商品リストに変換
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					ItemBean bean = new ItemBean(code, name, price);
					list.add(bean);
				}
				// 8. 商品リストを返却
				return list;
			}
		} catch (SQLException e) {
			// スタックトレースに表示
			e.printStackTrace();
			// DAO例外のスロー
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

}