package com.frontparissportifs

import com.frontparissportifs.network.team.TeamObjectResponse
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val json = "{\"idTeam\":\"144243\",\"idSoccerXML\":null,\"idAPIfootball\":\"10139\",\"intLoved\":null,\"strTeam\":\"AD Ceuta FC\",\"strTeamShort\":null,\"strAlternate\":\"AD Ceuta\",\"intFormedYear\":\"1956\",\"strSport\":\"Soccer\",\"strLeague\":\"Spanish Primera RFEF Group 1\",\"idLeague\":\"5086\",\"strLeague2\":\"\",\"idLeague2\":null,\"strLeague3\":\"\",\"idLeague3\":null,\"strLeague4\":\"\",\"idLeague4\":null,\"strLeague5\":\"\",\"idLeague5\":null,\"strLeague6\":\"\",\"idLeague6\":null,\"strLeague7\":\"\",\"idLeague7\":null,\"strDivision\":null,\"strManager\":\"\",\"strStadium\":\"Alfonso Murube\",\"strKeywords\":\"\",\"strRSS\":\"\",\"strStadiumThumb\":null,\"strStadiumDescription\":\"Estadio Alfonso Murube is a football stadium in the autonomous city of Ceuta, Spain in Northern Africa. It is the home stadium of Asociación Deportiva Ceuta, who currently play in Segunda División B - Group 4, with a capacity of 6,500 seats. The stadium was inaugurated with a match between Ceuta and Algeciras Club de Fútbol, with the home team winning 4–2.\",\"strStadiumLocation\":\"Ceuta\",\"intStadiumCapacity\":\"6500\",\"strWebsite\":\"\",\"strFacebook\":\"\",\"strTwitter\":\"\",\"strInstagram\":\"\",\"strDescriptionEN\":\"Agrupación Deportiva Ceuta Fútbol Club is a Spanish football team based in the autonomous city of Ceuta. Founded in 1956, it plays in Tercera División – Group 10, holding home matches at Estadio Alfonso Murube with a capacity of 6,500 seats.\",\"strDescriptionDE\":null,\"strDescriptionFR\":null,\"strDescriptionCN\":null,\"strDescriptionIT\":null,\"strDescriptionJP\":null,\"strDescriptionRU\":null,\"strDescriptionES\":null,\"strDescriptionPT\":null,\"strDescriptionSE\":null,\"strDescriptionNL\":null,\"strDescriptionHU\":null,\"strDescriptionNO\":null,\"strDescriptionIL\":null,\"strDescriptionPL\":null,\"strKitColour1\":\"\",\"strKitColour2\":\"\",\"strKitColour3\":\"\",\"strGender\":\"Male\",\"strCountry\":\"Spain\",\"strTeamBadge\":\"https://www.thesportsdb.com/images/media/team/badge/aknt991637628186.png\",\"strTeamJersey\":null,\"strTeamLogo\":null,\"strTeamFanart1\":null,\"strTeamFanart2\":null,\"strTeamFanart3\":null,\"strTeamFanart4\":null,\"strTeamBanner\":null,\"strYoutube\":\"\",\"strLocked\":\"unlocked\"}"
        val gson = Gson()

        val team: TeamObjectResponse = gson.fromJson(json, TeamObjectResponse::class.java)





        assertEquals(team.idTeam, "144243")
        assertEquals(team.idSoccerXML, null)
        assertEquals(team.intFormedYear, "1956")
    }
}