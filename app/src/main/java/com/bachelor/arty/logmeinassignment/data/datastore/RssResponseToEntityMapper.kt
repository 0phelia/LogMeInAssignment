package com.bachelor.arty.logmeinassignment.data.datastore

import com.bachelor.arty.logmeinassignment.data.entity.ArticleEntity
import com.prof.rssparser.Article
import java.text.ParseException
import java.text.SimpleDateFormat

const val DEFAULT_PARSED_VALUE = "default"
const val DEFAULT_PARSED_URL = "www.google.com"

class RssResponseToEntityMapper {

    // source: https://stackoverflow.com/questions/47961570/extract-date-from-pubdate-rss-tag
    val formatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z")

    fun map(articlesResponse: List<Article>): List<ArticleEntity> {


        return articlesResponse.map { response ->
            with(response) {
                ArticleEntity(
                    title ?: DEFAULT_PARSED_VALUE,
                    description ?: DEFAULT_PARSED_VALUE,
                    link ?: DEFAULT_PARSED_URL,
                    parseDate(pubDate)
                )
            }
        }
    }


    private fun parseDate(date : String?) : Long {
        return try {
            val pubdate = formatter.parse(date)
            pubdate.time
        } catch (e : ParseException) {
            0L
        }
    }
}
