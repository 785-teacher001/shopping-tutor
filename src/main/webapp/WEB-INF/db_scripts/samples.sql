/**
 * ページネーション機能追加用サンプルデータ投入SQL
 */
-- sampleデータベースに接続
\c sample
-- categoryテーブルを主キーを含めて初期化
TRUNCATE TABLE category RESTART IDENTITY;

INSERT INTO category (name) VALUES ('本');
INSERT INTO category (name) VALUES ('DVD');
INSERT INTO category (name) VALUES ('ゲーム');
INSERT INTO category (name) VALUES ('食品');

-- itemテーブルを主キーを含めて初期化
TRUNCATE TABLE item RESTART IDENTITY;

INSERT INTO item(category_code, name, price) VALUES(1, 'Javaの基本', 2500);
INSERT INTO item(category_code, name, price) VALUES(1, 'MLB Fun', 980);
INSERT INTO item(category_code, name, price) VALUES(1, '料理BOOK!', 1200);
INSERT INTO item(category_code, name, price) VALUES(2, 'なつかしのアニメシリーズ', 2000);
INSERT INTO item(category_code, name, price) VALUES(2, 'The Racer', 1000);
INSERT INTO item(category_code, name, price) VALUES(2, 'Space Wars 3', 1800);
INSERT INTO item(category_code, name, price) VALUES(3, 'パズルゲーム', 780);
INSERT INTO item(category_code, name, price) VALUES(3, 'Invader Fighter', 3400);
INSERT INTO item(category_code, name, price) VALUES(3, 'Play the BascketBall', 2200);

-- 「本」カテゴリ：7件
INSERT INTO item(category_code, name, price) VALUES(1, 'Javaの応用', 2800);
INSERT INTO item(category_code, name, price) VALUES(1, '賛否両論な設計論', 1800);
INSERT INTO item(category_code, name, price) VALUES(1, 'Javaの応用・SQL編', 2400);
INSERT INTO item(category_code, name, price) VALUES(1, 'HTML入門', 1800);
INSERT INTO item(category_code, name, price) VALUES(1, 'JavaでWebアプリ', 2750);
INSERT INTO item(category_code, name, price) VALUES(1, 'HTMLとCSS、ときどきJavaScript', 980);
INSERT INTO item(category_code, name, price) VALUES(1, 'はじめてのHTML+CSS', 3200);

-- 「DVD」カテゴリ：7件
INSERT INTO item(category_code, name, price) VALUES(2, '宇宙で大戦争 ベーダー卿の意外な素顔', 5400);
INSERT INTO item(category_code, name, price) VALUES(2, '宇宙で大戦争 C-3POは抽象クラス', 5300);
INSERT INTO item(category_code, name, price) VALUES(2, '宇宙で大戦争 ルークの黒歴史すっぱ抜かれる', 4800);
INSERT INTO item(category_code, name, price) VALUES(2, 'マサカリ担いだ金太郎', 6200);
INSERT INTO item(category_code, name, price) VALUES(2, '金太郎の情報処理合格記', 6400);
INSERT INTO item(category_code, name, price) VALUES(2, '金太郎 VS メカ金太郎', 5800);
INSERT INTO item(category_code, name, price) VALUES(2, '元祖！金太郎', 6200);

-- 「ゲーム」カテゴリ：7件
INSERT INTO item(category_code, name, price) VALUES(3, 'ドラゴニア戦記', 3800);
INSERT INTO item(category_code, name, price) VALUES(3, 'ドラゴニア戦記Ⅱ', 3800);
INSERT INTO item(category_code, name, price) VALUES(3, 'ドラゴニア戦記Ⅲ', 3800);
INSERT INTO item(category_code, name, price) VALUES(3, 'ドラゴニア戦記Ⅳ', 3800);
INSERT INTO item(category_code, name, price) VALUES(3, 'クエストマニア', 3800);
INSERT INTO item(category_code, name, price) VALUES(3, 'クエストマニアⅡ', 3800);
INSERT INTO item(category_code, name, price) VALUES(3, 'クエストマニアⅢ', 3800);
INSERT INTO item(category_code, name, price) VALUES(3, 'Javaソース間違い探し', 2870);

-- 「食品」カテゴリ：4件
INSERT INTO item(category_code, name, price) VALUES(4, 'チキンビリヤニ', 2000);
INSERT INTO item(category_code, name, price) VALUES(4, 'たこ焼き', 400);
INSERT INTO item(category_code, name, price) VALUES(4, 'タコライス', 1200);
INSERT INTO item(category_code, name, price) VALUES(4, 'ビーフシチュ', 3800);










