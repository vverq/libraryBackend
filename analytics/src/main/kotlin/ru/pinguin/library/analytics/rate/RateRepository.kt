package ru.pinguin.library.analytics.rate

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import ru.pinguin.library.analytics.books.ShortBookPojo

@Mapper
interface RateRepository {

    @Select(
        """
            select 
                isbn,
                title, 
                round(avg(rate), 2)
            from 
                ${"$"}{dbname}.books b
            left join 
                ${"$"}{dbname}.book_rate br
                    on b.isbn = br.isbn
            group by
                title,
                isbn
        """
    )
    fun getAllBooksWithAvg(): List<ShortBookPojo>

    @Insert(
        """
        insert into ${"$"}{dbname}.book_rate values (now(), #{username}, #{isbn}, #{rate})
    """
    )
    fun rateBook(username: String, isbn: String, rate: Int)
}