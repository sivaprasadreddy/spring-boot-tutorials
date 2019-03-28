package com.sivalabs.geeksclub

import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import java.io.File
import java.net.URL


fun main(args: Array<String>) {

    val feedURLs = mutableListOf(
            "https://sivalabs.in/feed/"
            )
    var tagId = 1
    var tagsMap = hashMapOf<String,Int>()

    var id = 1

    val input = SyndFeedInput()
    val tagEntries = mutableListOf<String>()
    val linkEntries = mutableListOf<String>()
    val linkTagEntries = mutableListOf<String>()
    val feedMap = hashMapOf<String, String>()

    fun readFeed(feedUrl: URL) {
        var feed = input.build(XmlReader(feedUrl))

        feed.entries.forEach { entry ->
            val rec = "($id, '${entry.title}','${entry.link}', 2, '2018-03-27'),"
            if(!feedMap.containsKey(entry.link)){
                feedMap.put(entry.link, entry.title)

                linkEntries.add(rec)
                entry.categories.forEach { c ->
                    if(!tagsMap.containsKey(c.name)) {
                        tagsMap.put(c.name, tagId)
                        tagEntries.add("($tagId, '${c.name}'),")
                        tagId++
                    }
                    linkTagEntries.add("($id, ${tagsMap.get(c.name)}),")
                }
                id++
            }
        }
    }

    feedURLs.forEach { url ->
        var page = 1
        while(true) {
            val feedUrl = URL("$url?paged=$page")
            try {
                readFeed(feedUrl)
            } catch (e: Exception){
                e.printStackTrace()
               break
            }
            println("Reading $url?paged=$page is done.")
            if(page == 5) break
            page++
        }
    }

    /*
    println("INSERT INTO tags(id, name) VALUES\n")
    tagEntries.forEach { println(it) }
    println(";")

    println("INSERT INTO links(id, title, url, created_by, created_on) VALUES\n")
    linkEntries.forEach { println(it) }
    println(";")

    println("INSERT INTO link_tag(link_id, tag_id) VALUES\n")
    linkTagEntries.forEach { println(it) }
    println(";")
    */

    File("temp.txt").bufferedWriter().use { out ->

        out.write("INSERT INTO tags(id, name) VALUES\n")
        tagEntries.forEach {
            out.write(it)
            out.newLine()
        }
        out.write(";\n")

        out.write("INSERT INTO links(id, title, url, created_by, created_on) VALUES\n")
        linkEntries.forEach {
            out.write(it)
            out.newLine()
        }
        out.write(";\n")

        out.write("INSERT INTO link_tag(link_id, tag_id) VALUES\n")
        linkTagEntries.forEach {
            out.write(it)
            out.newLine()
        }
        out.write(";\n")
    }
}


