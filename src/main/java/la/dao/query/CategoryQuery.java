package la.dao.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import la.dao.query.params.CategoryBindParams;

/**
 * カテゴリ検索の問い合せクラス
 */
public class CategoryQuery extends AbstractQery<CategoryBindParams> {
	
	/** クラス定数：実行するSQL文字列 */
	private static final String SQL = "SELECT * FROM item WHERE category_code = ? ORDER BY code LIMIT ? OFFSET ?";
	
	/**
	 * 引数なしコンストラクタ
	 */
	public CategoryQuery() {}

	/**
	 * コンストラクタ
	 * @param params 検索条件
	 */
	public CategoryQuery(CategoryBindParams params) {
		this.setParams(params);
	}

	/**
	 * @see AbstractQery#getSql()
	 */
	@Override
	public String getSql() {
		return SQL;
	}
	
	/**
	 * @see AbstractQery#bind(PreparedStatement)
	 */
	@Override
	public void bind(PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, this.getParams().getCategoryCode());
		pstmt.setInt(2, this.getParams().getPageSize());
		pstmt.setInt(3, this.calcOffset());
	}

}
