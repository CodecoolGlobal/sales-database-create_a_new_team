-- Data inserted to orderdates

INSERT INTO orderdates
SELECT TO_DATE(rtrim(substring(order_date, 1, length(order_date)-4)), 'MM/DD/YYYY'),
       CAST(qtr_id AS quarter_year), CAST(month_id AS integer),
       CAST(year_id AS integer) FROM temp_all_sales ON CONFLICT DO NOTHING;

-- Data inserted to products



