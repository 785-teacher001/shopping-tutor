package test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import la.bean.ItemBean;
import la.dao.ItemDAO;

class ItemDaoTest extends DbSetupTest {
	
	/** テスト対象クラス：system under test */
	ItemDAO sut;
	
	/* テスト補助定数：ページあたりの表示件数 */
	private static final int PAGE_SIZE = 4;
	
	@BeforeEach
	void setUp() throws Exception {
		sut = new ItemDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	@DisplayName("商品名あいまい検索のページネーションのテストクラス")
	class FindByNamePaged {
		@Test
		@DisplayName("Test-11:【先頭ページの表示】キーワード「Java」での検索の最初のページは4件表示される")
		void test_11_キーワードJavaでの検索の最初のページは4件表示される() throws Exception {
			// setup
			String keyword = "Java";
			int page = 1;
			List<ItemBean> expected = List.of(new ItemBean(1,  "Javaの基本", 2500),
											  new ItemBean(10, "Javaの応用", 2800),
											  new ItemBean(12, "Javaの応用・SQL編", 2400),
											  new ItemBean(14, "JavaでWebアプリ", 2750));
			// execute
			List<ItemBean> actual = sut.findByNamePaged(keyword, PAGE_SIZE, page);
			// verify
			assertItems(expected, actual);
		}
		
		@Test
		@DisplayName("Test-12:【中間ページの表示】キーワード「ニア」での検索の2ページ目は4件表示される")
		void test_12_キーワードニアでの検索の2ページ目は4件表示される() throws Exception {
			// setup
			String keyword = "ニア";
			int page = 2;
			List<ItemBean> expected = List.of(new ItemBean(28, "クエストマニア", 3800),
											  new ItemBean(29, "クエストマニアⅡ", 3800),
											  new ItemBean(30, "クエストマニアⅢ", 3800),
											  new ItemBean(32, "ドラムマニア", 4300));
			// execute
			List<ItemBean> actual = sut.findByNamePaged(keyword, PAGE_SIZE, page);
			// verify
			assertItems(expected, actual);
		}
		
		@Test
		@DisplayName("Test-13:【末尾ページの表示】キーワード「a」での検索の3ページ目は2件表示される")
		void test_13_キーワードaでの検索の3ページ目は2件表示される() throws Exception {
			// setup
			String keyword = "a";
			int page = 3;
			List<ItemBean> expected = List.of(new ItemBean(15, "HTMLとCSS、ときどきJavaScript", 980),
											  new ItemBean(31, "Javaソース間違い探し", 2870));
			// execute
			List<ItemBean> actual = sut.findByNamePaged(keyword, PAGE_SIZE, page);
			// verify
			assertItems(expected, actual);
		}
		
		@Test
		@DisplayName("Test-14:【1ページに収まる】キーワード「金太郎」での検索結果数がページ表示数と同じである場合は1ページに収まる")
		void test_14_キーワード金太郎での検索結果数がページ表示数と同じである場合は1ページに収まる() throws Exception {
			// setup
			String keyword = "金太郎";
			int page = 1;
			List<ItemBean> expected = List.of(new ItemBean(20, "マサカリ担いだ金太郎", 6200),
											  new ItemBean(21, "金太郎の情報処理合格記", 6400),
											  new ItemBean(22, "金太郎 VS メカ金太郎", 5800),
											  new ItemBean(23, "元祖！金太郎", 6200));
			// execute
			List<ItemBean> actual = sut.findByNamePaged(keyword, PAGE_SIZE, page);
			// verify
			assertItems(expected, actual);
		}
	}
	
	@Nested
	@DisplayName("カテゴリ検索のページネーションのテストクラス")
	class FindByCategoryPaged {
		@Test
		@DisplayName("Test-01:【先頭ページの表示】書籍カテゴリについて最初のページには4件表示される")
		void test_01_書籍カテゴリについて最初のページには4件表示される() throws Exception {
			// setup
			int categoryCode = 1;
			int page = 1;
			List<ItemBean> expected = List.of(new ItemBean(1, "Javaの基本", 2500),
											  new ItemBean(2, "MLB Fun", 980),
											  new ItemBean(3, "料理BOOK!", 1200),
											  new ItemBean(10, "Javaの応用", 2800));
			// execute
			List<ItemBean> actual = sut.findByCategoryPage(categoryCode, PAGE_SIZE, page);
			// verify
			assertItems(expected, actual);
		}
		
		@Test
		@DisplayName("Test-02:【中間ページの表示】DVDカテゴリについて2ページ目には4件表示される")
		void test_02_DVDカテゴリについて2ページ目には4件表示される() throws Exception {
			// setup
			int categoryCode = 2;
			int page = 2;
			List<ItemBean> expected = List.of(new ItemBean(18, "宇宙で大戦争 C-3POは抽象クラス", 5300),
											  new ItemBean(19, "宇宙で大戦争 ルークの黒歴史すっぱ抜かれる", 4800),
											  new ItemBean(20, "マサカリ担いだ金太郎", 6200),
											  new ItemBean(21, "金太郎の情報処理合格記", 6400));
			// execute
			List<ItemBean> actual = sut.findByCategoryPage(categoryCode, PAGE_SIZE, page);
			// verify
			assertItems(expected, actual);
		}
		
		@Test
		@DisplayName("Test-03:【末尾ページの表示】ゲームカテゴリについて4ページ目には1件表示される")
		void test_03_ゲームカテゴリについて4ページ目には1件表示される() throws Exception {
			// setup
			int categoryCode = 3;
			int page = 4;
			List<ItemBean> expected = List.of(new ItemBean(33, "ソニアの冒険", 2800));
			// execute
			List<ItemBean> actual = sut.findByCategoryPage(categoryCode, PAGE_SIZE, page);
			// verify
			assertItems(expected, actual);
		}
		
		@Test
		@DisplayName("Test-04:【1ページに収まる】食品カテゴリについて総件数がページ表示数と同じである場合は1ページに収まる")
		void test_04_食品カテゴリについて総件数がページ表示数と同じである場合は1ページに収まる() throws Exception {
			// setup
			int categoryCode = 4;
			int page = 1;
			List<ItemBean> expected = List.of(new ItemBean(34, "チキンビリヤニ", 2000),
											  new ItemBean(35, "たこ焼き", 400),
											  new ItemBean(36, "タコライス", 1200),
											  new ItemBean(37, "ビーフシチュ", 3800));
			// execute
			List<ItemBean> actual = sut.findByCategoryPage(categoryCode, PAGE_SIZE, page);
			// verify
			assertItems(expected, actual);
		}
	}
	
	/**
	 * 期待値と実行値を比較する
	 * @param expected 期待値のList<ItemBean>
	 * @param actual   実行値のList<ItemBean>
	 */
	private void assertItems(List<ItemBean> expected, List<ItemBean> actual) {
		// 検索結果の件数の判定
		assertEquals(expected.size(), actual.size());
		// 検索結果のすべての要素の判定
		for (int i = 0; i < expected.size(); i++) {
			ItemBeanEx expectedEx = convertBeanToEx(expected.get(i));
			ItemBeanEx actualEx = convertBeanToEx(actual.get(i));
			assertEquals(expectedEx.toString(), actualEx.toString());
		}
	}
	
	/**
	 * ItemBeanをテスト用ItemBeanExに変換する
	 * @param bean 変換対象ItemBeanインスタンス
	 * @return テスト用ItemBeanExインスタンス
	 */
	private ItemBeanEx convertBeanToEx(ItemBean bean) {
		ItemBeanEx beanEx = new ItemBeanEx(bean.getCode(), bean.getName(), bean.getPrice());
		return beanEx;
	}

}
