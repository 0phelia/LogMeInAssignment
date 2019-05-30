package com.bachelor.arty.logmeinassignment.data.entity

sealed class RssFeedSource(val id: Int, val source: String, val name: String) {
    class AlJazeera() : RssFeedSource(1,"https://www.aljazeera.com/xml/rss/all.xml", "AlJazeera")
    class Yahoo() : RssFeedSource(2,"https://www.yahoo.com/news/rss/world", "Yaahoo news")
    class FeedSpot() : RssFeedSource(3, "https://blog.feedspot.com/world_news_rss_feeds/", "FeedSpot")
    class Independent() : RssFeedSource(4,"http://www.independent.co.uk/news/world/rss", "Independent News")

    /**
     *  looks horrible i know, but have no time to figure out why this sealed class cant be made parcelable
     */

    companion object {
        fun getSourceByName(id: Int): RssFeedSource {
            return when (id) {
                1 -> AlJazeera()
                2 -> Yahoo()
                3 -> FeedSpot()
                4 -> Independent()
                else -> Independent()
            }
        }
    }

}
