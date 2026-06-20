package la.dao.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import la.dao.criteria.AbstractCriteria;
import la.dao.criteria.CategoryCriteria;

/**
 * カテゴリ検索の問い合せクラス
 */
public class CategoryQuery extends AbstractQery {
	
	/** クラス定数：実行するSQL文字列 */
	private static final String SQL = "SELECT * FROM item WHERE category_code = ? ORDER BY code LIMIT ? OFFSET ?";
	
	/**
	 * 引数なしコンストラクタ
	 */
	public CategoryQuery() {}

	/**
	 * コンストラクタ
	 * @param criteria 検索条件
	 */
	public CategoryQuery(AbstractCriteria criteria) {
		super(criteria);
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
		CategoryCriteria criteria = (CategoryCriteria)this.getCriteria();
		pstmt.setInt(1, criteria.getCategoryCode());
		pstmt.setInt(2, criteria.getPageSize());
		pstmt.setInt(3, this.calcOffset());
	}

}
