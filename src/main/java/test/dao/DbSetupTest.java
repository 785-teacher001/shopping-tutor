package test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * DAOテスト用の共通基底クラス：テスト実行前にデータベースの初期化を行う
 */
class DbSetupTest {

	/* クラス定数群*/
	// データベース接続関連情報
	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql:sample";
	private static final String DB_USER = "student";
	private static final String DB_PASSWORD = "himitu";
	
	/* staticフィールド */
	// traucate用SQL
	private static String SQL_TRUNCATE_CATEGORY = "TRUNCATE TABLE category RESTART IDENTITY";
	private static String SQL_TRUNCATE_ITEM = "TRUNCATE TABLE item RESTART IDENTITY";
	// データベース接続関連オブジェクト
	private static Connection con = null;
	private static PreparedStatement pstmt = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		// JDBCドライバのロード
		Class.forName(JDBC_DRIVER);
		// データベース接続オブジェクトの取得
		con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		
		// SQL実行オブジェクトの取得とSQLの実行：categoryテーブルのtruncate
		pstmt = con.prepareStatement(SQL_TRUNCATE_CATEGORY);
		pstmt.executeUpdate();
		
		// SQL実行オブジェクトの取得とSQLの実行：サンプルレコードをcategoryテーブルに登録
		pstmt = con.prepareStatement(createSqlInsertCaetgroy());
		pstmt.executeUpdate();
		
		// SQL実行オブジェクトの取得とSQLの実行：itemテーブルのtruncate
		pstmt = con.prepareStatement(SQL_TRUNCATE_ITEM);
		pstmt.executeUpdate();
		
		// SQL実行オブジェクトの取得とSQLの実行：サンプルレコードをitemテーブルに登録
		pstmt = con.prepareStatement(createSqlInsertItem());
		pstmt.executeUpdate();
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		// categoryテーブルの原状復帰
		pstmt = con.prepareStatement(SQL_TRUNCATE_CATEGORY);
		pstmt.executeUpdate();
		pstmt= con.prepareStatement(createSqlInitCaetgory());
		pstmt.executeUpdate();
		
		// itemテーブルの原状復帰
		pstmt = con.prepareStatement(SQL_TRUNCATE_ITEM);
		pstmt.executeUpdate();
		pstmt = con.prepareStatement(createSqlInitItem());
		pstmt.executeUpdate();
		
		// データベース操作関連オブジェクトの破棄
		pstmt.close();
		con.close();
	}
	
	/**
	 * categoryテーブルを初期化するSQLを生成する
	 * @return　categoryテーブルを初期化するSQL
	 */
	private static String createSqlInitCaetgory() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("INSERT INTO category (name) VALUES ");
		builder.append("('本'), ");
		builder.append("('DVD'), ");
		builder.append("('ゲーム')");
		
		return builder.toString();
	}
	
	/**
	 * サンプルレコードをcategoryテーブルに登録するSQLを生成する
	 * @return　サンプルレコードをcategoryテーブルに登録するSQL
	 */
	private static String createSqlInsertCaetgroy() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("INSERT INTO category (name) VALUES ");
		builder.append("('本'), ");
		builder.append("('DVD'), ");
		builder.append("('ゲーム'), ");
		builder.append("('食品')");
		
		return builder.toString();
	}

	/**
	 * itemテーブルを初期化するSQLを生成する
	 * @return itemテーブルを初期化するSQL
	 */
	static String createSqlInitItem() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("INSERT INTO item (category_code, name, price) VALUES ");
		builder.append("(1, 'Javaの基本', 2500), ");
		builder.append("(1, 'MLB Fun', 980), ");
		builder.append("(1, '料理BOOK!', 1200), ");
		builder.append("(2, 'なつかしのアニメシリーズ', 2000), ");
		builder.append("(2, 'The Racer', 1000), ");
		builder.append("(2, 'Space Wars 3', 1800), ");
		builder.append("(3, 'パズルゲーム', 780), ");
		builder.append("(3, 'Invader Fighter', 3400), ");
		builder.append("(3, 'Play the BascketBall', 2200)");
		
		return builder.toString();
	}
	
	/**
	 * サンプルレコードをitemテーブルに登録するSQLを生成する
	 * @return サンプルレコードをitemテーブルに登録するSQL
	 */
	static String createSqlInsertItem() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("INSERT INTO item (category_code, name, price) VALUES ");
		builder.append("(1, 'Javaの基本', 2500), ");
		builder.append("(1, 'MLB Fun', 980), ");
		builder.append("(1, '料理BOOK!', 1200), ");
		builder.append("(2, 'なつかしのアニメシリーズ', 2000), ");
		builder.append("(2, 'The Racer', 1000), ");
		builder.append("(2, 'Space Wars 3', 1800), ");
		builder.append("(3, 'パズルゲーム', 780), ");
		builder.append("(3, 'Invader Fighter', 3400), ");
		builder.append("(3, 'Play the BascketBall', 2200), ");
		
		builder.append("(1, 'Javaの応用', 2800), ");
		builder.append("(1, '賛否両論な設計論', 1800), ");
		builder.append("(1, 'Javaの応用・SQL編', 2400), ");
		builder.append("(1, 'HTML入門', 1800), ");
		builder.append("(1, 'JavaでWebアプリ', 2750), ");
		builder.append("(1, 'HTMLとCSS、ときどきJavaScript', 980), ");
		builder.append("(1, 'はじめてのHTML+CSS', 3200), ");
		
		builder.append("(2, '宇宙で大戦争 ベーダー卿の意外な素顔', 5400), ");
		builder.append("(2, '宇宙で大戦争 C-3POは抽象クラス', 5300), ");
		builder.append("(2, '宇宙で大戦争 ルークの黒歴史すっぱ抜かれる', 4800), ");
		builder.append("(2, 'マサカリ担いだ金太郎', 6200), ");
		builder.append("(2, '金太郎の情報処理合格記', 6400), ");
		builder.append("(2, '金太郎 VS メカ金太郎', 5800), ");
		builder.append("(2, '元祖！金太郎', 6200), ");

		builder.append("(3, 'ドラゴニア戦記', 3800), ");
		builder.append("(3, 'ドラゴニア戦記Ⅱ', 3800), ");
		builder.append("(3, 'ドラゴニア戦記Ⅲ', 3800), ");
		builder.append("(3, 'ドラゴニア戦記Ⅳ', 3800), ");
		builder.append("(3, 'クエストマニア', 3800), ");
		builder.append("(3, 'クエストマニアⅡ', 3800), ");
		builder.append("(3, 'クエストマニアⅢ', 3800), ");
		builder.append("(3, 'Javaソース間違い探し', 2870), ");
		builder.append("(3, 'ドラムマニア', 4300), ");
		builder.append("(3, 'ソニアの冒険', 2800), ");
		
		builder.append("(4, 'チキンビリヤニ', 2000), ");
		builder.append("(4, 'たこ焼き', 400), ");
		builder.append("(4, 'タコライス', 1200), ");
		builder.append("(4, 'ビーフシチュ', 3800)");

		return builder.toString();
	}

}
