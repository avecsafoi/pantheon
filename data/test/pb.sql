/* MyBatis_SQLID kr.co.koscom.olympus.pb.db.mapper.OnTblSpotOrdMapper.page50 */
SELECT A.* FROM (
                    select * from on_tbl_spot_ord where ord_dt = '20250101'
                ) A
WHERE (a.ordDt > '20010101' OR (a.ordDt = '20010101'
    AND (acntNo > ' ' OR (acntNo = ' '
        AND (isuNo > ' ' OR (isuNo = ' '
            AND (ordNo > 0)))))))
ORDER BY ordDt asc, acntNo asc, isuNo asc, ordNo asc
LIMIT 50;

select * from on_tbl_spot_ord;