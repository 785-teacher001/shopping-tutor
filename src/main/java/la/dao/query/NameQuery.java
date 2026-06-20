package la.dao.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import la.dao.criteria.AbstractCriteria;
import la.dao.criteria.NameCriteria;

/**
 * 商品名あいまい検索の問い合わせクラス
 */
public class NameQuery extends AbstractQery {

	/** クラス定数：実行するSQL文字列 */
	private static final String SQL = "SELECT * FROM item WHERE name LIKE ? ORDER BY code LIMIT ? OFFSET ?";
	
	/**
	 * 引数なしコンストラクタ
	 */
	public NameQuery() {}

	/**
	 * コンストラクタ
	 * @param criteria 検索条件
	 */
	public NameQuery(AbstractCriteria criteria) {
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
		NameCriteria criteria = (NameCriteria) this.getCriteria();
		pstmt.setString(1, "%" + criteria.getKeyword() + "%");
		pstmt.setInt(2, criteria.getPageSize());
		pstmt.setInt(3, this.calcOffset());
	}

}
