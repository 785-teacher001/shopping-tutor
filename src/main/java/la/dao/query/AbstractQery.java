package la.dao.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import la.dao.criteria.AbstractCriteria;

/**
 * 問い合わせクラス
 */
public abstract class AbstractQery {
	
	/**
	 * フィールド
	 */
	private AbstractCriteria criteria; // 検索条件クラス
	
	/**
	 * 引数なしコンストラクタ
	 */
	public AbstractQery() {}
	
	/**
	 * コンストラクタ
	 * @param criteria 検索条件クラス
	 */
	public AbstractQery(AbstractCriteria criteria) {
		this.criteria = criteria; // 検索条件クラス
	}

	public AbstractCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(AbstractCriteria criteria) {
		this.criteria = criteria;
	}

	/**
	 * オフセット位置の計算
	 * @return オフセット位置（スキップ件数）
	 */
	protected int calcOffset() {
		int page = this.getCriteria().getPage();
		int pageSize = this.getCriteria().getPageSize();
		return (page - 1) * pageSize;
	}
	
	/**
	 * 実行するSQLを取得する
	 * @return 実行するSQL
	 */
	public abstract String getSql();
	
	/**
	 * パラメータでプレースホルダを置き換える
	 * @param pstmt SQL実行オブジェクト
	 * @throws SQLException 
	 */
	public abstract void bind(PreparedStatement pstmt) throws SQLException;
	
}
