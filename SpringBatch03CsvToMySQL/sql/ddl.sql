DROP TABLE IF EXISTS raw_report;
DROP TABLE IF EXISTS report;

# 광고 CPC(Cost Per Click), CPM(Cost Per 1000 impressions)
# 일자, 노출수, 클릭수, 수익
CREATE TABLE raw_report (
	date VARCHAR(10),
	impressions VARCHAR(12),
	clicks  VARCHAR(10),
	earning  VARCHAR(10)
);

CREATE TABLE report (
	date DATE,
	impressions INT,
	clicks  INT,
	earning  FLOAT
);

SELECT * FROM raw_report;
SELECT * FROM report;
