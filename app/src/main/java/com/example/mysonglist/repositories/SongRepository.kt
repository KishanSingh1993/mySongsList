package com.example.mysonglist.repositories

import com.example.mysonglist.model.Image
import com.example.mysonglist.model.Link
import com.example.mysonglist.retrofit.ApiService
import com.example.mysonglist.roomDB.SongDao
import com.example.mysonglist.roomDB.SongEntity
import org.w3c.dom.Element
import org.w3c.dom.Node
import retrofit2.await
import javax.xml.parsers.DocumentBuilderFactory

class SongRepository(private val songDao: SongDao, private val apiService: ApiService) {

    // Retrieve songs either from API or database
    suspend fun getSongs(): List<SongEntity> {
        // Check if there are songs in the database
        val songsFromDb = songDao.getSongs()

        // If database is empty, fetch from API and insert into database
        if (songsFromDb.isEmpty()) {
            val xmlString = fetchXmlString() // Fetch XML string from API
            val songsFromApi = parseXml(xmlString)
            songDao.insertSong(songsFromApi) // Insert all songs into the database
            return songsFromApi
        }

        return songsFromDb
    }

    // Function to fetch XML string from API
    private suspend fun fetchXmlString(): String {
        // Use Retrofit's suspending API to fetch data
        return apiService.getFeed().await() // You need to use Retrofit's Coroutine Call Adapter Factory
    }

    // Parse XML string into a list of SongEntity
    private suspend fun parseXml(xmlString: String): List<SongEntity> {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        val inputStream = xmlString.byteInputStream()
        val doc = builder.parse(inputStream)
        doc.documentElement.normalize()

        val nodeList = doc.getElementsByTagName("entry")
        val entries = mutableListOf<SongEntity>()

        for (i in 0 until nodeList.length) {
            val node = nodeList.item(i)
            if (node.nodeType == Node.ELEMENT_NODE) {
                val element = node as Element
                val id = element.getElementsByTagName("id").item(0)?.textContent
                val title = element.getElementsByTagName("title").item(0)?.textContent
                val links = element.getElementsByTagName("link")
                val images = element.getElementsByTagName("im:image")
                val mp4aLink = mutableListOf<Link>()
                val mp4aImages = mutableListOf<Image>()
                val priceElement = element.getElementsByTagName("im:price").item(0) as Element
                val amount = priceElement.getAttribute("amount")
                val currency = priceElement.getAttribute("currency")

                for (j in 0 until links.length) {
                    val linkElement = links.item(j) as Element
                    if (linkElement.getAttribute("type") == "audio/x-m4a") {
                        val link = Link(linkElement.getAttribute("type"), linkElement.getAttribute("href"))
                        mp4aLink.add(link)
                    }
                }

                for (j in 0 until images.length) {
                    val imageElement = images.item(j) as Element
                    val height = imageElement.getAttribute("height")
                    val url = imageElement.textContent
                    val image = Image(height, url)
                    mp4aImages.add(image)
                }

                if (mp4aImages.isNotEmpty() && mp4aLink.isNotEmpty()) {
                    entries.add(
                        SongEntity(
                            id = id.orEmpty(),
                            title = title.orEmpty(),
                            imageUrl = mp4aImages[0].url.orEmpty(),
                            link = mp4aLink[0].href.orEmpty(),
                            amount = amount.orEmpty(),
                            currency = currency.orEmpty()
                        )
                    )
                }
            }
        }

        return entries
    }
}
